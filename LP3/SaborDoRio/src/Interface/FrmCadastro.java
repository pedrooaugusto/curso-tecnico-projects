package Interface;

import Controle.ControleConfig;
import Negocio.Config;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.Timer;

public class FrmCadastro extends javax.swing.JFrame {

    public FrmCadastro() {
        initComponents();
        layoutSetter();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        Panel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtDataHora = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        pnlC2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        pnlE = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pnlP = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnC = new javax.swing.JLabel();
        btnE = new javax.swing.JLabel();
        btnP = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JLabel();
        btnCadastro = new javax.swing.JLabel();
        btnControle = new javax.swing.JLabel();
        btnControle1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(233, 233, 243));

        Panel.setBackground(new java.awt.Color(233, 233, 243));
        Panel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 5, new java.awt.Color(59, 59, 59)));
        Panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PanelMouseDragged(evt);
            }
        });
        Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelMousePressed(evt);
            }
        });

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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        pnlC2.setBackground(new java.awt.Color(153, 0, 51));
        pnlC2.setBackground(Color.WHITE);

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cliente");

        javax.swing.GroupLayout pnlC2Layout = new javax.swing.GroupLayout(pnlC2);
        pnlC2.setLayout(pnlC2Layout);
        pnlC2Layout.setHorizontalGroup(
            pnlC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlC2Layout.setVerticalGroup(
            pnlC2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlC2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        pnlE.setBackground(new java.awt.Color(0, 51, 153));
        pnlE.setBackground(Color.WHITE);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Empresa");

        javax.swing.GroupLayout pnlELayout = new javax.swing.GroupLayout(pnlE);
        pnlE.setLayout(pnlELayout);
        pnlELayout.setHorizontalGroup(
            pnlELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlELayout.setVerticalGroup(
            pnlELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlELayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        pnlP.setBackground(new java.awt.Color(204, 102, 0));
        pnlP.setBackground(Color.WHITE);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Produto");

        javax.swing.GroupLayout pnlPLayout = new javax.swing.GroupLayout(pnlP);
        pnlP.setLayout(pnlPLayout);
        pnlPLayout.setHorizontalGroup(
            pnlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlPLayout.setVerticalGroup(
            pnlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        btnC.setBackground(new java.awt.Color(255, 255, 255));
        btnC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnC.setForeground(new java.awt.Color(255, 255, 255));
        btnC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/client.png"))); // NOI18N
        btnC.setToolTipText("");
        btnC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnC.setOpaque(true);
        btnC.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnCMouseMoved(evt);
            }
        });
        btnC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCMouseExited(evt);
            }
        });

        btnE.setBackground(new java.awt.Color(255, 255, 255));
        btnE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnE.setForeground(new java.awt.Color(255, 255, 255));
        btnE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/job.png"))); // NOI18N
        btnE.setToolTipText("");
        btnE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnE.setOpaque(true);
        btnE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEMouseMoved(evt);
            }
        });
        btnE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEMouseExited(evt);
            }
        });

        btnP.setBackground(new java.awt.Color(255, 255, 255));
        btnP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnP.setForeground(new java.awt.Color(255, 255, 255));
        btnP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/food.png"))); // NOI18N
        btnP.setToolTipText("");
        btnP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnP.setOpaque(true);
        btnP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnPMouseMoved(evt);
            }
        });
        btnP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnE, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnP, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        jLabel1.setText("Cadastros");

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

        btnCadastro.setBackground(new java.awt.Color(100, 100, 100));
        btnCadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/folder.png"))); // NOI18N
        btnCadastro.setToolTipText("Cadastros");
        btnCadastro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCadastro.setOpaque(true);

        btnControle.setBackground(new java.awt.Color(59, 59, 59));
        btnControle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnControle.setForeground(new java.awt.Color(255, 255, 255));
        btnControle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnControle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/pedido.png"))); // NOI18N
        btnControle.setToolTipText("Controle de pedidos");
        btnControle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnControle.setOpaque(true);
        btnControle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnControleMouseClicked(evt);
            }
        });

        btnControle1.setBackground(new java.awt.Color(59, 59, 59));
        btnControle1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnControle1.setForeground(new java.awt.Color(255, 255, 255));
        btnControle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnControle1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/currency-icon1.png"))); // NOI18N
        btnControle1.setToolTipText("Controle de pedidos");
        btnControle1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnControle1.setOpaque(true);
        btnControle1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnControle1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnControle, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnControle1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnControle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(btnControle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMouseDragged

    }//GEN-LAST:event_PanelMouseDragged

    private void PanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMousePressed

    }//GEN-LAST:event_PanelMousePressed

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        new FrmMainMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnControleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnControleMouseClicked
        new FrmControle().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnControleMouseClicked

    
    
    
    
    
    private void btnCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCMouseClicked
        new FrmCliente().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCMouseClicked

    private void btnCMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCMouseMoved
        btnC.setBackground(new Color(255,153,153));
        pnlC2.setBackground(new Color(153,0,51));
    }//GEN-LAST:event_btnCMouseMoved

    private void btnCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCMouseExited
        btnC.setBackground(Color.WHITE);
        pnlC2.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnCMouseExited
    
    private void btnEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEMouseMoved
        btnE.setBackground(new Color(102,153,255));
        pnlE.setBackground(new Color(0,51,153));
    }//GEN-LAST:event_btnEMouseMoved

    private void btnEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEMouseExited
        btnE.setBackground(Color.WHITE);
        pnlE.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnEMouseExited

    private void btnEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEMouseClicked
        new FrmEmpresa().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnEMouseClicked

    
    
    
    
    
    private void btnPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPMouseClicked
       new FrmProduto().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnPMouseClicked

    private void btnPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPMouseMoved
         btnP.setBackground(new Color(255,153,102));
         pnlP.setBackground(new Color(204,102,0));
    }//GEN-LAST:event_btnPMouseMoved

    private void btnPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPMouseExited
        btnP.setBackground(Color.WHITE);
        pnlP.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnPMouseExited

    private void btnControle1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_btnControle1MouseClicked
    {//GEN-HEADEREND:event_btnControle1MouseClicked
        // TODO add your handling code here:
        new FrmFiado().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnControle1MouseClicked

    public final void layoutSetter() {
        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDataHora.setText(new Date().toLocaleString().replaceAll(" ", "  -  "));
            }
        });
        timer.setDelay(1000);
        timer.setRepeats(true);
        timer.start();
        
        
        
        
        
        
        
        
        /*pnlC2.setVisible(false);
        pnlE.setVisible(false);
        pnlP.setVisible(false);*/
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
            java.util.logging.Logger.getLogger(FrmCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCadastro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel btnC;
    private javax.swing.JLabel btnCadastro;
    private javax.swing.JLabel btnControle;
    private javax.swing.JLabel btnControle1;
    private javax.swing.JLabel btnE;
    private javax.swing.JLabel btnP;
    private javax.swing.JLabel btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel pnlC2;
    private javax.swing.JPanel pnlE;
    private javax.swing.JPanel pnlP;
    private javax.swing.JLabel txtDataHora;
    // End of variables declaration//GEN-END:variables
}
