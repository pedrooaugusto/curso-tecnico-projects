package Negocio;

public class Cliente {

    private int codigo;
    private String nome;
    private String tel; //telefone fixo
    private String cel; //celular mobile
    private String email;
    private String endereco;
    private Empresa empresa;
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

    public Empresa getEmpresa()
    {
        return empresa;
    }

    public void setEmpresa(Empresa empresa)
    {
        this.empresa = empresa;
    }

}
