/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;
import dao.DaoPedidoProduto;
import java.util.List;
import modelo.Pedido_Produto;

public class ControlePedidoProduto {
    	private DaoPedidoProduto daoC;
	
	public ControlePedidoProduto() {
		try 
		{
			this.daoC = new DaoPedidoProduto();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Pedido_Produto consultar(int codigo){
		return this.daoC.consultar(codigo);
	}
	
	public void incluir(Pedido_Produto u)throws Exception{
		this.daoC.incluir(u);
	}
	
	public void alterar(Pedido_Produto u) throws Exception{
		this.daoC.alterar(u);
	}
	
	public void excluir(Pedido_Produto u) throws Exception{
		this.daoC.excluir(u);
	}
	
	public List<Pedido_Produto> listar(){
		return this.daoC.listar();
	}
        public List<Pedido_Produto> listarItemsPedido(int pedido){
            //Retorna todos os items do pedido x
            return this.daoC.listarDoPedido(pedido);
	}
}
