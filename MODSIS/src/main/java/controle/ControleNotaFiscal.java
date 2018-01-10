package controle;

import java.util.List;

import dao.DaoNotaFiscal;
import modelo.NotaFiscal;

public class ControleNotaFiscal {
	private DaoNotaFiscal daoC;
	
	public ControleNotaFiscal() {
		try 
		{
			this.daoC = new DaoNotaFiscal();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public NotaFiscal consultar(int codigo){
		return this.daoC.consultar(codigo);
	}
	
	public void incluir(NotaFiscal u)throws Exception{
		this.daoC.incluir(u);
	}
	
	public void alterar(NotaFiscal u) throws Exception{
		this.daoC.alterar(u);
	}
	
	public void excluir(NotaFiscal u) throws Exception{
		this.daoC.excluir(u);
	}
	
	public List<NotaFiscal> listar(){
		return this.daoC.listar();
	}
}
