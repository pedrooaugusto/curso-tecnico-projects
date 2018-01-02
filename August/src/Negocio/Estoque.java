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
public class Estoque
{
    private int estoqueMaximo;
    private int estoqueMinimo;
    private float estoqueAtual;

    public int getEstoqueMaximo()
    {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(int estoqueMaximo)
    {
        this.estoqueMaximo = estoqueMaximo;
    }

    public int getEstoqueMinimo()
    {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo)
    {
        this.estoqueMinimo = estoqueMinimo;
    }

    public float getEstoqueAtual()
    {
        return estoqueAtual;
    }

    public void setEstoqueAtual(float estoqueAtual)
    {
        this.estoqueAtual = estoqueAtual;
    }
    
}
