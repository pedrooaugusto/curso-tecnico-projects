/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pedroaugusto.mycomponents;

import com.pedroaugusto.transicoes.ControleTransicoes;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
/**
 *
 * @author usuario
 */
public class THDialog extends javax.swing.JDialog {

    private Object selectedElement = "";
    final private THComboBox label;
    private List<Object> elementos = new ArrayList<Object>();
    private List<Object> keys = new ArrayList<Object>();
    private DefaultListModel modeloLista;
    private ControleTransicoes cT = new ControleTransicoes(null);
    
    public THDialog(java.awt.Frame parent, THComboBox label, boolean modal, int x, int y) 
    {
        super(parent, modal);
        initComponents();
        this.label = label;
        this.jList1.setModel(new DefaultListModel());
        this.modeloLista = (DefaultListModel) jList1.getModel();
        this.jLabel1.setText(label.getPlaceholderText());
    }
    
    public void setLabelText(String text)
    {
       jLabel1.setText(text);
    }
    
    public void setSelectedItem(Object o) throws Exception
    {
        if(elementos.contains(o))
            jList1.setSelectedValue(o, true);
        else
            throw new Exception("Não existe esse elemento na lista");
    }
    
    public void setSelectedIndex(int i) throws Exception
    {
        if(elementos.size() - 1 > i)
            jList1.setSelectedIndex(i);
        else
            throw new Exception("O tamanho da lista é menor que o index"); 
    }
    
    public Object getSelectedKey()
    {
        return keys.get(getSelectedIndex());
    }
    
    public Object getSelectedItem()
    {    
        return selectedElement;
    }
    
    public int getSelectedIndex()
    {
        return jList1.getSelectedIndex();
    }
    
    public void addItem(Object item)
    {
        elementos.add(item);
        modeloLista.addElement(item);
    }
    
    public void addItem(Object item, Object key)
    {
        keys.add(key);
        addItem(item);
    }
    
    public void mostrar()
    {
        if(getSelectedIndex() < 0)
            jList1.setSelectedIndex(0);
        else
            jList1.ensureIndexIsVisible(getSelectedIndex());
        cT.abreJanela(this, 377);
    }
    
    public void limparLista()
    {
        modeloLista.clear();
        if(label.hasKeys())
            keys.clear();
        elementos.clear();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener()
        {
            public void windowGainedFocus(java.awt.event.WindowEvent evt)
            {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt)
            {
                formWindowLostFocus(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(42, 42, 42));
        jPanel1.setForeground(new java.awt.Color(232, 232, 232));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jList1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5hi", "huhuiui", "hjihji", "ihj", "iji", "jij", "i", "ji" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setCellRenderer(new ComboboxRenderer());
        jList1.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jList1MouseClicked(evt);
            }
        });
        jList1.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jList1KeyPressed(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TODO add your handling code here:
        if(!elementos.isEmpty())
        {
            label.setText((String) selectedElement);
            if(label.hasKeys())
                label.setKey((Object)keys.get(getSelectedIndex()));
        }
        else
        {
            label.setPlaceholder((String) jLabel1.getText());
            if(label.hasKeys())
                label.setKey((Object)0);
        }
        if(label.getActionListener() != null)
            label.getActionListener().action();
        cT.fechaJanela(this, true);
    }//GEN-LAST:event_formWindowLostFocus

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        selectedElement = (String) jList1.getSelectedValue();
    }//GEN-LAST:event_jList1ValueChanged

    private void jList1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jList1KeyPressed
    {//GEN-HEADEREND:event_jList1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            formWindowLostFocus(null);
    }//GEN-LAST:event_jList1KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jList1MouseClicked
    {//GEN-HEADEREND:event_jList1MouseClicked
        // TODO add your handling code here:
        formWindowLostFocus(null);
    }//GEN-LAST:event_jList1MouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowGainedFocus
    {//GEN-HEADEREND:event_formWindowGainedFocus
        // TODO add your handling code here:
        //evt.getWindow().setFocusableWindowState(false);
    }//GEN-LAST:event_formWindowGainedFocus


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
