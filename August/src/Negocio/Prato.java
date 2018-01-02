/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class Prato
{
    private int ID;
    private String nome;
    private float preco;
    private String foto;
    private String modoPreparo;
    private String secao;
    private float calorias;
    private List<Ingrediente> ingrediente = new ArrayList<Ingrediente>();
    private List<Float> quantidade = new ArrayList<>();//Poderia ter criado uma classe pra isso ou uma matriz

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

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public String getModoPreparo()
    {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo)
    {
        this.modoPreparo = modoPreparo;
    }

    public String getSecao()
    {
        return secao;
    }

    public void setSecao(String secao)
    {
        this.secao = secao;
    }

    public float getCalorias()
    {
        return calorias;
    }

    public void setCalorias(float calorias)
    {
        this.calorias = calorias;
    }

    public List<Ingrediente> getIngrediente()
    {
        return ingrediente;
    }

    public void setIngrediente(List<Ingrediente> ingrediente)
    {
        this.ingrediente = ingrediente;
    }
    
    public Ingrediente getIngrediente(int i)
    {
        return ingrediente.get(i);
    }
    
    public void setIngrediente(Ingrediente i)
    {
        this.ingrediente.add(i);
    }
    
    public List<Float> getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(List<Float> quantidade)
    {
        this.quantidade = quantidade;
    }
    
    public float getQuantidade(int i)
    {
        return quantidade.get(i);
    }
    
    public void setQuantidade(float i)
    {
        this.quantidade.add(i);
    }
}
