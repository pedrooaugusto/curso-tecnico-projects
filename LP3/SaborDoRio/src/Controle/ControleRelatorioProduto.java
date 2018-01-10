package Controle;

import Negocio.Produto;
import Persistencia.DaoBasico;
import Persistencia.DaoRelatorioProduto;
import java.util.List;
import java.util.Map;

public class ControleRelatorioProduto implements ControleBasico{
    
    DaoRelatorioProduto dRP = new DaoRelatorioProduto();
    
    @Override
    public int setManipular(Object o, char task) {
        int result = 2;
        if(dRP instanceof DaoBasico)
        {
            switch(task)
            {
                case 'A':
                    result = (dRP.alterar(o) ? 0 : 1);
                    break;
                case 'E':
                    result = (dRP.excluir(o) ? 0 : 1);
                    break;
                case 'I':
                    result = (dRP.incluir(o) ? 0 : 1);
                    break;
            }
        }
        return result;
    }

    @Override
    public Object getBusca(int iD1, int iD2) {
        Object o = null;
        if(dRP instanceof DaoBasico)
        {
            o = dRP.busca(iD1, iD2);
        }
        return o;
    }

    @Override
    public List<Object> lista(String filtro) {
        List<Object> lista = null;
        if(dRP instanceof DaoBasico)
        {
            lista = dRP.carregarLista(filtro);
        }
        return lista;
    }
    public Map<Produto, Integer> getProdutosNoPeriodo(String a, String b){
        if(dRP instanceof DaoBasico)
            return dRP.getPedidosPeriodo(a, b);
        return null;
    }

    @Override
    public boolean criaRelatorio(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
