package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.Pagamento;
import controle.ControlePagamento;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "PagamentoBean")
@RequestScoped
public class PagamentoBean extends BasicoBean{
    
    private static final long serialVersionUID = 1L;
    private Pagamento pagamento = new Pagamento();
    private String message = "Cadastro de Pagamentos";
    public List<Pagamento> lista = new ArrayList<>();
    public String selec; 
    
    public PagamentoBean() throws Exception{
        ControlePagamento cC = new ControlePagamento();
        lista = cC.listar();
    }

    public List<Pagamento> getLista()
    {
        return lista;
    }

    public void setLista(List<Pagamento> lista)
    {
        this.lista = lista;
    }
    
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public Pagamento getPagamento()
    {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento)
    {
        this.pagamento = pagamento;
    }

    @Override
    public void buscar()
    {
        ControlePagamento cC;
        cC = new ControlePagamento();
        pagamento = cC.consultar(pagamento.getID());
        if (pagamento != null)
        {
            existente();
            message = "Pagamento encontrado!";
            currentColors = corExistente;
        } else
        {
            message = "Pagamento inexistente!";
            inexistente();
            currentColors = corInexistente;
        }
    }

    @Override
    public void incluir() throws Exception
    {
        ControlePagamento cC = new ControlePagamento();
        cC.incluir(pagamento);
        message = "Pagamento incluido!";
        existente();
    }

    @Override
    public void excluir() throws Exception
    {
        ControlePagamento cC = new ControlePagamento();
        cC.excluir(pagamento);
        clear();
        message = "Pagamento excluido!";
    }

    @Override
    public void editar() throws Exception
    {
        existente();
        ControlePagamento cC = new ControlePagamento();
        cC.alterar(pagamento);
        message = "Alterado com sucesso!";
    }
    
    public void nothin()
    {
        //selec = "BLUE DRAGON!";
    }

    public String getSelec()
    {
        return selec;
    }

    public void setSelec(String selec)
    {
        this.selec = selec;
    }
    
    @Override
    public void clear()
    {
        pagamento = null;
        inexistente();
    }
}
