package Controle;

import Persistencia.DaoBasico;
import Persistencia.DaoEmpresa;
import java.util.List;

public class ControleEmpresa implements ControleBasico {

    DaoEmpresa dE = new DaoEmpresa();
    
    @Override
    public int setManipular(Object o, char task) {
        int result = 2;
        if(dE instanceof DaoBasico)
        {
            switch(task)
            {
                case 'A':
                    result = (dE.alterar(o) ? 0 : 1);
                    break;
                case 'E':
                    result = (dE.excluir(o) ? 0 : 1);
                    break;
                case 'I':
                    result = (dE.incluir(o) ? 0 : 1);
                    break;
            }
        }
        return result;
    }

    @Override
    public Object getBusca(int iD1, int iD2) {
        Object o = null;
        if(dE instanceof DaoBasico)
        {
            o = dE.busca(iD1, iD2);
        }
        return o;
    }

    @Override
    public List<Object> lista(String filtro) {
        List<Object> lista = null;
        if(dE instanceof DaoBasico)
        {
            lista = dE.carregarLista(filtro);
        }
        return lista;
    }

    @Override
    public boolean criaRelatorio(String texto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
