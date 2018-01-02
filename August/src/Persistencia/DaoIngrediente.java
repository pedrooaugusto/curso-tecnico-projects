/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.util.List;
import Negocio.*;
import Persistencia.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class DaoIngrediente implements DaoFabricaObjetos
{

    @Override
    public boolean incluir(Object o)
    {
        Ingrediente i = (Ingrediente) o;
        DaoManipular dM = new DaoManipular();
        String sql = "insert into Ingrediente(ID_ing, Nome_ing, Unidade_ing, Categoria_ing, "
                   + "PrecoUnitario_ing, EstoqueAtual_ing, EstoqueMinimo_ing, EstoqueMaximo_ing"
                   + ") values (?,?,?,?,?,?,?,?)";
        PreparedString inst = new PreparedString(sql);
        inst.setInt(1, i.getID());
        inst.setString(2, i.getNome());
        inst.setString(3, i.getUnidadeMedida());
        inst.setString(4, i.getCategoria());
        inst.setFloat(5, i.getPrecoUnitario());
        inst.setFloat(6, i.getEstoque().getEstoqueAtual());
        inst.setInt(7, i.getEstoque().getEstoqueMinimo());
        inst.setInt(8, i.getEstoque().getEstoqueMaximo());
        return dM.getCadastrar(inst.getPreparedString());
    }

    @Override
    public boolean alterar(Object o)
    {
        Ingrediente i = (Ingrediente) o;
        DaoManipular dM = new DaoManipular();
        String sql = "update Ingrediente set Nome_ing = ?, Unidade_ing = ?, Categoria_ing = ? "
                   + ",PrecoUnitario_ing = ?, EstoqueMinimo_ing = ?, EstoqueMaximo_ing = ? "
                   + "where ID_ing = ?";
        PreparedString inst = new PreparedString(sql);
        inst.setString(1, i.getNome());
        inst.setString(2, i.getUnidadeMedida());
        inst.setString(3, i.getCategoria());
        inst.setFloat(4, i.getPrecoUnitario());
        inst.setInt(5, i.getEstoque().getEstoqueMinimo());
        inst.setInt(6, i.getEstoque().getEstoqueMaximo());
        inst.setInt(7, i.getID());
        return dM.getCadastrar(inst.getPreparedString());
    }

    @Override
    public boolean excluir(int chave1, int chave2)
    {
        DaoManipular dM = new DaoManipular();
        String inst = "delete from Ingrediente where ID_ing = "+chave1;
        return dM.getCadastrar(inst);
    }

    @Override
    public Ingrediente busca(int chave1, int chave2)
    {
        String inst = "select * from Ingrediente where ID_ing = "+chave1;
        Ingrediente i = null;
        DaoLote dL = new DaoLote();
        try
        {
           DaoManipular dM = new DaoManipular(); 
           ResultSet rS = dM.getConsultar(inst);
           if (rS.next()) {//==true
              i  = new Ingrediente();
              i.setID(chave1);
              i.setCategoria(rS.getString("Categoria_ing"));
              i.setNome(rS.getString("Nome_ing"));
              i.setPrecoUnitario(rS.getFloat("PrecoUnitario_ing"));
              i.setUnidadeMedida(rS.getString("Unidade_ing"));
              Estoque e = new Estoque();
              e.setEstoqueAtual(rS.getFloat("EstoqueAtual_ing"));
              e.setEstoqueMaximo(rS.getInt("EstoqueMaximo_ing"));
              e.setEstoqueMinimo(rS.getInt("EstoqueMinimo_ing"));
              i.setEstoque(e);
              //i.setLotes(dL.carrgaProduto(chave1));
           }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Object> carrega()
    {
       String inst="select * from Ingrediente order by ID_ing";
       List<Object> lista = new ArrayList<>();
       Ingrediente i;
       DaoIngrediente dI;
       try {
           DaoManipular dM = new DaoManipular();
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   dI = new DaoIngrediente();
                   while(rS.next())
                   {
                       i = dI.busca(rS.getInt("ID_ing"), 0);
                       lista.add(i);
                   }
               }
           }          
       }
       catch(SQLException e){
            e.printStackTrace();
       }
      return(lista);
    }

    /*public boolean atualizarEstoque(String oprecao, float valor, Ingrediente who)
    {
        String sql = "update Ingrediente set EstoqueAtual_ing = ? where ID_ing = ?";
        DaoManipular dM = new DaoManipular();
        PreparedString inst = new PreparedString(sql);
        if(oprecao.equals("in"))
        {
            inst.setFloat(1, who.getEstoque().getEstoqueAtual() + valor);
        }
        else
        {
            if((who.getEstoque().getEstoqueAtual() - valor) <= 0)
            {
                inst.setFloat(1, 0);
            }
            else
            {
                inst.setFloat(1, who.getEstoque().getEstoqueAtual() - valor);
            }
        }
        inst.setInt(2, who.getID());
        return (dM.getCadastrar(inst.getPreparedString()));
    }*/
    
    @Override
    public int verificaChave(int chave)
    {
       int total = -1; 
       String inst = "Select count(Ingrediente_pin) as total from Prato_Ingrediente "
                   + "where Ingrediente_pin = "+chave;              
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
    public int verificaChaveLotes(int chave)
    {
       int total = -1; 
       String inst = "Select count(Numero_lot) as total from Lote "
                   + "where Produto_lot = "+chave;              
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
