package Interface;

import Controle.ControleConfig;
import Negocio.Config;
import java.awt.Dimension;
import com.pedroaugusto.transicoes.ControleTransicoes;
import java.awt.Color;
public class JpnlMenuItem extends javax.swing.JPanel {

    private ControleTransicoes cT = new ControleTransicoes(this);
    private int widthInicial;
    FrmMainMenu main;
    private int option;
    //ControleConfig cC = new ControleConfig();
    //Config C = (Config) cC.getBusca(1, 0);
    
    public JpnlMenuItem(int option, FrmMainMenu main)
    {
        
        initComponents();
        
        this.option=option;
        this.main=main;
        
        //cT = new ControleTransicoes(this);
    }

    public JpnlMenuItem() {
        initComponents();
        this.option = 42;
        this.main = null;
    }
    public void setText(String texto)
    {
        jLabel2.setText(texto);
    }
    public void setInitWidth(int w)
    {
        widthInicial = w;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 0, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setPreferredSize(new java.awt.Dimension(401, 92));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("hello hayley");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addContainerGap(246, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        //if(C.isAnimacoes()){
            /*Dimension to = getSize();
            to.setSize(widthInicial, 70);
            cT.manipularDimenssao(to);*/
        //}
        //jLabel2.setText(jLabel2.getText().replace("uma descrição sore o que essa tela faz", ""));
        //leia o mouseEntered()
    }//GEN-LAST:event_formMouseExited

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        //Dimension to = getSize();
        //widthInicial = (int) to.getWidth();
        //to.setSize(845, 70);
        //cT.manipularDimenssao(to);
            
            switch(option){
                case 1:
                    cT = new ControleTransicoes(this);
                    cT.manipularDimenssao(new Dimension(820, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem2);
                    cT.manipularDimenssao(new Dimension(520, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem3);
                    cT.manipularDimenssao(new Dimension(420, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem4);
                    cT.manipularDimenssao(new Dimension(320, 70), 200);
                    break;
                case 2:
                    cT = new ControleTransicoes(main.jpnlMenuItem1);
                    cT.manipularDimenssao(new Dimension(520, 70), 200);
                    cT = new ControleTransicoes(this);
                    cT.manipularDimenssao(new Dimension(820, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem3);
                    cT.manipularDimenssao(new Dimension(520, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem4);
                    cT.manipularDimenssao(new Dimension(420, 70), 200);
                    break;
                case 3:
                    cT = new ControleTransicoes(main.jpnlMenuItem1);
                    cT.manipularDimenssao(new Dimension(420, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem2);
                    cT.manipularDimenssao(new Dimension(520, 70), 200);
                    cT = new ControleTransicoes(this);
                    cT.manipularDimenssao(new Dimension(820, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem4);
                    cT.manipularDimenssao(new Dimension(520, 70), 200);
                    break;
                case 4:
                    cT = new ControleTransicoes(main.jpnlMenuItem1);
                    cT.manipularDimenssao(new Dimension(320, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem2);
                    cT.manipularDimenssao(new Dimension(420, 70), 200);
                    cT = new ControleTransicoes(main.jpnlMenuItem3);
                    cT.manipularDimenssao(new Dimension(520, 70), 200);
                    cT = new ControleTransicoes(this);
                    cT.manipularDimenssao(new Dimension(820, 70), 200);
                    break;
            }
        //queria colocar aqui pra quando passasse o mouse adicionasse ao texto do Panel uma descrição. Tipo um tool tipo text.
        //como se fosse isso aqui, mas diferente um pra cada:
        //jLabel2.setText(jLabel2.getText()+" uma descrição sore o que essa tela faz");
    }//GEN-LAST:event_formMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
