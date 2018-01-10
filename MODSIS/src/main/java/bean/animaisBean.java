package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "animaisBean")
@RequestScoped
public class animaisBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final String names[] = {"Vaca", "Cachorro", "Gato"};
	private List<String> animais = new ArrayList<String>(Arrays.asList(names));
	
	private String nome = "Pedro";
	
	public String getNome(){
		if(nome == "")
			return "Vanilla";
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public List<String> getAnimais(){
		return animais;
	}
	
	public void setAnimais(List<String> a){
		this.animais = a;
	}
}
