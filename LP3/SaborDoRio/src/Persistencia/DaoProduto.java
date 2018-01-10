package Persistencia;

import Controle.ControleEstoque;
import Controle.ControleMonitorado;
import Negocio.Estoque;
import Negocio.Produto;
import Negocio.Monitorado;
import Negocio.RelatorioProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProduto implements DaoBasico{
        
    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Produto e = (Produto) o;
        String inst = "insert into produto (codigo_pro, nome_pro, preco_pro, monitorado_pro) "
                + "values (?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getID());
                pS.setString(2, e.getNome());
                pS.setFloat(3, e.getPreco());
                pS.setBoolean(4, e.isMonitorado());
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

    @Override
    public boolean alterar(Object o) {
        boolean result = true;
        Produto e = (Produto) o;
        String inst = "update produto set nome_pro = ?, preco_pro = ?, monitorado_pro = ? "
                + "where codigo_pro = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setString(1, e.getNome());
                pS.setFloat(2, e.getPreco());
                pS.setBoolean(3, e.isMonitorado());
                pS.setInt(4, e.getID());
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
    
    @Override
    public boolean excluir(Object o) {
        boolean result = true;
        Produto e = (Produto) o;
        String inst = "delete from produto where codigo_pro = ?";
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
    public Object busca(int chave1, int chave2) {
        String inst="Select * from produto where codigo_pro = ?";
        Produto e = null;
        ResultSet rS;
        ControleEstoque cE = new ControleEstoque();
        ControleMonitorado cM = new ControleMonitorado();
        Estoque q = null;
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    if(rS.getBoolean("monitorado_pro"))
                    {
                        Monitorado pe = (Monitorado) cM.getBusca(chave1, -1);
                        //q = (Estoque) cE.getBusca(rS.getInt("codigo_pro"), -1);
                        //pe.setEstoque(q);
                        e = pe;
                    }
                    else
                    {   
                        e = new Produto();
                    }
                    e.setID(rS.getInt("codigo_pro"));
                    e.setNome(rS.getString("nome_pro"));
                    e.setPreco(rS.getFloat("preco_pro"));
                    e.setMonitorado(rS.getBoolean("monitorado_pro"));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException();
        }
        return(e);
    }

    @Override
    public List<Object> carregarLista(String filtro) {
        String inst = "select * from produto "+filtro;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoProduto dP = new DaoProduto();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dP.busca(rS.getInt("codigo_pro"), -1);
                        lista.add(o);
                    }
                rS.close();
                teste.close();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }    
        return(lista);
    }
    
}
