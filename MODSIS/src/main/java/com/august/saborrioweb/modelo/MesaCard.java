package com.august.saborrioweb.modelo;

import java.text.NumberFormat;

public class MesaCard {
	private int numero;
	private int pedidoId;
	private double totalPrice;
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(int pedidoId) {
		this.pedidoId = pedidoId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String precoS(){
		return NumberFormat.getCurrencyInstance().format(totalPrice);
	}
	
}
