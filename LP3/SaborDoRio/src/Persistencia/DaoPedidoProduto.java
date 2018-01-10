package Persistencia;

import Controle.ControlePedido;
import Controle.ControleProduto;
import Negocio.Pedido;
import Negocio.PedidoProduto;
import Negocio.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPedidoProduto implements DaoBasico{
    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        PedidoProduto e = (PedidoProduto) o;
        String inst = "insert into pedidoproduto (pedido_pp, produto_pp, quant_pp, comentario_pp, numeracao_pp) "
                + "values (?, ?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getPedido().getID());
                pS.setInt(2, e.getProduto().getID());
                pS.setInt(3, e.getQuant());
                pS.setString(4, e.getComentario());
                pS.setInt(5, e.getNumeracao());
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
        PedidoProduto e = (PedidoProduto) o;
        String inst = "update pedidoproduto set produto_pp = ?, quant_pp = ?, comentario_pp = ? "
                + "where pedido_pp = ? and numeracao_pp = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getProduto().getID());
                pS.setInt(2, e.getQuant());
                pS.setString(3, e.getComentario());
                pS.setInt(4, e.getPedido().getID());
                pS.setInt(5, e.getNumeracao());
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
        PedidoProduto e = (PedidoProduto) o;
        String inst = "delete from pedidoproduto where pedido_pp = ? and numeracao_pp = ?";
        try{
            Connection con = DaoConexao.getInstancia().getCon();   
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, e.getPedido().getID());
                teste.setInt(2, e.getNumeracao());
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
        String inst="Select * from pedidoproduto where codigo_pp = ?";
        PedidoProduto e = null;
        ResultSet rS;
        ControlePedido cP = new ControlePedido();
        ControleProduto cPr = new ControleProduto();
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    e = new PedidoProduto();
                        e.setCodigo(rS.getInt("codigo_pp"));
                        e.setPedido((Pedido) cP.getBusca(rS.getInt("pedido_pp"), chave2));
                        e.setComentario(rS.getString("comentario_pp"));
                        e.setProduto((Produto) cPr.getBusca(rS.getInt("produto_pp"), chave2));
                        e.setQuant(rS.getInt("quant_pp"));
                        e.setNumeracao(rS.getInt("numeracao_pp"));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException(ee);
        }
        return(e);
    }

    @Override
    public List<Object> carregarLista(String filtro) {
        String inst = "select * from pedidoproduto "+filtro; //order by pedido_pp, codigo_pp;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = busca(rS.getInt("codigo_pp"), -1);
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
    
    public List<Object> getItemsDoPedido(int pedido)
    {
        String inst = "Select * from pedidoproduto where pedido_pp = ? order by numeracao_pp";
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                teste.setInt(1, pedido);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next())
                    {
                        o = busca(rS.getInt("codigo_pp"), -1);
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
