package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DaoConexao {

    private static DaoConexao instancia = null;
    
    private Connection con;
    
    private DaoConexao(){
        this.con = null;
        final String Driver = "com.mysql.jdbc.Driver";
        String url, user, password;
        
        user =     "root";
        password = "";
        
        url = "jdbc:mysql://127.0.0.1/sabordorio?user="+user+"&password="+password;
            
        try {
            con = DriverManager.getConnection(url);
            Class.forName(Driver).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão " +e.getMessage(),"Banco de Dados não acessível", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }
    
    public synchronized static DaoConexao getInstancia(){
        if(instancia == null){
            instancia = new DaoConexao();
        }
        return instancia;
    }
    
    //synchronized = Executar sem permitir que o processo seja interrompido
    
    public static void setInstancia(DaoConexao instancia){
        DaoConexao.instancia = instancia;
    }
    public Connection getCon(){
        if(con == null)
            getInstancia();
        return con;
    }
    
    public void setCon(Connection con){
        this.con = con;
    }
}