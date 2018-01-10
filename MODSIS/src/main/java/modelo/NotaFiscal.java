package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name="notaFiscal")
public class NotaFiscal implements Serializable { //antiga classe Mesa

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int ID;
    
    @ManyToOne
    @JoinColumn(name = "cliente", nullable = true)
    private Cliente cliente;
    
    @OneToOne
    @JoinColumn(name = "pagamento", nullable = false, unique = true)
    private Pagamento pagamento;
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
}
