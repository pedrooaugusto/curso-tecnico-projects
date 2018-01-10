
package Interface;

import Controle.ControleCliente;
import Controle.ControleNotaFiscal;
import Controle.ControlePagamento;
import Controle.Diversos;
import Negocio.Cliente;
import Negocio.NotaFiscal;
import java.awt.Color;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class FrmFiado extends javax.swing.JFrame {

    private ControleCliente cC = new ControleCliente();
    private ControleNotaFiscal cNF = new ControleNotaFiscal();
    private ControlePagamento cP = new ControlePagamento();
    private List<Object> clientes;
    public FrmFiado()
    {
        initComponents();
        CustomCelulaJTable tdRenderer = new CustomCelulaJTable(1);
        //jTable1.setModel(new DefaultTableModel());
        int[] tamanhos = {25, 200, 200};
        for (int i = 0; i < jTable1.getColumnModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(tdRenderer);
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(new CustomCabecalhoJTable(1));
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(tamanhos[i]);
        }
        jTable1.setRowHeight(20);
        jTable1.setRowMargin(5);
        jList1.setModel(new DefaultListModel());
        jPanel5.setVisible(false);
    }

    private void carregarCombo()
    /*Carrega o combo de clientes*/
    {
        clientes = cC.lista("order by nome_cli");
        if (!clientes.isEmpty()) 
        {
            for (Object a : clientes) 
            {
                Cliente c = (Cliente) a;
                jComboBox1.addItem(c.getNome());
            }
            jComboBox1.setSelectedIndex(-1);
        }
        else
        {
            Diversos.mostrarDados("Não foram encontrados clientes. "
                    + "O pagamento não poderá ser adiado enquanto não"
                    + " houver clientes registrados.", "Ops!", false);
        }
    }
    
    private List<Integer> meses = new ArrayList<>();
    private void carregarHistoricoCli(int id)
    /*Carrega o historico de contas do cliente*/
    {
        if(jPanel5.isVisible())
            jPanel5.setVisible(false);
        DefaultTableModel mod = (DefaultTableModel) jTable1.getModel();
        for (int i = mod.getRowCount() - 1; i >= 0; i--){
            mod.removeRow(i);
        }
        meses.clear();
        jLabel7.setText("Total: "+NumberFormat.getCurrencyInstance().format(0));
        List<Object> lista = cNF.lista("");
        DefaultListModel l = new DefaultListModel();
        int i = 0;
        for (Object a: lista)
        {
            NotaFiscal nf = (NotaFiscal) a;
            if(nf.getCliente().getCodigo() == id)
            {
                int mouthInt = Integer.parseInt(nf.getPagamento().getData().substring(3,5));// 09-05-1098
                int dayInt = Integer.parseInt(nf.getPagamento().getData().substring(0,2));
                int dayIntCli = Integer.parseInt(nf.getCliente().getDiaPagamento().substring(0, 2));
                if(dayInt <= dayIntCli)
                    mouthInt = (--mouthInt <= 0 ? 12 : mouthInt);
                    //mouthInt = (++mouthInt > 12 ? 1 : mouthInt);
                if(!meses.contains(mouthInt))
                {
                    String mes = new DateFormatSymbols().getMonths()[mouthInt-1];
                    String aberto = nf.getPagamento().isPago() ? "" : " ( não pago )";
                    l.addElement(i+++" - "+mes+aberto);
                    meses.add(mouthInt);
                }
            }
        }
        jList1.setModel(l);
    }
    
    private List<NotaFiscal> listaNotas = new ArrayList<>();
    private void carregarComprasMes(int id, int mes, String d)
    /*Carrega as compras realizadas no mes x que foram pra fiado*/
    {
        jPanel5.setVisible(true);
        listaNotas.clear();
        DefaultTableModel mod = (DefaultTableModel) jTable1.getModel();
        for (int i = mod.getRowCount() - 1; i >= 0; i--){
            mod.removeRow(i);
        }
        List<Object> lista = cNF.lista("");
        String diaPag = null;
        float valor = 0;
        for (Object a: lista)
        {
            NotaFiscal nf = (NotaFiscal) a;
            if(nf.getCliente().getCodigo() == id)
            {
                int mouthInt = Integer.parseInt(nf.getPagamento().getData().substring(3,5));// 09-05-1098
                int dayInt = Integer.parseInt(nf.getPagamento().getData().substring(0,2));
                int dayIntCli = Integer.parseInt(nf.getCliente().getDiaPagamento());
                if(dayInt <= dayIntCli)
                    mouthInt = (--mouthInt <= 0 ? 12 : mouthInt);
                if(mouthInt == mes)
                {
                    int n = mod.getRowCount();
                    n+=1;
                    Object[] rowData = new Object[]{n, nf.getPagamento().getData(), 
                        NumberFormat.getCurrencyInstance().format(nf.getPagamento().getValor())};
                    mod.addRow(rowData);
                    diaPag = nf.getPagamento().getRealizacaoPagamento();
                    valor+= nf.getPagamento().getValor();
                    listaNotas.add(nf);
                }
            }
        }
        String mesS = new DateFormatSymbols().getMonths()[mes-1];
        jLabel4.setText("<html><u>Histórico do mês de: "+mesS+"</u></html>");
        mes = (++mes > 12 ? 1 : mes);
        jLabel5.setText("Data Vencimento: "+d+"-"+(mes < 10 ? "0"+mes : mes)+"-2017");
        jLabel6.setText("Data Pagamento: "+((diaPag == null || diaPag.isEmpty()) ? "" : diaPag));
        jLabel7.setText("Total: "+NumberFormat.getCurrencyInstance().format(valor));
        if(!(diaPag == null || diaPag.isEmpty()))
            jButton2.setEnabled(false);
        else
            jButton2.setEnabled(!false);
    }
    
    private void quitarConta()
    /*Paga a conta do cliente x referente ao mês y*/
    {
        if(jComboBox1.getSelectedIndex() > -1 && jList1.getSelectedIndex() > -1)
        {
            String r = new Date().toLocaleString().replaceAll("/", "-");
            String d = "";
            int a = 1;
            for (int i = 0; i < 10; i++)
                d += r.charAt(i);
            for(NotaFiscal nf : listaNotas)
            {
                nf.getPagamento().setPago(true);
                nf.getPagamento().setRealizacaoPagamento(d);
                a = cP.setManipular(nf.getPagamento(), 'A');
                if(a != 0)
                    break;
            }
            if(a == 0)
            {
                Diversos.mostrarDados("O mês em questão foi pago com sucesso!", "Nota Fiscal", true);
                jPanel5.setVisible(false);
                int index = jComboBox1.getSelectedIndex();
                jComboBox1.setSelectedIndex(index);
            }
            else
            {
                Diversos.mostrarDados("Problemas ao concluir o pagamento.", "Nota Fiscal", false);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtDataHora = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnDesligar1 = new javax.swing.JLabel();
        btnDesligar2 = new javax.swing.JLabel();
        btnDesligar3 = new javax.swing.JLabel();
        btnDesligar4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(59, 59, 59));

        jPanel8.setBackground(new java.awt.Color(59, 59, 59));

        txtDataHora.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtDataHora.setForeground(new java.awt.Color(255, 255, 255));
        txtDataHora.setText("27/12/1988 - 00:00:00");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDataHora)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtDataHora)
                .addGap(0, 0, 0))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setText("Selecione o cliente:");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);

        jList1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jList1.setSelectionBackground(new java.awt.Color(153, 153, 255));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        jLabel12.setText("Selecione uma conta:");

        jPanel5.setBackground(new java.awt.Color(222, 222, 252));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("<html><u>Histórico do mês de: Janeiro</u></html>");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Data Vencimento: 05/02/2017");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Data Pagamento: ??/??/????");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 204));
        jButton2.setText("Pagar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane4.getViewport().setBackground(new Color(255,255,255));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Data", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Total: R$ 40, 19");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                            .addComponent(jLabel4))
                        .addGap(6, 6, 6))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 431, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(59, 59, 59));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pagamento de contas pendentes");

        btnDesligar1.setBackground(new java.awt.Color(59, 59, 59));
        btnDesligar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar1.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/back.png"))); // NOI18N
        btnDesligar1.setToolTipText("Voltar para o menu principal");
        btnDesligar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesligar1.setOpaque(true);
        btnDesligar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDesligar1MouseClicked(evt);
            }
        });

        btnDesligar2.setBackground(new java.awt.Color(59, 59, 59));
        btnDesligar2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar2.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/folder.png"))); // NOI18N
        btnDesligar2.setToolTipText("Cadastros");
        btnDesligar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesligar2.setOpaque(true);
        btnDesligar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDesligar2MouseClicked(evt);
            }
        });

        btnDesligar3.setBackground(new java.awt.Color(100, 100, 100));
        btnDesligar3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar3.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/currency-icon1.png"))); // NOI18N
        btnDesligar3.setToolTipText("Controle de pedidos");
        btnDesligar3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDesligar3.setOpaque(true);

        btnDesligar4.setBackground(new java.awt.Color(59, 59, 59));
        btnDesligar4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar4.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/pedido.png"))); // NOI18N
        btnDesligar4.setToolTipText("Pedidos");
        btnDesligar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesligar4.setOpaque(true);
        btnDesligar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDesligar4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDesligar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnDesligar4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDesligar3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesligar2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDesligar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDesligar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesligar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnDesligar4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        // TODO add your handling code here:
        carregarCombo();
    }//GEN-LAST:event_formWindowOpened

    private void btnDesligar1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnDesligar1MouseClicked
    {//GEN-HEADEREND:event_btnDesligar1MouseClicked
        new FrmMainMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDesligar1MouseClicked

    private void btnDesligar2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnDesligar2MouseClicked
    {//GEN-HEADEREND:event_btnDesligar2MouseClicked
        new FrmCadastro().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDesligar2MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox1ActionPerformed
    {//GEN-HEADEREND:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        int index = jComboBox1.getSelectedIndex();
        if(index > -1 && !clientes.isEmpty())
        {
            Cliente c = (Cliente) clientes.get(index);
            carregarHistoricoCli(c.getCodigo());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jList1MouseClicked
    {//GEN-HEADEREND:event_jList1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_jList1ValueChanged
    {//GEN-HEADEREND:event_jList1ValueChanged
        // TODO add your handling code here:
        int index = jComboBox1.getSelectedIndex();
        int index2 = jList1.getSelectedIndex();
        if(!evt.getValueIsAdjusting() && index > -1 && index2 > -1)
        {
            Cliente c = (Cliente) clientes.get(index);
            int mes = meses.get(index2);
            carregarComprasMes(c.getCodigo(), mes, c.getDiaPagamento());
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        // TODO add your handling code here:
        quitarConta();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnDesligar4MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnDesligar4MouseClicked
    {//GEN-HEADEREND:event_btnDesligar4MouseClicked
        // TODO add your handling code here:
        new FrmControle().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDesligar4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Metal".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(FrmFiado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmFiado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmFiado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmFiado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                new FrmFiado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnDesligar1;
    private javax.swing.JLabel btnDesligar2;
    private javax.swing.JLabel btnDesligar3;
    private javax.swing.JLabel btnDesligar4;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel txtDataHora;
    // End of variables declaration//GEN-END:variables
}
