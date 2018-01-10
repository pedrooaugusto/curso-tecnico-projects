/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * Essa classe representa um bean básico
 * já que todos os beans tem métodos e variaveis
 * iguais é mais fácil criar um bean, no caso este,
 * e colocar o que vai ser comum a todos os outros aqui;
 * e depois criar um Produto extends BasicoBean.
 */
public abstract class BasicoBean implements Serializable{
    
    protected boolean btnIncluir = false;
    protected boolean btnAlterar = false;
    protected boolean btnExcluir = false;
    protected final String corExistente[] = {"rgb(7, 114, 175)", "#bec7f1"};
    protected final String corInexistente[] = {"#da1212", "#f1bebe"};
    protected String[] currentColors = corExistente;
    
    public abstract void incluir() throws Exception;
    public abstract void editar() throws Exception;
    public abstract void excluir() throws Exception;
    public abstract void buscar();
    public abstract void clear();
    
    protected void existente()
    /*
        Ativa e desativa botões depois de uma
        busca que retornou em algo
    */
    {
    	setBtnAlterar(false);
    	setBtnIncluir(true);
    	setBtnExcluir(false);
    }
    protected void inexistente()
    /*
        Ativa e desativa botões depois de uma
        busca com nenhum resultado
    */
    {
    	setBtnAlterar(true);
	setBtnIncluir(false);
	setBtnExcluir(true);
    }
    /*
        Abaixo todos os gets and sets
    */
    public boolean isBtnIncluir()
    {
        return btnIncluir;
    }

    public void setBtnIncluir(boolean btnIncluir)
    {
        this.btnIncluir = btnIncluir;
    }

    public boolean isBtnAlterar()
    {
        return btnAlterar;
    }

    public void setBtnAlterar(boolean btnAlterar)
    {
        this.btnAlterar = btnAlterar;
    }

    public boolean isBtnExcluir()
    {
        return btnExcluir;
    }

    public void setBtnExcluir(boolean btnExcluir)
    {
        this.btnExcluir = btnExcluir;
    }

    public String[] getCurrentColors()
    {
        return currentColors;
    }

    public void setCurrentColors(String[] currentColors)
    {
        this.currentColors = currentColors;
    }
}
