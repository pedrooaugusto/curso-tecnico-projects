package Controle;

import Persistencia.DaoBasico;
import Persistencia.DaoEstoque;
import java.util.List;
import javax.swing.ComboBoxModel;

public class ControleEstoque implements ControleBasico{

    DaoEstoque dE = new DaoEstoque();

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

    public void atualizarEstoque(int onde, float quantidade, String operacao)
    {
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
