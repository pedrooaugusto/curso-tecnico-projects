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
public class Lote
{
    private int Numero;
    private float quantidadeInicial;
    private float quantidadeAtual;
    private String validade;
    private Ingrediente ingrediente;
    
    public String getValidade()
    {
        return validade;
    }

    public void setValidade(String validade)
    {
        this.validade = validade;
    }
    
    public int getNumero()
    {
        return Numero;
    }

    public void setNumero(int Numero)
    {
        this.Numero = Numero;
    }

    public float getQuantidadeInicial()
    {
        return quantidadeInicial;
    }

    public void setQuantidadeInicial(float quantidadeInicial)
    {
        this.quantidadeInicial = quantidadeInicial;
    }

    public float getQuantidadeAtual()
    {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(float quantidadeAtual)
    {
        this.quantidadeAtual = quantidadeAtual;
    }

    public Ingrediente getIngrediente()
    {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente)
    {
        this.ingrediente = ingrediente;
    }
    
}
