/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class BotaoMesa implements Serializable{
    private Pedido pedido;
    private int numeroMesa;
    private List<Pedido_Produto> items = new ArrayList<>();
    private String status = "Livre";
    private float precoTotal = 0;
    
    public BotaoMesa(int numeroMesa)
    {
        this.numeroMesa = numeroMesa;
    }
    public Pedido getPedido()
    {
        return pedido;
    }

    public void setPedido(Pedido pedido)
    {
        this.pedido = pedido;
    }

    public int getNumeroMesa()
    {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa)
    {
        this.numeroMesa = numeroMesa;
    }

    public List<Pedido_Produto> getItems()
    {
        return items;
    }

    public void setItems(List<Pedido_Produto> items)
    {
        this.items = items;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setItems2(List<Pedido_Produto> itemsl)
    {
        this.items = itemsl;
        if(itemsl != null)
        {
            status = "Ocupada";
            if(itemsl.isEmpty())
                precoTotal = 0;
            else
            {   
                float tot = 0;
                for(Pedido_Produto a : itemsl)
                    tot+= a.getProduto().getPreco()*a.getQuant();
                tot = tot*100;
                tot = Math.round(tot);
                tot = tot /100;
                precoTotal = tot;
            }
        }
        else
        {
            status = "Ocupada";
            precoTotal = 0;
        }
    }
    
    public float getPrecoTotal()
    {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal)
    {
        this.precoTotal = precoTotal;
    }

    public void addItemPrice(float f)
    {
        this.precoTotal += f;
    }
    
}
