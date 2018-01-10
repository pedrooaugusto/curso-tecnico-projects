package controle;

import java.util.List;

import modelo.Produto;
import dao.DaoProduto;

public class ControleProduto {
	private DaoProduto daoC;
	
	public ControleProduto() {
		try 
		{
			this.daoC = new DaoProduto();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Produto consultar(int codigo){
		return this.daoC.consultar(codigo);
	}
	
	public void incluir(Produto u)throws Exception{
		this.daoC.incluir(u);
	}
	
	public void alterar(Produto u) throws Exception{
		this.daoC.alterar(u);
	}
	
	public void excluir(Produto u) throws Exception{
		this.daoC.excluir(u);
	}
	
	public List<Produto> listar(){
		return this.daoC.listar();
	}
}
