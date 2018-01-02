/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aluno
 */
public class DaoManipular extends DaoConexao {
 
  public boolean getCadastrar(String inst){
  
    boolean result = true;
          
    try {
        Connection con = getConnection();
        try (Statement sT = con.createStatement()) {
            sT.executeUpdate(inst);
        }
    }   
    catch(SQLException e){
            System.out.println(inst);
            e.printStackTrace();
            result = false;           
    }
    return(result);  
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
  
  public DefaultTableModel apresentaDados (ResultSet rS){ 
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
}