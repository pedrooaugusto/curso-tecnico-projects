package Negocio;

public class Estoque {
    
    private float quantidadeAtual;
    private float quantidadeMinima;
    private float quantidadeMaxima;
    private String unidadeMedida;// {uni, kg, l, m}

    public float getQuantidadeAtual()
    {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(float quantidadeAtual)
    {
        this.quantidadeAtual = quantidadeAtual;
    }

    public float getQuantidadeMinima()
    {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(float quantidadeMinima)
    {
        this.quantidadeMinima = quantidadeMinima;
    }

    public float getQuantidadeMaxima()
    {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(float quantidadeMaxima)
    {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public String getUnidadeMedida()
    {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida)
    {
        this.unidadeMedida = unidadeMedida;
    }
    
    public void adicionar(float valor)
    {
        this.quantidadeAtual += valor;
    }
    
    public void subtrair(float valor)
    {
        this.quantidadeAtual -= valor;
    }
}
