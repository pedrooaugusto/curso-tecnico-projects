package com.august.saborrioweb.modelo;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name="pedido_produto")
public class Pedido_Produto implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int codigo;
    @ManyToOne
    @JoinColumn(name = "pedido", nullable = false)
    private Pedido pedido;
    @Transient
    private int codProduto;
    @ManyToOne
    @JoinColumn(name = "produto", nullable = false)
    private Produto produto;
    private int quant = 1;
    private String comentario = "";
    private int numeracao;
    public int getNumeracao()
    {
        return numeracao;
    }

    public void setNumeracao(int numeracao)
    {
        this.numeracao = numeracao;
    }
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    public String total()
    {
        return NumberFormat.getCurrencyInstance().format(quant * produto.getPreco());
    }

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
    
}
