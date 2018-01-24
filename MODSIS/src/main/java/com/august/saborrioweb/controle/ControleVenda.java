package com.august.saborrioweb.controle;

import java.util.List;

import com.august.saborrioweb.modelo.Venda;
import com.august.saborrioweb.dao.DaoVenda;

public class ControleVenda {
	private DaoVenda daoC;
	
	public ControleVenda() {
		try 
		{
			this.daoC = new DaoVenda();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Venda consultar(int codigo){
		return this.daoC.consultar(codigo);
	}
	
	public void incluir(Venda u){
		this.daoC.incluir(u);
	}
	
	public void alterar(Venda u){
		this.daoC.alterar(u);
	}
	
	public void excluir(Venda u){
		this.daoC.excluir(u);
	}
	
	public List<Venda> listar(){
		return this.daoC.listar();
	}
}
