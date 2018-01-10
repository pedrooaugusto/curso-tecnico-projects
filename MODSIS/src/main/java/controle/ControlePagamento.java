package controle;

import java.util.List;

import dao.DaoPagamento;
import modelo.Pagamento;

public class ControlePagamento {
	private DaoPagamento daoC;
	
	public ControlePagamento() {
		try 
		{
			this.daoC = new DaoPagamento();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Pagamento consultar(int codigo){
		return this.daoC.consultar(codigo);
	}
	
	public void incluir(Pagamento u)throws Exception{
		this.daoC.incluir(u);
	}
	
	public void alterar(Pagamento u) throws Exception{
		this.daoC.alterar(u);
	}
	
	public void excluir(Pagamento u) throws Exception{
		this.daoC.excluir(u);
	}
	
	public List<Pagamento> listar(){
		return this.daoC.listar();
	}
}
