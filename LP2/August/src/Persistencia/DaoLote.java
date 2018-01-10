/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.*;
import Persistencia.DaoIngrediente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pedro
 */
public class DaoLote implements DaoFabricaObjetos
{

    @Override
    public boolean incluir(Object o)
    {
        Lote l = (Lote) o;
        DaoManipular dM = new DaoManipular();
        DaoIngrediente dI = new DaoIngrediente();
        String sql = "insert into Lote(Numero_lot, QuantidadeInicial_lot, QuantidadeAtual_lot,"
                   + " Validade_lot, Produto_lot) values (?,?,?,?,?)";
        PreparedString inst = new PreparedString(sql);
        inst.setInt(1, l.getNumero());
        inst.setFloat(2, l.getQuantidadeInicial());
        inst.setFloat(3, l.getQuantidadeAtual());
        inst.setString(4, l.getValidade());
        inst.setInt(5, l.getIngrediente().getID());
        return( dM.getCadastrar(inst.getPreparedString()) && 
                atualizarEstoque("in", l.getQuantidadeInicial(), l));
    }

    @Override
    public boolean alterar(Object o)
    {
        Lote l = (Lote) o;
        DaoManipular dM = new DaoManipular();
        DaoIngrediente dI = new DaoIngrediente();
        String sql = "update Lote set QuantidadeInicial_lot = ?, QuantidadeAtual_lot = ?, "
                   + "Validade_lot = ? where Numero_lot = ?";
        PreparedString inst = new PreparedString(sql);
        inst.setFloat(1, l.getQuantidadeInicial());
        inst.setFloat(2, l.getQuantidadeAtual());
        inst.setString(3, l.getValidade());
        inst.setInt(4, l.getNumero());
        return(dM.getCadastrar(inst.getPreparedString()));
    }

    @Override
    public boolean excluir(int chave1, int chave2)
    {
        DaoManipular dM = new DaoManipular();
        DaoIngrediente dI = new DaoIngrediente();
        Lote l = busca(chave1, 0);
        String inst = "delete from Lote where Numero_lot = "+chave1;
        atualizarEstoque("out", l.getQuantidadeAtual(), l);
        return dM.getCadastrar(inst);
    }

    @Override
    public Lote busca(int chave1, int chave2)
    {
        String inst = "select * from Lote where Numero_lot = "+chave1;
        Lote l = null;
        DaoIngrediente dI;                     
        try
        {
           DaoManipular dM = new DaoManipular(); 
           ResultSet rS = dM.getConsultar(inst);
           if (rS.next()) {//==true
              l  = new Lote();
              dI = new DaoIngrediente();
              Object o = dI.busca(rS.getInt("Produto_lot"), 0);
              Ingrediente i = (Ingrediente) o;
              l.setNumero(chave1);
              l.setValidade(rS.getString("Validade_lot"));
              l.setIngrediente(i);
              l.setQuantidadeAtual(rS.getFloat("QuantidadeAtual_lot"));
              l.setQuantidadeInicial(rS.getFloat("QuantidadeInicial_lot"));
           }
        }
        catch (SQLException e){
           e.printStackTrace(); 
        }
        return l;
    }

    @Override
    public List<Object> carrega()
    {
       String inst="select * from Lote order by Numero_lot";
       List<Object> lista = new ArrayList<>();
       Lote l;
       DaoLote dL;
       try {
           DaoManipular dM = new DaoManipular();
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   dL = new DaoLote();
                   while (rS.next())
                   {
                       l = dL.busca(rS.getInt("Numero_lot"), 0);
                       lista.add(l);
                   }
               }
           }          
       }
       catch(SQLException e){
            System.out.println("problemas ao selecionar o Numero " + e.getSQLState()); 
       }
      return(lista);
    }

    @Override
    public int verificaChave(int chave)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<Lote> carrgaProduto(int ing)//Devolve uma lista com todos os lotes do produto x
    {
        List<Lote> lista = new ArrayList<>();
        String inst = "select * from Lote where Produto_lot = "+ing+" order by Numero_lot";
        Lote l;
        DaoLote dL;
        try {
           DaoManipular dM = new DaoManipular();
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   dL = new DaoLote();
                   while(rS.next())
                    {
                       l = dL.busca(rS.getInt("Numero_lot"), 0);
                       lista.add(l);
                    }
               }
           }          
        }
        catch(SQLException e){
            System.out.println("problemas ao selecionar o Numero " + e.getSQLState()); 
        }
       return(lista);
    }
    public Lote getCurrentLote(Ingrediente i, float quantidade)/*
                                                                Me devolve um lote apto para ser usado,
                                                                ou seja, com validade e quantidade nos conformes...
                                                                */
    {   
        String inst = "select * from lote where Produto_lot = "+i.getID()+" and "
                    + "QuantidadeAtual_lot >= "+quantidade+" and Date(Validade_lot) "
                    + "> Date(now()) order by Numero_lot";
        Lote l = null;
        DaoLote dL = new DaoLote();
        try 
        {
           DaoManipular dM = new DaoManipular();
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS.next()) 
               {
                   l = new Lote();
                   l = dL.busca(rS.getInt("Numero_lot"), 0);
               }
           }          
        }
        catch(SQLException e){
            System.out.println("problemas ao selecionar o Numero " + e.getSQLState()); 
        }
        return l;
    }
    
    public boolean verificarEstoque(Pedido p)//Verificar se existem lotes aptos a serem usados
    {
        boolean apto = true;
        int itens = p.getItens().size();
        int i = 0;
        while( i < itens)
        {
            Prato prato = p.getItens().get(i);
            for (int j = 0; j < prato.getIngrediente().size(); j++)
            {
                Ingrediente in = prato.getIngrediente().get(j);
                Float quant = prato.getQuantidade(j);
                if(getCurrentLote(in, quant) == null)
                {
                    i = itens;
                    apto = false;
                    Diversos.mostrarDados("Não foi possivel completar a ação.\n"
                    + "Ingrediente: "+in.getNome()+"\n"
                    + "Possiveis causas:\n1- Lote fora da validade\n2-Estoque atual insuficiente", "out", false);
                    break;
                }
            }
            i++;
        }
        return apto;
    }
    public boolean atualizarEstoque(String oprecao, float valor, Lote lote)//
    {
        //System.out.println("Valor: "+valor+" Quantidade atual: "+lote.getQuantidadeAtual());
        String attLote = "update Lote set QuantidadeAtual_lot = ? where Numero_lot = ?";
        String attEstoque = "update Ingrediente set EstoqueAtual_ing = ? where ID_ing = ?";
        PreparedString loteInst = new PreparedString(attLote);
        PreparedString estoqueInst = new PreparedString(attEstoque);
        DaoManipular dM = new DaoManipular();
        DaoIngrediente dI = new DaoIngrediente();
        Ingrediente i = dI.busca(lote.getIngrediente().getID(), 0);
        if(oprecao.equals("in"))
        {
            loteInst.setFloat(1, lote.getQuantidadeAtual());//Isso não muda
            estoqueInst.setFloat(1, i.getEstoque().getEstoqueAtual() + valor);
        }
        else
        {
            if((lote.getQuantidadeAtual() - valor) <= 0)//Caso saia -5 batatas
            {
                loteInst.setFloat(1, 0);//O lote recebe o valor zero(...)
                estoqueInst.setFloat(1, i.getEstoque().getEstoqueAtual() - lote.getQuantidadeAtual());
            }
            else
            {
                loteInst.setFloat(1, lote.getQuantidadeAtual() - valor);
                estoqueInst.setFloat(1, i.getEstoque().getEstoqueAtual() - valor);
            }
        }
        loteInst.setInt(2, lote.getNumero());
        estoqueInst.setInt(2, i.getID());
        return (dM.getCadastrar(loteInst.getPreparedString()) && 
                dM.getCadastrar(estoqueInst.getPreparedString()));
    }
    
}
