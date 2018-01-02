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
public class Pedido
{

    private int ID;
    private List<Prato> itens = new ArrayList<>();
    private String nome;
    private Endereco endereco;
    private String formaPagamento;
    private float troco;
    private String tel;
    private Entregador entregador;
    private String data;
    private float valorTotal;
    private String tempoEntrega;
    private int senha;
    private String status;

    public int getID()
    {
        return this.ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public List<Prato> getItens()
    {
        return itens;
    }

    public void setItens(List<Prato> itens)
    {
        this.itens = itens;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public String getFormaPagamento()
    {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento)
    {
        this.formaPagamento = formaPagamento;
    }

    public float getTroco()
    {
        return troco;
    }

    public void setTroco(float troco)
    {
        this.troco = troco;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public Entregador getEntregador()
    {
        return entregador;
    }

    public void setEntregador(Entregador entregador)
    {
        this.entregador = entregador;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public float getValorTotal()
    {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal)
    {
        this.valorTotal = valorTotal;
    }

    public String getTempoEntrega()
    {
        return tempoEntrega;
    }

    public void setTempoEntrega(String tempoEntrega)
    {
        this.tempoEntrega = tempoEntrega;
    }

    public int getSenha()
    {
        return senha;
    }

    public void setSenha(int senha)
    {
        this.senha = senha;
    }

    public void setStatus(String s)
    {
        this.status = s;
    }

    public String getStatus()
    {
        return this.status;
    }
}
