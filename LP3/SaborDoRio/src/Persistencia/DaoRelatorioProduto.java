package Persistencia;

import Controle.ControleProduto;
import Negocio.Pedido;
import Negocio.Produto;
import Negocio.RelatorioProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoRelatorioProduto implements DaoBasico{

    DaoProduto dP = new DaoProduto();
    
    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        RelatorioProduto e = (RelatorioProduto) o;
        String inst = "insert into relatorioproduto (produto_relp, quantidade_relp, datainicio_relp) "
                + "values (?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getProduto().getID());
                pS.setFloat(2, e.getQuantidade());
                pS.setString(3, e.getDataInicio());
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
        RelatorioProduto e = (RelatorioProduto) o;
        String inst = "update relatorioproduto set datainicio_relp = ?, quantidade_relp = ? "
                + "where produto_relp = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setString(1, e.getDataInicio());
                pS.setFloat(2, e.getQuantidade());
                pS.setInt(3, e.getProduto().getID());
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
        RelatorioProduto e = (RelatorioProduto) o;
        String inst = "delete from relatorioproduto where produto_relp = ?";
        try{
            Connection con = DaoConexao.getInstancia().getCon();   
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, e.getProduto().getID());
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
        String inst="Select * from relatorioproduto where produto_relp = ?";
        RelatorioProduto e = null;
        ResultSet rS;
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    e = new RelatorioProduto();
                    e.setProduto((Produto) dP.busca(rS.getInt("produto_relp"), -1));
                    e.setDataInicio(rS.getString("datainicio_relp"));
                    e.setQuantidade(rS.getFloat("quantidade_relp"));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException();
        }
        return(e);}

    @Override
    public List<Object> carregarLista(String filtro) {
        String inst = "select * from relatorioproduto "+filtro;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoRelatorioProduto dRP = new DaoRelatorioProduto();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dRP.busca(rS.getInt("produto_relp"), -1);
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
    public List<Integer> match(String begin, String end){
        List<Integer> lista = new ArrayList<>();
        String query = "SELECT * FROM pagamento where STR_TO_DATE(data_pag, '%d-%m-%Y') > "
                + "STR_TO_DATE('"+begin+"', '%d-%m-%Y') and STR_TO_DATE(data_pag, '%d-%m-%Y')"
                + " < STR_TO_DATE('"+end+"', '%d-%m-%Y')";
        ResultSet rS;
        List<Object> o;
        DaoPedidoProduto dPP = new DaoPedidoProduto();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(query)){
                rS = teste.executeQuery(query);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        //o = dPP.getItemsDoPedido(rS.getInt("id_pag"));
                        lista.add(rS.getInt("id_pag"));
                    }
                rS.close();
                teste.close();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    public Map<Produto, Integer> getPedidosPeriodo(String begin, String end)
    {
        Map<Produto, Integer> a = new HashMap<>();
        List<Integer> lista = match(begin, end);
        String who = lista.toString().replaceAll("[\\[]", "(").replaceAll("[\\]]", ")");
        String query = "SELECT pedido_pp, produto_pp, sum(quant_pp) FROM "
                + "pedidoproduto where pedido_pp in "+who+" group by produto_pp";
        DaoProduto dP = new DaoProduto();
        ResultSet rS;
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(query)){
                rS = teste.executeQuery(query);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        Produto p = (Produto) dP.busca(rS.getInt("produto_pp"), -1);
                        int quantidade = rS.getInt("sum(quant_pp)");
                        a.put(p, quantidade);
                    }
                rS.close();
                teste.close();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        } 
        return a;
    }
    
    /*public static void main(String args[]){
        List<Integer> k = new ArrayList<>(Arrays.asList(1,6,7,8));
        System.out.println(k.toString().replaceAll("[\\[]", "(").replaceAll("[\\]]", ")"));
    }*/
}
