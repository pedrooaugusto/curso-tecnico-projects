package Controle;

import Persistencia.DaoBasico;
import Persistencia.DaoPedido;
import java.util.List;
import javax.swing.ComboBoxModel;

public class ControlePedido implements ControleBasico{

    DaoPedido dP = new DaoPedido();
    
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

    public Object getUltimaInsercao()
    {
        Object o = null;
        if(dP instanceof DaoBasico)
        {
            o = dP.ultimaInsercao();
        }
        return o;
    }
    
    public List<Object> listaClientesDaMesa(int numMesa) {
        List<Object> lista = null;
        if(dP instanceof DaoBasico)
        {
            lista = dP.listaClientes(numMesa);
        }
        return lista;
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

    @Override
    public boolean criaRelatorio(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
