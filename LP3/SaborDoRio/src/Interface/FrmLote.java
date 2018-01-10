/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controle.ControleLote;
import Controle.ControleProduto;
import Controle.Diversos;
import Negocio.Lote;
import Negocio.Monitorado;
import Negocio.Produto;
import com.pedroaugusto.mycomponents.ClickListener;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class FrmLote extends javax.swing.JFrame {

    /**
     * Creates new form FrmLote
     */
    private ControleLote cL = new ControleLote();
    private ControleProduto cP = new ControleProduto();
    private List<Object> listaProduto = new ArrayList<Object>();
    private boolean insercao = true;
    public FrmLote()
    {
        initComponents();
        comboProduto.addActionListener(new ClickListener() {
            @Override
            public void action()
            {
                if(comboProduto.getSelectedIndex() != -1)
                {
                    Monitorado m = (Monitorado) listaProduto.get(
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
        l.setMonitorado((Monitorado) listaProduto.get(comboProduto.getSelectedIndex()));
        return l;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootLote = new javax.swing.JPanel();
        JpnlForm = new javax.swing.JPanel();
        JpnlBotoes = new javax.swing.JPanel();
        buttonSave = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonClear = new javax.swing.JButton();
        JlblWarning = new javax.swing.JLabel();
        comboProduto = new com.pedroaugusto.mycomponents.THComboBox();
        fieldQntAtual = new com.pedroaugusto.mycomponents.PAField();
        fieldQntInicial = new com.pedroaugusto.mycomponents.PAField();
        fieldValidade = new com.pedroaugusto.mycomponents.PAField();
        JlblUni = new javax.swing.JLabel();
        JlblUni1 = new javax.swing.JLabel();
        JpnlSearchBox = new javax.swing.JPanel();
        searchBox = new javax.swing.JTextField();
        SearchBoxicon = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Lote");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        rootLote.setBackground(new java.awt.Color(242, 242, 242));

        JpnlForm.setBackground(new java.awt.Color(255, 255, 255));
        JpnlForm.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 153, 153))));
        JpnlForm.setForeground(new java.awt.Color(204, 204, 204));

        JpnlBotoes.setBackground(new java.awt.Color(252, 252, 252));
        JpnlBotoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        JpnlBotoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

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
        JpnlBotoes.add(buttonSave);

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
        JpnlBotoes.add(buttonEdit);

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
        JpnlBotoes.add(buttonDelete);

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
        JpnlBotoes.add(buttonClear);

        JlblWarning.setText("(*) Campos obrigátorios");

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
        JlblUni.setBorder(new javax.swing.border.MatteBorder(null));
        JlblUni.setOpaque(true);

        JlblUni1.setBackground(new java.awt.Color(102, 102, 102));
        JlblUni1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        JlblUni1.setForeground(new java.awt.Color(255, 255, 255));
        JlblUni1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JlblUni1.setText("UNIDADE'S");
        JlblUni1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JlblUni1.setOpaque(true);

        javax.swing.GroupLayout JpnlFormLayout = new javax.swing.GroupLayout(JpnlForm);
        JpnlForm.setLayout(JpnlFormLayout);
        JpnlFormLayout.setHorizontalGroup(
            JpnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlFormLayout.createSequentialGroup()
                .addGroup(JpnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JpnlFormLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(JpnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlblWarning)
                            .addComponent(JpnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JpnlFormLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(JpnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldQntAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldQntInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldValidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(JpnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlblUni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JlblUni1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        JpnlFormLayout.setVerticalGroup(
            JpnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JpnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldQntAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JlblUni1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JpnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fieldQntInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JlblUni, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(fieldValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(JlblWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(JpnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

        javax.swing.GroupLayout rootLoteLayout = new javax.swing.GroupLayout(rootLote);
        rootLote.setLayout(rootLoteLayout);
        rootLoteLayout.setHorizontalGroup(
            rootLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootLoteLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(rootLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JpnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JpnlSearchBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        rootLoteLayout.setVerticalGroup(
            rootLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootLoteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpnlSearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JpnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jMenuBar1.setBackground(new java.awt.Color(121, 134, 203));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102))));

        jMenu1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/rback.png"))); // NOI18N
        jMenu1.setText("Voltar");
        jMenu1.setToolTipText("Voltar");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenuItem2.setText("Voltar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/rhome.png"))); // NOI18N
        jMenu2.setText("Inicio");
        jMenu2.setToolTipText("Principal");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenuItem1.setText("Menu Principal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/product.png"))); // NOI18N
        jMenu3.setText("Produto");
        jMenu3.setToolTipText("Principal");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenuItem3.setText("Abrir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rootLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootLote, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        // TODO add your handling code here:
        listaProduto = cP.lista("order by nome_pro");
        for (int i = listaProduto.size() -1; i > -1; i--)
            if (!((Produto)listaProduto.get(i)).isMonitorado()) 
                listaProduto.remove(i);
        comboProduto.limparLista();
        if(!listaProduto.isEmpty())
        {
            for(Object a : listaProduto)
            {
                Monitorado b = (Monitorado) a;
                comboProduto.addItem(b.getNome(), b.getID());
            }
            comboProduto.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        dispose();
        new FrmMainMenu().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem2ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem2ActionPerformed
        // TODO add your handling code here
        dispose();
        new FrmControle().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem3ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if(searchBox.isEditable())
        {
            new FrmProduto().setVisible(true);
            dispose();
        }
        else
        {
            Produto k = ((Produto) listaProduto.get(
                    comboProduto.getSelectedIndex()));
            System.out.println(k.getID());
            new FrmProduto(k).setVisible(true);
          //  new ProdutoFrmOld(k).setVisible(true);
        }
        //dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                new FrmLote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlblUni;
    private javax.swing.JLabel JlblUni1;
    private javax.swing.JLabel JlblWarning;
    private javax.swing.JPanel JpnlBotoes;
    private javax.swing.JPanel JpnlForm;
    private javax.swing.JPanel JpnlSearchBox;
    private javax.swing.JLabel SearchBoxicon;
    private javax.swing.JButton buttonClear;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonSave;
    private com.pedroaugusto.mycomponents.THComboBox comboProduto;
    private com.pedroaugusto.mycomponents.PAField fieldQntAtual;
    private com.pedroaugusto.mycomponents.PAField fieldQntInicial;
    private com.pedroaugusto.mycomponents.PAField fieldValidade;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel rootLote;
    private javax.swing.JTextField searchBox;
    // End of variables declaration//GEN-END:variables
}
