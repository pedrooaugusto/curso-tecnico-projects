/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.Entregador;
import Negocio.Ingrediente;
import Negocio.Lote;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class DaoEntregador implements DaoFabricaObjetos{

    @Override
    public boolean incluir(Object o) 
    {
        Entregador e = (Entregador) o;
        DaoManipular dM = new DaoManipular();
        String sql = "insert into Entregador(ID_ent, CPF_ent, Nome_ent, Veiculo_ent"
                   + ", DataNasc_ent, Salario_ent, FotoURL_ent) values(?, ?, ?, ?, ?"
                   + ", ?, ?)";
        PreparedString inst = new PreparedString(sql);
        inst.setInt(1, e.getID());
        inst.setString(2, e.getCpf());
        inst.setString(3, e.getNome());
        inst.setString(4, e.getVeiculo());
        inst.setString(5, e.getDataNascimento());
        inst.setFloat(6, e.getSalario());
        inst.setString(7, e.getFotoURL());
        return(dM.getCadastrar(inst.getPreparedString()));
    }

    @Override
    public boolean alterar(Object o) 
    {
        Entregador e = (Entregador) o;
        DaoManipular dM = new DaoManipular();
        String sql = "update Entregador set CPF_ent = ?, Nome_ent = ?, Veiculo_ent = ?"
                   + ", DataNasc_ent = ?, Salario_ent = ?, FotoURL_ent = ? where ID_ent = ?";
        PreparedString inst = new PreparedString(sql);
        inst.setString(1, e.getCpf());
        inst.setString(2, e.getNome());
        inst.setString(3, e.getVeiculo());
        inst.setString(4, e.getDataNascimento());
        inst.setFloat(5, e.getSalario());
        inst.setString(6, e.getFotoURL());
        inst.setInt(7, e.getID());
        
        return (dM.getCadastrar(inst.getPreparedString()));
    }

    @Override
    public boolean excluir(int chave1, int chave2) {
        DaoManipular dM = new DaoManipular();
        String inst = "delete from Entregador where ID_ent = "+chave1;
        return (dM.getCadastrar(inst));
    }

    @Override
    public Entregador busca(int chave1, int chave2)
    {
        String inst = "select * from Entregador where ID_ent = "+chave1;
        Entregador e = null;
        try
        {
           DaoManipular dM = new DaoManipular(); 
           ResultSet rS = dM.getConsultar(inst);
           if (rS.next()) 
           {
               e = new Entregador();
               e.setID(rS.getInt("ID_ent"));
               e.setCpf(rS.getString("CPF_ent"));
               e.setDataNascimento(rS.getString("DataNasc_ent"));
               e.setFotoURL(rS.getString("FotoURL_ent"));
               e.setNome(rS.getString("Nome_ent"));
               e.setSalario(rS.getFloat("Salario_ent"));
               e.setVeiculo(rS.getString("Veiculo_ent"));
           }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return e;
    }

    @Override
    public List<Object> carrega() 
    {
       String inst="select * from Entregador order by ID_ent";
       List<Object> lista = new ArrayList<>();
       Entregador e;
       DaoEntregador dE;
       try {
           DaoManipular dM = new DaoManipular();
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   dE = new DaoEntregador();
                   while (rS.next())
                   {
                       e = dE.busca(rS.getInt("ID_ent"), 0);
                       lista.add(e);
                   }
               }
           }          
       }
       catch(SQLException ex){
            System.out.println("problemas ao selecionar o Numero " + ex.getSQLState()); 
       }
      return(lista);
    }
    
    @Override
    public int verificaChave(int chave) 
    {
       int total = -1; 
       String inst = "Select count(Entregador_ped) as total from Pedido where Entregador_ped = "+chave;              
       try
       {
            DaoManipular dM = new DaoManipular(); 
            ResultSet rS = dM.getConsultar(inst);
            if (rS.next())
            total = rS.getInt("Total");  
       }catch (SQLException e)
       {
            System.out.println("Problemas na consulta da chave " + e.getSQLState());
       }
       return(total); 
    }
}