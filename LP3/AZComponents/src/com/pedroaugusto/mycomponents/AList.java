/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pedroaugusto.mycomponents;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Pedro
 */
public class AList extends javax.swing.JPanel {

    
    private Component nextToGetFocus;
    private List<String[]> resultado = new ArrayList<String[]>();
    private DataProvider pai;
    public AList()
    {
        initComponents();
        list.setFixedCellHeight(30);
        list.setCellRenderer(new ListRenderer());
    }
    public void setNextToGetFocus(Component a)
    {
        nextToGetFocus = a;
    }

    public DataProvider getPai()
    {
        return pai;
    }

    public void setPai(DataProvider pai)
    {
        this.pai = pai;
    }
    public String[] getSelectedValue()
    {
        if(list.getSelectedIndex() >= 0 && !list.getSelectedValue()[1].equals("-42"))
            return list.getSelectedValue();
        else
            return null;
    }
    public void setSelectedValue(String id)
    {
        pai.showAll();
        DefaultListModel listaModelo = (DefaultListModel) list.getModel();
        listaModelo.elements().hasMoreElements();
        Enumeration k = listaModelo.elements();
        while(k.hasMoreElements())
        {
            String a[] = (String[]) k.nextElement();
            if(a[2].equals(id))
            {
                list.setSelectedValue(a, true);
                java.awt.event.KeyEvent et = new java.awt.event.KeyEvent(this, 0, 0, 0, 10);
                fieldKeyReleased(et);
                break;
            }
        }
    }
    public void clearComponent()
    {
        field.setText("");
        list.clearSelection();
    }
    public void addElement(Object[]... a)
    {
        DefaultListModel listaModelo = (DefaultListModel) list.getModel();
        for(Object b[] : a)
            listaModelo.addElement(b);
    }
    public void limparLista()
    {
        DefaultListModel listaModelo = (DefaultListModel) list.getModel();
        listaModelo.removeAllElements();
    }
    private boolean teclaControle(int tecla)
    {
        boolean teclaControle = true;
        int index = list.getSelectedIndex();
        int tamanho = list.getModel().getSize() - 1;    
        switch(tecla)
        {
            case KeyEvent.VK_UP:
                index-=1;
                if(index < 0)
                    index = 0;
            break;
            case KeyEvent.VK_DOWN:
                index+=1;
                if(index > tamanho)
                    index = tamanho;
            break;
            default:
                teclaControle = false;
        }
        if(teclaControle && !list.getModel().getElementAt(0)[1].contains("#PPAP"))
        {
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
        return teclaControle;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        field = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<String[]>();

        field.setBackground(new java.awt.Color(249, 249, 249));
        field.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        field.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(156, 156, 255)), javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        field.setMargin(new java.awt.Insets(0, 3, 0, 3));
        field.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fieldActionPerformed(evt);
            }
        });
        field.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                fieldKeyReleased(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(156, 156, 255)), javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2)));

        list.setBackground(new java.awt.Color(244, 244, 244));
        list.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        list.setModel(new DefaultListModel());
        list.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                listMouseClicked(evt);
            }
        });
        list.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                listKeyReleased(evt);
            }
        });
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                listValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(field, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(field, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fieldKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_fieldKeyReleased
    {//GEN-HEADEREND:event_fieldKeyReleased
        // TODO add your handling code here:
        DefaultListModel listaModelo = (DefaultListModel) list.getModel();
        String query = field.getText();
        if(!teclaControle(evt.getKeyCode()))
        {
            listaModelo.removeAllElements();
            if (!query.isEmpty())
            {
                resultado = pai.pesquisa(query);
                if (resultado.isEmpty())
                    if(!query.toLowerCase().contains("doctor"))
                        listaModelo.addElement(new String[]{"'" + query.toUpperCase() + "' não encontrado",
                            "-42"});
                    else
                        listaModelo.addElement(new String[]{"Wibbly Wobbly Timey Wimey... stuff", "-42"});
                else
                    for(String nome[] : resultado)
                        listaModelo.addElement(nome);
            }
            else
            {
                pai.showAll();
            }
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                list.setSelectedIndex(0);
                nextToGetFocus.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_fieldKeyReleased

    private void fieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_fieldActionPerformed
    {//GEN-HEADEREND:event_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldActionPerformed

    private void listMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_listMouseClicked
    {//GEN-HEADEREND:event_listMouseClicked
        // TODO add your handling code here:
        if (list.getSelectedIndex() >= 0)
        {
            if(!list.getSelectedValue()[1].equals("-42"))
            {
                listValueChanged(null);
                java.awt.event.KeyEvent et = new java.awt.event.KeyEvent(evt.getComponent(), 0, 0, 0, 10);
                fieldKeyReleased(et);
            }
        }
    }//GEN-LAST:event_listMouseClicked

    private void listKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_listKeyReleased
    {//GEN-HEADEREND:event_listKeyReleased
        if(KeyEvent.VK_ENTER == evt.getKeyCode())
        {
            fieldKeyReleased(evt);
            list.setSelectedIndex(0);
        }
    }//GEN-LAST:event_listKeyReleased

    private void listValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_listValueChanged
    {//GEN-HEADEREND:event_listValueChanged
        // TODO add your handling code here:
        if (list.getSelectedIndex() >= 0)
        {
            if(!list.getSelectedValue()[1].equals("-42"))
            {
                String a = list.getSelectedValue()[0];
                field.setText(a);
            }
        }
    }//GEN-LAST:event_listValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField field;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String[]> list;
    // End of variables declaration//GEN-END:variables
}
