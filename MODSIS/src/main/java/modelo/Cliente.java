package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="cliente")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int codigo;
    private String nome;
    private String tel; //telefone fixo
    private String cel; //celular mobile
    private String email;
    private String endereco;
    private String diaPagamento;
    private String estadoConta;
    
    public String getDiaPagamento()
    {
        return diaPagamento;
    }

    public void setDiaPagamento(String diaPagamento)
    {
        this.diaPagamento = diaPagamento;
    }

    public String getEstadoConta()
    {
        return estadoConta;
    }

    public void setEstadoConta(String estadoConta)
    {
        this.estadoConta = estadoConta;
    }
    
    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getTel()
    {
        return tel;
    }

    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getCel()
    {
        return cel;
    }

    public void setCel(String cel)
    {
        this.cel = cel;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

	public String getCodigoString() {
		return codigo==0? "": codigo+"";
	}

	public void setCodigoString(String codigoString) {
		this.codigo = Integer.parseInt(codigoString.equals("") ? "0" : codigoString);
	}
    
}
