package com.august.saborrioweb.controle;

import java.util.List;

import com.august.saborrioweb.dao.DaoPedido;
import com.august.saborrioweb.modelo.MesaCard;
import com.august.saborrioweb.modelo.Pedido;

public class ControlePedido {
	private DaoPedido daoP;
	
	public ControlePedido() {
		try 
		{
			this.daoP = new DaoPedido();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<MesaCard> makeCards(){
		return daoP.makeCards();
	}
	
	public Pedido consultar(int codigo){
		return this.daoP.consultar(codigo);
	}
	
	public void alterar(Pedido pedido){
		daoP.alterar(pedido);
	}
	
	public void incluir(Pedido p){
		daoP.incluir(p);
	}
}
