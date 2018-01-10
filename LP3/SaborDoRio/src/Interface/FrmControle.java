package Interface;

import Controle.ControleConfig;
import Negocio.Config;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.util.Date;

public final class FrmControle extends javax.swing.JFrame {

    private int pX = 0, pY = 0;
//    ControlePedido cP = new ControlePedido();

    public FrmControle() {
        initComponents();
        loadConfigurations();
        layoutSetter();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel3 = new javax.swing.JPanel();
        Panel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtDataHora = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDesligar1 = new javax.swing.JLabel();
        btnDesligar2 = new javax.swing.JLabel();
        btnDesligar3 = new javax.swing.JLabel();
        btnDesligar4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setUndecorated(true);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(233, 233, 243));

        Panel.setBackground(new java.awt.Color(233, 233, 243));
        Panel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 5, new java.awt.Color(59, 59, 59)));
        Panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            public void mouseDragged(java.awt.event.MouseEvent evt)
            {
                PanelMouseDragged(evt);
            }
        });
        Panel.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                PanelMousePressed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(233, 233, 243));
        jScrollPane2.setBorder(null);

        jPanel2.setBackground(new java.awt.Color(233, 233, 243));
        jPanel2.setLayout(new java.awt.GridLayout(2, 5));
        jScrollPane2.setViewportView(jPanel2);

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
                .addContainerGap(698, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtDataHora)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2)
                .addGap(6, 6, 6))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(59, 59, 59));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Controle de pedidos");

        btnDesligar1.setBackground(new java.awt.Color(59, 59, 59));
        btnDesligar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar1.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/back.png"))); // NOI18N
        btnDesligar1.setToolTipText("Voltar para o menu principal");
        btnDesligar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesligar1.setOpaque(true);
        btnDesligar1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
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
        btnDesligar2.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnDesligar2MouseClicked(evt);
            }
        });

        btnDesligar3.setBackground(new java.awt.Color(100, 100, 100));
        btnDesligar3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar3.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/pedido.png"))); // NOI18N
        btnDesligar3.setToolTipText("Controle de pedidos");
        btnDesligar3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDesligar3.setOpaque(true);

        btnDesligar4.setBackground(new java.awt.Color(59, 59, 59));
        btnDesligar4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDesligar4.setForeground(new java.awt.Color(255, 255, 255));
        btnDesligar4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesligar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/currency-icon1.png"))); // NOI18N
        btnDesligar4.setToolTipText("Cadastros");
        btnDesligar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDesligar4.setOpaque(true);
        btnDesligar4.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                btnDesligar4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDesligar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDesligar3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesligar4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDesligar2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDesligar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDesligar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDesligar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11))
            .addComponent(btnDesligar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 956, 635);
    }// </editor-fold>//GEN-END:initComponents

    private void PanelMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_PanelMousePressed
    {//GEN-HEADEREND:event_PanelMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_PanelMousePressed

    private void PanelMouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_PanelMouseDragged
    {//GEN-HEADEREND:event_PanelMouseDragged
        setLocation(getLocation().x + evt.getX() - pX, getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_PanelMouseDragged

    private void btnDesligar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesligar2MouseClicked
        new FrmCadastro().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDesligar2MouseClicked

    private void btnDesligar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesligar1MouseClicked
        new FrmMainMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDesligar1MouseClicked

    private void btnDesligar4MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnDesligar4MouseClicked
    {//GEN-HEADEREND:event_btnDesligar4MouseClicked
        // TODO add your handling code here:
        new FrmFiado().setVisible(true);
        dispose();
        
    }//GEN-LAST:event_btnDesligar4MouseClicked

    public void layoutSetter() {

        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDataHora.setText(new Date().toLocaleString().replaceAll(" ", "  -  "));
            }
        });
        timer.setDelay(1000);
        timer.setRepeats(true);
        timer.start();
        for (int i = 1; i <= config.getnMesas(); i++) {
            CardPanel j = new CardPanel(i);
            j.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CardPanel a = (CardPanel) e.getComponent();
                    new FrmMesa(a.getId(), a).setVisible(true);
                    dispose();
                }
            });
            jPanel2.add(j);
        }
    }

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FrmControle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmControle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmControle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmControle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        new FrmControle().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel btnDesligar1;
    private javax.swing.JLabel btnDesligar2;
    private javax.swing.JLabel btnDesligar3;
    private javax.swing.JLabel btnDesligar4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel txtDataHora;
    // End of variables declaration//GEN-END:variables
}
