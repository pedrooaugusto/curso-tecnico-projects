/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Negocio.Lote;
import Negocio.Monitorado;
import Persistencia.DaoBasico;
import Persistencia.DaoLote;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class ControleLote implements ControleBasico{

    DaoLote dL = new DaoLote();
    @Override
    public int setManipular(Object o, char task)
    {
        int result = 2;
        if(dL instanceof DaoBasico)
        {
            Lote antigo = null;
            switch(task)
            {
                case 'A':
                    antigo = (Lote) getBusca(((Lote) o).getID(), -1);
                    result = (dL.alterar(o) ? 0 : 1);
                    break;
                case 'E':
                    result = (dL.excluir(o) ? 0 : 1);
                    break;
                case 'I':
                    result = (dL.incluir(o) ? 0 : 1);
                    break;
            }
            if(result == 0)
                result = atualizarEstoque(o, task, antigo);
        }
        return result;
    }
    
    private int atualizarEstoque(Object o, char task, Lote m)
    {
        Lote l = (Lote) o;
        System.out.println("current value lote:"+l.getMonitorado().getEstoque().getQuantidadeAtual());
        float qntAtual = l.getQuantidadeAtual();
        float qntAntigo = (m != null ? m.getQuantidadeAtual() : 0);
        if(task == 'I')
        {
            l.getMonitorado().getEstoque().adicionar(qntAtual);
        }
        else if(task == 'E')
        {
            l.getMonitorado().getEstoque().subtrair(qntAtual);
        }
        else if(task == 'A' && m != null)
        {
            if(qntAntigo > qntAtual)//Ou seja o cara diminiu
            {
                l.getMonitorado().getEstoque().subtrair(qntAntigo - qntAtual);
                System.out.println("Antigo menos novo: "+qntAntigo+" - "+qntAtual+" = "+(qntAntigo - qntAtual));
                System.out.println("valor estoque dos sub: "+l.getMonitorado().getEstoque().getQuantidadeAtual());
            }
            else if(qntAntigo < qntAtual)//Ou seja o cara aumentou
            {
                l.getMonitorado().getEstoque().adicionar(qntAtual - qntAntigo);
            }
        }
        ControleMonitorado cM = new ControleMonitorado();
        return cM.setManipular(l.getMonitorado(), 'A');
    }
    @Override
    public Object getBusca(int iD1, int iD2)
    {
        Object o = null;
        if(dL instanceof DaoBasico)
        {
            o = dL.busca(iD1, iD2);
        }
        return o;
    }

    @Override
    public List<Object> lista(String filtro)
    {
        List<Object> lista = null;
        if(dL instanceof DaoBasico)
        {
            lista = dL.carregarLista("");
        }
        return lista;
    }
    
    public boolean estoqueBaixa(int produto, float quantidadeNecessaria)
    {
        //quero 20; tenho 15 no primeiro lote e 20 no segundo
        List<Object> lista = null;
        List<Lote> monitorado = new ArrayList<>();
        int index = 0;
        float quantidadePossuo = 0;
        boolean j = false;
        if(dL instanceof DaoBasico)
            lista = dL.giveMeALoteToUseOnInOut(produto);
        if(lista != null)
        {
            Lote l = (Lote) lista.get(index);
            if(l.getQuantidadeAtual() >= quantidadeNecessaria)
            {
                quantidadePossuo = quantidadeNecessaria;
                //quantidadePossuo = quantidadePossuo + (quantidadeNecessaria - quantidadePossuo)
                l.setQuantidadeAtual(l.getQuantidadeAtual() - quantidadePossuo);
                monitorado.add(l);
            }
            else if(l.getQuantidadeAtual() < quantidadeNecessaria)
            {
                float aux = quantidadeNecessaria;
                //quero 50; tenho 23 no primeiro lote e 20 no segundo
                do
                {
                    l = (Lote) lista.get(index);
                    if(l.getQuantidadeAtual() < aux){
                        quantidadePossuo += l.getQuantidadeAtual();
                        aux -= l.getQuantidadeAtual();//quantidadePossuo; 50 - 23 = 27
                        l.setQuantidadeAtual(0);//13 no segundo
                    }
                    else{
                        //5, 6, 8,
                        l.setQuantidadeAtual(l.getQuantidadeAtual() - (quantidadeNecessaria - quantidadePossuo));
                        quantidadePossuo = quantidadePossuo + (quantidadeNecessaria - quantidadePossuo);
                    }
                    monitorado.add(l);
                    index+=1;
                }while(quantidadePossuo != quantidadeNecessaria);        
            }
            j = true;
        }
        else     
            j = false;
        for (Lote l : monitorado)
        {
            System.out.println("Lote valor atual:"+l.getQuantidadeAtual());
            Lote k = (Lote) getBusca(l.getID(), -1);
            l.getMonitorado().setEstoque(k.getMonitorado().getEstoque());
            setManipular(l, 'A');
        }
        return j;
    }
    
    @Override
    public boolean criaRelatorio(String texto)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
