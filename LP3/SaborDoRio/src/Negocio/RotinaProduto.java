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
public class RotinaProduto {
    private int ID;
    private Monitorado produto;
    private Rotina rotina;
    private float quantidade;

    public RotinaProduto(){
        
    }
    public RotinaProduto(Monitorado p, float quantidade)
    {
        this.produto = p;
        this.quantidade = quantidade;
    }
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public Monitorado getProduto()
    {
        return produto;
    }

    public void setProduto(Monitorado produto)
    {
        this.produto = produto;
    }

    public Rotina getRotina()
    {
        return rotina;
    }

    public void setRotina(Rotina rotina)
    {
        this.rotina = rotina;
    }

    public float getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(float quantidade)
    {
        this.quantidade = quantidade;
    }
    
}
