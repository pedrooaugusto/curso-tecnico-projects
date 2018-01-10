package Interface;

import Controle.ControleCliente;
import Controle.ControleEstoque;
import Controle.ControleMonitorado;
import Controle.ControleNotaFiscal;
import Controle.ControlePagamento;
import Controle.ControlePedido;
import Controle.ControlePedidoProduto;
import Controle.ControleProduto;
import Controle.ControleRelatorioProduto;
import Controle.Diversos;
import Negocio.Cliente;
import Negocio.Monitorado;
import Negocio.NotaFiscal;
import Negocio.Pagamento;
import Negocio.Pedido;
import Negocio.PedidoProduto;
import Negocio.RelatorioProduto;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;

public class FrmFecharConta extends javax.swing.JFrame {

    public FrmFecharConta(Pedido pedido, FrmMesa FATHER) {
        initComponents();
        this.pedido = pedido;
        loadPedido();
        this.FATHER = FATHER;
        lblPagMouseClicked(null);
    }

    private final ControleMonitorado cM = new ControleMonitorado();
    private final ControleProduto cPr = new ControleProduto();
    private final ControleEstoque cE = new ControleEstoque();
    private final ControlePedido cPD = new ControlePedido();
    private final ControlePagamento cP = new ControlePagamento();
    private final ControlePedidoProduto cPP = new ControlePedidoProduto();
    private final ControleNotaFiscal cNF = new ControleNotaFiscal();
    private final ControleCliente cC = new ControleCliente();
    private final ControleRelatorioProduto cRP = new ControleRelatorioProduto();
    private final Pedido pedido;
    private List<Object> produtos = new ArrayList<>();
    private List<Object> clientes = new ArrayList<>();
    private FrmMesa FATHER;
    private boolean agora = false; //se a compra vai ser paga agora ou depois
    private boolean pag = true; //diz se existem clientes registrados pra poder pagar depois

    public final void loadPedido() {
        lblCliente.setText(pedido.getCliente());
        lblMesa.setText("Mesa " + pedido.getMesa());
        txtNF.setText(pedido.getID() + "");
        setDate();

        for(Object o : cPP.lista("")){
            PedidoProduto pp = (PedidoProduto) o;
            if(pp.getPedido().getID()==pedido.getID())
                produtos.add(pp);
        }
        
        float ttl = getValorBruto();
        txtValor.setText("" + ttl);
        lblValorBruto.setText("Valor bruto: " + NumberFormat.getCurrencyInstance().format(ttl));

        listProdutos.setModel(new DefaultListModel<String>());
        DefaultListModel a = (DefaultListModel) listProdutos.getModel();

        for (Object o : produtos) {
            PedidoProduto pp = (PedidoProduto) o;
            a.addElement(pp.getQuant() + "x ............. " + pp.getProduto().getNome());
        }

        listProdutos.setModel(a);
    }

    public NotaFiscal montarNotaFiscal() {
        NotaFiscal nf = new NotaFiscal();
        Pagamento pg = new Pagamento();
        Cliente c = new Cliente();
        c.setCodigo(0);
        Pedido pd = new Pedido();

        nf.setID(Integer.parseInt(txtNF.getText()));

        pg.setID(nf.getID());
        pg.setData(txtData.getText());
        pg.setPago(agora);
        pg.setValor(Float.parseFloat(txtValor.getText()));

        pd = pedido;
        pd.setAberto(!pedido.isAberto());

        if (!agora) {
            c = (Cliente) clientes.get(cmbCliente.getSelectedIndex());
            c.setEstadoConta("pendente");
            c.setDiaPagamento("10"/*getDataPagamento()*/);
        }

        nf.setPagamento(pg);
        nf.setPedido(pd);
        nf.setCliente(c);

        return nf;
    }

    public String getDataPagamento() { //O cliente tem até o dia 10 do mês seguinte para pagar tudo que foi consumido no mês atual.
        int mes = Integer.parseInt(txtData.getText().substring(3, 5));
        int ano = Integer.parseInt(txtData.getText().substring(6, 10));
        String diaPagamento="";
        
        if(mes<12)
            mes+=1;
        else{
            mes=1;
            ano+=1;
        }
        
        diaPagamento = "10-"+mes+"-"+ano;
        
        return diaPagamento;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnConcluir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        pnlPag = new javax.swing.JPanel();
        lblPag = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtData = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProdutos = new javax.swing.JList<>();
        cmbCliente = new com.pedroaugusto.mycomponents.THComboBox();
        lblValorBruto = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtNF = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblCliente = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblMesa = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(233, 233, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 59, 59), 2));

        jLabel1.setBackground(new java.awt.Color(59, 59, 59));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Finalizando pedido...");
        jLabel1.setOpaque(true);

        btnConcluir.setBackground(new java.awt.Color(102, 102, 255));
        btnConcluir.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        btnConcluir.setForeground(new java.awt.Color(255, 255, 255));
        btnConcluir.setText("Concluir");
        btnConcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Efetuação");

        pnlPag.setBackground(new java.awt.Color(102, 102, 255));

        lblPag.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblPag.setForeground(new java.awt.Color(255, 255, 255));
        lblPag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPag.setText("AGORA");
        lblPag.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPagMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlPagLayout = new javax.swing.GroupLayout(pnlPag);
        pnlPag.setLayout(pnlPagLayout);
        pnlPagLayout.setHorizontalGroup(
            pnlPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblPag, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlPagLayout.setVerticalGroup(
            pnlPagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblPag, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addComponent(pnlPag, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Data");

        txtData.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(102, 102, 255)));
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setText("00-00-0000");
        txtData.setFormatterFactory(Diversos.FormatoMascara("##-##-####"));
        txtData.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(txtData)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Valor");

        txtValor.setBackground(new java.awt.Color(249, 249, 249));
        txtValor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setText("0000.00");
        txtValor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(102, 102, 255)));
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValor)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
        );

        listProdutos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 255)));
        listProdutos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(listProdutos);

        cmbCliente.setFieldName("Cliente");
        cmbCliente.setIcon("name.png");
        cmbCliente.setPlaceholder("Cliente a ser cobrado");

        lblValorBruto.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblValorBruto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblValorBruto.setText("Valor bruto: R$0000.00");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("NF");

        txtNF.setBackground(new java.awt.Color(249, 249, 249));
        txtNF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNF.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(102, 102, 255)));
        txtNF.setEditable(false);
        txtNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNF, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel19)
                .addComponent(txtNF, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cmbCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorBruto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(12, 12, 12))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        lblCliente.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        lblCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCliente.setText("Hayley Nichole Williams");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Cliente");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        lblMesa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMesa.setText("Mesa 12");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMesa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMesa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnCancelar.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelar.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void btnConcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcluirActionPerformed
        boolean ok = false;
        String message = "";

        //verifica se está tudo devidamente preenchido
        if (!txtData.getText().isEmpty() && !txtData.getText().equals("  -  -   ")
                && !txtValor.getText().isEmpty()) {
            if (!agora) {
                if (!cmbCliente.isEmpty()) {
                    ok = true;
                } else {
                    message = "Selecione um cliente para ser cobrado.";
                    ok=false;
                }
            } else {
                ok = true;
            }
        } else {
            message = "Preencha todos os campos!";
        }

        if (ok) {
            NotaFiscal nf = montarNotaFiscal();
            if (cP.setManipular(nf.getPagamento(), 'I') == 0
                    && cPD.setManipular(nf.getPedido(), 'A') == 0
                    && cNF.setManipular(nf, 'I') == 0) {

                message = "Concluído!";

                
                for (Object o : produtos) {                    
                    PedidoProduto pp = (PedidoProduto) o;
                    
                    if (pp.getProduto().isMonitorado()) {
                        
                        Monitorado m = (Monitorado) cM.getBusca(pp.getProduto().getID(), 0);
                        m.getEstoque().subtrair(pp.getQuant());
                        
                        if (cE.setManipular(m, 'A') != 0) {
                            Diversos.mostrarDados("Erro na atualização do estoque do produto "+pp.getProduto().getNome(), "Ops!", false);
                        }
                    }
                    
                    RelatorioProduto rp = (RelatorioProduto) cRP.getBusca(pp.getProduto().getID(),-1);
                    rp.setQuantidade(rp.getQuantidade()+pp.getQuant());
                    if(cRP.setManipular(rp, 'A')!= 0)
                        Diversos.mostrarDados("Não foi possível acertar os relatórios do produto: "+pp.getProduto().getNome(), "ops", false);
                }

                if (!agora) {
                    
                    if (cC.setManipular(nf.getCliente(), 'A') == 0) {
                        message += " O cliente poderá efetuar"
                                + " o pagamento até o dia "
                                + getDataPagamento();
                    } else {
                        Diversos.mostrarDados("Problemas ao atualizar o "
                                + "estado do cliente.", "Nota Fiscal - Cliente", false);
                    }
                }
                Diversos.mostrarDados(message, "Nota Fiscal", true);
            } else {
                Diversos.mostrarDados("Problemas ao concluir o pagamento.", "Nota Fiscal", false);
            }
        } else {
            Diversos.mostrarDados(message, "Ops!", false);
        }

        FATHER.limparTudo();
        dispose();

        /*
        
         if(!searchBox.getText().isEmpty() &&
         !searchBox.getText().equals("Pesquisar cliente"))
         {
         if(!fieldNome.isEmpty() & !fieldTelefone.isEmpty()
         & !fieldCelular.isEmpty()
         & !comboBoxEmpresa.isEmpty())
         {
         Cliente c = montarObjeto();
         int a;
         if(buttonSave.isEnabled())
         {
         a = cC.setManipular(c, 'I');
         if(a == 0)
         Diversos.mostrarDados("Incluido com sucesso!", "Cliete",
         true);
         else
         Diversos.mostrarDados("Problemas na inclusão!", "Cliente",
         false);
         }
         else
         {
         a = cC.setManipular(c, 'A');
         if(a == 0)
         Diversos.mostrarDados("Alterado com sucesso!", "Cliete",
         true);
         else
         Diversos.mostrarDados("Problemas ao alterar!", "Cliente",
         false);
         }
         buttonSave.setEnabled(false);
         searchBox.setEditable(false);
         }
         }*/
    }//GEN-LAST:event_btnConcluirActionPerformed

    private void lblPagMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPagMouseClicked
        if (pag) {
            if (agora) {
                lblPag.setText("DEPOIS");
                pnlPag.setBackground(new Color(59, 59, 59));
                cmbCliente.setVisible(true);
            } else {
                lblPag.setText("AGORA");
                pnlPag.setBackground(new Color(102, 102, 255));
                cmbCliente.setVisible(false);
            }
        } else {
            Diversos.mostrarDados("Não foi encontrado clientes. O pagamento não poderá ser adiado enquanto não houver clientes registrados.", "Ops!", false);
        }
        agora = !agora;
    }//GEN-LAST:event_lblPagMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        clientes = cC.lista("order by nome_cli");
        pag = true;
        lblPag.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cmbCliente.limparLista();

        if (!clientes.isEmpty()) {
            for (Object a : clientes) {
                Cliente c = (Cliente) a;
                cmbCliente.addItem(c.getNome() + " - " + c.getEmpresa().getRazaoSocial());
            }
            cmbCliente.setSelectedIndex(-1);
        } else {
            Diversos.mostrarDados("Não foram encontrados clientes. O pagamento não poderá ser adiado enquanto não houver clientes registrados.", "Ops!", false);
            pag = false;
            lblPag.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        }
    }//GEN-LAST:event_formWindowOpened

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        dispose();
    }//GEN-LAST:event_formFocusLost

    private void txtNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNFActionPerformed

    public final void setDate() {
        String r = new Date().toLocaleString().replaceAll("/", "-");
        String d = "";
        for (int i = 0; i < 10; i++) {
            d += r.charAt(i);
        }
        txtData.setText(d);
    }

    public final float getValorBruto() {
        float total = 0;
        for (Object o : produtos) {
            PedidoProduto pp = (PedidoProduto) o;
            total += pp.getProduto().getPreco() * pp.getQuant();
        }
        return total;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmFecharConta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFecharConta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFecharConta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFecharConta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFecharConta(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConcluir;
    private com.pedroaugusto.mycomponents.THComboBox cmbCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblMesa;
    private javax.swing.JLabel lblPag;
    private javax.swing.JLabel lblValorBruto;
    private javax.swing.JList<String> listProdutos;
    private javax.swing.JPanel pnlPag;
    private javax.swing.JFormattedTextField txtData;
    private javax.swing.JTextField txtNF;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
