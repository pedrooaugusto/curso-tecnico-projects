package Interface;

import Controle.ControleLote;
import Controle.ControleMonitorado;
import Controle.ControlePedidoProduto;
import Controle.ControleProduto;
import Controle.ControleRelatorioProduto;
import Controle.Diversos;
import Negocio.Estoque;
import Negocio.Lote;
import Negocio.Monitorado;
import Negocio.PedidoProduto;
import Negocio.Produto;
import Negocio.RelatorioProduto;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrmProduto extends javax.swing.JFrame {

    public FrmProduto() {
        initComponents();
        listaProdutos = cP.lista("order by nome_pro");
        limpar();
    }

    public FrmProduto(Object o) {
        initComponents();
        limpar();
        carregarObjeto((Produto) o);
        modoEdicao = true;
        listaProdutos = cP.lista("order by nome_pro");
    }

    boolean modoEdicao = false;
    /*se verdadeiro, indica que um objeto já existente
        no banco de dados está carregado na tela e pronto para edição*/
    boolean monitorado = true;
    boolean venda = true;
    private Produto objetoAberto;
    private List listaProdutos = new ArrayList<>();
    private final ControleProduto cP = new ControleProduto();
    private final ControleMonitorado cM = new ControleMonitorado();
    private final ControlePedidoProduto cPP = new ControlePedidoProduto();
    private final ControleRelatorioProduto cRP = new ControleRelatorioProduto();
    private final ControleLote cL = new ControleLote();

    private Produto montarObjeto() {
        Monitorado m = new Monitorado();
        m.setID(Integer.parseInt(txtCodigo.getText()));
        m.setNome(txtNome.getText());
        m.setPreco(Float.parseFloat(txtPreco.getText().replaceAll("[R|$| ]", "").replace(",", ".")));
        m.setMonitorado(monitorado);

        if (monitorado) {
            Estoque e = new Estoque();
            e.setQuantidadeMaxima(Float.parseFloat(txtQtnMax.getText()));
            e.setQuantidadeMinima(Float.parseFloat(txtQtnMin.getText()));
            e.setUnidadeMedida((String) CmbUni.getSelectedItem());
            m.setEstoque(e);
            m.setaVenda(venda);
        }
        return m;
    }

    private RelatorioProduto montarObjetoRelatorio(Produto p) {
        RelatorioProduto rp = new RelatorioProduto();
        rp.setProduto(p);
        rp.setQuantidade(0);
        rp.setDataInicio(new Date().toLocaleString().substring(0, 10));

        return rp;
    }

    public final void carregarObjeto(Produto p) {
        objetoAberto = p;

        txtCodigo.setText(p.getID() + "");
        txtNome.setText(p.getNome());
        txtPreco.setText(p.getPreco() + "");

        if (p.isMonitorado() != monitorado) {
            lblMonitorarMouseClicked(null);
        }

        if (p.isMonitorado()) {
            Monitorado m = (Monitorado) p;

            txtQtnMin.setText(m.getEstoque().getQuantidadeMinima() + "");
            txtQtnMax.setText(m.getEstoque().getQuantidadeMaxima() + "");
            CmbUni.setSelectedItem(m.getEstoque().getUnidadeMedida());

            if (m.isaVenda() != venda) {
                lblVendaMouseClicked(null);
            }

            txtQntAtual.setVisible(true);
            txtQntAtual.setText(m.getEstoque().getQuantidadeAtual()
                    + " " + m.getEstoque().getUnidadeMedida() + "(s)");
        }

        txtCodigo.setEditable(false);
    }

    private void limpar() {
        modoEdicao = false;
        objetoAberto = null;

        txtQtnMax.setText("99");
        txtQtnMin.setText("0");

        txtNome.setText("");
        txtPreco.setText("");
        if (monitorado) {
            lblMonitorarMouseClicked(null);
        }

        CmbUni.setSelectedIndex(0);

        txtCodigo.setText(gerarCodigo() + "");
        txtCodigo.setEditable(true);

        txtQntAtual.setVisible(false);
    }

    public final int gerarCodigo() {

        int cod = 0;
        for (Object o : listaProdutos) {
            Produto p = (Produto) o;
            if (p.getID() > cod) {
                cod = p.getID();
            }
        }
        cod++;
        return cod;
    }

    public int validarCampos() {
        int ok = 1;
        if (!txtCodigo.getText().isEmpty()
                && !txtNome.getText().isEmpty()
                && !txtPreco.getText().isEmpty()) {

            if (monitorado) {
                if (!txtQtnMin.getText().isEmpty()
                        && !txtQtnMax.getText().isEmpty()
                        && CmbUni.getSelectedIndex() > -1) {
                    ok = 0;
                }
            } else {
                ok = 0;
            }

            if (!modoEdicao) {
                for (Object o : listaProdutos) {
                    Produto p = (Produto) o;
                    if (p.getID() == Integer.parseInt(txtCodigo.getText())) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnVoltar = new javax.swing.JLabel();
        btnProduto = new javax.swing.JLabel();
        btnEmpresa = new javax.swing.JLabel();
        btnCliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtNome = new com.pedroaugusto.mycomponents.PAField();
        txtPreco = new com.pedroaugusto.mycomponents.PAField();
        pnl = new javax.swing.JPanel();
        lblMonitorar = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        txtCodigo = new com.pedroaugusto.mycomponents.PAField();
        jLabel3 = new javax.swing.JLabel();
        pnlMonitorado = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        pnl1 = new javax.swing.JPanel();
        lblVenda = new javax.swing.JLabel();
        txtQtnMin = new com.pedroaugusto.mycomponents.PAField();
        txtQtnMax = new com.pedroaugusto.mycomponents.PAField();
        CmbUni = new com.pedroaugusto.mycomponents.THComboBox();
        txtQntAtual = new com.pedroaugusto.mycomponents.PAField();
        pnlP = new javax.swing.JPanel();
        buttonList = new javax.swing.JLabel();
        buttonSave = new javax.swing.JLabel();
        buttonDelete = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(954, 637));

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

        btnProduto.setBackground(new java.awt.Color(100, 100, 100));
        btnProduto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnProduto.setForeground(new java.awt.Color(255, 255, 255));
        btnProduto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/food mini.png"))); // NOI18N
        btnProduto.setToolTipText("Produtos");
        btnProduto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProduto.setOpaque(true);

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

        btnCliente.setBackground(new java.awt.Color(59, 59, 59));
        btnCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/client mini.png"))); // NOI18N
        btnCliente.setToolTipText("Clientes");
        btnCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliente.setOpaque(true);
        btnCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClienteMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(233, 97, 0));
        jLabel2.setText("Produtos");

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
            .addComponent(btnProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 5, new java.awt.Color(59, 59, 59)));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204))), javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(102, 102, 102))));

        txtNome.setFieldName("*Nome");
        txtNome.setIcon("name.png");
        txtNome.setPlaceholder("Nome de identificação do produto");

        txtPreco.setFieldName("*Preço");
        txtPreco.setIcon("money.png");
        txtPreco.setPlaceholder("Preço único do produto");
        txtPreco.setOnlyNumbers(true);

        pnl.setBackground(new java.awt.Color(102, 102, 255));

        lblMonitorar.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblMonitorar.setForeground(new java.awt.Color(255, 255, 255));
        lblMonitorar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonitorar.setText("Sim");
        lblMonitorar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMonitorar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMonitorarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlLayout = new javax.swing.GroupLayout(pnl);
        pnl.setLayout(pnlLayout);
        pnlLayout.setHorizontalGroup(
            pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMonitorar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );
        pnlLayout.setVerticalGroup(
            pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMonitorar, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        label.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label.setText("Monitorar estoque");

        txtCodigo.setFieldName("*Código");
        txtCodigo.setIcon("name.png");
        txtCodigo.setPlaceholder("Código de identificação do produto");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("(*) Campos obrigátorios");

        pnlMonitorado.setBackground(new java.awt.Color(255, 255, 255));

        label1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label1.setText("*À venda");

        pnl1.setBackground(new java.awt.Color(102, 102, 255));

        lblVenda.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lblVenda.setForeground(new java.awt.Color(255, 255, 255));
        lblVenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVenda.setText("Sim");
        lblVenda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVendaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        txtQtnMin.setFieldName("*Qntd. Mínima");
        txtQtnMin.setIcon("bottom arrow.png");
        txtQtnMin.setPlaceholder("Quantidade mínima para estoque");

        txtQtnMax.setFieldName("*Qntd. Máxima");
        txtQtnMax.setIcon("topp arrow.png");
        txtQtnMax.setPlaceholder("Quantidade máxima para estoque");

        CmbUni.setFieldName("*Unid. Medida");
        CmbUni.setIcon("ic_label.png");
        CmbUni.setModel("[Unidade, Quilograma, Litro]");
        CmbUni.setPlaceholder("");

        txtQntAtual.setFieldName("Qntd. Atual");
        txtQntAtual.setIcon("ic_label.png");
        txtQntAtual.setPlaceholder("");
        txtQntAtual.setEditable(false);
        txtQntAtual.setVisible(false);

        javax.swing.GroupLayout pnlMonitoradoLayout = new javax.swing.GroupLayout(pnlMonitorado);
        pnlMonitorado.setLayout(pnlMonitoradoLayout);
        pnlMonitoradoLayout.setHorizontalGroup(
            pnlMonitoradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonitoradoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlMonitoradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlMonitoradoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonitoradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQtnMax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQtnMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CmbUni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQntAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMonitoradoLayout.setVerticalGroup(
            pnlMonitoradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonitoradoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(txtQtnMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtQtnMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CmbUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(txtQntAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(pnlMonitorado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
            .addComponent(pnlMonitorado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlP.setBackground(new java.awt.Color(233, 97, 0));

        javax.swing.GroupLayout pnlPLayout = new javax.swing.GroupLayout(pnlP);
        pnlP.setLayout(pnlPLayout);
        pnlPLayout.setHorizontalGroup(
            pnlPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGroup(jPanel2Layout.createSequentialGroup()
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpar)))
                .addContainerGap())
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

    private void btnVoltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoltarMouseClicked
        new FrmCadastro().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVoltarMouseClicked

    private void btnEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpresaMouseClicked
        // TODO add your handling code here:
        new FrmEmpresa().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnEmpresaMouseClicked

    private void btnClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseClicked
        new FrmCliente().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnClienteMouseClicked

    private void lblMonitorarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMonitorarMouseClicked
        monitorado = !monitorado;

        if (monitorado) {
            pnl.setBackground(new Color(102, 102, 255));
            lblMonitorar.setText("Sim");
        } else {
            pnl.setBackground(new Color(59, 59, 59));
            lblMonitorar.setText("Não");
        }

        pnlMonitorado.setVisible(monitorado);
    }//GEN-LAST:event_lblMonitorarMouseClicked

    private void lblVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVendaMouseClicked
        venda = !venda;

        if (venda) {
            pnl1.setBackground(new Color(102, 102, 255));
            lblVenda.setText("Sim");
        } else {
            pnl1.setBackground(new Color(59, 59, 59));
            lblVenda.setText("Não");
        }
    }//GEN-LAST:event_lblVendaMouseClicked

    private void buttonListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonListMouseClicked
        new FrmListas(cP.lista("order by nome_pro"), this, "Produtos").setVisible(true);
        dispose();
    }//GEN-LAST:event_buttonListMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
        int v = validarCampos();

        if (v == 0) {

            Produto p = montarObjeto();
            int rP, rM = -1;

            if (!modoEdicao) //Incluir um objeto
            {
                rP = cP.setManipular(p, 'I');

                if (!p.isMonitorado()) {
                    if (rP == 0) {
                        Diversos.mostrarDados("O produto foi salvo.", "Tudo certo!", true);
                        modoEdicao = true;
                        objetoAberto = p;

                        if (cRP.setManipular(montarObjetoRelatorio(p), 'I') != 0) {
                            Diversos.mostrarDados("Não foi possível gerar um relatório para este produto.", "Ops!", false);
                        }
                    } else {
                        Diversos.mostrarDados("Problemas ao salvar o produto.", "Ops!", false);
                    }
                } else {
                    rM = cM.setManipular(p, 'I');

                    if (rP == 0 && rM == 0) {
                        Diversos.mostrarDados("O produto foi salvo.", "Tudo certo!", true);
                        modoEdicao = true;
                        objetoAberto = p;
                        
                        if (cRP.setManipular(montarObjetoRelatorio(p), 'I') != 0) {
                            Diversos.mostrarDados("Não foi possível gerar um relatório para este produto.", "Ops!", false);
                        }
                    } else {
                        Diversos.mostrarDados("Problemas ao salvar o produto.", "Ops!", false);
                    }
                }
            } else //Alterar um objeto
            {
                Produto old = (Produto) cP.getBusca(p.getID(), -1);
                rP = cP.setManipular(p, 'A');
                if (old.isMonitorado() == p.isMonitorado()) {
                    if (p.isMonitorado() == false) {
                        if (rP == 0) {
                            Diversos.mostrarDados("As alterações do produto foram salvas.", "Tudo certo!", true);
                        } else {
                            Diversos.mostrarDados("Problemas ao salvar as alterações do produto.", "Ops!", false);
                        }
                    } else {
                        rM = cM.setManipular(p, 'A');
                        if (rP == 0 && rM == 0) {
                            Diversos.mostrarDados("As alterações do produto foram salvas.", "Tudo certo!", true);
                        } else {
                            Diversos.mostrarDados("Problemas ao salvar as alterações do produto.", "Ops!", false);
                        }
                    }
                } else {
                    if (old.isMonitorado() && !p.isMonitorado()) {
                        rM = cM.setManipular(old, 'E');//EXCLUIR ANTIGO
                    } else {
                        rM = cM.setManipular(p, 'I');
                    }
                    if (rP == 0 && rM == 0) {
                        Diversos.mostrarDados("As alterações do produto foram salvas.", "Tudo certo!", true);
                    } else {
                        Diversos.mostrarDados("Problemas ao salvar as alterações do produto.", "Ops!", false);
                    }
                }
            }

        } else {
            if (v == 1) {
                Diversos.mostrarDados("Preencha todos os campos!", "Atenção!", false);
            } else if (v == 2) {
                Diversos.mostrarDados("Já existe um produto com este código.", "Atenção!", false);
                if (Diversos.confirmar("Podemos gerar um código automático para o produto?", "")) {
                    txtCodigo.setText(gerarCodigo() + "");
                    buttonSaveMouseClicked(evt);
                }
            }
        }

    }//GEN-LAST:event_buttonSaveMouseClicked

    private void buttonDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDeleteMouseClicked

        if (modoEdicao) {

            if (Diversos.confirmar("Excluir este produto também o excluirá dos pedidos salvos e em andamento.\n"
                    + "Continuar?", "Atenção!")) {

                int rP = 0, rM = 0, rRP = 0;

                RelatorioProduto rp = montarObjetoRelatorio(objetoAberto);

                if (cRP.getBusca(rp.getProduto().getID(), -1) != null) {
                    rRP = cRP.setManipular(rp, 'E');
                }

                if (objetoAberto.isMonitorado()) {

                    rM = cM.setManipular(objetoAberto, 'E');

                }

                rP = cP.setManipular(objetoAberto, 'E');
                
                if (rRP != 0 || rM != 0 || rP != 0) {
                    Diversos.mostrarDados("Não foi possível excluir o produto.", "Ops!", false);
                } else {
                    limpar();
                }

            }
        }

        /*
        
        
        if (modoEdicao) {

            if (Diversos.confirmar("Excluir este produto também o excluirá dos pedidos salvos e em andamento.\n"
                    + "Continuar?", "Atenção!")) {

                /*
                List listaPP = new ArrayList<>();
                boolean ok = true;

                listaPP = cPP.lista("where produto_pp = " + objetoAberto.getID());
                for (Object o : listaPP) {
                    PedidoProduto PpParaExcluir = (PedidoProduto) o;
                    if (cPP.setManipular(PpParaExcluir, 'E') != 0) {
                        Diversos.mostrarDados("Não foi possível apagar o produto de todoso os pedidos.\n"
                                + "Portanto não será possível apagar o produto.", "Ops!", false);
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    int rP = -1, rM = -1, rL = -1;

                    if (objetoAberto.isMonitorado()) {

                        rM = cM.setManipular(objetoAberto, 'E');

                        List listaLote = new ArrayList<>();
                        listaLote = cL.lista("where monitorado_lote = " + objetoAberto.getID());

                        for (Object o : listaLote) {
                            Lote l = (Lote) o;

                            if (cL.setManipular(l, 'E') != 0) {
                                Diversos.mostrarDados("Não foi possível apagar o produto de todoso os pedidos.\n"
                                        + "Portanto não será possível apagar o produto.", "Ops!", false);
                                ok = false;
                                break;
                            }
                        }
                    }

                    if (ok) {

                        rP = cP.setManipular(objetoAberto, 'E');

                        if (rM != 0 || rP != 0) {
                            Diversos.mostrarDados("Não foi possível excluir o produto.", "Ops!", false);
                        } else {
                            limpar();
                        }
/*
                    }
                }
            }
        }
        
         */
    }//GEN-LAST:event_buttonDeleteMouseClicked

    private void btnLimparMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimparMouseClicked
        limpar();
    }//GEN-LAST:event_btnLimparMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.pedroaugusto.mycomponents.THComboBox CmbUni;
    private javax.swing.JLabel btnCliente;
    private javax.swing.JLabel btnEmpresa;
    private javax.swing.JLabel btnLimpar;
    private javax.swing.JLabel btnProduto;
    private javax.swing.JLabel btnVoltar;
    private javax.swing.JLabel buttonDelete;
    private javax.swing.JLabel buttonList;
    private javax.swing.JLabel buttonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel lblMonitorar;
    private javax.swing.JLabel lblVenda;
    private javax.swing.JPanel pnl;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnlMonitorado;
    private javax.swing.JPanel pnlP;
    private com.pedroaugusto.mycomponents.PAField txtCodigo;
    private com.pedroaugusto.mycomponents.PAField txtNome;
    private com.pedroaugusto.mycomponents.PAField txtPreco;
    private com.pedroaugusto.mycomponents.PAField txtQntAtual;
    private com.pedroaugusto.mycomponents.PAField txtQtnMax;
    private com.pedroaugusto.mycomponents.PAField txtQtnMin;
    // End of variables declaration//GEN-END:variables
}
