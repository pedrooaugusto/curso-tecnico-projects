/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

/**
 *
 * @author Pedro
 */
public class PreparedString
{
    private String instrucao;
    private String variaveis[];
    public PreparedString(String instr)
    {
        int numVars = count(instr, "?");
        variaveis = new String[numVars];
        instrucao = instr.replace("?", "%s");
    }
    
    public void setDouble(int i, double valor)
    {
        variaveis[i-1] = ""+valor+"";
    }
    
    public void setFloat(int i, float valor)
    {
        variaveis[i-1] = ""+valor+"";
    }
    
    public void setString(int i, String valor)
    {
        String semApostrfos = valor.replace("'", "");
        variaveis[i-1] = "'"+semApostrfos+"'";
    }
    
    public void setBoolean(int i, boolean valor)
    {
        variaveis[i-1] = ""+valor+"";
    }
    
    public void setInt(int i, int valor)
    {
        variaveis[i-1] = ""+valor+"";
    }
    public String getPreparedString()
    {
        String resul = "";
        try
        {
            resul = String.format(instrucao, (Object[]) variaveis);
            if(resul.contains("null"))
            {
                System.out.println("Existem variaveis que não foram preenchidas\n"+resul);
            }   
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        return resul;
    }
    private int count(String instr, String oq)
    {
        int quantos = 0;
        for(int i = 0; i < instr.length(); i++)
        {
            if(instr.substring(i, i+1).equals(oq))
            {
                quantos++;
            }
        }
        return quantos;
    }

}
