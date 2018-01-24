package com.august.saborrioweb.bean;



import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.august.saborrioweb.controle.ControleCliente;
import com.august.saborrioweb.controle.ControlePedido;
import com.august.saborrioweb.controle.ControleVenda;
import com.august.saborrioweb.modelo.Pedido;
import com.august.saborrioweb.modelo.Venda;

@ManagedBean(name = "vendaBean")
@RequestScoped
public class VendaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Venda venda = new Venda();
	private int pedidoID = -1;
	private int codCliente = -1;
	private String currentDate = "";
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public int getPedidoID() {
		return pedidoID;
	}

	public void setPedidoID(int pedidoID) {
		this.pedidoID = pedidoID;
	}
	
	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	
	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public void load(){
		if(pedidoID > 0)
		{
			ControlePedido cP = new ControlePedido();
			Pedido p = cP.consultar(pedidoID);
			if(p != null && p.isAberto())
				venda.setPedido(p);
			else{
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath()+"/mesa");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void incluir(){
		ControlePedido cP = new ControlePedido();
		Pedido p = cP.consultar(pedidoID);
		p.setAberto(false);
		cP = new ControlePedido();
		cP.alterar(p);
		venda.setPedido(p);
		if(codCliente > 0){
			ControleCliente c = new ControleCliente();
			venda.setCliente(c.consultar(codCliente));
		}
		venda.setPago(true);
		venda.setValor(p.getPrecoTotal());
		venda.setData(currentDate);
		ControleVenda cv = new ControleVenda();
		cv.incluir(venda);
	}
	
	public List<Venda> listar(){
		return new ControleVenda().listar();
	}
}
