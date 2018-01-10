/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class Rotina {
    private int ID;
    private String shortName;
    private String desc;
    private List<RotinaProduto> items = new ArrayList<RotinaProduto>();
    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public List<RotinaProduto> getItems()
    {
        return items;
    }

    public void setItems(List<RotinaProduto> items)
    {
        this.items = items;
    }
    
    public void addProduto(RotinaProduto item){
        items.add(item);
    }
}
