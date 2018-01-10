package com.pedroaugusto.mycomponents;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

public class Placeholder implements FocusListener, MouseListener, KeyListener{

    private String placeholderText;
    private boolean ativado;
    private final JTextField tf;
    private boolean onlyNumbers = false;
    private ClickListener executeAfter = null;
    public Placeholder(JTextField tf, String placeholder, boolean ativado)
    {
        this.placeholderText = placeholder;
        this.ativado = ativado;
        this.tf = tf;
        tf.addFocusListener(this);
        tf.addKeyListener(this);
        tf.addMouseListener(this);
    }

    public void setExecuteAfter(ClickListener executeAfter)
    {
        this.executeAfter = executeAfter;
    }

    public boolean isOnlyNumbers()
    {
        return onlyNumbers;
    }

    public void setOnlyNumbers(boolean onlyNumbers)
    {
        this.onlyNumbers = onlyNumbers;
    }

    public String getPlaceholder()
    {
        return placeholderText;
    }
    public void turnOn(String text)
    {
        this.placeholderText = text;
        this.ativado = true;
        keyReleased(null);
    }
    public boolean isEmpty()
    {
        return tf.getText().equals(placeholderText);
    }
    @Override
    public void focusGained(FocusEvent e)
    {
        if(ativado)
            if(tf.getText().equals(placeholderText))
                tf.setCaretPosition(0);
    }
    @Override
    public void focusLost(FocusEvent e)
    {
        if(ativado && tf.getText().isEmpty())
        {
            tf.setText(placeholderText);
            tf.setForeground(new Color(102, 102, 102));
            tf.setFont(new java.awt.Font("Segoe UI", 2, 14));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(ativado)
            if(tf.getText().equals(placeholderText))
                tf.setCaretPosition(0);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        
    }

    @Override
    public void keyTyped(KeyEvent evt)
    {
        if(onlyNumbers)
        {
            tf.setText(tf.getText().replace(',', '.'));
            String caractere = Character.toString(evt.getKeyChar());

            if (caractere.matches("[0-9]|[,]|[.]"))
            {
                if ((caractere.equals(".") || caractere.equals(",")) && tf.getText().isEmpty())
                {
                    evt.consume();
                }
                for (int i = 0; i < tf.getText().length(); i++)
                {
                    if (tf.getText().charAt(i) == '.')
                    {
                        if (caractere.equals(",") || caractere.equals("."))
                        {
                            evt.consume();
                        } else
                        {
                            int indexDoPonto = tf.getText().indexOf(".");
                            if (tf.getText().length() - indexDoPonto > 2)
                            {
                                evt.consume();
                            }
                        }
                    }
                }
            } else
            {
                evt.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent evt)
    {
        if(ativado)
        {
            if(tf.getText().equals(placeholderText))
            {
                if(!evt.isActionKey() && !evt.isAltDown() &&
                        !evt.isMetaDown() && !evt.isControlDown()){
                    if(!(evt.isShiftDown() && evt.getKeyCode() == 16))
                    {
                        tf.setText("");
                        tf.setForeground(Color.BLACK);
                        tf.setFont(new java.awt.Font("Segoe UI", 0, 14));
                    }
                }
                else
                {
                    tf.setText(placeholderText);
                    tf.setCaretPosition(0);
                    evt.consume();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(ativado)
        {
            if(tf.getText().contains(placeholderText))
            {
                tf.setText(placeholderText);
                tf.setCaretPosition(0);
            }
            if(tf.getText().isEmpty())
            {
                tf.setText(placeholderText);
                tf.setForeground(new Color(102, 102, 102));
                tf.setFont(new java.awt.Font("Segoe UI", 2, 14));
                tf.setCaretPosition(0);
            }
        }        
        if(executeAfter != null)
            executeAfter.action();
    }
    
}
