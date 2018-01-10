/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Negocio.Produto;
import Negocio.Rotina;
import Negocio.RotinaProduto;
import Persistencia.DaoBasico;
import Persistencia.DaoCliente;
import Persistencia.DaoRotina;
import Persistencia.DaoRotina_Produto;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class ControleRotina implements ControleBasico{

    DaoRotina dC = new DaoRotina();
    DaoRotina_Produto ppr = new DaoRotina_Produto();
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
                    boolean k = ppr.excluir(((Rotina) o).getID());
                    if(k)
                        result = (dC.excluir(o) ? 0 : 1);
                    break;
                case 'I':
                    result = (dC.incluir(o) ? 0 : 1);
                    if(result == 0)
                    {
                        Rotina r = dC.getLast();
                        for(RotinaProduto p : ((Rotina) o).getItems()){
                            p.setRotina(r);
                            ppr.incluir(p);
                        }
                    }
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
    public boolean criaRelatorio(String texto)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
