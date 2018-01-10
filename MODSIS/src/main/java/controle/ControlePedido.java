/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;
import dao.DaoPedido;
import java.util.List;
import modelo.Pedido;

public class ControlePedido {
    	private DaoPedido daoC;
	
	public ControlePedido() {
		try 
		{
			this.daoC = new DaoPedido();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public Pedido consultarUltimo(){
		return this.daoC.consultarUltimoPedido();
	}
	public Pedido consultar(int codigo){
		return this.daoC.consultar(codigo);
	}
	public Pedido consultarOndeMesa(int mesa){
            return this.daoC.consultarPedidoAtivoMesa(mesa);
        }
	public void incluir(Pedido u)throws Exception{
		this.daoC.incluir(u);
	}
	
	public void alterar(Pedido u) throws Exception{
		this.daoC.alterar(u);
	}
	
	public void excluir(Pedido u) throws Exception{
		this.daoC.excluir(u);
	}
	
	public List<Pedido> listar(){
		return this.daoC.listar();
	}
}
