package Controle;

import Persistencia.DaoBasico;
import Persistencia.DaoPagamento;
import java.util.List;

public class ControlePagamento implements ControleBasico{
    
    DaoPagamento dM = new DaoPagamento();
    
    @Override
    public int setManipular(Object o, char task)
    {
                int result = 2;
        if(dM instanceof DaoBasico)
        {
            switch(task)
            {
                case 'A':
                    result = (dM.alterar(o) ? 0 : 1);
                    break;
                case 'E':
                    result = (dM.excluir(o) ? 0 : 1);
                    break;
                case 'I':
                    result = (dM.incluir(o) ? 0 : 1);
                    break;
            }
        }
        return result;
    }

    @Override
    public Object getBusca(int iD1, int iD2)
    {
        Object o = null;
        if(dM instanceof DaoBasico)
        {
            o = dM.busca(iD1, iD2);
        }
        return o;
    }

    @Override
    public List<Object> lista(String filtro)
    {  
        List<Object> lista = null;
        if(dM instanceof DaoBasico)
        {
            lista = dM.carregarLista(filtro);
        }
        return lista;
    }

    @Override
    public boolean criaRelatorio(String texto)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
