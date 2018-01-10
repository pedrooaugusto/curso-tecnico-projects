package Persistencia;

import Controle.ControleCliente;
import Controle.ControlePagamento;
import Controle.ControlePedido;
import Negocio.Cliente;
import Negocio.Empresa;
import Negocio.NotaFiscal;
import Negocio.Pagamento;
import Negocio.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoNotaFiscal implements DaoBasico{

    ControleCliente cC = new ControleCliente();
    ControlePedido cP = new ControlePedido();
    ControlePagamento cPg = new ControlePagamento();
    
    @Override
    public boolean incluir(Object o)
    {
        boolean result = true;
        NotaFiscal p = (NotaFiscal) o;
        String inst = "insert into notafiscal (id_nf, cliente_nf, pagamento_nf, pedido_nf)"
                + "values (?, ?, ?, ?)";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, p.getID());
                pS.setInt(2, p.getCliente().getCodigo());
                pS.setInt(3, p.getPagamento().getID());
                pS.setInt(4, p.getPedido().getID());
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
        NotaFiscal p = (NotaFiscal) o;
        
        String inst = "update notafiscal set cliente_nf = ?, pagamento_nf = ?, pedido_nf = ? "
                + "where id_nf = ?";
        try
        {
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement pS = con.prepareStatement(inst))
            {
                pS.setInt(1, p.getCliente().getCodigo());
                pS.setInt(2, p.getPagamento().getID());
                pS.setInt(3, p.getPedido().getID());
                pS.setInt(4, p.getID());
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
        NotaFiscal p = (NotaFiscal) o;
        String inst = "delete from notafiscal where id_nf = ?";
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
        String inst="Select * from notafiscal where id_nf = ?";
        NotaFiscal p = null;
        ResultSet rS;
        
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                teste.setInt(1, chave1);
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    p = new NotaFiscal();
                    p.setID(rS.getInt("id_nf"));
                    p.setCliente((Cliente) cC.getBusca(rS.getInt("cliente_nf"), chave2));
                    p.setPedido((Pedido) cP.getBusca(rS.getInt("pedido_nf"), chave2));
                    p.setPagamento((Pagamento) cPg.getBusca(rS.getInt("pagamento_nf"), chave2));
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
        String inst = "select * from notafiscal "+filtro;
        
        List<Object> lista = new ArrayList<>();
        ResultSet rS;
        Object o;
        DaoNotaFiscal dNF = new DaoNotaFiscal();
        try{
            try(PreparedStatement teste = DaoConexao.getInstancia().getCon().prepareStatement(inst)){
                rS = teste.executeQuery(inst);
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS != null)
                    while(rS.next()){
                        o = dNF.busca(rS.getInt("id_nf"), -1);
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
    
    public int getLastID(){
        String inst = "select max(id_nf) from notafiscal as 'higher'";
        int id=0;
        ResultSet rS;
        
        try{
            Connection con = DaoConexao.getInstancia().getCon();
            try(PreparedStatement teste = con.prepareStatement(inst)){
                rS = teste.executeQuery();
                DaoConexao.getInstancia().setCon(DaoConexao.getInstancia().getCon());
                if(rS.next()){
                    id = rS.getInt("higher");
                }
            }
        }catch(SQLException ee){
            throw new RuntimeException();
        }
        
        return id;
    }
    
}
