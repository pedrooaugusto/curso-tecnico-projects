package controle;

import java.util.List;

import dao.DaoCliente;
import modelo.Cliente;

public class ControleCliente {
	private DaoCliente daoC;
	
	public ControleCliente() {
		try 
		{
			this.daoC = new DaoCliente();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Cliente consultar(int codigo){
		return this.daoC.consultar(codigo);
	}
	
	public void incluir(Cliente u)throws Exception{
		this.daoC.incluir(u);
	}
	
	public void alterar(Cliente u) throws Exception{
		this.daoC.alterar(u);
	}
	
	public void excluir(Cliente u) throws Exception{
		this.daoC.excluir(u);
	}
	
	public List<Cliente> listar(){
		return this.daoC.listar();
	}
}
