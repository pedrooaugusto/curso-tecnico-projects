/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface.CustomizarTabela;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Pedro
 */
public class CustomCabecalhoJTable extends JLabel implements TableCellRenderer
{
    public CustomCabecalhoJTable(int tema) 
    {
        Color corLetra, corFundo;
        String font;
        Border borda;
        int tamanhoLetra;
        if(tema == 1)
        {
            corFundo = new Color(102,102,255);
            corLetra = Color.WHITE;
            font = "Segoe UI Light";
            tamanhoLetra = 14;
            borda = BorderFactory.createEtchedBorder();
        }
        else
        {
            corFundo = Color.BLACK;
            corLetra = Color.GREEN;
            font = "Serif";
            tamanhoLetra = 14;
            borda = BorderFactory.createLineBorder(Color.WHITE);
        }
        setOpaque(true);
        setBackground(corFundo);
        setForeground(corLetra);
        setFont(new java.awt.Font(font, 1, tamanhoLetra));
        setHorizontalAlignment(JLabel.CENTER);
        setPreferredSize(new Dimension(25, 25));
        setBorder(borda);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) 
    {
        setText(value.toString());
        return this;
    }
}
