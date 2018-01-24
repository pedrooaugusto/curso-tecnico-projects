package com.august.saborrioweb.modelo;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="produto")
public class Produto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int ID;
    private String nome;
    private float preco;
    private boolean monitorado; //estoque
    
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public float getPreco()
    {
        return preco;
    }

    public void setPreco(float preco)
    {
        this.preco = preco;
    }    

    public boolean isMonitorado() {
        return monitorado;
    }

    public void setMonitorado(boolean monitorado) {
        this.monitorado = monitorado;
    }
    public String precoS()
    {
        return NumberFormat.getCurrencyInstance().format(preco);
    }
    
	public String getCodigoString() {
		return ID==0? "": ID+"";
	}

	public void setCodigoString(String codigoString) {
		this.ID = Integer.parseInt(codigoString.equals("") ? "0" : codigoString);
	}
}
