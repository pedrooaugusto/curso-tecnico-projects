
package Persistencia;
import java.sql.*;
import Negocio.Diversos;


public class DaoConexao {    
            
    public static Connection con = null;
    public static Connection getConnection(){
        if(con == null)//Too Many Connections sqn
        {
            final String Driver="com.mysql.jdbc.Driver";
            String url="jdbc:mysql://127.0.0.1/august?user=root&password=";
            //System.out.println(url);
            //Diversos.relatorio("Conexão ativa", "Exemplo com BD", true);
            try{
                Class.forName(Driver).newInstance();
                con =DriverManager.getConnection(url);
            }
            catch(ClassNotFoundException | IllegalAccessException 
                    | InstantiationException | SQLException e){
                Diversos.mostrarDados("Erro de conexão " + e.getMessage(),"August",false);
                e.printStackTrace();
            }
            return(con);
        }
        else
        {
            return con;
        }
    }           
}
