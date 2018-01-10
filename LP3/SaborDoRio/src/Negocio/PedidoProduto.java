package Negocio;

public class PedidoProduto {
    
    private int codigo;
    private Pedido pedido;
    private Produto produto;
    private int quant;
    private String comentario;
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
    
}
