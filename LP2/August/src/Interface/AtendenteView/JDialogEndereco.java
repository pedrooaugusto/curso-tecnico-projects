/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface.AtendenteView;

import Negocio.Diversos;
import Negocio.*;
/**
 *
 * @author Pedro
 */
public class JDialogEndereco extends javax.swing.JDialog {

    /** Creates new form JDialogEndereco */
    JFrmPedido telaPedido = null;//Tela que chamou este jDialog
    public JDialogEndereco(JFrmPedido telaPedido, boolean modal) 
    {
        super(telaPedido, modal);
        setLookAndFeel();
        initComponents();
        this.telaPedido = telaPedido;
        if(!(telaPedido.getEndereco() == null))//Testando se é uma alteração ou uma inclusão de pedido
        {
            carregarObjeto(telaPedido.getEndereco());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtRua = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxtNumero = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLblTituloJanela = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTxtBairro = new javax.swing.JTextField();
        Logo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPnlBotoes = new javax.swing.JPanel();
        jBtnAlterar = new javax.swing.JButton();
        jLblVoltar = new javax.swing.JLabel();
        jLblLimpar = new javax.swing.JLabel();
        jCmBMunicipio = new javax.swing.JComboBox();

        jScrollPane1.setViewportView(jTextPane1);

        jScrollPane2.setViewportView(jTextPane2);

        jScrollPane3.setViewportView(jTextPane3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Endereco");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(22, 73, 154));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(255, 255, 0), new java.awt.Color(255, 153, 51)));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bairro:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Rua:");

        jTxtRua.setBackground(new java.awt.Color(102, 102, 255));
        jTxtRua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTxtRua.setForeground(new java.awt.Color(255, 255, 255));
        jTxtRua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 2));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Casa Nº:");

        jTxtNumero.setBackground(new java.awt.Color(102, 102, 255));
        jTxtNumero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTxtNumero.setForeground(new java.awt.Color(255, 255, 255));
        jTxtNumero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 2));
        jTxtNumero.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTxtNumeroKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Municipio:");

        jLblTituloJanela.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLblTituloJanela.setForeground(new java.awt.Color(255, 255, 255));
        jLblTituloJanela.setText("Endereço");

        jTxtBairro.setBackground(new java.awt.Color(102, 102, 255));
        jTxtBairro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTxtBairro.setForeground(new java.awt.Color(255, 255, 255));
        jTxtBairro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 2));

        Logo.setBackground(new java.awt.Color(22, 73, 154));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/c5.png"))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("August");

        org.jdesktop.layout.GroupLayout LogoLayout = new org.jdesktop.layout.GroupLayout(Logo);
        Logo.setLayout(LogoLayout);
        LogoLayout.setHorizontalGroup(
            LogoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(LogoLayout.createSequentialGroup()
                .add(jLabel17)
                .add(2, 2, 2)
                .add(jLabel1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LogoLayout.setVerticalGroup(
            LogoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel1)
            .add(jLabel17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        jPnlBotoes.setBackground(new java.awt.Color(22, 73, 154));

        jBtnAlterar.setBackground(new java.awt.Color(102, 102, 255));
        jBtnAlterar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAlterar.setText("Alterar");
        jBtnAlterar.setBorderPainted(false);
        jBtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnAlterarActionPerformed(evt);
            }
        });

        jLblVoltar.setBackground(new java.awt.Color(204, 0, 0));
        jLblVoltar.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLblVoltar.setForeground(new java.awt.Color(255, 255, 255));
        jLblVoltar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblVoltar.setText("←");
        jLblVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblVoltar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jLblVoltarMouseClicked(evt);
            }
        });

        jLblLimpar.setBackground(new java.awt.Color(204, 0, 0));
        jLblLimpar.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLblLimpar.setForeground(new java.awt.Color(255, 255, 255));
        jLblLimpar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/limpar2.png"))); // NOI18N
        jLblLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblLimpar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jLblLimparMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPnlBotoesLayout = new org.jdesktop.layout.GroupLayout(jPnlBotoes);
        jPnlBotoes.setLayout(jPnlBotoesLayout);
        jPnlBotoesLayout.setHorizontalGroup(
            jPnlBotoesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPnlBotoesLayout.createSequentialGroup()
                .add(jLblVoltar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(146, 146, 146)
                .add(jBtnAlterar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 214, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 86, Short.MAX_VALUE)
                .add(jLblLimpar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPnlBotoesLayout.setVerticalGroup(
            jPnlBotoesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlBotoesLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jBtnAlterar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPnlBotoesLayout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(jPnlBotoesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLblVoltar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLblLimpar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jCmBMunicipio.setBackground(new java.awt.Color(102, 102, 255));
        jCmBMunicipio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCmBMunicipio.setForeground(new java.awt.Color(255, 255, 255));
        jCmBMunicipio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Queimados", "Nova Iguaçu", "São João", "Belford Roxo", "Zona Norte" }));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(12, 12, 12)
                                .add(jLblTituloJanela, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 161, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 76, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(32, 32, 32)
                        .add(Logo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 161, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jCmBMunicipio, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 161, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTxtBairro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 216, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 161, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 161, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jTxtRua)
                                    .add(jTxtNumero))))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPnlBotoes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLblTituloJanela)
                        .add(0, 0, 0)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(Logo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel14)
                    .add(jCmBMunicipio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(14, 14, 14)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel2)
                    .add(jTxtBairro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel5)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(jTxtRua, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel6)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(jTxtNumero, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPnlBotoes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void limpar()
    {
        jTxtBairro.setText("");
        jTxtRua.setText("");
        jTxtNumero.setText("");
        jCmBMunicipio.setSelectedIndex(-1);
    }
    public void carregarObjeto(Endereco e)
    {
        jCmBMunicipio.setSelectedItem(e.getMunicipio());
        jTxtBairro.setText(e.getBairro());
        jTxtRua.setText(e.getRua());
        jTxtNumero.setText(String.valueOf(e.getNumero()));
    }
    private void jTxtNumeroKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTxtNumeroKeyReleased
    {//GEN-HEADEREND:event_jTxtNumeroKeyReleased
        // TODO add your handling code here:
        jTxtNumero.setText(Diversos.testaNum(jTxtNumero.getText(), 2));
    }//GEN-LAST:event_jTxtNumeroKeyReleased

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnAlterarActionPerformed
    {//GEN-HEADEREND:event_jBtnAlterarActionPerformed
        Endereco e;
        if(
            jCmBMunicipio.getSelectedIndex() >= 0
            && !jTxtBairro.getText().isEmpty()
            && !jTxtRua.getText().isEmpty()
            && !jTxtNumero.getText().isEmpty()
        )
        {
            e = new Endereco();
            e.setMunicipio(jCmBMunicipio.getSelectedItem().toString());
            e.setBairro(jTxtBairro.getText());
            e.setRua(jTxtRua.getText());
            e.setNumero(Integer.parseInt(jTxtNumero.getText()));
            telaPedido.setEndereco(e);//Envia para a tela que a chamou um novo Endereco
            dispose();
        }
        else
        {
            Diversos.mostrarDados("Preencha todos dados", "14:52", true);
        }
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jLblVoltarMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLblVoltarMouseClicked
    {//GEN-HEADEREND:event_jLblVoltarMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLblVoltarMouseClicked

    private void jLblLimparMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLblLimparMouseClicked
    {//GEN-HEADEREND:event_jLblLimparMouseClicked
        limpar();
    }//GEN-LAST:event_jLblLimparMouseClicked

    public void setLookAndFeel()
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
            java.util.logging.Logger.getLogger(JDialogEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JDialogEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JDialogEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JDialogEndereco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Logo;
    private javax.swing.JButton jBtnAlterar;
    private javax.swing.JComboBox jCmBMunicipio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLblLimpar;
    private javax.swing.JLabel jLblTituloJanela;
    private javax.swing.JLabel jLblVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnlBotoes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextField jTxtBairro;
    private javax.swing.JTextField jTxtNumero;
    private javax.swing.JTextField jTxtRua;
    // End of variables declaration//GEN-END:variables

}