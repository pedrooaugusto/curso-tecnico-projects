package Persistencia;

import Controle.ControleEmpresa;
import Negocio.Cliente;
import Negocio.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCliente implements DaoBasico{
    
    ControleEmpresa cE = new ControleEmpresa();
    @Override
    public boolean incluir(Object o) {
        boolean result = true;
        Cliente c = (Cliente) o;
        String inst = "insert into cliente (codigo_cli, nome_cli, tel_cli, cel_cli,"
                + " email_cli, endereco_cli, empresa_cli, diapagamento_cli, estadoconta_cli) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, c.getCodigo());
                pS.setString(2, c.getNome());
                pS.setString(3, c.getTel());
                pS.setString(4, c.getCel());
                pS.setString(5, c.getEmail());
                pS.setString(6, c.getEndereco());
                pS.setInt(7, c.getEmpresa().getID());
                pS.setString(8, c.getDiaPagamento());
                pS.setString(9, c.getEstadoConta());
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
        Cliente c = (Cliente) o;
        String inst = "update cliente set nome_cli = ?, tel_cli = ?, cel_cli = ?, email_cli = ?,"
                + " endereco_cli = ?, empresa_cli = ?, diapagamento_cli = ?, estadoconta_cli = ? "
                + "where codigo_cli = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setString(1, c.getNome());
                pS.setString(2, c.getTel());
                pS.setString(3, c.getCel());
                pS.setString(4, c.getEmail());
                pS.setString(5, c.getEndereco());
                pS.setInt(6, c.getEmpresa().getID());
                pS.setString(7, c.getDiaPagamento());
                pS.setString(8, c.getEstadoConta());
                pS.setInt(9, c.getCodigo());
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
        Cliente c = (Cliente) o;
        String inst = "delete from cliente where codigo_cli = ?";
        try{
            Connection con = DaoConexao.getInstancia().getCon();   
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, c.getCodigo());
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
        String inst="Select * from cliente where codigo_cli = ?";
        Cliente c = null;
        ResultSet rS;
        
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    c = new Cliente();
                    c.setCodigo(rS.getInt("codigo_cli"));
                    c.setNome(rS.getString("nome_cli"));
                    c.setTel(rS.getString("tel_cli"));
                    c.setCel(rS.getString("cel_cli"));
                    c.setEmail(rS.getString("email_cli"));
                    c.setEndereco(rS.getString("endereco_cli"));
                    c.setEmpresa((Empresa) cE.getBusca(rS.getInt("empresa_cli"), chave2));
                    c.setDiaPagamento(rS.getString("diapagamento_cli"));
                    c.setEstadoConta(rS.getString("estadoconta_cli"));
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException(ee);
        }
        return(c);
    }

    @Override
    public List<Object> carregarLista(String filtro) {
        String inst = "select * from cliente "+ filtro;/* where codigo_cli != 0 order by nome_cli;*/;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoCliente dC = new DaoCliente();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dC.busca(rS.getInt("codigo_cli"), -1);
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
