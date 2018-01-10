/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Pedro
 */
public class CustomCelulaJTable extends DefaultTableCellRenderer
{
    int tema;
    Color corLetra, corFundo;
    String font;
    Border borda;
    int tamanhoLetra;
    public CustomCelulaJTable(int tema)
    {
        this.tema = tema;
        if(tema == 1)
        {
            corFundo = new Color(255,255,255);
            corLetra = Color.BLACK;
            font = "Segoe UI Light";
            tamanhoLetra = 16;
            borda = BorderFactory.createEmptyBorder(3, 3, 3, 3);
        }
        else
        {
            corFundo = Color.WHITE;
            corLetra = Color.BLACK;
            font = "Segoe UI Light";
            tamanhoLetra = 16;
            borda = BorderFactory.createEmptyBorder(3, 3, 3, 3);;
        }
    }
    @Override
    protected void setValue(Object value)
    {
        setOpaque(true);
        setBackground(corFundo);
        setForeground(corLetra);
        setFont(new java.awt.Font(font, 0, tamanhoLetra));
        setPreferredSize(new Dimension(50, 50));
        setBorder(borda);
        super.setValue(value);
    }
    public CustomIconRenderer getIconRenderer(String icon)
    {
        return new CustomIconRenderer(icon);
    }
    public class CustomIconRenderer extends DefaultTableCellRenderer
    {
        String iconName;
        public CustomIconRenderer(String iconName)
        {
            this.iconName = iconName;
        }
        @Override
        protected void setValue(Object value)
        {
            setHorizontalAlignment(JLabel.CENTER);
            if(!value.toString().equals("false")){
                if(iconName.equals("edit")){
                    setToolTipText("editar esta ocorrência");
                }
                else if(iconName.equals("delete")){
                    setToolTipText("deletar esta ocorrência");
                    setBackground(new Color(251, 142, 142));
                    setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/"+iconName+".png")));
            }
            else
                super.setValue("");
        }
    }
    public class CustomLabelRenderer extends DefaultTableCellRenderer
    {
        int aligment;
        public CustomLabelRenderer(int aligment)
        {
            this.aligment = aligment;
        }
        @Override
        protected void setValue(Object value)
        {
            setOpaque(true);
            setBackground(corFundo);
            setForeground(corLetra);
            setFont(new java.awt.Font(font, 0, tamanhoLetra));
            setHorizontalAlignment(aligment);
            setPreferredSize(new Dimension(50, 50));
            setBorder(borda);
            setHorizontalAlignment(aligment);
            super.setValue(value);
        }
    }
}