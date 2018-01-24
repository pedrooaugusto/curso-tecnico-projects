
package com.august.saborrioweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.august.saborrioweb.controle.ControlePedido;
import com.august.saborrioweb.controle.ControlePedidoProduto;
import com.august.saborrioweb.controle.ControleProduto;
import com.august.saborrioweb.modelo.MesaCard;
import com.august.saborrioweb.modelo.Pedido;
import com.august.saborrioweb.modelo.Pedido_Produto;

@ManagedBean(name = "mesaBean")
@RequestScoped
public class MesaBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private Pedido pedido = new Pedido();
    private Pedido_Produto pedido_produto = new Pedido_Produto();
    
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
    
    public Pedido_Produto getPedido_produto() {
		return pedido_produto;
	}

	public void setPedido_produto(Pedido_Produto pedido_produto) {
		this.pedido_produto = pedido_produto;
	}

	public List<MesaCard> listMesas() throws Exception{
        ControlePedido cC = new ControlePedido();
        return cC.makeCards();
	}

	public void selectMesa(int idPedido, int numMesa){
		if(idPedido != 0){
			System.out.println(idPedido);
			ControlePedido cP = new ControlePedido();
			pedido = cP.consultar(idPedido);
		}
		else{
			pedido = new Pedido();
			pedido.setMesa(numMesa);
			pedido.setPrecoTotal(0);
		}
	}
	
	public void addItem(int a, int b){
		if(b == 0){
			pedido = new Pedido();
			pedido.setMesa(a);
			pedido.setPrecoTotal(0);
			pedido.setAberto(true);
			ControlePedido cp = new ControlePedido();
			cp.incluir(pedido);
			ControleProduto cpr = new ControleProduto();
			pedido_produto.setPedido(pedido);
			pedido_produto.setProduto(cpr.consultar(pedido_produto.getCodProduto()));
			pedido_produto.setNumeracao(pedido.getItems().size()+1);
			ControlePedidoProduto cpp = new ControlePedidoProduto();
			cpp.incluir(pedido_produto);
			pedido.addicionarItem(pedido_produto);
		}
		else{
			ControlePedido cp = new ControlePedido();
			pedido = cp.consultar(b);
			ControleProduto cpr = new ControleProduto();
			pedido_produto.setPedido(pedido);
			pedido_produto.setProduto(cpr.consultar(pedido_produto.getCodProduto()));
			pedido_produto.setNumeracao(pedido.getItems().size()+1);
			ControlePedidoProduto cpp = new ControlePedidoProduto();
			cpp.incluir(pedido_produto);
			pedido.addicionarItem(pedido_produto);
		}
	}
}
