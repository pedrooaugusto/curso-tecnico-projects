package Persistencia;

import Negocio.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoConfig implements DaoBasico{

    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Config c = (Config) o;
        String inst = "insert into config (nclipmesa_cfg, nmesas_cfg, codseguro_cfg, animacoes_cfg, cod_cfg) "
                + "values (?, ?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, c.getnCliPMesa());
                pS.setInt(2, c.getnMesas());
                pS.setBoolean(3, c.isCodSeguro());
                pS.setBoolean(4, c.isAnimacoes());
                pS.setInt(5, c.getCod());
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        }catch(SQLException e)
        {
            result  = false;
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean alterar(Object o) {
        boolean result = true;
        Config c = (Config) o;
        String inst = "update config set nclipmesa_cfg = ?, nmesas_cfg = ?, codseguro_cfg = ?, animacoes_cfg = ? "
                + "where cod_cfg = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, c.getnCliPMesa());
                pS.setInt(2, c.getnMesas());
                pS.setBoolean(3, c.isCodSeguro());
                pS.setBoolean(4, c.isAnimacoes());
                pS.setInt(5, c.getCod());
                pS.execute();
            }
            DaoConexao.getInstancia().setCon(con);
        }catch(SQLException e)
        {
            result  = false;
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean excluir(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object busca(int chave1, int chave2) {
        String inst="Select * from config where cod_cfg = ?";
        Config c = null;
        ResultSet rS;
        
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    c = new Config();
                    c.setCod(rS.getInt("cod_cfg"));
                    c.setnCliPMesa(rS.getInt("nclipmesa_cfg"));
                    c.setnMesas(rS.getInt("nmesas_cfg"));
                    c.setCodSeguro(rS.getBoolean("codseguro_cfg"));
                    c.setAnimacoes(rS.getBoolean("animacoes_cfg"));
                }
            }
        }catch(SQLException e){
            throw new RuntimeException();
        }
        return(c);
    }

    @Override 
    public List<Object> carregarLista(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
