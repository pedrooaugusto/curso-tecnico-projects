package bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.ControleCliente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modelo.Cliente;

@ManagedBean(name = "ClienteBean")
@RequestScoped
public class ClienteBean extends BasicoBean{
    private static final long serialVersionUID = 1L;
    private Cliente cliente = new Cliente();
    private String message = "Cadastro de clientes";
    private List<Cliente> lista = new ArrayList<Cliente>();

    public ClienteBean()
    {
        ControleCliente cC = new ControleCliente();
        lista = cC.listar();
    }
    
    public Cliente getCliente()
    {
        return cliente;
    }
    
    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public List<Cliente> getLista()
    {
        return lista;
    }

    public void setLista(List<Cliente> lista)
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

    @Override
    public void incluir() throws Exception
    {
        ControleCliente cC = new ControleCliente();
        cC.incluir(cliente);
        message = "Cliente incluir!";
        existente();
    }

    @Override
    public void editar() throws Exception
    {
        existente();
        ControleCliente cC = new ControleCliente();
        cC.alterar(cliente);
        message = "Alterado com sucesso!";
    }

    @Override
    public void excluir() throws Exception
    {
        ControleCliente cC = new ControleCliente();
        cC.excluir(cliente);
        clear();
        message = "Cliente excluido!";
    }

    @Override
    public void buscar()
    {
        ControleCliente cC;
        cC = new ControleCliente();
        cliente = cC.consultar(cliente.getCodigo());
        if (cliente != null)
        {
            existente();
            message = "Cliente encontrado!";
            currentColors = corExistente;
        } else
        {
            message = "Cliente inexistente!";
            inexistente();
            currentColors = corInexistente;
        }
    }

    @Override
    public void clear()
    {
        cliente = null;
        inexistente();
    }
    
}
