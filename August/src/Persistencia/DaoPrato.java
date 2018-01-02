/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import Negocio.Ingrediente;
import Negocio.Prato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class DaoPrato implements DaoFabricaObjetos
{

    @Override
    public boolean incluir(Object o)
    {
        Prato p = (Prato) o;
        DaoManipular dM = new DaoManipular();
        DaoPin dPin = new DaoPin();
        String sql = "insert into Prato(ID_pra, Nome_pra, Secao_pra, Preco_pra,"
                  + " ModoPreparo_pra, Calorias_pra) values (?, ?, ?, ?, ?, ?)";
        PreparedString inst = new PreparedString(sql);
        inst.setInt(1, p.getID());
        inst.setString(2, p.getNome());
        inst.setString(3, p.getSecao());
        inst.setFloat(4, p.getPreco());
        inst.setString(5, p.getModoPreparo());
        inst.setFloat(6, p.getCalorias());
        return ((dM.getCadastrar(inst.getPreparedString())) && (dPin.incluir(p)));
    }

    @Override
    public boolean alterar(Object o)
    {
        Prato p = (Prato) o;
        DaoManipular dM = new DaoManipular();
        String sql = "update Prato set Calorias_pra = ?, Nome_pra = ?, Secao_pra = ?,"
                   + " Preco_pra = ?, ModoPreparo_pra = ? where ID_pra = ?";
        PreparedString inst = new PreparedString(sql);
        inst.setInt(6, p.getID());
        inst.setString(2, p.getNome());
        inst.setString(3, p.getSecao());
        inst.setFloat(4, p.getPreco());
        inst.setString(5, p.getModoPreparo());
        inst.setFloat(1, p.getCalorias());
        return (dM.getCadastrar(inst.getPreparedString()));
    }

    @Override
    public boolean excluir(int chave1, int chave2)
    {
        DaoManipular dM = new DaoManipular();
        DaoPin dPin = new DaoPin();
        String sql = "delete from Prato where ID_pra = "+chave1;
        dPin.excluir(chave1, chave2);
        return (dM.getCadastrar(sql));
    }

    @Override
    public Prato busca(int chave1, int chave2)
    {
        String inst = "select * from Prato where ID_pra = "+chave1;
        Prato p = null;
        List quant;
        List ing;
        DaoPin dPin = new DaoPin();
        try
        {
           DaoManipular dM = new DaoManipular(); 
           ResultSet rS = dM.getConsultar(inst);
           if(rS.next()) 
           {
               p = new Prato();
               p.setID(rS.getInt("ID_pra"));
               p.setNome(rS.getString("Nome_pra"));
               p.setFoto(rS.getString("FotoURL_pra"));
               p.setPreco(rS.getFloat("Preco_pra"));
               p.setSecao(rS.getString("Secao_pra"));
               p.setModoPreparo(rS.getString("ModoPreparo_pra"));
               Object qAndI[] = dPin.carrega(p.getID());
               quant = (List<Float>)qAndI[0];
               ing = (List<Ingrediente>)qAndI[1];
               p.setIngrediente(ing);
               p.setQuantidade(quant);
           }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Object> carrega()
    {
       String inst="select * from Lote order by Numero_lot";
       List<Object> lista = new ArrayList<>();
       Prato p;
       DaoPrato dP;
       try {
           DaoManipular dM = new DaoManipular();
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   dP = new DaoPrato();
                   while (rS.next())
                   {
                       p = dP.busca(rS.getInt("ID_pra"), 0);
                       lista.add(p);
                   }
               }
           }          
       }
       catch(SQLException e){
            System.out.println("problemas ao selecionar o id" + e.getSQLState());
            e.printStackTrace();
       }
      return(lista);
    }

    @Override
    public int verificaChave(int chave)
    {
       int total = -1; 
       String inst = "Select count(Prato_ppr) as total from Pedido_Prato where Prato_ppr = "+chave;              
       try
       {
            DaoManipular dM = new DaoManipular(); 
            ResultSet rS = dM.getConsultar(inst);
            if (rS.next())
            total = rS.getInt("Total");  
       }catch (SQLException e)
       {
            System.out.println("Problemas na consulta da chave " + e.getSQLState());
       }
       return(total);
    }
    
    public List<Prato> pesquisa(String inicial)
    {
        String inst = "select * from Prato where Nome_pra like '"+inicial+"%'";
        List<Prato> nomes = new ArrayList<>();
        Prato p = null;
        try {
           DaoManipular dM = new DaoManipular();
           try (ResultSet rS = dM.getConsultar(inst)) 
           {
               if (rS != null) 
               {
                   while (rS.next())
                   {
                       p = busca(rS.getInt("ID_pra"), 0);/* --> too many connections */
                       /*p = new Prato();
                        p.setID(rS.getInt("ID_pra"));
                        p.setNome(rS.getString("Nome_pra"));
                        p.setFoto(rS.getString("FotoURL_pra"));
                        p.setPreco(rS.getFloat("Preco_pra"));
                        p.setSecao(rS.getString("Secao_pra"));
                        p.setModoPreparo(rS.getString("ModoPreparo_pra"));*/
                       nomes.add(p);
                   }
               }
           }          
       }
       catch(SQLException e){
            System.out.println("problemas ao selecionar os nomes" + e.getSQLState());
            e.printStackTrace();
       }
       return nomes;
    }
    
    public static String listarArrkjay(List array)
    {
	String lista="";
	for(int i = 0; i < array.size(); i++)
	{
            lista +=array.get(i)+"\n";
	}
	return lista;
    }
    
}
