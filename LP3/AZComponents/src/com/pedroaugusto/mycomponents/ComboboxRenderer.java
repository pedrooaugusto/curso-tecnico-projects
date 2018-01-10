package com.pedroaugusto.mycomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboboxRenderer extends JLabel implements ListCellRenderer{

    public ComboboxRenderer()
    {
        setFont(new java.awt.Font("Segoe UI", 0, 15));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3,3 ));
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        setText(value.toString());
        if(isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
    
}
