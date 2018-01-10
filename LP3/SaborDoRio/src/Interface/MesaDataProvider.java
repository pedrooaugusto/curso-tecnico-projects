/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controle.ControleBasico;
import Controle.ControleProduto;
import Negocio.Produto;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class MesaDataProvider {
    
    private static List<Object> PRODUTOS = null;
    private static final ControleBasico CP = new ControleProduto();
    
    public MesaDataProvider()
    {
        MesaDataProvider.PRODUTOS = CP.lista("order by nome_pro");
    }

    public static void attPlease()
    {
        MesaDataProvider.PRODUTOS = CP.lista("order by nome_pro");
    }
    public static List<Object> getProdutos()
    {
        if(PRODUTOS != null)
            return PRODUTOS;
        else
            return PRODUTOS = CP.lista("order by nome_pro");
    }
    public static Object pesquisar(int id)
    {
        for(Object a : PRODUTOS)
            if(((Produto)a).getID() == id)
                return a;
        return null;
    }   
}
