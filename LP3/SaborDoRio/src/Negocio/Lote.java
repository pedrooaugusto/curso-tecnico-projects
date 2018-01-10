package Negocio;

public class Lote {
    
    private int ID;
    private Monitorado monitorado;
    private String dataValidade;
    private float quantidadeInicial;
    private float quantidadeAtual;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Monitorado getMonitorado() {
        return monitorado;
    }

    public void setMonitorado(Monitorado monitorado) {
        this.monitorado = monitorado;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public float getQuantidadeInicial() {
        return quantidadeInicial;
    }

    public void setQuantidadeInicial(float quantidadeInicial) {
        this.quantidadeInicial = quantidadeInicial;
    }

    public float getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(float quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }
}
