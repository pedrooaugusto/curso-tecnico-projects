package Controle;

import Persistencia.DaoBasico;
import Persistencia.DaoCliente;
import java.util.List;

public class ControleCliente implements ControleBasico{

    DaoCliente dC = new DaoCliente();
    
    @Override
    public int setManipular(Object o, char task) {
        int result = 2;
        if(dC instanceof DaoBasico)
        {
            switch(task)
            {
                case 'A':
                    result = (dC.alterar(o) ? 0 : 1);
                    break;
                case 'E':
                    result = (dC.excluir(o) ? 0 : 1);
                    break;
                case 'I':
                    result = (dC.incluir(o) ? 0 : 1);
                    break;
            }
        }
        return result;
    }

    @Override
    public Object getBusca(int iD1, int iD2) {
        Object o = null;
        if(dC instanceof DaoBasico)
        {
            o = dC.busca(iD1, iD2);
        }
        return o;
    }

    @Override
    public List<Object> lista(String filtro) {
        List<Object> lista = null;
        if(dC instanceof DaoBasico)
        {
            lista = dC.carregarLista(filtro);
        }
        return lista;
    }

    @Override
    public boolean criaRelatorio(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
