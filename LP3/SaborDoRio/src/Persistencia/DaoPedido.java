package Persistencia;

import Controle.ControleNotaFiscal;
import Negocio.Pedido;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPedido implements DaoBasico{
    
    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Pedido e = (Pedido) o;
        String inst = "insert into pedido (cliente_ped, mesa_ped, aberto_ped) "
                + "values (?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setString(1, e.getCliente());
                pS.setInt(2, e.getMesa());
                pS.setBoolean(3, e.isAberto());
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
        Pedido e = (Pedido) o;
        String inst = "update pedido set cliente_ped = ?, mesa_ped = ?, aberto_ped = ? "
                + "where codigo_ped = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setString(1, e.getCliente());
                pS.setInt(2, e.getMesa());
                pS.setBoolean(3, e.isAberto());
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
        Pedido e = (Pedido) o;
        String inst = "delete from pedido where codigo_ped = ?";
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
        String inst="Select * from pedido where codigo_ped = ?";
        Pedido e = null;
        ControleNotaFiscal cM = new ControleNotaFiscal();
        //ControlePedidoProduto cPP = new ControlePedidoProduto();
        ResultSet rS;
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next())
                {
                    e = new Pedido();
                    e.setID(rS.getInt("codigo_ped"));
                    e.setCliente(rS.getString("cliente_ped"));
                    e.setMesa(rS.getInt("mesa_ped"));
                    e.setAberto(rS.getBoolean("aberto_ped"));
                    //e.setProdutos(cPP.itemsDoPedido(chave1));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException(ee);
        }
        return(e);
    }

    public Object ultimaInsercao()
    {
        String inst = "SELECT * FROM pedido order by codigo_ped desc limit 1";
        Pedido e = null;
        ResultSet rS;
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next())
                {
                    e = (Pedido) busca(rS.getInt("codigo_ped"), -1);
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException(ee);
        }
        return(e);
    }
    
    public List<Object> listaClientes(int mesa)
    {
        String inst = "SELECT * FROM pedido where mesa_ped = "+mesa+" and aberto_ped = 1";
        Object o = null;
        ResultSet rS;
        List<Object> lista = new ArrayList<>();
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                {
                    while(rS.next()){
                        o = busca(rS.getInt("codigo_ped"), -1);
                        lista.add(o);
                    }
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException(ee);
        }
        return lista;
    }
    
    @Override
    public List<Object> carregarLista(String filtro) {
        String inst = "select * from Pedido "+filtro;//order by mesa_ped;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoPedido dP = new DaoPedido();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dP.busca(rS.getInt("codigo_ped"), -1);
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
