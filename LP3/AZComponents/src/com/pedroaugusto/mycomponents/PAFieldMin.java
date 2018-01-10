/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pedroaugusto.mycomponents;

import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Pedro
 */
public class PAFieldMin extends javax.swing.JPanel {

    /**
     * Creates new form PAField
     */
    private String fieldName;
    private String iconName;
    private Placeholder placeholder;
    private boolean onlyNumbers = false;
    public PAFieldMin()
    {
        initComponents();
        placeholder = new Placeholder(field, "null", false);
    }
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
        this.label.setText(this.fieldName);
    }
    
    public void setPlaceholder(String text)
    {
        placeholder.turnOn(text);
    }
    
    public String getText()
    {
        return field.getText();
    }
    public void setText(String t)
    {
        field.setForeground(Color.BLACK);
        field.setFont(new java.awt.Font("Segoe UI", 0, 14));
        field.setText(t);
        if(t != null && t.equals(""))
        {
            placeholder.focusLost(null);
            emptyEffect(false);
        }
    }
    
    public boolean isOnlyNumbers()
    {
        return onlyNumbers;
    }

    public void setOnlyNumbers(boolean onlyNumbers)
    {
        this.onlyNumbers = onlyNumbers;
        placeholder.setOnlyNumbers(onlyNumbers);
    }
    
    public void setIcon(String iconName)
    {
        this.iconName = iconName;
        this.icon.setIcon(new ImageIcon(getClass().getResource("/ICONS/"+iconName)));
    }
    
    public boolean isEmpty()
    {
        if(field.getText().isEmpty() || placeholder.isEmpty())
        {
            emptyEffect(true);
            field.requestFocusInWindow();
            placeholder.focusGained(null);
            return true;
        }
        else
        {
            emptyEffect(false);
            return false;
        }
    }
    
    //Métodos para checar se o campo esta vazio
    private void emptyEffect(boolean b)
    {
        if(b)
        {
            icon.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(
                            1, 1, 1, 0, new Color(255, 51, 51)), 
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(
                            1, 0, 1, 1, new Color(255, 51, 51)), 
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            labelEmptyMessage.setSize(161, 18);
            labelEmptyMessage.setText("preencha este campo");
        }
        else
        {   
            icon.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(
                            1, 1, 1, 0, new Color(153, 153, 255)), 
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(
                            1, 0, 1, 1, new Color(153, 153, 255)), 
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            labelEmptyMessage.setSize(0, 0);
            labelEmptyMessage.setText("");
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        field = new javax.swing.JTextField();
        labelEmptyMessage = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(236, 62));
        setLayout(null);

        label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label.setText("Somebody");
        add(label);
        label.setBounds(0, 0, 100, 25);

        icon.setBackground(new java.awt.Color(252, 252, 252));
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pedroaugusto/mycomponents/default.png"))); // NOI18N
        icon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 143, 255)), javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        icon.setOpaque(true);
        add(icon);
        icon.setBounds(0, 32, 29, 30);

        field.setBackground(new java.awt.Color(252, 252, 252));
        field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 143, 255)), javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        add(field);
        field.setBounds(29, 32, 227, 30);

        labelEmptyMessage.setBackground(new java.awt.Color(255, 51, 51));
        labelEmptyMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelEmptyMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEmptyMessage.setText("preencha este campo");
        labelEmptyMessage.setOpaque(true);
        add(labelEmptyMessage);
        labelEmptyMessage.setBounds(95, 15, 160, 20);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField field;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel label;
    private javax.swing.JLabel labelEmptyMessage;
    // End of variables declaration//GEN-END:variables

}
