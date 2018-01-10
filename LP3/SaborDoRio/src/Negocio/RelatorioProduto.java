package Negocio;

public class RelatorioProduto {
    
    private Produto produto;
    private float quantidade; //quantidade vendida desde a dataInicio
    private String dataInicio; //início do relatório deste produto

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    
}
