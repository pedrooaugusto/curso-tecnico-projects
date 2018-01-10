/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pedroaugusto.mycomponents;

import java.awt.Color;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Pedro
 */
public class THComboBox extends javax.swing.JPanel {

    /**
     * Creates new form PAField
     */
    private String fieldName;
    private String iconName;
    private String placeholderText = "";
    private boolean keys = false;
    private String model = "";
    private ClickListener click = null;
    final private THDialog dialog;
    public THComboBox()
    {
        initComponents();
        this.dialog = new THDialog((JFrame) getParent(), this, false, 0, 0);
        showKeys(keys);
        setPlaceholder("Best Harry Potter Books");
        setModel("[the Cursed Child, the Philosophers Stone, the Goblet of Fire, "
                + "the Chamber of Secrets, the Half-Blood Prince]");
    }
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
        this.label.setText(this.fieldName);
    }
    public void setPlaceholder(String text)
    {
        placeholderText = text;
        dialog.setLabelText(text);
        writePlaceholder();
    }
    public String getPlaceholderText()
    {
        return placeholderText;
    }
    public void addActionListener(ClickListener click)
    {
        this.click = click;
    }
    public ClickListener getActionListener()
    {
        return click;
    }
    public void setIcon(String iconName)
    {
        this.iconName = iconName;
        this.icon.setIcon(new ImageIcon(getClass().getResource("/ICONS/"+iconName)));
    }
    public boolean isEmpty()
    {
        if(field.getText().equals(placeholderText) || field.getText().equals(""))
        {
            emptyEffect(true);
            return true;
        }
        else
        {
            emptyEffect(false);
            return false;
        }
    }
    public void setText(String text)
    {
        writeText(text);
    }
    private void writePlaceholder()
    {
        field.setText(placeholderText);
        field.setForeground(new Color(102, 102, 102));
        field.setFont(new java.awt.Font("Segoe UI", 2, 14));
    }
    private void writeText(String text)
    {
        field.setText(text);
        field.setForeground(Color.BLACK);
        field.setFont(new java.awt.Font("Segoe UI", 0, 14));
    }
    public void addItem(Object obj)
    {
        if(keys)
            try 
            {
                throw new Exception("Voce esta usando o metodo"
                        + " addItem(Object obj, Object key)");
            } catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        else    
            dialog.addItem(obj);
    }
    
    public void setModel(String items)
    {
        limparLista();
        model = items;
        items = items.replaceAll("[\\[|\\]]", "").replaceAll(", ", ",");
        Vector<String> c = new Vector<String>(Arrays.asList(items.split("[,]")));
        for(String f : c)
            addItem(f);
    }
    
    public void addItem(Object obj, Object key)
    {
        dialog.addItem(obj, key);
        if(!keys)
        {
            keys = true;
            showKeys(keys);
        }
    }
    public Object getSelectedItem()
    {
        return dialog.getSelectedItem();
    }
    public int getSelectedIndex()
    {
        return dialog.getSelectedIndex();
    }
    public void setSelectedItem(Object obj)
    {
        try
        {
            dialog.setSelectedItem(obj);
            setText((String) dialog.getSelectedItem());
            if(hasKeys())
                setKey(dialog.getSelectedKey());
            if(click != null)
                click.action();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void setSelectedIndex(int i)
    {
        try
        {
            if(i < 0)
            {
                writePlaceholder();
                dialog.setSelectedIndex(i);
                setKey("");
                emptyEffect(false);
                if(click != null)
                    click.action();
            }
            else
            {
                dialog.setSelectedIndex(i);
                setText((String) dialog.getSelectedItem());
                if(hasKeys())
                    setKey(dialog.getSelectedKey());
                if(click != null)
                    click.action();
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void limparLista()
    {
        dialog.limparLista();
        setPlaceholder(placeholderText);
        if(keys)
            keyLabel.setText("");
    }
    public void setKey(Object key)
    {
        keyLabel.setText(key.toString());
    }
    public boolean hasKeys()
    {
        return keys;
    }
    //Metodos para checar se o campo esta preenchido
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
                            1, 0, 1, 0, new Color(255, 51, 51)), 
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            labelEmptyMessage.setText("preencha este campo");
            
            keyLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                            1, 0, 1, 1, new Color(255, 51, 51)), 
                            BorderFactory.createMatteBorder(0, 0, 0, 0,
                                new Color(232, 232, 232))),
                        BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        }
        else
        {   
            icon.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(
                            1, 1, 1, 0, new Color(153, 153, 255)), 
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(
                            1, 0, 1, 0, new Color(153, 153, 255)), 
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            
            keyLabel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                            1, 0, 1, 1, new Color(153, 153, 255)), 
                            BorderFactory.createMatteBorder(0, 0, 0, 0,
                                new Color(232, 232, 232))),
                        BorderFactory.createEmptyBorder(1, 1, 1, 1)));

            labelEmptyMessage.setText("");
        }
    }
    private void showKeys(boolean a)
    {
        if(!a)
        {
            keyLabel.setText("");
            keyLabel.setBackground(new Color(252,252,252));
        }
        else
        {
            keyLabel.setBackground(new Color(237,237,237));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        label = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        field = new javax.swing.JTextField();
        labelEmptyMessage = new javax.swing.JLabel();
        keyLabel = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(299, 61));

        label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label.setText("KKKKKKKKKKKK");

        icon.setBackground(new java.awt.Color(252, 252, 252));
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pedroaugusto/mycomponents/default.png"))); // NOI18N
        icon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 143, 255)), javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        icon.setOpaque(true);

        field.setEditable(false);
        field.setBackground(new java.awt.Color(252, 252, 252));
        field.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 143, 255)), javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 1)));
        field.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                fieldMouseClicked(evt);
            }
        });
        field.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fieldActionPerformed(evt);
            }
        });

        labelEmptyMessage.setBackground(new java.awt.Color(255, 51, 51));
        labelEmptyMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelEmptyMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEmptyMessage.setOpaque(true);
        labelEmptyMessage.setPreferredSize(new java.awt.Dimension(160, 0));

        keyLabel.setBackground(new java.awt.Color(237, 237, 237));
        keyLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        keyLabel.setForeground(new java.awt.Color(0, 0, 0));
        keyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        keyLabel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 255)), javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(232, 232, 232))), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        keyLabel.setOpaque(true);
        keyLabel.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                keyLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelEmptyMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(icon)
                        .addGap(0, 0, 0)
                        .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(keyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(labelEmptyMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(keyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(field))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fieldMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_fieldMouseClicked
    {//GEN-HEADEREND:event_fieldMouseClicked
        // TODO add your handling code here:
        dialog.mostrar();
    }//GEN-LAST:event_fieldMouseClicked

    private void keyLabelMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_keyLabelMouseClicked
    {//GEN-HEADEREND:event_keyLabelMouseClicked
        // TODO add your handling code here
        fieldMouseClicked(evt);
    }//GEN-LAST:event_keyLabelMouseClicked

    private void fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField field;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel keyLabel;
    private javax.swing.JLabel label;
    private javax.swing.JLabel labelEmptyMessage;
    // End of variables declaration//GEN-END:variables

}
