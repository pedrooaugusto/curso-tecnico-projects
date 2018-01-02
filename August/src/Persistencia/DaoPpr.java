/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.Pedido;
import Negocio.Prato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class DaoPpr implements DaoFabricaObjetos
{

    @Override
    public boolean incluir(Object o)
    {
       Pedido p = (Pedido)o;
       DaoManipular dM = new DaoManipular();
       boolean ok = true;
        for (Prato pra : p.getItens())
        {
            String sql = "insert into Pedido_Prato(Pedido_ppr, Prato_ppr) values ("
                       + ""+p.getID()+", "+pra.getID()+")";
            if(dM.getCadastrar(sql) == false)
            {
                ok = false;
                break;
            }
        }
       return ok;
    }

    @Override
    public boolean alterar(Object o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean excluir(int chave1, int chave2)
    {
        DaoManipular dM = new DaoManipular();
        String inst = "delete from Pedido_Prato where Pedido_ppr = "+chave1;
        return (dM.getCadastrar(inst));
    }

    @Override
    public Object busca(int chave1, int chave2)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Prato> carrega(int chave)
    {
        DaoManipular dM = new DaoManipular();
        String inst = "select * from Pedido_Prato where Pedido_ppr = "+chave;
        List<Prato> lista = new ArrayList<>();
        Prato pra;
        DaoPrato dP;
        try {
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   dP = new DaoPrato();
                   while (rS.next())
                   {
                       pra = dP.busca(rS.getInt("Prato_ppr"), 0);
                       lista.add(pra);
                   }
               }
           }          
        }
        catch(SQLException e){
            System.out.println("problemas ao selecionar o prato" + e.getSQLState());
            e.printStackTrace();
        }
        return(lista);
    }

    @Override
    public int verificaChave(int chave)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> carrega()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    } 
}
