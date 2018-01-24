
package com.august.saborrioweb.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import com.august.saborrioweb.controle.ControleCliente;

@ManagedBean(name = "mainBean")
@RequestScoped
public class MainBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private String title = "Sabor do Rio";
    
    
    public MainBean(){
    	new ControleCliente();
    }
    
    public void setTitle(String title){
    	this.title = title;
    }
    
    public String getTitle(){
    	return this.title;
    }
}
