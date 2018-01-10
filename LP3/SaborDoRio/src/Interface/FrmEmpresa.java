package Interface;

import Controle.ControleEmpresa;
import Controle.Diversos;
import Negocio.Empresa;
import java.util.ArrayList;
import java.util.List;

public class FrmEmpresa extends javax.swing.JFrame {

    public FrmEmpresa() {
        initComponents();
        listaEmpresas = cE.lista("order by razaosocial_emp");
        limpar();
    }

    public FrmEmpresa(Object o) {
        initComponents();
        listaEmpresas = cE.lista("order by razaosocial_emp");
        limpar();
        modoEdicao = true;
        carregarObjeto((Empresa) o);
    }

    boolean modoEdicao = false;
    /*se verdadeiro, indica que um objeto já existente
        no banco de dados está carregado na tela e pronto para edição*/
    private Empresa objetoAberto;
    private List listaEmpresas = new ArrayList<>();
    private final ControleEmpresa cE = new ControleEmpresa();

    private Empresa montarObjeto() {

        Empresa e = new Empresa();
        e.setID(Integer.parseInt(txtCod.getText()));
        e.setRazaoSocial(txtRazao.getText());
        e.setTel1(txtTel1.getText());
        e.setTel2(txtTel2.getText());

        return e;
    }

    public final void carregarObjeto(Empresa e) {
        objetoAberto = e;

        txtCod.setText(e.getID() + "");
        txtRazao.setText(e.getRazaoSocial());
        txtTel1.setText(e.getTel1());
        txtTel2.setText(e.getTel2());

        txtCod.setEditable(false);
    }

    private void limpar() {
        modoEdicao = false;
        objetoAberto = null;

        txtCod.setText(gerarCodigo() + "");
        txtRazao.setText("");
        txtTel1.setText("");
        txtTel2.setText("");
    }

    public final int gerarCodigo() {

        listaEmpresas = cE.lista("order by razaosocial_emp");
        
        int cod = 0;
        for (Object o : listaEmpresas) {
            Empresa e = (Empresa) o;
            if (e.getID() > cod) {
                cod = e.getID();
            }
        }
        cod++;
        return cod;
    }

    public int validarCampos() {
        int ok = 1;
        if (!txtCod.getText().isEmpty()
                && !txtRazao.getText().isEmpty()
                && !txtTel1.getText().isEmpty()) {

            ok = 0;

            if (!modoEdicao) {
                for (Object o : listaEmpresas) {
                    Empresa e = (Empresa) o;
                    if (e.getID() == Integer.parseInt(txtCod.getText())) {
                        ok = 2;
                    }
                }
            }
        }
        return ok;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtRazao = new com.pedroaugusto.mycomponents.PAField();
        txtCod = new com.pedroaugusto.mycomponents.PAField();
        jLabel4 = new javax.swing.JLabel();
        txtTel1 = new com.pedroaugusto.mycomponents.PAField();
        txtTel2 = new com.pedroaugusto.mycomponents.PAField();
        pnlP = new javax.swing.JPanel();
        buttonList = new javax.swing.JLabel();
        buttonSave = new javax.swing.JLabel();
        buttonDelete = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnVoltar1 = new javax.swing.JLabel();
        btnProduto1 = new javax.swing.JLabel();
        btnEmpresa1 = new javax.swing.JLabel();
        btnCliente1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 5, new java.awt.Color(59, 59, 59)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        txtRazao.setFieldName("*Razão social");
        txtRazao.setIcon("name.png");
        txtRazao.setPlaceholder("Razão social da empresa");

        txtCod.setFieldName("*Código");
        txtCod.setIcon("name.png");
        txtCod.setPlaceholder("Código de identificação da empresa");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setText("(*) Campos obrigátorios");

        txtTel1.setFieldName("*Telefone¹");
        txtTel1.setIcon("telephone.png");
        txtTel1.setPlaceholder("0000-0000");

        txtTel2.setFieldName("Telefone²");
        txtTel2.setIcon("telephone.png");
        txtTel2.setPlaceholder("0000-0000");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtRazao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCod, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addGap(11, 11, 11)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtRazao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        pnlP.setBackground(new java.awt.Color(0, 141, 189));

        javax.swing.GroupLayout pnlPLayout = new javax.swing.GroupLayout(pnlP);
        pnlP.setLayout(pnlPLayout);
        pnlPLayout.setHorizontalGroup(
            pnlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlPLayout.setVerticalGroup(
            pnlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        buttonList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/list.png"))); // NOI18N
        buttonList.setToolTipText("Listar");
        buttonList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonListMouseClicked(evt);
            }
        });

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/save flat.png"))); // NOI18N
        buttonSave.setToolTipText("Salvar");
        buttonSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/trash.png"))); // NOI18N
        buttonDelete.setToolTipText("Excluir");
        buttonDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDeleteMouseClicked(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/brush.png"))); // NOI18N
        btnLimpar.setToolTipText("Limpar");
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimparMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonList)
                    .addComponent(buttonSave)
                    .addComponent(buttonDelete)
                    .addComponent(btnLimpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(pnlP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(buttonList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addGap(6, 6, 6)
                        .addComponent(btnLimpar)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(59, 59, 59));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cadastros");

        btnVoltar1.setBackground(new java.awt.Color(59, 59, 59));
        btnVoltar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVoltar1.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVoltar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/back.png"))); // NOI18N
        btnVoltar1.setToolTipText("Voltar");
        btnVoltar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar1.setOpaque(true);
        btnVoltar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltar1MouseClicked(evt);
            }
        });

        btnProduto1.setBackground(new java.awt.Color(59, 59, 59));
        btnProduto1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnProduto1.setForeground(new java.awt.Color(255, 255, 255));
        btnProduto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnProduto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/food mini.png"))); // NOI18N
        btnProduto1.setToolTipText("Produtos");
        btnProduto1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProduto1.setOpaque(true);
        btnProduto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProduto1MouseClicked(evt);
            }
        });

        btnEmpresa1.setBackground(new java.awt.Color(100, 100, 100));
        btnEmpresa1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEmpresa1.setForeground(new java.awt.Color(255, 255, 255));
        btnEmpresa1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEmpresa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/job mini.png"))); // NOI18N
        btnEmpresa1.setToolTipText("Empresas");
        btnEmpresa1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEmpresa1.setOpaque(true);

        btnCliente1.setBackground(new java.awt.Color(59, 59, 59));
        btnCliente1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCliente1.setForeground(new java.awt.Color(255, 255, 255));
        btnCliente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCliente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/client mini.png"))); // NOI18N
        btnCliente1.setToolTipText("Clientes");
        btnCliente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliente1.setOpaque(true);
        btnCliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCliente1MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 141, 189));
        jLabel6.setText("Empresas");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(btnCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpresa1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProduto1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEmpresa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonListMouseClicked
        new FrmListas(cE.lista("order by razaosocial_emp"), this, "Empresas").setVisible(true);
        dispose();
    }//GEN-LAST:event_buttonListMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
        int v = validarCampos();

        if (v == 0) {

            Empresa e = montarObjeto();
            int rE = -1;

            if (!modoEdicao) //Incluir um objeto
            {
                rE = cE.setManipular(e, 'I');

                if (rE == 0) {
                    Diversos.mostrarDados("A empresa foi salva.", "Tudo certo!", true);
                    modoEdicao = true;
                } else {
                    Diversos.mostrarDados("Problemas ao salvar a empresa.", "Ops!", false);
                }

            } else //Alterar um objeto
            {
                rE = cE.setManipular(e, 'A');

                if (rE == 0) {
                    Diversos.mostrarDados("As alterações da empresa foram salvas.", "Tudo certo!", true);
                    modoEdicao = true;
                } else {
                    Diversos.mostrarDados("Problemas ao salvar as alterações da empresa.", "Ops!", false);
                }
            }

        } else {
            if (v == 1) {
                Diversos.mostrarDados("Preencha todos os campos!", "Atenção!", false);
            } else if (v == 2) {
                Diversos.mostrarDados("Já existe uma empresa com este código.", "Atenção!", false);
                if (Diversos.confirmar("Podemos gerar um código automático para a empresa?", "")) {
                    txtCod.setText(gerarCodigo() + "");
                    buttonSaveMouseClicked(evt);
                }
            }
        }
    }//GEN-LAST:event_buttonSaveMouseClicked

    private void buttonDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDeleteMouseClicked

        if (modoEdicao) {

            if (Diversos.confirmar("Excluir esta empresa fará com que os clientes registrados nesta.\n"
                    + "empresa fiquem sem uma empresa definida."
                    + "Continuar?", "Atenção!")) {

                int rE = -1;
                
                rE = cE.setManipular(objetoAberto, 'E');

                if (rE != 0) {
                    Diversos.mostrarDados("Não foi possível excluir a empresa.", "Ops!", false);
                } else {
                    limpar();
                }

            }
        }
    }//GEN-LAST:event_buttonDeleteMouseClicked

    private void btnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimparMouseClicked
        limpar();
    }//GEN-LAST:event_btnLimparMouseClicked

    private void btnVoltar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltar1MouseClicked
        new FrmCadastro().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVoltar1MouseClicked

    private void btnProduto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProduto1MouseClicked
        new FrmProduto().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnProduto1MouseClicked

    private void btnCliente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCliente1MouseClicked
        new FrmCliente().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCliente1MouseClicked

    public static void main(String args[]) {
      
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEmpresa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCliente1;
    private javax.swing.JLabel btnEmpresa1;
    private javax.swing.JLabel btnLimpar;
    private javax.swing.JLabel btnProduto1;
    private javax.swing.JLabel btnVoltar1;
    private javax.swing.JLabel buttonDelete;
    private javax.swing.JLabel buttonList;
    private javax.swing.JLabel buttonSave;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel pnlP;
    private com.pedroaugusto.mycomponents.PAField txtCod;
    private com.pedroaugusto.mycomponents.PAField txtRazao;
    private com.pedroaugusto.mycomponents.PAField txtTel1;
    private com.pedroaugusto.mycomponents.PAField txtTel2;
    // End of variables declaration//GEN-END:variables
}
