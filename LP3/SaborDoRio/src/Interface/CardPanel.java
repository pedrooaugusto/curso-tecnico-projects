package Interface;

import Controle.ControlePedido;
import Controle.ControlePedidoProduto;
import Negocio.Pedido;
import Negocio.PedidoProduto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.border.Border;

public class CardPanel extends javax.swing.JPanel {

    private int id;
    private String algumaInformação;
    private String moreInfo;
    private int numeroCli;
    private float total;

    public CardPanel(int idCard) {
        initComponents();
        this.id = idCard;

        lblNumMesa.setText(String.valueOf(idCard));
        jPanel2.setVisible(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        carregarMesa();

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                moved();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                exited();
            }
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroCli() {
        return numeroCli;
    }

    public void setNumeroCli(int numeroCli) {
        this.numeroCli = numeroCli;
    }

    public void attInfo() {
        String t = "", u = NumberFormat.getCurrencyInstance().format(total);
        if (numeroCli > 0) {
            if (numeroCli != 1) {
                t = numeroCli + " clientes";
            } else {
                t = numeroCli + " cliente";
            }
        }else{
            t = "- - -";
            u = " ";
        }

        lblNClientes.setText(t);
        lblTotal.setText(u);
    }

    public String getAlgumaInformação() {
        return algumaInformação;
    }

    public void setAlgumaInformação(String algumaInformação) {
        this.algumaInformação = algumaInformação;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    private void carregarMesa() {
        ControlePedido cP = new ControlePedido();
        ControlePedidoProduto cPP = new ControlePedidoProduto();
        int i = 0;
        List<Object> lista = cP.listaClientesDaMesa(id);
        for(Object a : lista)
        {
            List<Object> f = cPP.itemsDoPedido(((Pedido) a).getID());
            for(Object b : f)
            {
                PedidoProduto c = (PedidoProduto)b;
                total+=c.getQuant() * c.getProduto().getPreco();
            }
        }
        numeroCli = lista.size();
        attInfo();
        //attProgressBar();
    }

    /*private void attProgressBar() {
        pgbCli.setMaximum(10);
        pgbCli.setMinimum(0);
        pgbCli.setValue(numeroCli);
    }*/
    public void moved() {
        setBackground(new Color(51, 102, 255));
        jPanel1.setBackground(new Color(51, 76, 255));
        lblNumMesa.setForeground(new Color(255, 255, 255));
        lblNClientes.setForeground(new Color(255, 255, 255));
        jPanel2.setVisible(true);
        lblTotal.setForeground(new Color(255, 255, 255));
    }

    public void exited() {
        setBackground(new Color(204, 216, 253));
        jPanel1.setBackground(new Color(153, 182, 255));
        lblNumMesa.setForeground(Color.BLACK);
        lblNClientes.setForeground(new Color(0,102,102));
        jPanel2.setVisible(false);
        lblTotal.setForeground(new Color(0,102,102));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblNumMesa = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNClientes1 = new javax.swing.JLabel();
        lblNClientes = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Things:");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hello Doctor");

        setBackground(new java.awt.Color(204, 216, 253));
        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(233, 233, 243), 5), javax.swing.BorderFactory.createMatteBorder(1, 1, 3, 3, new java.awt.Color(0, 0, 0))));
        setMaximumSize(new java.awt.Dimension(149, 205));
        setMinimumSize(new java.awt.Dimension(149, 205));
        setPreferredSize(new java.awt.Dimension(149, 205));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 182, 255));

        lblNumMesa.setBackground(new java.awt.Color(153, 153, 255));
        lblNumMesa.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        lblNumMesa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumMesa.setText("000");
        lblNumMesa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(78, 176, 255)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNumMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblNumMesa))
        );

        jPanel2.setBackground(new java.awt.Color(255, 0, 51));

        lblNClientes1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lblNClientes1.setForeground(new java.awt.Color(255, 255, 255));
        lblNClientes1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNClientes1.setText("ABRIR MESA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNClientes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblNClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblNClientes.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lblNClientes.setForeground(new java.awt.Color(0, 102, 102));
        lblNClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNClientes.setText("000 clientes");

        lblTotal.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 102, 102));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotal.setText("R$9999.99");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered

    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited

    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblNClientes;
    private javax.swing.JLabel lblNClientes1;
    private javax.swing.JLabel lblNumMesa;
    private javax.swing.JLabel lblTotal;
    // End of variables declaration//GEN-END:variables
}
