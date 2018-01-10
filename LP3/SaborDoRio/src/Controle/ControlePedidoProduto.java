package Controle;

import Negocio.PedidoProduto;
import Persistencia.DaoBasico;
import Persistencia.DaoPedidoProduto;
import java.util.List;
import javax.swing.ComboBoxModel;

public class ControlePedidoProduto implements ControleBasico{
    
    DaoPedidoProduto dP = new DaoPedidoProduto();
    
    @Override
    public int setManipular(Object o, char task) {
        int result = 2;
        if(dP instanceof DaoBasico)
        {
            switch(task)
            {
                case 'A':
                    result = (dP.alterar(o) ? 0 : 1);
                    break;
                case 'E':
                    result = (dP.excluir(o) ? 0 : 1);
                    break;
                case 'I':
                    result = (dP.incluir(o) ? 0 : 1);
                    break;
            }
        }
        return result;
    }

    @Override
    public Object getBusca(int iD1, int iD2) {
        Object o = null;
        if(dP instanceof DaoBasico)
        {
            o = dP.busca(iD1, iD2);
        }
        return o;
    }

    @Override
    public List<Object> lista(String filtro) {
        List<Object> lista = null;
        if(dP instanceof DaoBasico)
        {
            lista = dP.carregarLista(filtro);
        }
        return lista;
    }
    
    public List<Object> itemsDoPedido(int pedido) {
        List<Object> lista = null;
        if(dP instanceof DaoBasico)
        {
            lista = dP.getItemsDoPedido(pedido);
        }
        return lista;
    }


    @Override
    public boolean criaRelatorio(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
