package Persistencia;

import Negocio.Estoque;
import Negocio.Monitorado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEstoque implements DaoBasico{


    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Monitorado e = (Monitorado) o;
        String inst = "insert into estoque (id_est, unidademed_est, quantmin_est, quantmax_est, quantatual_est) "
                + "values (?, ?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getID());
                pS.setString(2, e.getEstoque().getUnidadeMedida());
                pS.setFloat(3, e.getEstoque().getQuantidadeMinima());
                pS.setFloat(4, e.getEstoque().getQuantidadeMaxima());
                pS.setFloat(5, e.getEstoque().getQuantidadeAtual());
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
        Monitorado e = (Monitorado) o;
        String inst = "update estoque set unidademed_est = ?, quantmin_est = ?, quantmax_est = ?, quantatual_est = ? "
                + "where id_est = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setString(1, e.getEstoque().getUnidadeMedida());
                pS.setFloat(2, e.getEstoque().getQuantidadeMinima());
                pS.setFloat(3, e.getEstoque().getQuantidadeMaxima());
                pS.setFloat(4, e.getEstoque().getQuantidadeAtual());
                pS.setInt(5, e.getID());
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
        Monitorado e = (Monitorado) o;
        String inst = "delete from estoque where id_est = ?";
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
        String inst="Select * from estoque where id_est = ?";
        Estoque e = null;
        ResultSet rS;
        
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    e = new Estoque();
                    e.setUnidadeMedida(rS.getString("unidademed_est"));
                    e.setQuantidadeMinima(rS.getFloat("quantmin_est"));
                    e.setQuantidadeMaxima(rS.getFloat("quantmax_est"));
                    e.setQuantidadeAtual(rS.getFloat("quantatual_est"));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException();
        }
        return(e);
    }

    @Override
    public List<Object> carregarLista(String filtro) {
        String inst = "select * from estoque "+filtro; //order by unidademed_est;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoEstoque dE = new DaoEstoque();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dE.busca(rS.getInt("id_est"), -1);
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
