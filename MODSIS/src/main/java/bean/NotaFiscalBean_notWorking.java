package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.NotaFiscal;
import controle.ControleNotaFiscal;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "NotaFiscalBean_notWorking")
@RequestScoped
public class NotaFiscalBean_notWorking extends BasicoBean{
    
    private static final long serialVersionUID = 1L;
    private NotaFiscal notafiscal = new NotaFiscal();
    private String message = "Cadastro de NotaFiscals";
    public List<NotaFiscal> lista = new ArrayList<>();
    public String selec; 
    
    public NotaFiscalBean_notWorking() throws Exception{
        ControleNotaFiscal cC = new ControleNotaFiscal();
        lista = cC.listar();
    }

    public List<NotaFiscal> getLista()
    {
        return lista;
    }

    public void setLista(List<NotaFiscal> lista)
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
    
    public NotaFiscal getNotaFiscal()
    {
        return notafiscal;
    }

    public void setNotaFiscal(NotaFiscal notafiscal)
    {
        this.notafiscal = notafiscal;
    }

    @Override
    public void buscar()
    {
        ControleNotaFiscal cC;
        cC = new ControleNotaFiscal();
        notafiscal = cC.consultar(notafiscal.getID());
        if (notafiscal != null)
        {
            existente();
            message = "NotaFiscal encontrado!";
            currentColors = corExistente;
        } else
        {
            message = "NotaFiscal inexistente!";
            inexistente();
            currentColors = corInexistente;
        }
    }

    @Override
    public void incluir() throws Exception
    {
        ControleNotaFiscal cC = new ControleNotaFiscal();
        cC.incluir(notafiscal);
        message = "NotaFiscal incluido!";
        existente();
    }

    @Override
    public void excluir() throws Exception
    {
        ControleNotaFiscal cC = new ControleNotaFiscal();
        cC.excluir(notafiscal);
        clear();
        message = "NotaFiscal excluido!";
    }

    @Override
    public void editar() throws Exception
    {
        existente();
        ControleNotaFiscal cC = new ControleNotaFiscal();
        cC.alterar(notafiscal);
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
        notafiscal = null;
        inexistente();
    }
}
