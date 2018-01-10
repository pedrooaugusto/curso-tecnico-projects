package Persistencia;

import Controle.ControleEstoque;
import Controle.ControleNotaFiscal;
import Negocio.Estoque;
import Negocio.Monitorado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoMonitorado implements DaoBasico{
    
    ControleEstoque cE = new ControleEstoque();
    
    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Monitorado e = (Monitorado) o;
        String inst = "insert into monitorado (codigo_mon, avenda_mon) "
                + "values (?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getID());
                pS.setBoolean(2, e.isaVenda());
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
        String inst = "update monitorado set avenda_mon = ? where codigo_mon = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setBoolean(1, e.isaVenda());
                pS.setInt(2, e.getID());
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
        String inst = "delete from monitorado where codigo_mon = ?";
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
        String inst="Select * from monitorado where codigo_mon = ?";
        Monitorado e = null;
        ControleNotaFiscal cM = new ControleNotaFiscal();
        //ControleMonitoradoProduto cPP = new ControleMonitoradoProduto();
        ResultSet rS;
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next())
                {
                    e = new Monitorado();
                    e.setID(rS.getInt("codigo_mon"));
                    e.setaVenda(rS.getBoolean("avenda_mon"));
                    e.setEstoque((Estoque) cE.getBusca(rS.getInt("codigo_mon"), chave2));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException(ee);
        }
        return(e);
    }

    @Override
    public List<Object> carregarLista(String filtro) {
        String inst = "select * from monitorado "+filtro;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoMonitorado dP = new DaoMonitorado();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dP.busca(rS.getInt("codigo_mon"), -1);
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
