/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface.GerenteView;

/**
 *
 * @author Pedro
 */
import Negocio.*;
import Persistencia.*;
import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
public class JFrmIngrediente extends javax.swing.JFrame
{

    /**
     * Creates new form Ingrediente
     */
    public JFrmIngrediente()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTxtID = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTxtNome = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jCmbCategoria = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jCmbUnidadeMedida = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jCmbLotes = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jLblTitulo = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Logo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jBtnLotes = new javax.swing.JButton();
        jBtnEstoque = new javax.swing.JButton();
        jPnlBotoes = new javax.swing.JPanel();
        jBtnIncluir = new javax.swing.JButton();
        jBtnAlterar = new javax.swing.JButton();
        jLblVoltar = new javax.swing.JLabel();
        jBtnExcluir = new javax.swing.JButton();
        jLblLimpar = new javax.swing.JLabel();
        jTxtPrecoUni = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ingrediente");
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowActivated(java.awt.event.WindowEvent evt)
            {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt)
            {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(22, 73, 154));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(255, 255, 0), new java.awt.Color(255, 153, 51)));

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID:");

        jTxtID.setBackground(new java.awt.Color(102, 102, 255));
        jTxtID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTxtID.setForeground(new java.awt.Color(255, 255, 255));
        jTxtID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 2));
        jTxtID.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                jTxtIDFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nome:");

        jTxtNome.setBackground(new java.awt.Color(102, 102, 255));
        jTxtNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTxtNome.setForeground(new java.awt.Color(255, 255, 255));
        jTxtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 2));

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Categoria:");

        jCmbCategoria.setBackground(new java.awt.Color(102, 102, 255));
        jCmbCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCmbCategoria.setForeground(new java.awt.Color(255, 255, 255));
        jCmbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tempero", "Carne", "Massa", "Molho", "Outros" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Unidade Medida:");

        jCmbUnidadeMedida.setBackground(new java.awt.Color(102, 102, 255));
        jCmbUnidadeMedida.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCmbUnidadeMedida.setForeground(new java.awt.Color(255, 255, 255));
        jCmbUnidadeMedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "KG", "L", "UNI" }));

        jLabel18.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Preço Unitário:");

        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Lotes:");

        jCmbLotes.setBackground(new java.awt.Color(102, 102, 255));
        jCmbLotes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCmbLotes.setForeground(new java.awt.Color(255, 255, 255));
        jCmbLotes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ainda não possui lotes." }));

        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Estoque:");

        jLblTitulo.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLblTitulo.setText("Ingrediente");

        Logo.setBackground(new java.awt.Color(22, 73, 154));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/cr2.png"))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("August");

        javax.swing.GroupLayout LogoLayout = new javax.swing.GroupLayout(Logo);
        Logo.setLayout(LogoLayout);
        LogoLayout.setHorizontalGroup(
            LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LogoLayout.createSequentialGroup()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        LogoLayout.setVerticalGroup(
            LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jBtnLotes.setBackground(new java.awt.Color(105, 105, 255));
        jBtnLotes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnLotes.setForeground(new java.awt.Color(255, 255, 255));
        jBtnLotes.setText("Ver");
        jBtnLotes.setToolTipText("Ver o lote ao lado");
        jBtnLotes.setBorderPainted(false);
        jBtnLotes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnLotes.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnLotesActionPerformed(evt);
            }
        });

        jBtnEstoque.setBackground(new java.awt.Color(105, 105, 255));
        jBtnEstoque.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnEstoque.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEstoque.setText("Consultar");
        jBtnEstoque.setToolTipText("Consultar o estoque");
        jBtnEstoque.setBorderPainted(false);
        jBtnEstoque.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEstoque.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnEstoqueActionPerformed(evt);
            }
        });

        jPnlBotoes.setBackground(new java.awt.Color(22, 73, 154));

        jBtnIncluir.setBackground(new java.awt.Color(102, 102, 255));
        jBtnIncluir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnIncluir.setForeground(new java.awt.Color(255, 255, 255));
        jBtnIncluir.setText("Incluir");
        jBtnIncluir.setBorderPainted(false);
        jBtnIncluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnIncluir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnIncluirActionPerformed(evt);
            }
        });

        jBtnAlterar.setBackground(new java.awt.Color(102, 102, 255));
        jBtnAlterar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        jBtnAlterar.setText("Alterar");
        jBtnAlterar.setBorderPainted(false);
        jBtnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnAlterarActionPerformed(evt);
            }
        });

        jLblVoltar.setBackground(new java.awt.Color(204, 0, 0));
        jLblVoltar.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLblVoltar.setForeground(new java.awt.Color(255, 255, 255));
        jLblVoltar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblVoltar.setText("←");
        jLblVoltar.setToolTipText("Voltar");
        jLblVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblVoltar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jLblVoltarMouseClicked(evt);
            }
        });

        jBtnExcluir.setBackground(new java.awt.Color(102, 102, 255));
        jBtnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnExcluir.setForeground(new java.awt.Color(255, 255, 255));
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.setBorderPainted(false);
        jBtnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnExcluirActionPerformed(evt);
            }
        });

        jLblLimpar.setBackground(new java.awt.Color(204, 0, 0));
        jLblLimpar.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLblLimpar.setForeground(new java.awt.Color(255, 255, 255));
        jLblLimpar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLblLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/limpar2.png"))); // NOI18N
        jLblLimpar.setToolTipText("Limpar");
        jLblLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblLimpar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jLblLimparMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPnlBotoesLayout = new javax.swing.GroupLayout(jPnlBotoes);
        jPnlBotoes.setLayout(jPnlBotoesLayout);
        jPnlBotoesLayout.setHorizontalGroup(
            jPnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlBotoesLayout.createSequentialGroup()
                .addComponent(jLblVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jBtnIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLblLimpar))
        );
        jPnlBotoesLayout.setVerticalGroup(
            jPnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPnlBotoesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPnlBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblVoltar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblLimpar, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jTxtPrecoUni.setBackground(new java.awt.Color(102, 102, 255));
        jTxtPrecoUni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTxtPrecoUni.setForeground(new java.awt.Color(255, 255, 255));
        jTxtPrecoUni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jCmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jCmbUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtPrecoUni, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jCmbLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPnlBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTxtID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jCmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jCmbUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTxtPrecoUni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCmbLotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnLotes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    //Persistencia
        DaoIngrediente dI = new DaoIngrediente();
        DaoLote dL = new DaoLote();
    //Negocio 
        List<Object> lista = new ArrayList<>();
    //Interface
        
    //Variaveis de Controle
        public Estoque estoque = null;//Para tela de estoque
        final String titulo = "August";
        boolean novoIngrediente = false;//(Obsoleto...)
    
    public Estoque getEstoque()//Estoque que é informado em outra tela
    {
        return estoque;
    }
    public void setEstoque(Estoque e)//Leia acima
    {
        this.estoque = e;
    }
    public void mouseOff(Component comp)/*
                                            No começo ao passar o mouse sobre um botao 
                                            ele brilhava, mas agora se tornou obsoleto
                                        */
    {
        JLabel bot = (JLabel) comp;
        bot.setBorder(null);
    }
    public void mouseOn(Component comp)//Leia acima
    {
        JLabel bot = (JLabel) comp;
        bot.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white, 1));
    }
    
    public void carregarObjeto(Ingrediente i)
    {
        jTxtID.setText(String.valueOf(i.getID()));
        jTxtNome.setText(i.getNome());
        jCmbCategoria.setSelectedItem(i.getCategoria());
        jCmbUnidadeMedida.setSelectedItem(i.getUnidadeMedida());
        setEstoque(i.getEstoque());
        for(int ind = 0; ind < i.getLotes().size(); ind++)
        {
            jCmbLotes.addItem(i.getLote(ind).getNumero());
            lista.add(i.getLote(ind));
        }
        jTxtPrecoUni.setText(NumberFormat.getCurrencyInstance().format(i.getPrecoUnitario()));
    }
    public void limpar()
    {
        jTxtID.setText("");
        jTxtID.setEditable(true);
        jTxtNome.setText("");
        jCmbCategoria.setSelectedIndex(-1);
        jCmbUnidadeMedida.setSelectedIndex(-1);
        jCmbLotes.removeAllItems();
        jCmbLotes.setSelectedIndex(-1);
        jTxtPrecoUni.setText("");
        jTxtID.requestFocusInWindow();
        jBtnIncluir.setEnabled(false);
        jBtnAlterar.setEnabled(false);
        jBtnExcluir.setEnabled(false);
        jBtnLotes.setEnabled(false);
        lista.clear();
        estoque = null;
    }
    private void jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnIncluirActionPerformed
    {//GEN-HEADEREND:event_jBtnIncluirActionPerformed
        // TODO add your handling code here:
        Ingrediente i;
        int id;
        if(!jTxtID.getText().isEmpty()
            && jCmbCategoria.getSelectedIndex() > -1
            && jCmbUnidadeMedida.getSelectedIndex() > -1
            && !jTxtNome.getText().isEmpty()
            && !jTxtPrecoUni.getText().isEmpty()
            && getEstoque() != null
        )
        {
            i = new Ingrediente();
            i.setID(Integer.parseInt(jTxtID.getText()));
            i.setNome(jTxtNome.getText());
            i.setCategoria(jCmbCategoria.getSelectedItem().toString());
            i.setUnidadeMedida(jCmbUnidadeMedida.getSelectedItem().toString());
            String preco = jTxtPrecoUni.getText().replace(",", ".");
            preco = preco.replace("R$ ", "");
            i.setPrecoUnitario(Float.parseFloat(preco));
            i.setEstoque(getEstoque());
            i.setLote(null);// ---> O.o
            if(jBtnIncluir.isEnabled())
            {
                if (dI.incluir(i))
                { //==true
                    Diversos.mostrarDados("Ingrediente foi inserido com sucesso", titulo, true);
                    jBtnAlterar.setEnabled(true);
                    jBtnExcluir.setEnabled(true);
                }
                else // ==false
                {
                    Diversos.mostrarDados("Problemas ao inserir os dados do ingrediente)", titulo, false);
                }
            }
            else
            {
                if(dI.alterar(i))
                {
                    Diversos.mostrarDados("Dados do ingrediente foram atualizados com sucesso", titulo, true);
                }
                else
                {
                    Diversos.mostrarDados("Problemas ao atualizar os dados do ingrediente", "No education!", false);
                    jBtnAlterar.setEnabled(false);
                    jBtnIncluir.setEnabled(false);
                }
            }
            jBtnIncluir.setEnabled(false);
        }
        else
        {
            Diversos.mostrarDados("Preencha todos os campos", "Blue Box", false);
        }
    }//GEN-LAST:event_jBtnIncluirActionPerformed

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnAlterarActionPerformed
    {//GEN-HEADEREND:event_jBtnAlterarActionPerformed
        // TODO add your handling code here:
        jBtnIncluirActionPerformed(evt);
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnExcluirActionPerformed
    {//GEN-HEADEREND:event_jBtnExcluirActionPerformed
        // TODO add your handling code here:
        if (Diversos.confirmar("Deseja excluir?", "knock knock"))
        {
            int existem = dI.verificaChave(Integer.parseInt(jTxtID.getText()));
            int existemLotes = dI.verificaChaveLotes(Integer.parseInt(jTxtID.getText()));
            if (existem > 0 || existemLotes > 0)
            {
                if (Diversos.confirmar("Esse ingrediente esta presente em: "
                        + "" + existem + " pratos e possui "+existemLotes+" lotes.\n"
                        + "Deseja realmente excluir?", titulo))
                    if (dI.excluir(Integer.parseInt(jTxtID.getText()), 0))
                    {
                        Diversos.mostrarDados("Ingrediente foi excluido com sucesso", titulo, true);
                        limpar();
                        jBtnAlterar.setEnabled(false);
                        jBtnExcluir.setEnabled(false);
                    } 
                    else
                    {
                        Diversos.mostrarDados("Problemas ao excluir um ingrediente ", "She said She said", false);
                    }
            }
            else
            {
                if (dI.excluir(Integer.parseInt(jTxtID.getText()), 0))
                {
                    Diversos.mostrarDados("Ingrediente foi excluido com sucesso", titulo, true);
                    limpar();
                    jBtnAlterar.setEnabled(false);
                    jBtnExcluir.setEnabled(false);
                } 
                else
                {
                    Diversos.mostrarDados("Problemas ao excluir um ingrediente ", "She said She said", false);
                }
            }
        }
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jLblVoltarMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLblVoltarMouseClicked
    {//GEN-HEADEREND:event_jLblVoltarMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLblVoltarMouseClicked

    private void jBtnEstoqueActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnEstoqueActionPerformed
    {//GEN-HEADEREND:event_jBtnEstoqueActionPerformed
        // TODO add your handling code here:
        String uni = (String) jCmbUnidadeMedida.getSelectedItem();
        if((jTxtNome.getText().isEmpty()) || (uni == null))
        {
            Diversos.mostrarDados("Prencha Nome e Unidade de medida antes "
                                + "de informar os dados do estoque", "duh!", false);
        }
        else
        {
            new JDialogEstoque(this, true, this, jTxtNome.getText(), uni).setVisible(true);
        }
    }//GEN-LAST:event_jBtnEstoqueActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowActivated
    {//GEN-HEADEREND:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated
    public void atualizarDados(java.awt.event.FocusEvent evt)
    {
        jTxtIDFocusLost(evt);
    }
    private void jTxtIDFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jTxtIDFocusLost
    {//GEN-HEADEREND:event_jTxtIDFocusLost
        // TODO add your handling code here:
        Ingrediente i = null;
        int id;
        if(!Diversos.testaNum(jTxtID.getText(), titulo))
        {
           jTxtID.setText("");
           jTxtID.requestFocusInWindow();
        }
        else
        { 
            id = Integer.parseInt(jTxtID.getText());
            if(!Diversos.intervalo(id, 0, 0, titulo))
            {
                jTxtID.setText("");
                jTxtID.requestFocusInWindow();
            }
            else
            {
                i = dI.busca(id, 0);
                jCmbLotes.removeAllItems();
                lista.clear();
                if (i == null)
                {
                    jBtnIncluir.setEnabled(true);
                    jCmbLotes.addItem("Ainda não possui nenhum lote.");
                    jCmbLotes.setSelectedIndex(0);
                    jBtnLotes.setEnabled(false);
                    Diversos.mostrarDados("Ingrediente inexistente","Pepperland",false);
                }    
                else
                {  
                    i.setLotes(dL.carrgaProduto(id));//Loop infinito
                    carregarObjeto(i);
                    jBtnAlterar.setEnabled(true);
                    jBtnExcluir.setEnabled(true);
                    jBtnLotes.setEnabled(true);
                }                
            } 
            jTxtID.setEditable(false);
        }
    }//GEN-LAST:event_jTxtIDFocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_formWindowOpened

    private void jBtnLotesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnLotesActionPerformed
    {//GEN-HEADEREND:event_jBtnLotesActionPerformed
        // TODO add your handling code here:
        int lote = jCmbLotes.getSelectedIndex();
        if(lote < 0)
        {
            lote = 0;
        }
        Object o = (!lista.isEmpty() ? lista.get(lote) : null);//Esse null é usado na tela de lote
        Lote l = (Lote) o;
        new JFrmLote(this, l).setVisible(true);
    }//GEN-LAST:event_jBtnLotesActionPerformed

    private void jLblLimparMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLblLimparMouseClicked
    {//GEN-HEADEREND:event_jLblLimparMouseClicked
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_jLblLimparMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Metal".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(JFrmIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JFrmIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JFrmIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JFrmIngrediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new JFrmIngrediente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Logo;
    private javax.swing.JButton jBtnAlterar;
    private javax.swing.JButton jBtnEstoque;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnIncluir;
    private javax.swing.JButton jBtnLotes;
    private javax.swing.JComboBox jCmbCategoria;
    private javax.swing.JComboBox jCmbLotes;
    private javax.swing.JComboBox jCmbUnidadeMedida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLblLimpar;
    private javax.swing.JLabel jLblTitulo;
    private javax.swing.JLabel jLblVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnlBotoes;
    private javax.swing.JSeparator jSeparator2;
    public javax.swing.JTextField jTxtID;
    public javax.swing.JTextField jTxtNome;
    public javax.swing.JTextField jTxtPrecoUni;
    // End of variables declaration//GEN-END:variables
}
