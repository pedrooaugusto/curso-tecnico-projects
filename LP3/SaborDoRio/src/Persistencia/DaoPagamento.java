package Persistencia;

import Negocio.Pagamento;
import Negocio.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPagamento implements DaoBasico {

    @Override
    public boolean incluir(Object o)
    {
        boolean result = true;
        Pagamento p = (Pagamento) o;
        String inst = "insert into pagamento (id_pag, pago_pag, data_pag, valor_pag)"
                + "values (?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, p.getID());
                pS.setBoolean(2, p.isPago());
                pS.setString(3, p.getData());
                pS.setFloat(4, p.getValor());
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
        Pagamento p = (Pagamento) o;
        String inst = "update pagamento set pago_pag = ?, data_pag = ?, valor_pag = ?, "
                + "realizacaoDoPagamento_pag = ? where id_pag = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(5, p.getID());
                pS.setString(4, p.getRealizacaoPagamento());
                pS.setBoolean(1, p.isPago());
                pS.setString(2, p.getData());
                pS.setFloat(3, p.getValor());
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
        Pagamento p = (Pagamento) o;
        String inst = "delete from pagamento where id_pag = ?";
        try{
            Connection con = DaoConexao.getInstancia().getCon();   
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, p.getID());
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
        String inst="Select * from pagamento where id_pag = ?";
        Pagamento p = null;
        ResultSet rS;
        
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    p = new Pagamento();
                    p.setID(rS.getInt("id_pag"));
                    p.setPago(rS.getBoolean("pago_pag"));
                    p.setData(rS.getString("data_pag"));
                    p.setValor(rS.getFloat("valor_pag"));
                    p.setRealizacaoPagamento(rS.getString("realizacaoDoPagamento_pag"));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException();
        }
        return(p);
    }

    @Override
    public List<Object> carregarLista(String filtro)
    {
        String inst = "select * from pagamento "+filtro;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoPagamento dNF = new DaoPagamento();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dNF.busca(rS.getInt("id_pag"), -1);
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
