package Persistencia;

import Negocio.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEmpresa implements DaoBasico {

    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Empresa e = (Empresa) o;
        String inst = "insert into empresa (codigo_emp, razaosocial_emp, tel1_emp, tel2_emp) "
                + "values (?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, e.getID());
                pS.setString(2, e.getRazaoSocial());
                pS.setString(3, e.getTel1());
                pS.setString(4, e.getTel2());
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
        Empresa e = (Empresa) o;
        String inst = "update empresa set razaosocial_emp = ?, tel1_emp = ?, tel2_emp = ? "
                + "where codigo_emp = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setString(1, e.getRazaoSocial());
                pS.setString(2, e.getTel1());
                pS.setString(3, e.getTel2());
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
        Empresa e = (Empresa) o;
        String inst = "delete from empresa where codigo_emp = ?";
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
        String inst="Select * from empresa where codigo_emp = ?";
        Empresa e = null;
        ResultSet rS;
        
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    e = new Empresa();
                            e.setID(rS.getInt("codigo_emp"));
                            e.setRazaoSocial(rS.getString("razaosocial_emp"));
                            e.setTel1(rS.getString("tel1_emp"));
                            e.setTel2(rS.getString("tel2_emp"));
                    
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException();
        }
        return(e);
    }

    @Override
    public List<Object> carregarLista(String filtro) {
        String inst = "select * from empresa "+filtro;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoEmpresa dE = new DaoEmpresa();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dE.busca(rS.getInt("codigo_emp"), -1);
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
