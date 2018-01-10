/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.Venda;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class DaoVenda implements DaoFabricaObjetos
{

    @Override
    public boolean incluir(Object o)
    {
        Venda v = (Venda) o;
        DaoManipular dM = new DaoManipular();
        String sql = "insert into Venda(Data_ven, Preco_ven, Entregador_ven) values (?, ?, ?)";
        PreparedString pS = new PreparedString(sql);
        pS.setString(1, v.getData());
        pS.setFloat(2, v.getTotal());
        pS.setInt(3, v.getEntregador().getID());
        return (dM.getCadastrar(pS.getPreparedString()));
    }

    @Override
    public boolean alterar(Object o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean excluir(int chave1, int chave2)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object busca(int chave1, int chave2)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> carrega()
    {
        throw new UnsupportedOperationException("Ainda não suportado.");
    }

    @Override
    public int verificaChave(int chave)
    {
        throw new UnsupportedOperationException("Ainda não suportado");
    }
    
}
