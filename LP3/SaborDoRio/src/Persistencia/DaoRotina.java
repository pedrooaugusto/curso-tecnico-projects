/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Controle.ControleProduto;
import Negocio.Produto;
import Negocio.Rotina;
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
public class DaoRotina implements DaoBasico{

    @Override
    public boolean incluir(Object o)
    {
        boolean result = true;
        Rotina e = (Rotina) o;
        String inst = "insert into rotinas (ID_rot, nome_rot, descricao_rot) "
                + "values (?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getID());
                pS.setString(2, e.getShortName());
                pS.setString(3, e.getDesc());
                pS.execute();
                DaoConexao.getInstancia().setCon(con);
            }
        }catch(SQLException ee)
        {
            result  = false;
            throw new RuntimeException(ee);
        }
        return result;
    }

    @Override
    public boolean alterar(Object o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean excluir(Object o)
    {
        boolean result = true;
        Rotina e = (Rotina) o;
        String inst = "delete from rotinas where ID_rot = ?";
        try{
            Connection con = DaoConexao.getInstancia().getCon();   
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, e.getID());
                teste.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        } catch (SQLException ee){
            result = false;
            throw new RuntimeException(ee);
        }
        return result;
    }

    @Override
    public Object busca(int chave1, int chave2)
    {
        String inst = "Select * from rotinas where ID_rot = ?";
        Rotina e = null;
        ResultSet rS;
        DaoRotina_Produto dpp = new DaoRotina_Produto();
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    e = new Rotina();
                    e.setID(rS.getInt("ID_rot"));
                    e.setShortName(rS.getString("nome_rot"));
                    e.setDesc(rS.getString("descricao_rot"));
                    e.setItems(dpp.carregarLista(e));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException();
        }
        return(e);
    }

    @Override
    public List<Object> carregarLista(String filtro)
    {
        String inst = "select * from rotinas order by ID_rot";
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        //DaoProduto dP = new DaoProduto();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = busca(rS.getInt("ID_rot"), -1);
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
    public Rotina getLast()
    {
        String inst = "SELECT * FROM rotinas order by ID_rot desc limit 1";
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Rotina o = null;
            try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next())
                    o = (Rotina) busca(rS.getInt("ID_rot"), -1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return o;
    }
}
