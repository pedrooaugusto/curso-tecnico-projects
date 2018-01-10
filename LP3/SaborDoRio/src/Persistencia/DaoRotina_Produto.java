/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Controle.ControleProduto;
import Negocio.Monitorado;
import Negocio.Produto;
import Negocio.Rotina;
import Negocio.RotinaProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class DaoRotina_Produto{

    public boolean incluir(Object o)
    {
        RotinaProduto k = (RotinaProduto) o;
        boolean result = true;
        String inst = "insert into produto_rotina (produto_prr, rotina_prr, quantidade_ppr)"
                + "values (?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(2, k.getRotina().getID());
                pS.setInt(1, k.getProduto().getID());
                pS.setFloat(3, k.getQuantidade());
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        }catch(SQLException ee)
        {
            result  = false;
            throw new RuntimeException(ee);
        }
        return result;
    }

    public boolean excluir(int o)
    {
        boolean result = true;
        String inst = "delete from produto_rotina where rotina_prr = ?";
        try{
            Connection con = DaoConexao.getInstancia().getCon();   
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, o);
                teste.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        } catch (SQLException ee){
            result = false;
            throw new RuntimeException(ee);
        }
        return result;
    }

    public Object busca(Rotina r, int chave1, int chave2)
    {
        String inst = "Select * from produto_rotina where rotina_prr = ? and produto_prr = ?";
        RotinaProduto e = null;
        ResultSet rS;
        ControleProduto cP = new ControleProduto();
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                teste.setInt(2, chave2);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next())
                {
                    e = new RotinaProduto();
                    e.setID(rS.getInt("ID_prr"));
                    e.setQuantidade(rS.getFloat("quantidade_ppr"));
                    e.setProduto((Monitorado)cP.getBusca(rS.getInt("produto_prr"), -1));
                    e.setRotina(r);
                }
            }
        }catch(Exception ek){
            ek.printStackTrace();
        }
        return(e);
    }
    
    public List<RotinaProduto> carregarLista(Rotina r)
    {
        String inst = "select * from produto_rotina where rotina_prr = "+r.getID();
        List<RotinaProduto> lista = new ArrayList<>();
        ResultSet rS;
        RotinaProduto o;
        ControleProduto cP = new ControleProduto();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                //teste.setInt(1, r.getID());
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = (RotinaProduto) busca(r, r.getID(), rS.getInt("produto_prr"));
                        lista.add(o);
                    }
                rS.close();
                teste.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }    
        return(lista);
    }
    
}
