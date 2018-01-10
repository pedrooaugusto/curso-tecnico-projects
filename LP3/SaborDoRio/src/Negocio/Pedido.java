package Negocio;

public class Pedido {
    private int ID; //cod do pedido
    private int mesa; //mesa do qual o pedido pertence
    private String cliente;
    private boolean aberto;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public boolean isAberto()
    {
        return aberto;
    }

    public void setAberto(boolean aberto)
    {
        this.aberto = aberto;
    }
    
    
}
