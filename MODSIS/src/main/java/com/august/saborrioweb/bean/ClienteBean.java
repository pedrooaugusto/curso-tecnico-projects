
package com.august.saborrioweb.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.august.saborrioweb.controle.ControleCliente;
import com.august.saborrioweb.modelo.Cliente;

@ManagedBean(name = "clienteBean")
@RequestScoped
public class ClienteBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private String searchCod = "";
    private Cliente cliente = new Cliente();
    private boolean buttons[] = {false, true, true};
    
    public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getSearchCod() {
		return searchCod;
	}

	public void setSearchCod(String searchCod) {
		this.searchCod = searchCod;
	}

	public void setTitle(String title){
    	this.title = title;
    }
    
    public String getTitle(){
    	return this.title;
    }
    
    public boolean[] getButtons() {
		return buttons;
	}

	public void setButtons(boolean[] buttons) {
		this.buttons = buttons;
	}

	public void buscar() throws Exception{
    	ControleCliente cc = new ControleCliente();
    	cliente  = cc.consultar(Integer.parseInt(searchCod));
    	if(cliente != null)
    	{
    		buttons[0] = true;
    		buttons[1] = false;
    		buttons[2] = false;
    	}
    	else
    	{
    		searchCod = "";
    		buttons[0] = !true;
    		buttons[1] = !false;
    		buttons[2] = !false;
    	}
    }
	
    public void incluir() throws Exception{
        ControleCliente cC = new ControleCliente();
        cC.incluir(cliente);
        cliente.setCodigoString(cliente.getCodigo()+"");
		buttons[0] = true;
		buttons[1] = false;
		buttons[2] = false;
    }
	
    public void alterar() throws Exception{
    	ControleCliente cC = new ControleCliente();
        cC.alterar(cliente);
    }
	
    public void excluir() throws Exception
    {
        ControleCliente cC = new ControleCliente();
        cC.excluir(cliente);
        cliente = null;
		searchCod = "";
		buttons[0] = !true;
		buttons[1] = !false;
		buttons[2] = !false;
    }
    
	public void limpar() throws Exception{
		cliente = null;
		searchCod = "";
		buttons[0] = !true;
		buttons[1] = !false;
		buttons[2] = !false;
	}
	
	public List<Cliente> listar() throws Exception{
        ControleCliente cC = new ControleCliente();
        return cC.listar();
	}
	
	public void load() throws Exception{
		if(cliente != null && cliente.getCodigo() != 0){
	    	ControleCliente cc = new ControleCliente();
	    	cliente  = cc.consultar(cliente.getCodigo());
	    	if(cliente != null)
	    	{
	    		buttons[0] = true;
	    		buttons[1] = false;
	    		buttons[2] = false;
	    	}
	    	else
	    	{
	    		searchCod = "";
	    		buttons[0] = !true;
	    		buttons[1] = !false;
	    		buttons[2] = !false;
	    	}
		}
	}
}
