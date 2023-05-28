package ProjetoVendas.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
    
    public Connection getConnection() {
      
        try{
            
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/BDVENDAS", "roberto", "itapi");
            
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
        
    }

}
