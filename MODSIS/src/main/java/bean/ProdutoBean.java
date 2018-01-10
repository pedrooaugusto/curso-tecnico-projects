package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.Produto;
import controle.ControleProduto;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "ProdutoBean")
@RequestScoped
public class ProdutoBean extends BasicoBean{
    
    private static final long serialVersionUID = 1L;
    private Produto produto = new Produto();
    private String message = "Cadastro de Produtos";
    public List<Produto> lista = new ArrayList<>();
    public String selec; 
    public ProdutoBean() throws Exception{
        ControleProduto cC = new ControleProduto();
        lista = cC.listar();
    }

    public List<Produto> getLista()
    {
        return lista;
    }

    public void setLista(List<Produto> lista)
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
    
    public Produto getProduto()
    {
        return produto;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    @Override
    public void buscar()
    {
        ControleProduto cC;
        cC = new ControleProduto();
        produto = cC.consultar(produto.getID());
        if (produto != null)
        {
            existente();
            message = "Produto encontrado!";
            currentColors = corExistente;
        } else
        {
            message = "Produto inexistente!";
            inexistente();
            currentColors = corInexistente;
        }
    }

    @Override
    public void incluir() throws Exception
    {
        ControleProduto cC = new ControleProduto();
        cC.incluir(produto);
        message = "Produto incluido!";
        existente();
    }

    @Override
    public void excluir() throws Exception
    {
        ControleProduto cC = new ControleProduto();
        cC.excluir(produto);
        clear();
        message = "Produto excluido!";
    }

    @Override
    public void editar() throws Exception
    {
        existente();
        ControleProduto cC = new ControleProduto();
        cC.alterar(produto);
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
        produto = null;
        inexistente();
    }
}
