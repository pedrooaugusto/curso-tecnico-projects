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
public class Ingrediente
{
    private Estoque estoque = null;
    private String nome;
    private int ID;
    private String unidadeMedida;
    private String categoria;
    private float precoUnitario;
    private List<Lote> lotes = new ArrayList<Lote>();
    
    public Estoque getEstoque()
    {
        return estoque; 
    }
    
    public void setEstoque(Estoque e)
    {
        this.estoque = e;
    }
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    public String getUnidadeMedida()
    {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida)
    {
        this.unidadeMedida = unidadeMedida;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public float getPrecoUnitario()
    {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario)
    {
        this.precoUnitario = precoUnitario;
    }
    
    public Lote getLote(int i)
    {
        return lotes.get(i);
    }
   
    public void setLote(Lote l)
    {
        lotes.add(l);
    }
    
    public void setLotes(List<Lote> lotes)
    {
        this.lotes = lotes;
    }
    public List<Lote> getLotes()
    {
        return this.lotes;
    }
}
