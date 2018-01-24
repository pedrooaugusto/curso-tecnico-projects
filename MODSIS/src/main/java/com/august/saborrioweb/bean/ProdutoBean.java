
package com.august.saborrioweb.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.august.saborrioweb.controle.ControleProduto;
import com.august.saborrioweb.modelo.Produto;

@ManagedBean(name = "produtoBean")
@RequestScoped
public class ProdutoBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title;
    private String searchCod = "";
    private Produto produto = new Produto();
    private boolean buttons[] = {false, true, true};
    
    
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
    	ControleProduto cc = new ControleProduto();
    	produto  = cc.consultar(Integer.parseInt(searchCod));
    	if(produto != null)
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
        ControleProduto cC = new ControleProduto();
        cC.incluir(produto);
        produto.setCodigoString(produto.getID()+"");
		buttons[0] = true;
		buttons[1] = false;
		buttons[2] = false;
    }
	
    public void alterar() throws Exception{
    	ControleProduto cC = new ControleProduto();
        cC.alterar(produto);
    }
	
    public void excluir() throws Exception
    {
        ControleProduto cC = new ControleProduto();
        cC.excluir(produto);
        produto = null;
		searchCod = "";
		buttons[0] = !true;
		buttons[1] = !false;
		buttons[2] = !false;
    }
    
	public void limpar() throws Exception{
		produto = null;
		searchCod = "";
		buttons[0] = !true;
		buttons[1] = !false;
		buttons[2] = !false;
	}
	
	public List<Produto> listar() throws Exception{
        ControleProduto cC = new ControleProduto();
        return cC.listar();
	}
	
	public void load() throws Exception{
		if(produto != null && produto.getID() != 0){
	    	ControleProduto cc = new ControleProduto();
	    	produto = cc.consultar(produto.getID());
	    	if(produto != null)
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
