package Negocio;

public class Pagamento {
    
    private int ID;
    private boolean pago;
    private String data;
    private String realizacaoPagamento;
    private float valor;
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getRealizacaoPagamento()
    {
        return realizacaoPagamento;
    }

    public void setRealizacaoPagamento(String realizacaoPagamento)
    {
        this.realizacaoPagamento = realizacaoPagamento;
    }
    
}
