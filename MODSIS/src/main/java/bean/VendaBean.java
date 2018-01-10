package bean;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controle.ControleCliente;
import controle.ControlePedido;
import controle.ControlePedidoProduto;
import controle.ControleProduto;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Pedido_Produto;
import modelo.Produto;

@ManagedBean(name = "VendaBean")
@ViewScoped
public class VendaBean implements Serializable{
    private Pedido pedido = new Pedido();
    private String message = "Cadastro de vendas";
    private List<Pedido> lista = new ArrayList<Pedido>();
    private Pedido_Produto item = new Pedido_Produto();
    private String destinoSalvar;
    private String acao = "Incluir";
    
    public VendaBean() 
    {
    	lista = new ControlePedido().listar();
	}
    
    public void loadVenda() throws Exception
    {
    	if(pedido.getID() != 0){
    		ControlePedido cP = new ControlePedido();
    		pedido = cP.consultar(pedido.getID());
    		acao = "Alterar";
    	}
    	else
    	{
    		acao = "Incluir";
    		pedido  = new Pedido();
    	}
    }
    
    public void adicionarItem() throws Exception
    {
    	ControleProduto cP = new ControleProduto();
    	Produto produto = cP.consultar(item.getCodProduto());
    	item.setProduto(produto);
    	int tam = pedido.getItems().size();
    	if(tam == 0)
    		item.setNumeracao(1);
    	else
    		item.setNumeracao(pedido.getItems().get(tam-1).getNumeracao()+1);
    	item.setComentario("none");
		pedido.addicionarItem(item);
    	if(!acao.equals("Incluir"))
    	{
    		ControlePedidoProduto cPP = new ControlePedidoProduto();
    		item.setPedido(pedido);
    		cPP.incluir(item);
    	}
    	item = new Pedido_Produto();
    }
    
    public void deletarItem(int numeracao) throws Exception
    {
    	int index = -1;
    	for(int i = 0; i < pedido.getItems().size(); i++)
    		if(pedido.getItems().get(i).getNumeracao() == numeracao)
    		{
    			index = i;
    			break;
    		}
    	Pedido_Produto pp = pedido.getItems().get(index);
    	pedido.deletarItem(index);
    	if(!acao.equals("Incluir"))
    	{
    		ControlePedidoProduto cPP = new ControlePedidoProduto();
    		cPP.excluir(pp);
    	}
    }
    
    public void limpar()
    {
    	pedido.getItems().clear();
    	item = new Pedido_Produto();
    }
    
    public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String incluirVenda() throws Exception
    {
    	ControleCliente cC = new ControleCliente();
    	Cliente cliente = cC.consultar(pedido.getCodCliente());
    	ControlePedido cP = new ControlePedido();
    	pedido.setAberto(false);
    	pedido.setCliente(cliente);
    	pedido.setMesa(0);
    	if(acao.equals("Incluir"))
    	{
	    	cP.incluir(pedido);
	    	cP = new ControlePedido();
	    	Pedido ped_banco = cP.consultarUltimo();
	    	for(Pedido_Produto p : pedido.getItems()){
	    		p.setPedido(ped_banco);
	    		ControlePedidoProduto cPP = new ControlePedidoProduto();
	    		cPP.incluir(p);
	    	}
    	}
    	else
    	{
    		cP.alterar(pedido);
    	}
    	pedido = new Pedido();
    	return "finalizado.xhtml";
    }

	public Pedido_Produto getItem() {
		return item;
	}
	
	public void setItem(Pedido_Produto item) {
		this.item = item;
	}
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String novo() {
		this.destinoSalvar = "VendaSucesso";
		this.pedido = new Pedido();
		return "index";
	}

	public String excluir(int nVenda) throws Exception
	{
		ControlePedido cP = new ControlePedido();
		for(Pedido_Produto p : cP.consultar(nVenda).getItems())
		{   
			ControlePedidoProduto cPP = new ControlePedidoProduto();
			cPP.excluir(p);
		}
		cP = new ControlePedido();
		Pedido dod = cP.consultar(nVenda);
		cP = new ControlePedido();
		cP.excluir(dod);
		return "listarVendas.xhtml";
	}
	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
	
	public List<Pedido> getLista() {
		return lista;
	}
	
	public void setLista(List<Pedido> lista) {
		this.lista = lista;
	}
	
}
