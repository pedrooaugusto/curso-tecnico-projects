package com.august.saborrioweb.modelo;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name="pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int ID; //cod do pedido
    private int mesa; //mesa do qual o pedido pertence
    private boolean aberto;
    @Transient
    private List<Pedido_Produto> items = new ArrayList<>();
    @Transient
    private float precoTotal;
    
    public boolean isAberto()
    {
        return aberto;
    }

    public void setAberto(boolean aberto)
    {
        this.aberto = aberto;
    }
    
    public String precoTotalS()
    {
    	return NumberFormat.getCurrencyInstance().format(precoTotal);
    }
    
    public float getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}

	public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
    
	public List<Pedido_Produto> getItems() {
		return items;
	}

	public void setItems(List<Pedido_Produto> items) {
		this.items = items;
		float tot = 0;
		for(Pedido_Produto a : items)
			tot+=a.getProduto().getPreco() * a.getQuant();
		precoTotal = tot;
	}
    public void addicionarItem(Pedido_Produto item)
    {
    	items.add(item);
    	float tot = item.getProduto().getPreco() * item.getQuant();
    	precoTotal+=tot;
    }
    public void deletarItem(int index)
    {
    	float tot = items.get(index).getProduto().getPreco() * 
    			items.get(index).getQuant();
    	precoTotal-=tot;
    	items.remove(index);
    }
}
