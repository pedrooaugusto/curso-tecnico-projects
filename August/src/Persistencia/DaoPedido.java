/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.Endereco;
import Negocio.Entregador;
import Negocio.Pedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class DaoPedido implements DaoFabricaObjetos
{

    @Override
    public boolean incluir(Object o)
    {
        Pedido p = (Pedido) o;
        DaoManipular dM = new DaoManipular();
        DaoPpr dP = new DaoPpr();
        String sql = " insert into Pedido(ID_ped, NomeCliente_ped, FormaPagamento_ped,"
                   + " Data_ped, Telefone_ped, Senha_ped, ValorTotal_ped, Entregador_ped,"
                   + " E_Municipio_ped, E_Bairro_ped, E_Rua_ped, E_CasaNumero_ped, Status_ped)"
                   + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedString inst = new PreparedString(sql);
        inst.setInt(1, p.getID());
        inst.setString(2, p.getNome());
        inst.setString(3, p.getFormaPagamento());
        inst.setString(4, p.getData());
        inst.setString(5, p.getTel());
        inst.setInt(6, p.getSenha());
        inst.setFloat(7, p.getValorTotal());
        inst.setInt(8, p.getEntregador().getID());
        inst.setString(9, p.getEndereco().getMunicipio());
        inst.setString(10, p.getEndereco().getBairro());
        inst.setString(11, p.getEndereco().getRua());
        inst.setInt(12, p.getEndereco().getNumero());
        inst.setString(13, p.getStatus());
        return ((dM.getCadastrar(inst.getPreparedString())) && (dP.incluir(p)));
    }
    
    @Override
    public boolean alterar(Object o)
    {
        Pedido p = (Pedido) o;
        DaoManipular dM = new DaoManipular();
        String sql = " update Pedido set NomeCliente_ped = ?, FormaPagamento_ped = ?,"
                   + " Telefone_ped = ?, Entregador_ped = ?, E_Municipio_ped = ?,"
                   + " E_Bairro_ped = ?, E_Rua_ped = ?, E_CasaNumero_ped = ? where ID_ped = ?";
        PreparedString inst = new PreparedString(sql);
        inst.setString(1, p.getNome());
        inst.setString(2, p.getFormaPagamento());
        inst.setString(3, p.getTel());
        inst.setInt(4, p.getEntregador().getID());
        inst.setString(5, p.getEndereco().getMunicipio());
        inst.setString(6, p.getEndereco().getBairro());
        inst.setString(7, p.getEndereco().getRua());
        inst.setInt(8, p.getEndereco().getNumero());
        inst.setInt(9, p.getID());
        return (dM.getCadastrar(inst.getPreparedString()));
    }

    @Override
    public boolean excluir(int chave1, int chave2)
    {
        DaoManipular dM = new DaoManipular();
        DaoPpr dP = new DaoPpr();
        String sql = "delete from Pedido where ID_ped = "+chave1;
        dP.excluir(chave1, chave2);
        return (dM.getCadastrar(sql));
    }

    @Override
    public Pedido busca(int chave1, int chave2)
    {
        String inst = "select * from Pedido where ID_ped = "+chave1;
        Pedido p = null;
        DaoPpr dP = new DaoPpr();
        DaoEntregador dE = new DaoEntregador();
        Entregador e = null;
        Endereco end = null;
        try
        {
           DaoManipular dM = new DaoManipular(); 
           ResultSet rS = dM.getConsultar(inst);
           if(rS.next()) 
           {
                p = new Pedido();
                p.setID(rS.getInt("ID_ped"));
                p.setNome(rS.getString("NomeCliente_ped"));
                p.setTel(rS.getString("Telefone_ped"));
                p.setFormaPagamento(rS.getString("FormaPagamento_ped"));
                p.setData(rS.getString("Data_ped"));
                p.setTempoEntrega(rS.getString("TempoEntrega_ped"));
                e = dE.busca(rS.getInt("Entregador_ped"), 0);
                p.setEntregador(e);
                p.setSenha(rS.getInt("Senha_ped"));
                p.setItens(dP.carrega(rS.getInt("ID_ped")));
                p.setValorTotal(rS.getFloat("ValorTotal_ped"));
                p.setStatus(rS.getString("Status_ped"));
                end = new Endereco();
                end.setMunicipio(rS.getString("E_Municipio_ped"));
                end.setBairro(rS.getString("E_Bairro_ped"));
                end.setRua(rS.getString("E_Rua_ped"));
                end.setNumero(rS.getInt("E_CasaNumero_ped"));
                p.setEndereco(end);
           }
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return p;
    }

    public boolean concluido(Pedido p)
    {
        DaoManipular dM = new DaoManipular();
        String sql = "update Pedido set Status_ped = ?, TempoEntrega_ped = ?"
                   + " where ID_ped = ?";
        PreparedString pS = new PreparedString(sql);
        pS.setString(1, p.getStatus());
        pS.setString(2, p.getTempoEntrega());
        pS.setInt(3, p.getID());
        return dM.getCadastrar(pS.getPreparedString());
    }
    
    public List<Object> carrega(int ent)
    {
       String inst="select * from Pedido where Entregador_ped = "+ent+" and Status_ped = 'Em andamento' order by Data_ped";
       List<Object> lista = new ArrayList<>();
       Pedido p;
       try {
           DaoManipular dM = new DaoManipular();
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   while (rS.next())
                   {
                       p = busca(rS.getInt("ID_ped"), 0);
                       lista.add(p);
                   }
               }
           }          
       }
       catch(SQLException e){
            System.out.println("problemas ao selecionar o entregador" + e.getSQLState());
            e.printStackTrace();
       }
      return(lista);
    }

    @Override
    public int verificaChave(int chave)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> carrega()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
