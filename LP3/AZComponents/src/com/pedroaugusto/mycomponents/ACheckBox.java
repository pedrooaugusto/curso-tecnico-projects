/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pedroaugusto.mycomponents;

import java.awt.Color;
import javax.swing.ImageIcon;

public class ACheckBox extends javax.swing.JPanel {

    
    private boolean ativado=false;
    private String iconName;
    private String fieldName;
    private ClickListener click;
    
    public ACheckBox() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        pnl = new javax.swing.JPanel();
        lbl = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setOpaque(false);

        label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label.setText("Question ?");

        icon.setBackground(new java.awt.Color(252, 252, 252));
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pedroaugusto/mycomponents/default.png"))); // NOI18N
        icon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 143, 255)), javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        icon.setOpaque(true);

        pnl.setBackground(new java.awt.Color(59, 59, 59));

        lbl.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbl.setForeground(new java.awt.Color(255, 255, 255));
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl.setText("Não");
        lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlLayout = new javax.swing.GroupLayout(pnl);
        pnl.setLayout(pnlLayout);
        pnlLayout.setHorizontalGroup(
            pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLayout.setVerticalGroup(
            pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(icon)
                .addGap(0, 0, 0)
                .addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMouseClicked
        ativado = !ativado;
        if(ativado){
            pnl.setBackground(new Color(102,102,255));
            lbl.setText("Sim");
        }else{
            pnl.setBackground(new Color(59,59,59));
            lbl.setText("Não");
        }
        /* DOT NOT FUCK SHIT UP, PLEASE! */
        if(click != null)
            click.action();
    }//GEN-LAST:event_lblMouseClicked

    public boolean isAtivado()
    {
        return ativado;
    }

    public void setAtivado(boolean ativado)
    {
        this.ativado = ativado;
        if(ativado){
            pnl.setBackground(new Color(102,102,255));
            lbl.setText("Sim");
        }else{
            pnl.setBackground(new Color(59,59,59));
            lbl.setText("Não");
        }
    }
    public void setAtivado(boolean ativado, String who)
    {
        setAtivado(ativado);
        if(click != null)
            click.action();
    }
    public void setIconName(String iconName)
    {
        this.iconName = iconName;
        this.icon.setIcon(new ImageIcon(getClass().getResource("/ICONS/"+iconName)));
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
        this.label.setText(this.fieldName);
    }
    public void addClickListener(ClickListener click)
    {
        this.click = click;
    }
        /*
    ativado = !ativado;
        if(ativado){
            jButton1.setBackground(new Color(99,232,72));
            jButton1.setText("Sim");
        }else{
            jButton1.setBackground(new Color(255,51,51));
            jButton1.setText("Não");
        }
        click.action();
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lbl;
    private javax.swing.JPanel pnl;
    // End of variables declaration//GEN-END:variables
}
