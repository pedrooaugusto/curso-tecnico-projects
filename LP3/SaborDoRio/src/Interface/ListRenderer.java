/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Pedro
 */
class ListRenderer extends JLabel implements ListCellRenderer{

    public ListRenderer()
    {
        setOpaque(true);
        setBackground(new Color(244,244,244));
        setFont(new java.awt.Font("Segoe UI", 0, 16));
        setBorder(BorderFactory.createEmptyBorder(3,3, 3, 3));
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, 
            int index, boolean isSelected, boolean cellHasFocus)
    {
        String data[] = value.toString().replaceAll("[\\]|\\[]", "").split(",");
        if(isSelected || cellHasFocus){
            setBackground(new Color(104, 104, 255));
            setForeground(Color.WHITE);
            setFont(new java.awt.Font("Segoe UI", 1, 16));
            setText(data[0]+" - "+data[1].replaceAll("[.]", ","));
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/white_arrow.png")));
        }
        else{
            setBackground(new Color(244,244,244));
            setForeground(Color.BLACK);
            setFont(new java.awt.Font("Segoe UI", 0, 16));
            setText(data[0]);
            setIcon(null);
        }
        return this;
    }
}
