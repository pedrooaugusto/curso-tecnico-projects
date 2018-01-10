//jPanel8.setLayout(new java.awt.GridLayout(6, 0, 0, 10));

package Interface;

import Controle.ControleConfig;
import Controle.ControlePedido;
import Controle.ControlePedidoProduto;
import Controle.Diversos;
import Negocio.Pedido;
import Negocio.PedidoProduto;
import Negocio.Produto;
import com.pedroaugusto.mycomponents.DataProvider;
import com.pedroaugusto.transicoes.ControleTransicoes;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class FrmMesa extends javax.swing.JFrame implements DataProvider {

    
    /* N�mero da mesa que foi aberta */
    private final int numeroMesa;

    /* CellRenderer para customizar a tabela */
    private CustomCelulaJTable tdRenderer = new CustomCelulaJTable(1);

    /* N�mero de clientes atualmente sentados na mesa e o n�mero m�ximo permitido */
    private int numClientes[] = {0, -1};//[0] = sentados, [1] m�ximo

    /* Controle de pedidos, comunica��o entre DAO (BD) e interface */
    private ControlePedido cP = new ControlePedido();

    /* Controle de pedido produto, comunica��o entre DAO (BD) e interface */
    private ControlePedidoProduto cPP = new ControlePedidoProduto();

    /*Pedido aberto na tela*/
    private Pedido pedidoAberto = new Pedido();

    /* 
     Map que relaciona o nome de cada cliente 
     sentado nesta mesa com um pedido (historico)
     Assim � possivel manter uma �nica comanda (historico)
     de pedidos pra cada cliente sentado na mesa.
     */
    Map<String, Pedido> clienteHitorico = new HashMap<>();

    /* N�mero de pedidos do cliente x sentado nesta mesa */
    private int numeroDePedidos = 0;

    /* Valor total de todos os pedidos do cliente x sentado nesta mesa*/
    private float precoAtual = 0;

    /* 
     Quando um item(pedido produto) precisa ser alterado 
     essa variavel guarda a qual linha da tabela
     ele pertence, dessa forma na hora de clicar
     em alterar sabe-se em qual linha da tabela
     vai acontecer a altera��o.
     */
    private int[] pedidoProdutoAlteracao = {-1, -1};//[0] linha da tabela, [1] numeracao do pedido produto

    /* 
     Lista de todos os pedidos(items) de um cliente x sentado a mesa.
     Veja isto como cada linha da tabela de pedidos.
     O Map foi escolhido pois � muito importante saber
     se essse pedido � o n�mero 1, 2, 3...45 e etc.
     */
    private Map<Integer, PedidoProduto> listaDePedidos = new HashMap<Integer, PedidoProduto>();

    /* Bot�o lateral atualmente selecionado */
    private JButton btnClienteSelecionado;

    /* Anima��es */
    private ControleTransicoes cT;

    /* Bot�o na tela FrmCadastro que desencadeou esta tela */
    private CardPanel botao;
    
    /* Tamanho... */
    private int WIDTH_COMANDA, HEIGHT_COMANDA;
    
    public FrmMesa(int numeroMesa, CardPanel botao) /* Como parametro � precisso saber que mesa � essa(1, 2...5...10) */ {
        initComponents();
        new ControleConfig().checkConfig();/* Incializando variavel de configura��o */
        WIDTH_COMANDA = jpnlClienteComanda.getWidth();
        HEIGHT_COMANDA = jpnlClienteComanda.getHeight();
        this.numeroMesa = numeroMesa;
        
        /* Ah, people say Im crazy dreaming my life way... */
        MesaDataProvider.attPlease();
        
        customizeTable();/* Dando 'cor' entre outras coisas a tebela */

        lblNumeroMesa.setText("MESA " + (numeroMesa <= 9
                ? "0" + numeroMesa : numeroMesa));
        numClientes[1] = ControleConfig.config.getnCliPMesa();/* Colocando n�mero max�mo de clientes por mesa */
        
        pnlClientes.setLayout(new java.awt.GridLayout(numClientes[1], 0, 0, 10));
        
        lblNumeroClientes.setText(numClientes[0] + "/" + numClientes[1]);
        aList1.setPai(this);/* AList precisa de um pai */

        aList1.setNextToGetFocus(jTextField2);/* AList precisa de algu�m pra chamar quando acabar o n�mero 2 */

        showAll();/* Fazendo a AList mostrar todos os items */

        cT = new ControleTransicoes(jpnlClienteComanda);
        jpnlClienteComanda.setVisible(false);
        carregarClientes(numeroMesa);/* Carregando clientes sentados a mesa */
        this.botao = botao;
    }

    public void customizeTable() /*
     Aqui basicamente � dada cor e forma para a (�) tabela.
     Preste aten��o na mecanica que � usada para adicionar
     eventos de click em uma das c�lulas da tabela (Edit, Delete).
     */ {
        int[] tamanhos = {25, 357, 110, 110, 150, 30, 35, 35};
        for (int i = 0; i < jTable2.getColumnModel().getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(tdRenderer);
            jTable2.getColumnModel().getColumn(i).setHeaderRenderer(new CustomCabecalhoJTable(1));
            jTable2.getColumnModel().getColumn(i).setPreferredWidth(tamanhos[i]);
        }
        jTable2.setRowHeight(35);
        jTable2.setRowMargin(5);
        jTable2.getColumnModel().getColumn(5).setCellRenderer(tdRenderer.getIconRenderer("email"));
        jTable2.getColumnModel().getColumn(6).setCellRenderer(tdRenderer.getIconRenderer("edit"));
        jTable2.getColumnModel().getColumn(7).setCellRenderer(tdRenderer.getIconRenderer("delete"));
        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /*
                 Quando ocorre um click em qualquer parte da tabela
                 esse evento � disparado.
                 E propria tabela nos prove um metodo que diz em qual
                 linha e coluna da tabela um ponto aleat�rio A(x, y)
                 se encontra, neste casao o ponto � coordenada do meu click.
                 */
                int row = jTable2.rowAtPoint(e.getPoint());
                int col = jTable2.columnAtPoint(e.getPoint());
                if (col == 7) {
                    DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
                    int numeracao = (Integer) mod.getValueAt(row, 0);
                    PedidoProduto pp = listaDePedidos.get(numeracao);
                    float tot = pp.getQuant() * pp.getProduto().getPreco();
                    String tot1 = NumberFormat.getCurrencyInstance().format(tot);
                    if (Diversos.confirmar("Voc� esta prestes a excluir um total de " + tot1
                            + " em pedidos deste cliente.\nDesaja confirmar ?", "ATEN��O")) {
                        int result = cPP.setManipular(pp, 'E');
                        if (result == 0) {
                            precoAtual -= tot;
                            lblPrecoTotal1.setText(NumberFormat.getCurrencyInstance().format(precoAtual));
                            listaDePedidos.remove(numeracao);
                            mod.removeRow(row);
                        } else {
                            Diversos.mostrarDados("Problemas ao excluir item!", "ERROR", false);
                        }
                    }
                } else if (col == 6) {
                    DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
                    int numeracao = (Integer) mod.getValueAt(row, 0);
                    carregarPedidoProduto(listaDePedidos.get(numeracao));
                    pedidoProdutoAlteracao[0] = row;
                    pedidoProdutoAlteracao[1] = numeracao;
                }
            }
        });
    }
    
    private void carregarPedidoProduto(PedidoProduto pp) /*
     Carregar um item no form de de adcionar item com
     a finalidade de adita-lo.
     */ {
        jTextArea1.setText(pp.getComentario());
        jTextField2.setText(pp.getQuant() + "");
        aList1.setSelectedValue(pp.getProduto().getID() + "");
        btnAdd.setText("Alterar");
    }
    
    private void carregarHistoricoPedidos(Pedido p) 
    /*
        Quando um cliente � selecionado no menu lateral a esquerda
        � preciso carregar na tebela tudo aquilo que foi comsumido
        por ele e mostrar mais alguns dados. Este m�todo faz isso.
        Note que este m�todo � o irm�o ca�ula do m�todo abaixo.
    */ 
    {
        DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
        for (int i = mod.getRowCount() - 1; i >= 0; i--) {
            mod.removeRow(i);
        }
        listaDePedidos.clear();
        precoAtual = 0;
        numeroDePedidos = 0;
        List<Object> items = cPP.itemsDoPedido(p.getID());
        PedidoProduto item = null;
        Object linha[] = null;
        for (Object a : items) {
            item = (PedidoProduto) a;
            float total = item.getProduto().getPreco() * item.getQuant();
            linha = new Object[]{item.getNumeracao(), item.getProduto().getNome(),
                NumberFormat.getCurrencyInstance().format(item.getProduto().getPreco()), item.getQuant(),
                NumberFormat.getCurrencyInstance().format(total), "false",
                "edit", "delete"};
            mod.addRow(linha);
            listaDePedidos.put(item.getNumeracao(), item);
            precoAtual += total;
            numeroDePedidos = item.getNumeracao();//Pega a numera��o do �ltimo a ter passado no for loop
        }
        lblPrecoTotal1.setText(NumberFormat.getCurrencyInstance().format(precoAtual));
        jTable2.setModel(mod);
    }
    
    private void carregarClientes(int numMesa) /*
     Este m�todo vem antes do m�tdo acima.
     Ele � chamado quando na tela antes dessa
     algu�m seleciona uma mesa. Ele basicamente
     carrega no menu lateral o nome dos clientes
     sentados a esta mesa.
     */ {
        List<Object> lista = cP.listaClientesDaMesa(numMesa);
        Pedido p;
        for (Object a : lista) {
            p = (Pedido) a;
            clienteHitorico.put(p.getCliente(), p);
            pnlClientes.add(getBtnCliente(p));
        }
        numClientes[0] = lista.size();//N�mero de clientes atualmente sentado a mesa
        lblNumeroClientes.setText(numClientes[0] + "/" + numClientes[1]);
        jLblStatus.setText(numClientes[0] == 0 ? "Livre" : "Ocupado");
        if(numClientes[0] == 0)
            jLblStatus.setForeground(new Color(51,153,0));
        else
            jLblStatus.setForeground(new Color(255,51,51));
        pnlClientes.revalidate();
    }
    
    @Override
    public void showAll() /*
     Isto � coisa usada pela AList,
     ele mostra todos os elementos
     que habitam na mesma, AList.
     */ {
        aList1.limparLista();
        for (Object a : MesaDataProvider.getProdutos()) {
            Produto p = (Produto) a;
            aList1.addElement(new String[]{
                p.getNome(), String.valueOf(p.getPreco()),
                String.valueOf(p.getID())});
        }
    }
    
    @Override
    public List<String[]> pesquisa(String query) /*   
     Isto � coisa usada pela AList,
     ele mostra todos os elementos
     que habitam na mesma, AList, e come�am com 'query'.
     */ {
        List<String[]> lista = new ArrayList<String[]>();
        for (Object a : MesaDataProvider.getProdutos()) {
            Produto p = (Produto) a;
            if (p.getNome().toLowerCase().startsWith(query.toLowerCase())) {
                lista.add(new String[]{
                    p.getNome(), String.valueOf(p.getPreco()),
                    String.valueOf(p.getID())});
            }
        }
        return lista;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        pnlClientes = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblSelecione = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jpnlClienteComanda = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblNomeCliente = new javax.swing.JLabel();
        removeCliente = new javax.swing.JLabel();
        lblSelecione2 = new javax.swing.JLabel();
        aList1 = new com.pedroaugusto.mycomponents.AList();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblSelecione3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        lblSelecione4 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lblPrecoTotal1 = new javax.swing.JLabel();
        btnFecharConta = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblNumeroMesa = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JLabel();
        addCliente = new javax.swing.JLabel();
        jLblStatus = new javax.swing.JLabel();
        lblNumeroClientes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mesa");
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(233, 233, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 5, new java.awt.Color(59, 59, 59)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        pnlClientes.setBackground(new java.awt.Color(255, 255, 255));
        pnlClientes.setLayout(new java.awt.GridLayout(6, 0, 0, 10));

        jPanel2.setBackground(new java.awt.Color(59, 59, 59));

        lblSelecione.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblSelecione.setForeground(new java.awt.Color(255, 255, 255));
        lblSelecione.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSelecione.setText("Selecione um cliente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblSelecione, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(lblSelecione, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 204, 0));
        jPanel3.setOpaque(false);

        jpnlClienteComanda.setBackground(new java.awt.Color(255, 255, 255));
        jpnlClienteComanda.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        jPanel6.setBackground(new java.awt.Color(59, 59, 59));

        lblNomeCliente.setBackground(new java.awt.Color(59, 59, 59));
        lblNomeCliente.setFont(new java.awt.Font("Century Gothic", 0, 28)); // NOI18N
        lblNomeCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblNomeCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNomeCliente.setText("HAYLEY N. WILLIAMS");
        lblNomeCliente.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 0, 102)));
        lblNomeCliente.setOpaque(true);

        removeCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/trash.png"))); // NOI18N
        removeCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblNomeCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removeCliente)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(removeCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblSelecione2.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblSelecione2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSelecione2.setText("Produto");

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(156, 156, 255)));

        jTextArea1.setBackground(new java.awt.Color(249, 249, 249));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(2);
        jTextArea1.setTabSize(3);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jScrollPane3.setViewportView(jTextArea1);

        lblSelecione3.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblSelecione3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSelecione3.setText("Quantidade");

        jTextField2.setBackground(new java.awt.Color(249, 249, 249));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField2.setText("1");
        jTextField2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(156, 156, 255)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        lblSelecione4.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblSelecione4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSelecione4.setText("Comentário");

        btnAdd.setBackground(new java.awt.Color(102, 102, 255));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(254, 254, 254));
        btnAdd.setText("Adicionar");
        btnAdd.setBorder(null);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setFocusable(false);
        btnAdd.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnLimpar.setBackground(new java.awt.Color(255, 51, 51));
        btnLimpar.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        btnLimpar.setForeground(new java.awt.Color(254, 254, 254));
        btnLimpar.setText("Limpar");
        btnLimpar.setBorder(null);
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.setFocusable(false);
        btnLimpar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(238, 238, 255));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jScrollPane5.getViewport().setBackground(new Color(255,255,255));

        jTable2.setFont(new java.awt.Font("Noto Sans", 0, 10)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Descrição", "Preço", "Quant.", "Total", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setCellSelectionEnabled(true);
        jTable2.setShowVerticalLines(false);
        jScrollPane5.setViewportView(jTable2);
        jTable2.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jPanel5.setBackground(new java.awt.Color(59, 59, 59));

        lblPrecoTotal1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lblPrecoTotal1.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecoTotal1.setText("R$ 4745,45");

        btnFecharConta.setBackground(new java.awt.Color(102, 102, 255));
        btnFecharConta.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        btnFecharConta.setForeground(new java.awt.Color(254, 254, 254));
        btnFecharConta.setText("Fechar Conta");
        btnFecharConta.setBorder(null);
        btnFecharConta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFecharConta.setFocusable(false);
        btnFecharConta.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnFecharConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharContaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(btnFecharConta, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPrecoTotal1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 5, Short.MAX_VALUE)
                .addComponent(lblPrecoTotal1)
                .addGap(6, 6, 6))
            .addComponent(btnFecharConta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jpnlClienteComandaLayout = new javax.swing.GroupLayout(jpnlClienteComanda);
        jpnlClienteComanda.setLayout(jpnlClienteComandaLayout);
        jpnlClienteComandaLayout.setHorizontalGroup(
            jpnlClienteComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlClienteComandaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlClienteComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(aList1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSelecione4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSelecione3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblSelecione2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnlClienteComandaLayout.setVerticalGroup(
            jpnlClienteComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnlClienteComandaLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jpnlClienteComandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnlClienteComandaLayout.createSequentialGroup()
                        .addComponent(lblSelecione2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSelecione3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSelecione4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addGap(6, 6, 6)
                        .addComponent(btnLimpar))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlClienteComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlClienteComanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(59, 59, 59));

        lblNumeroMesa.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        lblNumeroMesa.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroMesa.setText("Mesa 99");

        btnVoltar.setBackground(new java.awt.Color(59, 59, 59));
        btnVoltar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/back.png"))); // NOI18N
        btnVoltar.setToolTipText("Voltar para o controle de mesas");
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.setOpaque(true);
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltarMouseClicked(evt);
            }
        });

        addCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/addPeople.png"))); // NOI18N
        addCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addClienteMouseClicked(evt);
            }
        });

        jLblStatus.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLblStatus.setForeground(new java.awt.Color(255, 51, 51));
        jLblStatus.setText("Ocupado");
        jLblStatus.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblNumeroClientes.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        lblNumeroClientes.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumeroClientes.setText("99/99");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumeroMesa)
                .addGap(18, 18, 18)
                .addComponent(jLblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNumeroClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCliente)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNumeroClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblNumeroMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLblStatus)
                    .addComponent(addCliente))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFecharContaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnFecharContaActionPerformed
    {//GEN-HEADEREND:event_btnFecharContaActionPerformed
         new FrmFecharConta(pedidoAberto, this).setVisible(true);
    }//GEN-LAST:event_btnFecharContaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosed
    {//GEN-HEADEREND:event_formWindowClosed
        // TODO add your handling code here:
        //System.out.println("here");
        botao.attInfo();
    }//GEN-LAST:event_formWindowClosed

    public void limparTudo()
    {
        String nome = lblNomeCliente.getText();
        removerClienteView(nome);
        clienteHitorico.remove(nome);
        btnClienteSelecionado = null;
        pedidoAberto = null;
        jpnlClienteComanda.setVisible(false);
        pnlClientes.revalidate();
    }
    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        new FrmControle().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void addClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addClienteMouseClicked
 
        if (numClientes[0] < numClientes[1]) {
            DialogEscolherCliente escolherCliente = new DialogEscolherCliente(this, true, numeroMesa);
            escolherCliente.setVisible(true);
            if (escolherCliente.getClienteNome() != null) {
                Pedido pedido = new Pedido();
                pedido.setCliente(escolherCliente.getClienteNome());
                pedido.setMesa(numeroMesa);
                pedido.setAberto(true);
                int result = cP.setManipular(pedido, 'I');
                pedido = (Pedido) cP.getUltimaInsercao();
                if (result == 0) {
                    clienteHitorico.put(escolherCliente.getClienteNome(), pedido);
                    pnlClientes.add(getBtnCliente(pedido));
                    pnlClientes.revalidate();
                    numClientes[0] += 1;
                    lblNumeroClientes.setText(numClientes[0] + "/" + numClientes[1]);
                    botao.setNumeroCli(numClientes[0]);
                } else {
                    Diversos.mostrarDados("Problemas ao adcionar cliente", "ERROR", false);
                }
            }
        }

    }//GEN-LAST:event_addClienteMouseClicked

    private void removeClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeClienteMouseClicked
        /*
            Remove um cliente da mesa usando a função abaixo
        */
        String nome = lblNomeCliente.getText();
        if (Diversos.confirmar("O cliente "+nome+" será removido"
                + " da mesa "+numeroMesa+".\nTodos os pedidos serão perdidos.\n"
                + "Deseja continuar?", "Atenção!"))
        {
            try
            {
                for(Map.Entry<Integer, PedidoProduto> p : listaDePedidos.entrySet())
                    cPP.setManipular(p.getValue(), 'E');
                Pedido ped = clienteHitorico.get(nome);
                cP.setManipular(ped, 'E');
                removerClienteView(nome);
                clienteHitorico.remove(nome);
                btnClienteSelecionado = null;
                pedidoAberto = null;
                jpnlClienteComanda.setVisible(false);
                pnlClientes.revalidate();
                numClientes[0] -= 1;
                lblNumeroClientes.setText(numClientes[0] + "/" + numClientes[1]);
            }catch(Exception e)
            {
                Diversos.mostrarDados("Ocorreu um erro ao remover o cliente da mesa! :(", "ERROR", false);
            }
        }
       
    }//GEN-LAST:event_removeClienteMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
// TODO add your handling code here:
        if (aList1.getSelectedValue() != null) {
            
            String[] valor = aList1.getSelectedValue();
            /*
             valor[0] = descricao produto
             valor[1] = preco produto
             valor[2] = id produto 
             */
            DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
            int quantidade = Integer.parseInt(jTextField2.getText());
            float preco = Float.parseFloat(valor[1]);
            float total = quantidade * preco;
            
            String comment = "false";
            /*if(!jTextArea1.getText().isEmpty())
             comment = "comment";*/
            
            Object linha[] = new Object[]{-1, valor[0],
                NumberFormat.getCurrencyInstance().format(preco), quantidade,
                NumberFormat.getCurrencyInstance().format(total), comment,
                "edit", "delete"};
            PedidoProduto itemNovo = new PedidoProduto();
            itemNovo.setPedido(clienteHitorico.get(lblNomeCliente.getText()));
            itemNovo.setComentario(jTextArea1.getText());
            itemNovo.setQuant(quantidade);
            itemNovo.setProduto((Produto) MesaDataProvider.pesquisar(Integer.parseInt(valor[2])));
            if (btnAdd.getText().equals("Adicionar")) {
                numeroDePedidos += 1;
                linha[0] = numeroDePedidos;
                itemNovo.setNumeracao(numeroDePedidos);
                int result = cPP.setManipular(itemNovo, 'I');
                if (result == 0) {
                    mod.addRow(linha);
                    listaDePedidos.put(numeroDePedidos, itemNovo);
                    precoAtual += total;
                } else {
                    Diversos.mostrarDados("Problemas ao inserir item!", "ERROR", false);
                }
            } else {
                linha[0] = pedidoProdutoAlteracao[1];
                itemNovo.setNumeracao(pedidoProdutoAlteracao[1]);
                PedidoProduto itemAntigo = listaDePedidos.get(pedidoProdutoAlteracao[1]);
                int result = cPP.setManipular(itemNovo, 'A');
                if (result == 0) {
                    for (int i = 0; i < linha.length; i++) {
                        mod.setValueAt(linha[i], pedidoProdutoAlteracao[0], i);
                    }
                    float precoTotalAntigo = itemAntigo.getQuant() * itemAntigo.getProduto().getPreco();
                    precoAtual -= precoTotalAntigo;//Subtraindo total desse item de antes da altera��o
                    precoAtual += total;//Adicionando o novo toal desse item, depois da altera��o
                    listaDePedidos.put(pedidoProdutoAlteracao[1], itemNovo);
                } else {
                    Diversos.mostrarDados("Problemas ao alterar um item!", "ERROR", false);
                }
            }
            lblPrecoTotal1.setText(NumberFormat.getCurrencyInstance().format(precoAtual));
            btnLimparActionPerformed(null);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
// TODO add your handling code here:
        jTextArea1.setText("");
        jTextField2.setText("1");
        aList1.clearComponent();
        btnAdd.setText("Adicionar");
        pedidoProdutoAlteracao[0] = -1;
        pedidoProdutoAlteracao[1] = -1;
        showAll();
    }//GEN-LAST:event_btnLimparActionPerformed
    private void removerClienteView(String nome) /*
     Remove um cliente da mesa
     */ {
        Component remover = null;
        for (Component a : pnlClientes.getComponents()) {
            if (a instanceof JButton && ((JButton) a).getText().equals(nome)) {
                remover = a;
                break;
            }
        }
        pnlClientes.remove(remover);
    }
    
    private JButton getBtnCliente(final Pedido pedido) 
    /*
        Cria um dos bot�es no menu lateral
        cada bot]ao representa um cliente sentado
        a mesa.
    */ 
    {
        JButton btnCliente = new JButton();
        btnCliente.setBackground(new java.awt.Color(51,102,255));
        btnCliente.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnCliente.setText(pedido.getCliente());
        btnCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));
        btnCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliente.setFocusable(false);
        btnCliente.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarHistoricoPedidos(pedido);
                jpnlClienteComanda.setVisible(true);
                JButton atual = (JButton) e.getSource();
                if (btnClienteSelecionado != null) {
                    btnClienteSelecionado.setBackground(new java.awt.Color(51,102,255));
                    atual.setBackground(new java.awt.Color(20,20,100));
                } else {                    
                    atual.setBackground(new java.awt.Color(20,20,100));
                }
                btnLimparActionPerformed(null);
                btnClienteSelecionado = atual;
                lblNomeCliente.setText(pedido.getCliente());
                cT.abrirFromZero(new Dimension(0, HEIGHT_COMANDA), new Dimension(WIDTH_COMANDA, HEIGHT_COMANDA), 300);
                
                pedidoAberto = pedido;
            }
        });
        return btnCliente;
    }

    public FrmMesa() throws Exception {
        this.numeroMesa = -1;
        throw new Exception("O programa n�o pode ser inicializado a partir daqui. Abra atrav�s de um Card.");
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmMesa(1, null).setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.pedroaugusto.mycomponents.AList aList1;
    private javax.swing.JLabel addCliente;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFecharConta;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JLabel btnVoltar;
    private javax.swing.JLabel jLblStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel jpnlClienteComanda;
    private javax.swing.JLabel lblNomeCliente;
    private javax.swing.JLabel lblNumeroClientes;
    private javax.swing.JLabel lblNumeroMesa;
    private javax.swing.JLabel lblPrecoTotal1;
    private javax.swing.JLabel lblSelecione;
    private javax.swing.JLabel lblSelecione2;
    private javax.swing.JLabel lblSelecione3;
    private javax.swing.JLabel lblSelecione4;
    private javax.swing.JPanel pnlClientes;
    private javax.swing.JLabel removeCliente;
    // End of variables declaration//GEN-END:variables
}
