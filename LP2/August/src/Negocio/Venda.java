/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

/**
 *
 * @author Pedro
 */
public class Venda
{
    private String data;
    private float total;
    private Entregador entregador;

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public float getTotal()
    {
        return total;
    }

    public void setTotal(float total)
    {
        this.total = total;
    }

    public Entregador getEntregador()
    {
        return entregador;
    }

    public void setEntregador(Entregador entregador)
    {
        this.entregador = entregador;
    }
    
}
