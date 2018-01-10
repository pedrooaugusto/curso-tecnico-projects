package Negocio;

public class Produto {
    
    private int ID;
    private String nome;
    private float preco;
    private boolean monitorado; //estoque

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public float getPreco()
    {
        return preco;
    }

    public void setPreco(float preco)
    {
        this.preco = preco;
    }    

    public boolean isMonitorado() {
        return monitorado;
    }

    public void setMonitorado(boolean monitorado) {
        this.monitorado = monitorado;
    }
    
}
