package Interface;

import Controle.ControleConfig;
import Controle.Diversos;
import Negocio.Config;
import com.pedroaugusto.transicoes.ControleTransicoes;

public class FrmMainMenu extends javax.swing.JFrame {

    public FrmMainMenu() {
        initComponents();
        loadConfigurations();
    }

    private final ControleConfig cConfig = new ControleConfig();
    private Config config;

    public final void loadConfigurations() {
        try {
            config = (Config) cConfig.getBusca(1, -1);
            if (config != null) {
                System.out.println("Configuração salva retornada com sucesso.");
            } else {
                config = cConfig.getDefaultConfig();
                System.out.println("Configuração padrão retornada. Erro: Busca nula.");
            }
        } catch (Exception e) {
            config = cConfig.getDefaultConfig();
            System.out.println("Configuração padrão retornada. Erro:\n" + e.getMessage());
        }
    }

    private final ControleTransicoes cT = new ControleTransicoes(this);
    private int pX = 0, pY = 0;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JpnlMenuItems = new javax.swing.JPanel();
        jpnlMenuItem1 = new Interface.JpnlMenuItem(1, this);
        jpnlMenuItem2 = new Interface.JpnlMenuItem(2, this);
        jpnlMenuItem3 = new Interface.JpnlMenuItem(3, this);
        jpnlMenuItem4 = new Interface.JpnlMenuItem(4, this);
        JpnlMenuOptions = new javax.swing.JPanel();
        btnDesligar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDesligar1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(233, 233, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 59, 59), 5));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        JpnlMenuItems.setBackground(new java.awt.Color(233, 233, 243));
        JpnlMenuItems.setOpaque(false);

        jpnlMenuItem1.setBackground(new java.awt.Color(0, 140, 255));
        jpnlMenuItem1.setText("Abrir turno");
        jpnlMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnlMenuItem1MouseClicked(evt);
            }
        });

        jpnlMenuItem2.setBackground(new java.awt.Color(0, 140, 255));
        jpnlMenuItem2.setText("Estoque");
        jpnlMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnlMenuItem2MouseClicked(evt);
            }
        });

        jpnlMenuItem3.setBackground(new java.awt.Color(0, 140, 255));
        jpnlMenuItem3.setText("Relatórios");
        jpnlMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnlMenuItem3MouseClicked(evt);
            }
        });

        jpnlMenuItem4.setBackground(new java.awt.Color(0, 140, 255));
        jpnlMenuItem4.setText("Backup");

        javax.swing.GroupLayout JpnlMenuItemsLayout = new javax.swing.GroupLayout(JpnlMenuItems);
        JpnlMenuItems.setLayout(JpnlMenuItemsLayout);
        JpnlMenuItemsLayout.setHorizontalGroup(
            JpnlMenuItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlMenuItemsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlMenuItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpnlMenuItem4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlMenuItem2, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(jpnlMenuItem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnlMenuItem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(327, Short.MAX_VALUE))
        );
        JpnlMenuItemsLayout.setVerticalGroup(
            JpnlMenuItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlMenuItemsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jpnlMenuItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnlMenuItem2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnlMenuItem3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnlMenuItem4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        JpnlMenuOptions.setBackground(new java.awt.Color(59, 59, 59));

        btnDesligar.setBackground(new java.awt.Color(59, 59, 59));
        btnDesligar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/off.png"))); // NOI18N
        btnDesligar.setToolTipText("Encerrar sessão");
        btnDesligar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesligar.setOpaque(true);
        btnDesligar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDesligarMouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(51, 0, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SOBRE");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JpnlMenuOptionsLayout = new javax.swing.GroupLayout(JpnlMenuOptions);
        JpnlMenuOptions.setLayout(JpnlMenuOptionsLayout);
        JpnlMenuOptionsLayout.setHorizontalGroup(
            JpnlMenuOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlMenuOptionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JpnlMenuOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesligar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        JpnlMenuOptionsLayout.setVerticalGroup(
            JpnlMenuOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlMenuOptionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDesligar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpnlMenuItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JpnlMenuOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JpnlMenuItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JpnlMenuOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(59, 59, 59));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sabor do Rio");

        btnDesligar1.setBackground(new java.awt.Color(59, 59, 59));
        btnDesligar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar1.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/home.png"))); // NOI18N
        btnDesligar1.setToolTipText("");
        btnDesligar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDesligar1.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDesligar1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDesligar1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesligarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesligarMouseClicked
        //if(Diversos.confirmar("Finalizar programa?", "DESLIGAR")){
            try{
                cT.fechaJanela(this, false);
            }catch(Exception e){
                System.exit(0);
            }
        //}
    }//GEN-LAST:event_btnDesligarMouseClicked

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        setLocation(getLocation().x + evt.getX() - pX, getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jpnlMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlMenuItem1MouseClicked
        try {
            cT.novaJanela(this, new FrmControle());
        } catch (Exception e) {
            new FrmControle().setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jpnlMenuItem1MouseClicked

    private void jpnlMenuItem2MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jpnlMenuItem2MouseClicked
    {//GEN-HEADEREND:event_jpnlMenuItem2MouseClicked
       new FrmControleEstoque().setVisible(true);
       dispose();
    }//GEN-LAST:event_jpnlMenuItem2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        Diversos.mostrarDados("Developed by Pedro Augusto da Silva Ribeiro & Thales Zarzar de Souza", "Sobre", true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jpnlMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlMenuItem3MouseClicked
        new FrmRelatorios().setVisible(true);
        dispose();
    }//GEN-LAST:event_jpnlMenuItem3MouseClicked

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
            java.util.logging.Logger.getLogger(FrmMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        FrmMainMenu k = new FrmMainMenu();
        k.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpnlMenuItems;
    private javax.swing.JPanel JpnlMenuOptions;
    private javax.swing.JLabel btnDesligar;
    private javax.swing.JLabel btnDesligar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public Interface.JpnlMenuItem jpnlMenuItem1;
    public Interface.JpnlMenuItem jpnlMenuItem2;
    public Interface.JpnlMenuItem jpnlMenuItem3;
    public Interface.JpnlMenuItem jpnlMenuItem4;
    // End of variables declaration//GEN-END:variables
}
