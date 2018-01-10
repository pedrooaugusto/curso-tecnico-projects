/*
    *   EXTREME GO HORSE AHEAD
    *   DO NOT MODIFY, IT'S WORKING!
    *
 */
package Interface;

import Controle.ControleLote;
import Controle.ControleProduto;
import Controle.ControleRotina;
import Controle.Diversos;
import Negocio.Lote;
import Negocio.Monitorado;
import Negocio.Produto;
import Negocio.Rotina;
import Negocio.RotinaProduto;
import com.pedroaugusto.mycomponents.ClickListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro
 */
public class FrmControleEstoque extends javax.swing.JFrame {
    private ControleLote cL = new ControleLote();
    private ControleProduto cP = new ControleProduto();
    private ControleRotina cR = new ControleRotina();
    private List<Object> listaProdutoIn = new ArrayList<Object>();
    private List<Object> listaProdutoOut = new ArrayList<Object>();
    private List<RotinaProduto> listaProdutoSelecionadosToOut = new ArrayList<RotinaProduto>();
    private List<Object> rotinasLista = new ArrayList<Object>();
    private JButton[] currentAndLastButtonSelected = {null, null};
    private boolean insercao = true;
    private String aba = "in";
    public FrmControleEstoque()
    {
        initComponents();
        jPanel10.setVisible(false);
        jButton9.setEnabled(false);
        comboProduto.addActionListener(new ClickListener() {
            @Override
            public void action()
            {
                if(comboProduto.getSelectedIndex() != -1)
                {
                    Monitorado m = (Monitorado) listaProdutoIn.get(
                            comboProduto.getSelectedIndex());
                    String uni = m.getEstoque().getUnidadeMedida();
                    JlblUni1.setText(uni.toUpperCase()+"'s");
                    JlblUni.setText(uni.toUpperCase()+"'s");
                    JlblUni1.setVisible(true);
                    JlblUni.setVisible(true);
                }
                else
                {
                    JlblUni1.setVisible(false);
                    JlblUni.setVisible(false);
                }
            }
        });
        fieldQntAtual.addKeyTypedListener(new ClickListener() {
            @Override
            public void action()
            {
                if(insercao)
                {
                    fieldQntInicial.setText(fieldQntAtual.getText());
                }
            }
        });
        CustomCelulaJTable tdRenderer = new CustomCelulaJTable(1);
        //jTable1.setModel(new DefaultTableModel());
        int[] tamanhos = {25, 300, 100};
        for (int i = 0; i < jTable1.getColumnModel().getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(tdRenderer);
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(new CustomCabecalhoJTable(1));
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(tamanhos[i]);
        }
        jTable1.setRowHeight(25);
        jTable1.setRowMargin(5);
        limpar();
    }
    private void limpar()
    {
        searchBox.setEditable(true);
        searchBox.setText("");
        comboProduto.setSelectedIndex(-1);
        JlblUni1.setVisible(false);
        JlblUni.setVisible(false);
        buttonDelete.setEnabled(false);
        buttonEdit.setEnabled(false);
        buttonSave.setEnabled(true);
        fieldQntAtual.setText("");
        fieldQntInicial.setText("");
        fieldValidade.setText("");
        insercao = true;
        searchBox.requestFocusInWindow();
    }
    private void carregarObjeto(Lote l)
    {
        searchBox.setText("Lote número: "+l.getID());
        searchBox.setEditable(false);
        searchBox.setBackground(Color.white);
        comboProduto.setSelectedItem(l.getMonitorado().getNome());
        fieldQntAtual.setText(l.getQuantidadeAtual()+"");
        fieldQntInicial.setText(l.getQuantidadeInicial()+"");
        fieldValidade.setText(l.getDataValidade());
    }
    private Lote montarObjeto()
    {
        Lote l = new Lote();
        l.setID(Integer.parseInt(searchBox.getText().replaceFirst(
                "Lote número: ", "")));
        l.setDataValidade(fieldValidade.getText());
        l.setQuantidadeAtual(Float.parseFloat(fieldQntAtual.getText()));
        l.setQuantidadeInicial(Float.parseFloat(fieldQntInicial.getText()));
        l.setMonitorado((Monitorado) listaProdutoIn.get(comboProduto.getSelectedIndex()));
        return l;
    }
    private void carregarComboSaida(){
        if(!listaProdutoIn.isEmpty()){
            jComboBox1.removeAllItems();
            listaProdutoOut.clear();
            for(Object c : listaProdutoIn){
                if(((Monitorado) c).isaVenda()){
                    jComboBox1.addItem(((Produto) c).getNome());
                    listaProdutoOut.add(c);
                }
            }
            jComboBox1.setSelectedIndex(-1);
        }
        else
            Diversos.mostrarDados("Não existem produtos cadastrados!", "Estoque", false);
    }
    private void limparOut(){
        jComboBox1.setSelectedIndex(-1);
        jSpinner1.setValue(1.0);
        listaProdutoSelecionadosToOut.clear();
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        for (int i = d.getRowCount() - 1; i >= 0; i--){
            d.removeRow(i);
        }
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
    }
    private void adicoinarNaListaParaDarBaixa(){
        if(jComboBox1.getSelectedIndex() > -1){
            Monitorado p = (Monitorado) listaProdutoOut.get(jComboBox1.getSelectedIndex());
            double quant = (double) jSpinner1.getValue();
            DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
            int nRow = d.getRowCount() + 1;
            Object[] linha = new Object[]{nRow, p.getNome(), quant+" "
                    +p.getEstoque().getUnidadeMedida()};
            d.addRow(linha);
            listaProdutoSelecionadosToOut.add(new RotinaProduto(p, (float)quant));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtDataHora = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        rootLote1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        JpnlSearchBox = new javax.swing.JPanel();
        searchBox = new javax.swing.JTextField();
        SearchBoxicon = new javax.swing.JLabel();
        comboProduto = new com.pedroaugusto.mycomponents.THComboBox();
        fieldQntAtual = new com.pedroaugusto.mycomponents.PAField();
        fieldQntInicial = new com.pedroaugusto.mycomponents.PAField();
        fieldValidade = new com.pedroaugusto.mycomponents.PAField();
        JlblUni = new javax.swing.JLabel();
        JlblUni1 = new javax.swing.JLabel();
        JpnlBotoes1 = new javax.swing.JPanel();
        buttonSave = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        rotNumber = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(59, 59, 59));

        jPanel4.setBackground(new java.awt.Color(59, 59, 59));

        txtDataHora.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtDataHora.setForeground(new java.awt.Color(255, 255, 255));
        txtDataHora.setText("27/12/1988 - 00:00:00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDataHora)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtDataHora)
                .addGap(0, 0, 0))
        );

        btnVoltar.setBackground(new java.awt.Color(59, 59, 59));
        btnVoltar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/back.png"))); // NOI18N
        btnVoltar.setToolTipText("Voltar para o menu principal");
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.setOpaque(true);
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltarMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Estoque");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(221, 221, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));
        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setText("+ Entrada");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("<html>Lorem ipsum doler set up a meth fot fetch the d</html>");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(242, 242, 242));
        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel4.setText("- Saída");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("<html>Lorem ipsum doler set up a meth fot fetch the d</html>");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));
        jPanel6.setLayout(new javax.swing.OverlayLayout(jPanel6));

        rootLote1.setBackground(new java.awt.Color(221, 221, 255));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        jLabel6.setText("Lote");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setOpaque(false);

        JpnlSearchBox.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        searchBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        searchBox.setToolTipText("Informe o id do cliente");
        searchBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        searchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBoxFocusLost(evt);
            }
        });
        searchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBoxActionPerformed(evt);
            }
        });
        searchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchBoxKeyTyped(evt);
            }
        });

        SearchBoxicon.setBackground(new java.awt.Color(255, 255, 255));
        SearchBoxicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/search.png"))); // NOI18N
        SearchBoxicon.setToolTipText("Informe o id do cliente");
        SearchBoxicon.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        SearchBoxicon.setOpaque(true);

        javax.swing.GroupLayout JpnlSearchBoxLayout = new javax.swing.GroupLayout(JpnlSearchBox);
        JpnlSearchBox.setLayout(JpnlSearchBoxLayout);
        JpnlSearchBoxLayout.setHorizontalGroup(
            JpnlSearchBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlSearchBoxLayout.createSequentialGroup()
                .addComponent(SearchBoxicon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(searchBox))
        );
        JpnlSearchBoxLayout.setVerticalGroup(
            JpnlSearchBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchBox, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
            .addComponent(SearchBoxicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        comboProduto.setFieldName("Produto");
        comboProduto.setModel("[]");
        comboProduto.setName(""); // NOI18N
        comboProduto.setPlaceholder("Selecione um produto");

        fieldQntAtual.setFieldName("Quant. Atual");
        fieldQntAtual.setPlaceholder("Quantidade Atual");
        fieldQntAtual.setOnlyNumbers(true);

        fieldQntInicial.setFieldName("Quant. Inicial");
        fieldQntInicial.setPlaceholder("Quantidade Inicial");
        fieldQntInicial.setEditable(false);

        fieldValidade.setFieldName("Validade");
        fieldValidade.setPlaceholder("Ex: 19/06/1998");

        JlblUni.setBackground(new java.awt.Color(102, 102, 102));
        JlblUni.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        JlblUni.setForeground(new java.awt.Color(255, 255, 255));
        JlblUni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlblUni.setText("UNIDADE'S");
        JlblUni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JlblUni.setOpaque(true);

        JlblUni1.setBackground(new java.awt.Color(102, 102, 102));
        JlblUni1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        JlblUni1.setForeground(new java.awt.Color(255, 255, 255));
        JlblUni1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlblUni1.setText("UNIDADE'S");
        JlblUni1.setBorder(new javax.swing.border.MatteBorder(null));
        JlblUni1.setOpaque(true);

        JpnlBotoes1.setBackground(new java.awt.Color(252, 252, 252));
        JpnlBotoes1.setOpaque(false);
        JpnlBotoes1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        buttonSave.setBackground(new java.awt.Color(153, 153, 255));
        buttonSave.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        buttonSave.setForeground(new java.awt.Color(255, 255, 255));
        buttonSave.setText("Salvar");
        buttonSave.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        buttonSave.setFocusPainted(false);
        buttonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSave.setIconTextGap(2);
        buttonSave.setInheritsPopupMenu(true);
        buttonSave.setPreferredSize(new java.awt.Dimension(100, 31));
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        JpnlBotoes1.add(buttonSave);

        buttonEdit.setBackground(new java.awt.Color(153, 153, 255));
        buttonEdit.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        buttonEdit.setForeground(new java.awt.Color(255, 255, 255));
        buttonEdit.setText("Editar");
        buttonEdit.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        buttonEdit.setFocusPainted(false);
        buttonEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEdit.setIconTextGap(2);
        buttonEdit.setInheritsPopupMenu(true);
        buttonEdit.setPreferredSize(new java.awt.Dimension(100, 31));
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });
        JpnlBotoes1.add(buttonEdit);

        buttonDelete.setBackground(new java.awt.Color(153, 153, 255));
        buttonDelete.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        buttonDelete.setForeground(new java.awt.Color(255, 255, 255));
        buttonDelete.setText("Excluir");
        buttonDelete.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        buttonDelete.setFocusPainted(false);
        buttonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDelete.setIconTextGap(2);
        buttonDelete.setInheritsPopupMenu(true);
        buttonDelete.setPreferredSize(new java.awt.Dimension(100, 31));
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });
        JpnlBotoes1.add(buttonDelete);

        buttonClear.setBackground(new java.awt.Color(153, 153, 255));
        buttonClear.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        buttonClear.setForeground(new java.awt.Color(255, 255, 255));
        buttonClear.setText("Limpar");
        buttonClear.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        buttonClear.setFocusPainted(false);
        buttonClear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonClear.setIconTextGap(2);
        buttonClear.setInheritsPopupMenu(true);
        buttonClear.setPreferredSize(new java.awt.Dimension(100, 31));
        buttonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClearActionPerformed(evt);
            }
        });
        JpnlBotoes1.add(buttonClear);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JpnlSearchBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JpnlBotoes1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fieldValidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(comboProduto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldQntInicial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(fieldQntAtual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JlblUni1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(JlblUni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpnlSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldQntAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JlblUni, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldQntInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JlblUni1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(fieldValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(JpnlBotoes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout rootLote1Layout = new javax.swing.GroupLayout(rootLote1);
        rootLote1.setLayout(rootLote1Layout);
        rootLote1Layout.setHorizontalGroup(
            rootLote1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootLote1Layout.createSequentialGroup()
                .addGroup(rootLote1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootLote1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rootLote1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        rootLote1Layout.setVerticalGroup(
            rootLote1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootLote1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel6.add(rootLote1);

        jPanel10.setBackground(new java.awt.Color(221, 221, 255));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 30)); // NOI18N
        jLabel7.setText("Saída");

        jLabel8.setBackground(new java.awt.Color(233, 233, 253));
        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("<html>Dar baixa em produtos que não estão a venda, e por isso não podem ser descontados de forma automática na <br>hora da venda. É o caso dos ingredientes usados para preparar o prato do dia.</html>");

        jPanel11.setOpaque(false);

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        jLabel9.setText("Escolha uma rotina:");

        jScrollPane1.setBorder(null);
        jScrollPane1.getViewport().setBackground(new Color(204,204,255));

        jPanel13.setBackground(new java.awt.Color(255, 153, 102));
        jPanel13.setOpaque(false);
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.Y_AXIS));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Macarronada");
        jButton1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0, new java.awt.Color(204, 204, 255)));
        jButton1.setFocusable(false);
        jButton1.setMaximumSize(new java.awt.Dimension(148, 40));
        jButton1.setMinimumSize(new java.awt.Dimension(148, 40));
        jPanel13.add(jButton1);

        jButton2.setBackground(new java.awt.Color(0, 51, 153));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Bolo de Uva");
        jButton2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0, new java.awt.Color(204, 204, 255)));
        jButton2.setFocusable(false);
        jButton2.setMaximumSize(new java.awt.Dimension(148, 40));
        jButton2.setMinimumSize(new java.awt.Dimension(148, 40));
        jPanel13.add(jButton2);

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Arroz com Uva");
        jButton3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0, new java.awt.Color(204, 204, 255)));
        jButton3.setFocusable(false);
        jButton3.setMaximumSize(new java.awt.Dimension(148, 40));
        jButton3.setMinimumSize(new java.awt.Dimension(148, 40));
        jPanel13.add(jButton3);

        jButton4.setBackground(new java.awt.Color(102, 102, 102));
        jButton4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Osso de galinha");
        jButton4.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0, new java.awt.Color(204, 204, 255)));
        jButton4.setFocusable(false);
        jButton4.setMaximumSize(new java.awt.Dimension(148, 40));
        jButton4.setMinimumSize(new java.awt.Dimension(148, 40));
        jPanel13.add(jButton4);

        jScrollPane1.setViewportView(jPanel13);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(6, 6, 6))
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        jLabel10.setText("<html><u>Retirar produtos do estoque</u></html>");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel11.setText("Produto");

        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel12.setText("Qnt.");

        jSpinner1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, null, 1.0d));
        jSpinner1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("unidade(s)");
        jLabel13.setOpaque(true);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setText("ADD");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(204, 255, 102));
        jButton6.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton6.setText("Apenas Dar Baixa");
        jButton6.setToolTipText("Apenas dar baixa nos items que foram postos nessa lista");
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(204, 255, 102));
        jButton7.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton7.setText("Salvar Rotina E Dar Baixa");
        jButton7.setToolTipText("Da baixa em todos os items da lista e em seguida salva essa lista para futuras baixas com os mesmos elementos");
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(204, 255, 102));
        jButton8.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton8.setText("Limpar");
        jButton8.setFocusable(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
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
                "", "Produto", "Quantidade"
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

        jButton9.setBackground(new java.awt.Color(255, 51, 51));
        jButton9.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jButton9.setText("Excluir");
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel14.setText("Rotina:");

        rotNumber.setText("000000");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, 239, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rotNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(rotNumber))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jSpinner1)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel10);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVoltar)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnVoltarMouseClicked
    {//GEN-HEADEREND:event_btnVoltarMouseClicked
        new FrmMainMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel7MouseExited
    {//GEN-HEADEREND:event_jPanel7MouseExited
        // TODO add your handling code here:
        if(!aba.equals("in")){
            jPanel7.setBackground(new Color(242, 242, 242));
            jPanel7.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        }
    }//GEN-LAST:event_jPanel7MouseExited

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel8MouseExited
    {//GEN-HEADEREND:event_jPanel8MouseExited
        // TODO add your handling code here:
        if(!aba.equals("out")){
            jPanel8.setBackground(new Color(242, 242, 242));
            jPanel8.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        }
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel7MouseEntered
    {//GEN-HEADEREND:event_jPanel7MouseEntered
        // TODO add your handling code here:
        if(!aba.equals("in")){
            jPanel7.setBackground(new Color(221, 221, 255));
            jPanel7.setBorder(BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));
        }
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel8MouseEntered
    {//GEN-HEADEREND:event_jPanel8MouseEntered
        // TODO add your handling code here:
        if(!aba.equals("out")){
            jPanel8.setBackground(new Color(221, 221, 255));
            jPanel8.setBorder(BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));
        }
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel7MouseClicked
    {//GEN-HEADEREND:event_jPanel7MouseClicked
        // TODO add your handling code here:
        aba = "in";
        jPanel8MouseExited(evt);
        rootLote1.setVisible(!false);
        jPanel10.setVisible(!true);
        jButton8ActionPerformed(null);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jPanel8MouseClicked
    {//GEN-HEADEREND:event_jPanel8MouseClicked
        // TODO add your handling code here:
        aba = "out";
        jPanel7MouseExited(evt);
        rootLote1.setVisible(false);
        jPanel10.setVisible(true);
        listaProdutoSelecionadosToOut.clear();
        jPanel13.removeAll();
        rotinasLista = cR.lista("");
        if(!rotinasLista.isEmpty())
            for(int i = 0; i < rotinasLista.size(); i++)
            {
                carregarRotinaNaListaDeRotinas((Rotina)rotinasLista.get(i));
            }
    }//GEN-LAST:event_jPanel8MouseClicked

    private void carregarRotinaNaListaDeRotinas(final Rotina i){
        javax.swing.JButton btn = new javax.swing.JButton();
        btn.setBackground(new java.awt.Color(102, 102, 102));
        btn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btn.setForeground(new java.awt.Color(255, 255, 255));
        btn.setText(i.getShortName());
        btn.setBorder(javax.swing.BorderFactory.createMatteBorder(
                3, 0, 3, 0, new java.awt.Color(204, 204, 255)));
        btn.setFocusable(false);
        btn.setMinimumSize(new Dimension(148, 40));
        btn.setMaximumSize(new Dimension(148, 40));
        btn.addActionListener(new EventoClick(i));
        jPanel13.add(btn);
        jPanel13.revalidate();
    }
    class EventoClick implements ActionListener{

        private final Rotina rot;
        public EventoClick(Rotina r){
            this.rot = r;
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            limparOut();
            listaProdutoSelecionadosToOut.clear();
            listaProdutoSelecionadosToOut.addAll(rot.getItems());
            carregarRotinaNaTela(rot);
            jButton6.setEnabled(true);
            jButton7.setEnabled(!true);
            jButton9.setEnabled(true);
            rotNumber.setText(rot.getID()+"");
            if(currentAndLastButtonSelected[0] != null){
                currentAndLastButtonSelected[0].setBackground(new Color(102, 102, 102));
                currentAndLastButtonSelected[0] = (JButton) e.getSource();
                currentAndLastButtonSelected[0].setBackground(new Color(0, 51, 153));
            }
            else{
                currentAndLastButtonSelected[0] = (JButton) e.getSource();
                currentAndLastButtonSelected[0].setBackground(new Color(0, 51, 153));
            }
        }
        public Rotina getRotina(){return rot;}
        
    }
    
    private void carregarRotinaNaTela(Rotina r)
    {
        DefaultTableModel d = (DefaultTableModel) jTable1.getModel();
        int nRow = 1;
        for(RotinaProduto p : r.getItems())
        {
            Object[] linha = new Object[]{nRow, p.getProduto().getNome(), 
                p.getQuantidade()+" "+p.getProduto().getEstoque().getUnidadeMedida()};
            d.addRow(linha);
            nRow++;
        }
    }
    
    private void searchBoxFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_searchBoxFocusGained
    {//GEN-HEADEREND:event_searchBoxFocusGained
        // TODO add your handling code here:
        Diversos.setPlaceholder(true, searchBox, "Pesquisar produto");
    }//GEN-LAST:event_searchBoxFocusGained

    private void searchBoxFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_searchBoxFocusLost
    {//GEN-HEADEREND:event_searchBoxFocusLost
        // TODO add your handling code here:
        //System.out.println(evt.paramString());
        Diversos.setPlaceholder(false, searchBox, "Pesquisar lote");
        if(!searchBox.getText().isEmpty() &&
            !searchBox.getText().equals("Pesquisar lote"))
        {
            int id = Integer.parseInt(searchBox.getText().
                replaceAll("Lote número: ", ""));
            Lote l = (Lote) cL.getBusca(id, -1);
            if(l != null)
            {
                carregarObjeto(l);
                insercao = false;
                buttonDelete.setEnabled(true);
                buttonEdit.setEnabled(true);
                buttonSave.setEnabled(false);
            }
        }
        else
        {
            searchBox.requestFocusInWindow();
        }
    }//GEN-LAST:event_searchBoxFocusLost

    private void searchBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchBoxActionPerformed
    {//GEN-HEADEREND:event_searchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxActionPerformed

    private void searchBoxKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_searchBoxKeyTyped
    {//GEN-HEADEREND:event_searchBoxKeyTyped
        // TODO add your handling code here:
        String c = evt.getKeyChar()+"";
        if(!c.matches("[0-9]"))
        evt.consume();
    }//GEN-LAST:event_searchBoxKeyTyped

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSaveActionPerformed
    {//GEN-HEADEREND:event_buttonSaveActionPerformed
        // TODO add your handling code here:
        if(!searchBox.getText().isEmpty() &&
            !searchBox.getText().equals("Pesquisar Produto"))
        {
            if((!comboProduto.isEmpty() & !fieldQntAtual.isEmpty() &
                !fieldQntInicial.isEmpty() &  !fieldValidade.isEmpty()))
        {
            Lote l = montarObjeto();
            int a;
            if (buttonSave.isEnabled())
            {
                a = cL.setManipular(l, 'I');
                if(a == 0)
                Diversos.mostrarDados("Incluido com sucesso!", "Lote",
                    true);
                else
                Diversos.mostrarDados("Problemas na inclusão!", "Lote",
                    false);
            }
            else
            {
                a = cL.setManipular(l, 'A');
                if(a == 0)
                Diversos.mostrarDados("Alterado com sucesso!", "Lote",
                    true);
                else
                Diversos.mostrarDados("Problemas ao alterar!", "Lote",
                    false);
            }
            buttonSave.setEnabled(false);
            searchBox.setEditable(false);
            searchBox.setBackground(Color.white);
        }
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonEditActionPerformed
    {//GEN-HEADEREND:event_buttonEditActionPerformed
        // TODO add your handling code here:
        buttonSaveActionPerformed(evt);
    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonDeleteActionPerformed
    {//GEN-HEADEREND:event_buttonDeleteActionPerformed
        // TODO add your handling code here:
        if(Diversos.confirmar("Deseja excluir este lote?", "Lote"))
        {
            int id = Integer.parseInt(searchBox.getText().
                replaceAll("Lote número: ", ""));
            Lote l = (Lote) cL.getBusca(id, -1);
            int o = cL.setManipular(l, 'E');
            if(o == 0)
            {
                Diversos.mostrarDados("Excluído com sucesso!", "Lote",
                    true);
                limpar();
            }
            else
            Diversos.mostrarDados("Problemas ao excluir", "Lote",
                false);
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonClearActionPerformed
    {//GEN-HEADEREND:event_buttonClearActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_buttonClearActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        // TODO add your handling code here:
        listaProdutoIn = cP.lista("order by nome_pro");
        for (int i = listaProdutoIn.size() -1; i > -1; i--)
            if (!((Produto)listaProdutoIn.get(i)).isMonitorado()) 
                listaProdutoIn.remove(i);
        comboProduto.limparLista();
        if(!listaProdutoIn.isEmpty())
        {
            for(Object a : listaProdutoIn)
            {
                Monitorado b = (Monitorado) a;
                comboProduto.addItem(b.getNome(), b.getID());
            }
            comboProduto.setSelectedIndex(-1);
        }
        carregarComboSaida();
    }//GEN-LAST:event_formWindowOpened

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton5ActionPerformed
    {//GEN-HEADEREND:event_jButton5ActionPerformed
        // TODO add your handling code here
        adicoinarNaListaParaDarBaixa();
        if(jButton6.isEnabled() || jButton7.isEnabled())
        {
            jButton6.setEnabled(true);
            jButton7.setEnabled(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton6ActionPerformed
    {//GEN-HEADEREND:event_jButton6ActionPerformed
        // TODO add your handling code here:
        /*
            Aqui entra um método para dar baixa no estoque
            dos produtos.
        */
        int i = 0;
        boolean c = true;
        do
        {
            RotinaProduto rr = listaProdutoSelecionadosToOut.get(i);
            if(rr.getProduto().getEstoque().getQuantidadeAtual() < rr.getQuantidade())
                c = false;
            else
                c = cL.estoqueBaixa(rr.getProduto().getID(), rr.getQuantidade());
            i++;
        }while(c && i < listaProdutoSelecionadosToOut.size());
        if(c){
            Diversos.mostrarDados("Os produtos foram descontados com sucesso!", "Title", true);
        }
        else
        {
            Diversos.mostrarDados("Ocorreu um erro ao descontar produtos!", "title", false);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton7ActionPerformed
    {//GEN-HEADEREND:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Rotina rotina = new Rotina();
        if(!listaProdutoSelecionadosToOut.isEmpty()){
            String name = Diversos.leDados("Hello, my dear Doctor.\n"
                    + "Please enter a name for this routine:", "I'm Ginger?");
            rotina.setShortName(name);
            rotina.setItems(listaProdutoSelecionadosToOut);
            rotina.setDesc("_/|\\_");
            int i = cR.setManipular(rotina, 'I');
            if(i == 0){
                Diversos.mostrarDados("KÊ KÊ ISSO? É O FONE DE OUVIDO!", "Title_", true);
                carregarRotinaNaListaDeRotinas(rotina);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton8ActionPerformed
    {//GEN-HEADEREND:event_jButton8ActionPerformed
        // TODO add your handling code here:
        limparOut();
        if(currentAndLastButtonSelected[0] != null)
            currentAndLastButtonSelected[0].setBackground(new Color(102, 102, 102));
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jButton9.setEnabled(!true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBox1ActionPerformed
    {//GEN-HEADEREND:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        int in = jComboBox1.getSelectedIndex();
        if(in > -1 && !listaProdutoOut.isEmpty()){
            jLabel13.setText(((Monitorado) listaProdutoOut.get(in)).
                    getEstoque().getUnidadeMedida());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton9ActionPerformed
    {//GEN-HEADEREND:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(Diversos.confirmar("Deseja exlcuir esta rotina", "excluir rotiina")){
            Rotina n = new Rotina();
            n.setID(Integer.parseInt(rotNumber.getText()));
            int a = cR.setManipular(n, 'E');
            if(a == 0){
                Diversos.mostrarDados("Rotina exlcuida com suceasso!", "title", true);
                jPanel13.remove(currentAndLastButtonSelected[0]);
                currentAndLastButtonSelected[0] = null;
                jPanel13.revalidate();
                jPanel13.invalidate();
                jPanel13.repaint();
                jButton8ActionPerformed(null);
            }
            else
                Diversos.mostrarDados("Ocorreu um erro ao excluir!", "title", false);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmControleEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmControleEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmControleEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmControleEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                new FrmControleEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlblUni;
    private javax.swing.JLabel JlblUni1;
    private javax.swing.JPanel JpnlBotoes1;
    private javax.swing.JPanel JpnlSearchBox;
    private javax.swing.JLabel SearchBoxicon;
    private javax.swing.JLabel btnVoltar;
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonSave;
    private com.pedroaugusto.mycomponents.THComboBox comboProduto;
    private com.pedroaugusto.mycomponents.PAField fieldQntAtual;
    private com.pedroaugusto.mycomponents.PAField fieldQntInicial;
    private com.pedroaugusto.mycomponents.PAField fieldValidade;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel rootLote1;
    private javax.swing.JLabel rotNumber;
    private javax.swing.JTextField searchBox;
    private javax.swing.JLabel txtDataHora;
    // End of variables declaration//GEN-END:variables
}
