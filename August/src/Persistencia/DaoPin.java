/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.Ingrediente;
import Negocio.Prato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class DaoPin implements DaoFabricaObjetos
{

    @Override
    public boolean incluir(Object o)
    {
       Prato p = (Prato)o;
       DaoManipular dM = new DaoManipular();
       boolean ok = true;
       System.out.println(p.getIngrediente().size());
        for (int i = 0; i < p.getIngrediente().size(); i++)
        {
            String sql = "insert into Prato_Ingrediente(Prato_pin, Ingrediente_pin, Quantidade_pin) values ("
                       + ""+p.getID()+", "+p.getIngrediente(i).getID()+", "+p.getQuantidade(i)+")";
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
        String inst = "delete from Prato_Ingrediente where Prato_pin = "+chave1;
        return (dM.getCadastrar(inst));
    }

    @Override
    public Object busca(int chave1, int chave2)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Object[] carrega(int chave)
    {
        DaoManipular dM = new DaoManipular();
        String inst = "select * from Prato_Ingrediente where Prato_pin = "+chave;
        List<Ingrediente> listaIng = new ArrayList<>();
        List<Float> listaQuant = new ArrayList<>();
        Object zip[] = new Object[2];
        Ingrediente i;
        DaoIngrediente dI;
        float quant;
        try {
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   dI = new DaoIngrediente();
                   while (rS.next())
                   {
                       i = dI.busca(rS.getInt("Ingrediente_pin"), 0);
                       quant = rS.getFloat("Quantidade_pin");
                       listaIng.add(i);
                       listaQuant.add(quant);
                   }
               }
           }          
        }
        catch(SQLException e){
            System.out.println("problemas ao selecionar o ingrediente" + e.getSQLState());
            e.printStackTrace();
        }
        zip[0] = listaQuant;
        zip[1] = listaIng;
        
        return(zip);
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
