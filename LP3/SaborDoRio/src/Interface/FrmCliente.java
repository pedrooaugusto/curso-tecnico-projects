package Interface;

import Controle.ControleCliente;
import Controle.ControleEmpresa;
import Controle.Diversos;
import Negocio.Cliente;
import Negocio.Empresa;
import java.util.ArrayList;
import java.util.List;

public class FrmCliente extends javax.swing.JFrame {

    public FrmCliente() {
        initComponents();
        listaClientes = cC.lista("order by nome_cli");
        listaEmpresas = cE.lista("order by razaosocial_emp");
        carregarEmpresas();
        limpar();
    }

    public FrmCliente(Object o) {
        initComponents();
        limpar();
        modoEdicao = true;
        listaClientes = cC.lista("order by nome_cli");
        listaEmpresas = cE.lista("order by razaosocial_emp");
        carregarEmpresas();
        carregarObjeto((Cliente) o);
    }

    boolean modoEdicao = false;
    /*se verdadeiro, indica que um objeto já existente
        no banco de dados está carregado na tela e pronto para edição*/
    private Cliente objetoAberto;
    private List listaClientes = new ArrayList<>();
    private List listaEmpresas = new ArrayList<>();
    private final ControleCliente cC = new ControleCliente();
    private final ControleEmpresa cE = new ControleEmpresa();

    private void carregarEmpresas() {
        for (Object o : listaEmpresas) {
            Empresa e = (Empresa) o;
            cmbEmpresa.addItem(e.getRazaoSocial() + "");
        }
    }

    private Cliente montarObjeto() {

        Cliente c = new Cliente();
        c.setCodigo(Integer.parseInt(txtCodigo.getText()));
        c.setNome(txtNome.getText());
        c.setEmail(txtEmail.getText());
        c.setEmpresa((Empresa) listaEmpresas.get(cmbEmpresa.getSelectedIndex()-1));
        c.setEndereco(txtEndereco.getText());
        c.setTel(txtTelefone.getText());
        c.setCel(txtCelular.getText());

        c.setEstadoConta("ok");
        c.setDiaPagamento("");

        return c;
    }

    public final void carregarObjeto(Cliente c) {
        objetoAberto = c;

        txtCodigo.setText(c.getCodigo() + "");
        txtNome.setText(c.getNome());
        txtEmail.setText(c.getEmail());
        cmbEmpresa.setSelectedItem(c.getEmpresa().getRazaoSocial());
        txtEndereco.setText(c.getEndereco());
        txtTelefone.setText(c.getTel());
        txtCelular.setText(c.getCel());

        txtCodigo.setEditable(false);

        txtStatus.setVisible(true);
        txtStatus.setText(c.getEstadoConta());
        txtDiaPag.setVisible(true);
        txtDiaPag.setText(c.getDiaPagamento());
    }

    private void limpar() {
        modoEdicao = false;
        objetoAberto = null;

        txtCodigo.setText(gerarCodigo() + "");
        txtNome.setText("");
        txtEmail.setText("");
        cmbEmpresa.setSelectedIndex(-1);
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtCelular.setText("");

        txtStatus.setVisible(false);
        txtDiaPag.setVisible(false);
    }

    public final int gerarCodigo() {

        int cod = 0;
        for (Object o : listaClientes) {
            Cliente c = (Cliente) o;
            if (c.getCodigo() > cod) {
                cod = c.getCodigo();
            }
        }
        cod++;
        return cod;
    }

    public int validarCampos() {
        int ok = 1;
        if (!txtCodigo.getText().isEmpty()
                && !txtNome.getText().isEmpty()
                && !txtTelefone.getText().isEmpty()
                && cmbEmpresa.getSelectedIndex() > -1) {

            ok = 0;

            if (!modoEdicao) {
                for (Object o : listaClientes) {
                    Cliente c = (Cliente) o;
                    if (c.getCodigo() == Integer.parseInt(txtCodigo.getText())) {
                        ok = 2;
                    }
                }
            }
        }
        return ok;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtNome = new com.pedroaugusto.mycomponents.PAField();
        txtCodigo = new com.pedroaugusto.mycomponents.PAField();
        cmbEmpresa = new com.pedroaugusto.mycomponents.THComboBox();
        txtEmail = new com.pedroaugusto.mycomponents.PAField();
        jLabel3 = new javax.swing.JLabel();
        txtEndereco = new com.pedroaugusto.mycomponents.PAField();
        txtTelefone = new com.pedroaugusto.mycomponents.PAField();
        txtCelular = new com.pedroaugusto.mycomponents.PAField();
        txtStatus = new com.pedroaugusto.mycomponents.PAField();
        txtDiaPag = new com.pedroaugusto.mycomponents.PAField();
        pnlP = new javax.swing.JPanel();
        buttonList = new javax.swing.JLabel();
        buttonSave = new javax.swing.JLabel();
        buttonDelete = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JLabel();
        btnProduto = new javax.swing.JLabel();
        btnEmpresa = new javax.swing.JLabel();
        btnCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(954, 637));

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 5, new java.awt.Color(59, 59, 59)));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        txtNome.setFieldName("*Nome");
        txtNome.setIcon("name.png");
        txtNome.setPlaceholder("Nome completo do cliente");

        txtCodigo.setFieldName("*Código");
        txtCodigo.setIcon("name.png");
        txtCodigo.setPlaceholder("Código de identificação do cliente");

        cmbEmpresa.setFieldName("*Empresa");
        cmbEmpresa.setIcon("work.png");
        cmbEmpresa.setModel("[]");
        cmbEmpresa.setPlaceholder("Selecione uma empresa");

        txtEmail.setFieldName("Email");
        txtEmail.setIcon("email.png");
        txtEmail.setPlaceholder("exemplo@email.com");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("(*) Campos obrigátorios");

        txtEndereco.setFieldName("Endereço");
        txtEndereco.setIcon("location.png");
        txtEndereco.setPlaceholder("Endereço residencial");

        txtTelefone.setFieldName("*Telefone");
        txtTelefone.setIcon("telephone.png");
        txtTelefone.setPlaceholder("0000-0000");

        txtCelular.setFieldName("Celular");
        txtCelular.setIcon("mobilephone.png");
        txtCelular.setPlaceholder("00000-0000");

        txtStatus.setFieldName("Status");
        txtStatus.setIcon("ic_info.png");
        txtStatus.setPlaceholder("");
        txtStatus.setEditable(false);
        txtStatus.setVisible(false);

        txtDiaPag.setFieldName("Dia p/ pagamento");
        txtDiaPag.setIcon("ic_date_range.png");
        txtDiaPag.setPlaceholder("");
        txtDiaPag.setEditable(false);
        txtDiaPag.setVisible(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(96, 96, 96)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCelular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaPag, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cmbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDiaPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pnlP.setBackground(new java.awt.Color(233, 4, 8));

        javax.swing.GroupLayout pnlPLayout = new javax.swing.GroupLayout(pnlP);
        pnlP.setLayout(pnlPLayout);
        pnlPLayout.setHorizontalGroup(
            pnlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 891, Short.MAX_VALUE)
        );
        pnlPLayout.setVerticalGroup(
            pnlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        buttonList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/list.png"))); // NOI18N
        buttonList.setToolTipText("Listar");
        buttonList.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonListMouseClicked(evt);
            }
        });

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/save flat.png"))); // NOI18N
        buttonSave.setToolTipText("Salvar");
        buttonSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/trash.png"))); // NOI18N
        buttonDelete.setToolTipText("Excluir");
        buttonDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDeleteMouseClicked(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/brush.png"))); // NOI18N
        btnLimpar.setToolTipText("Limpar");
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimparMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonList)
                    .addComponent(buttonSave)
                    .addComponent(buttonDelete)
                    .addComponent(btnLimpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(pnlP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addGap(6, 6, 6)
                        .addComponent(btnLimpar)))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(59, 59, 59));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastros");

        btnVoltar.setBackground(new java.awt.Color(59, 59, 59));
        btnVoltar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/back.png"))); // NOI18N
        btnVoltar.setToolTipText("Voltar");
        btnVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoltar.setOpaque(true);
        btnVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVoltarMouseClicked(evt);
            }
        });

        btnProduto.setBackground(new java.awt.Color(59, 59, 59));
        btnProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnProduto.setForeground(new java.awt.Color(255, 255, 255));
        btnProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/food mini.png"))); // NOI18N
        btnProduto.setToolTipText("Produtos");
        btnProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProduto.setOpaque(true);
        btnProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProdutoMouseClicked(evt);
            }
        });

        btnEmpresa.setBackground(new java.awt.Color(59, 59, 59));
        btnEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        btnEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/job mini.png"))); // NOI18N
        btnEmpresa.setToolTipText("Empresas");
        btnEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmpresa.setOpaque(true);
        btnEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEmpresaMouseClicked(evt);
            }
        });

        btnCliente.setBackground(new java.awt.Color(100, 100, 100));
        btnCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/client mini.png"))); // NOI18N
        btnCliente.setToolTipText("Clientes");
        btnCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCliente.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(233, 4, 8));
        jLabel2.setText("Clientes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
        int v = validarCampos();

        if (v == 0) {

            Cliente c = montarObjeto();
            int rC = -1;

            if (!modoEdicao) //Incluir um objeto
            {
                rC = cC.setManipular(c, 'I');

                if (rC == 0) {
                    Diversos.mostrarDados("O cliente foi salvo.", "Tudo certo!", true);
                    modoEdicao = true;
                } else {
                    Diversos.mostrarDados("Problemas ao salvar o cliente.", "Ops!", false);
                }

            } else //Alterar um objeto
            {
                rC = cC.setManipular(c, 'A');

                if (rC == 0) {
                    Diversos.mostrarDados("As alterações do cliente foram salvas.", "Tudo certo!", true);
                    modoEdicao = true;
                } else {
                    Diversos.mostrarDados("Problemas ao salvar as alterações do cliente.", "Ops!", false);
                }
            }

        } else {
            if (v == 1) {
                Diversos.mostrarDados("Preencha todos os campos!", "Atenção!", false);
            } else if (v == 2) {
                Diversos.mostrarDados("Já existe um cliente com este código.", "Atenção!", false);
                if (Diversos.confirmar("Podemos gerar um código automático para o cliente?", "")) {
                    txtCodigo.setText(gerarCodigo() + "");
                    buttonSaveMouseClicked(evt);
                }
            }
        }
    }//GEN-LAST:event_buttonSaveMouseClicked

    private void buttonDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDeleteMouseClicked

        if (modoEdicao) {

            if (Diversos.confirmar("Excluir este cliente também excluirá suas possíveis contas pendentes.\n"
                    + "Continuar?", "Atenção!")) {

                int rC = -1;

                rC = cC.setManipular(objetoAberto, 'E');

                if (rC != 0) {
                    Diversos.mostrarDados("Não foi possível excluir o cliente.", "Ops!", false);
                } else {
                    limpar();
                }

            }
        }

    }//GEN-LAST:event_buttonDeleteMouseClicked

    private void buttonListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonListMouseClicked
        new FrmListas(cC.lista("order by nome_cli, empresa_cli"), this, "Clientes").setVisible(true);
        dispose();
    }//GEN-LAST:event_buttonListMouseClicked

    private void btnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimparMouseClicked
        limpar();
    }//GEN-LAST:event_btnLimparMouseClicked

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        new FrmCadastro().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpresaMouseClicked
        // TODO add your handling code here:
        new FrmEmpresa().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnEmpresaMouseClicked

    private void btnProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProdutoMouseClicked
        new FrmProduto().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnProdutoMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCliente;
    private javax.swing.JLabel btnEmpresa;
    private javax.swing.JLabel btnLimpar;
    private javax.swing.JLabel btnProduto;
    private javax.swing.JLabel btnVoltar;
    private javax.swing.JLabel buttonDelete;
    private javax.swing.JLabel buttonList;
    private javax.swing.JLabel buttonSave;
    private com.pedroaugusto.mycomponents.THComboBox cmbEmpresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel pnlP;
    private com.pedroaugusto.mycomponents.PAField txtCelular;
    private com.pedroaugusto.mycomponents.PAField txtCodigo;
    private com.pedroaugusto.mycomponents.PAField txtDiaPag;
    private com.pedroaugusto.mycomponents.PAField txtEmail;
    private com.pedroaugusto.mycomponents.PAField txtEndereco;
    private com.pedroaugusto.mycomponents.PAField txtNome;
    private com.pedroaugusto.mycomponents.PAField txtStatus;
    private com.pedroaugusto.mycomponents.PAField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
