package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.Pedido;
import controle.ControlePedido;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "PedidoBean")
@RequestScoped
public class PedidoBean extends BasicoBean{
    
    private static final long serialVersionUID = 1L;
    private Pedido pedido = new Pedido();
    private String message = "Cadastro de Pedidos";
    public List<Pedido> lista = new ArrayList<>();
    public String selec; 
    
    public PedidoBean() throws Exception{
        ControlePedido cC = new ControlePedido();
        lista = cC.listar();
    }

    public List<Pedido> getLista()
    {
        return lista;
    }

    public void setLista(List<Pedido> lista)
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
    
    public Pedido getPedido()
    {
        return pedido;
    }

    public void setPedido(Pedido pedido)
    {
        this.pedido = pedido;
    }

    @Override
    public void buscar()
    {
        ControlePedido cC;
        cC = new ControlePedido();
        pedido = cC.consultar(pedido.getID());
        if (pedido != null)
        {
            existente();
            message = "Pedido encontrado!";
            currentColors = corExistente;
        } else
        {
            message = "Pedido inexistente!";
            inexistente();
            currentColors = corInexistente;
        }
    }

    @Override
    public void incluir() throws Exception
    {
        ControlePedido cC = new ControlePedido();
        cC.incluir(pedido);
        message = "Pedido incluido!";
        existente();
    }

    @Override
    public void excluir() throws Exception
    {
        ControlePedido cC = new ControlePedido();
        cC.excluir(pedido);
        clear();
        message = "Pedido excluido!";
    }

    @Override
    public void editar() throws Exception
    {
        existente();
        ControlePedido cC = new ControlePedido();
        cC.alterar(pedido);
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
        pedido = null;
        inexistente();
    }
}
