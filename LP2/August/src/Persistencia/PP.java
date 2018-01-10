/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import static Persistencia.DaoConexao.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pedro
 */
public class PP {
    public DefaultTableModel retornaLista(int op, String texto)
    {
        DefaultTableModel modelo = null;
        String inst = "";
        ResultSet rS = null;
        switch (op)
        {
            case 1://Total em estoque rs
                inst = "select * from entregador";
                break;
        }
        rS = getConsultar(inst);
        if (rS != null)
        {
            modelo = apresentaDados(rS);
        }
        return (rS != null ? modelo : null);
    }
    public DefaultTableModel apresentaDados (ResultSet rS)
    { 
       DefaultTableModel modelo = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column)
        {
            return false;
        }
       };
       String coluna;
       try{
          ResultSetMetaData rsmd = rS.getMetaData();
          for (int i=1; i<=rsmd.getColumnCount(); i++){
              //coluna = rsmd.getColumnName(i).toUpperCase();
              coluna = rsmd.getColumnLabel(i);
              modelo.addColumn(coluna);       
          }
          while(rS.next()){
               Object[] objeto = new Object[rsmd.getColumnCount()];
               for (int i=0; i<rsmd.getColumnCount();i++)
                     objeto[i] = rS.getObject(i+1);
               modelo.addRow(objeto);
          }      
       }
       catch (SQLException e){
           System.out.println("problemas ao exibir os dados "+ e.getMessage());
    }
    return(modelo);   
  }
  public ResultSet getConsultar (String inst){
     ResultSet rS = null; 
     try{
        Connection con = getConnection();
        Statement sT = con.createStatement();
        rS = sT.executeQuery(inst);
     }   
     catch (SQLException e){
         System.out.println(inst);
         e.printStackTrace();  
     }
    return rS;
  }
    
}
