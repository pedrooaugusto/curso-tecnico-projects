package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.NotaFiscal;
import controle.ControleNotaFiscal;
import controle.ControlePedido;
import controle.ControlePedidoProduto;
import controle.ControlePagamento;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Pagamento;
import modelo.Pedido;
import modelo.Pedido_Produto;


@ManagedBean(name = "NotaFiscalBean")
@RequestScoped
public class NotaFiscalBean extends BasicoBean{
    
    private static final long serialVersionUID = 1L;
    private NotaFiscal notafiscal = new NotaFiscal();
    private String message = "Cadastro de NotaFiscals";
    public List<NotaFiscal> lista = new ArrayList<>();
    public String selec;
    private Pagamento currentPagamento = new Pagamento();
    private Pedido currentPedido = new Pedido();
    private int currentMesa;
    private int nCliente;
    public NotaFiscalBean() throws Exception {
    
    }
    
    public void loadPedido()
    {
        ControlePedido cP = new ControlePedido();
        currentPedido = cP.consultarOndeMesa(currentMesa);
        ControlePedidoProduto cPP = new ControlePedidoProduto();
        List<Pedido_Produto> l = cPP.listarItemsPedido(currentPedido.getID());
        float tot = 0;
        for(Pedido_Produto d : l)
            tot+=d.getQuant() * d.getProduto().getPreco();
        tot = tot*100;
        tot = Math.round(tot);
        tot = tot /100;
        currentPagamento = new Pagamento();
        currentPagamento.setPago(true);
        currentPagamento.setValor(tot);
        currentPagamento.setData(currentData());
        currentPagamento.setPedido(currentPedido);
    }

    public int getnCliente()
    {
        return nCliente;
    }

    public void setnCliente(int nCliente)
    {
        this.nCliente = nCliente;
    }

    public String currentData()
    {
        DateFormat dF = DateFormat.getDateInstance(); 
        return dF.format(new Date());
    }
    public int getCurrentMesa()
    {
        return currentMesa;
    }

    public void setCurrentMesa(int currentMesa)
    {
        this.currentMesa = currentMesa;
    }
    
    public Pedido getCurrentPedido()
    {
        return currentPedido;
    }

    public void setCurrentPedido(Pedido currentPedido)
    {
        this.currentPedido = currentPedido;
    }

    
    public Pagamento getCurrentPagamento()
    {
        return currentPagamento;
    }

    public void setCurrentPagamento(Pagamento currentPagamento)
    {
        this.currentPagamento = currentPagamento;
    }

    public String col(){
        return "fecharConta.xhtml";
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
        int a = currentMesa;
        ControlePedido cP = new ControlePedido();
        currentPedido = cP.consultarOndeMesa(currentMesa);
        ControlePagamento cC = new ControlePagamento();
        currentPagamento.setPedido(currentPedido);
        cC.incluir(currentPagamento);
        cP = new ControlePedido();
        //currentPedido = cP.consultarOndeMesa(a);
        currentPedido.setAberto(false);
        cP.alterar(currentPedido);
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
