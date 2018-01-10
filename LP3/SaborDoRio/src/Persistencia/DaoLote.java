package Persistencia;

import Controle.ControleProduto;
import Negocio.Lote;
import Negocio.Monitorado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoLote implements DaoBasico{

    @Override
    public boolean incluir(Object o)
    {
        boolean result = true;
        Lote e = (Lote) o;
        String inst = "insert into lote (id_lote, monitorado_lote, "
                + "validade_lote, qntinicial_lote, qntatual_lote) "
                + "values (?, ?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getID());
                pS.setInt(2, e.getMonitorado().getID());
                pS.setString(3, e.getDataValidade());
                pS.setFloat(4, e.getQuantidadeInicial());
                pS.setFloat(5, e.getQuantidadeAtual());
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
    public boolean alterar(Object o)
    {
        boolean result = true;
        Lote l = (Lote) o;
        String inst = "update lote set validade_lote = ?, monitorado_lote = ?,"
                + " qntinicial_lote = ?, qntatual_lote = ? "
                + "where id_lote = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setString(1, l.getDataValidade());
                pS.setInt(2, l.getMonitorado().getID());
                pS.setFloat(3, l.getQuantidadeInicial());
                pS.setFloat(4, l.getQuantidadeAtual());
                pS.setInt(5, l.getID());
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
    public boolean excluir(Object o)
    {
        boolean result = true;
        Lote l = (Lote) o;
        String inst = "delete from lote where id_lote = ?";
        try{
            Connection con = DaoConexao.getInstancia().getCon();   
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, l.getID());
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
        String inst="Select * from lote where id_lote = ?";
        Lote l = null;
        Monitorado p = null;
        ResultSet rS;
        ControleProduto cP = new ControleProduto();
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    l = new Lote();
                    l.setDataValidade(rS.getString("validade_lote"));
                    l.setQuantidadeAtual(rS.getFloat("qntatual_lote"));
                    l.setQuantidadeInicial(rS.getFloat("qntinicial_lote"));
                    l.setID(rS.getInt("id_lote"));
                    l.setMonitorado((Monitorado) cP.getBusca(rS.getInt("monitorado_lote"), -1));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException(ee);
        }
        return(l);
    }

    @Override
    public List<Object> carregarLista(String filtro)
    {
        String inst = "select * from lote "+filtro;//order by validade_lote;
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = busca(rS.getInt("id_lote"), -1);
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
    
    public List<Object> giveMeALoteToUseOnInOut(int id){
        String query = "where monitorado_lote = "+id+" order by STR_TO_DATE(validade_lote, '%d/%m/%Y')";
        List<Object> obj = carregarLista(query);
        if(obj.isEmpty())
            return null;
        else
            return obj;
    }
    
}
