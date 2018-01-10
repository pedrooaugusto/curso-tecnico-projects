package Negocio;

public class Monitorado extends Produto {
    
    private Estoque estoque;
    private boolean aVenda;
    
    public Estoque getEstoque()
    {
        return estoque;
    }

    public void setEstoque(Estoque estoque)
    {
        this.estoque = estoque;
    }
    
    public boolean isaVenda()
    {
        return aVenda;
    }

    public void setaVenda(boolean aVenda)
    {
        this.aVenda = aVenda;
    }
    
}
