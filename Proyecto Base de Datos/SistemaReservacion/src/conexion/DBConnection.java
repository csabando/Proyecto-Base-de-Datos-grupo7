package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String user = "root";
    private final String pass = "toby"; 
    private static final Logger LOGGER = Logger.getLogger("DBConnection Logger");
    
    
    
    private DBConnection(){   
    }
    public static DBConnection getInstance(){
        
        if(dbConnection == null){
            
            dbConnection = new DBConnection();
            
        }
        
        return dbConnection;
    }
    
    public Connection getConnection() {
        
        return connection;
        
    }
    public void setConnection(Connection connexion) {
        this.connection = connexion;
    }
    
    /**
     * Método de tipo void para establecer connection con la Base de datos
     */
    public void conectar() {
        
        LOGGER.log(Level.INFO, "Establishing the database connection...");
        
        try{
            this.connection = DriverManager.getConnection(url, user, pass);
            LOGGER.log(Level.INFO, "¡The database connection was established successfully!");
            
        } catch(Exception e){
            
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
    	
    /**
     * Método para cerrar la connection a la base de datos, no retorna nada
     */
    public void desconectar(){
        
	try {
            connection.close();
            
	} catch (SQLException e) {
            
            LOGGER.log(Level.SEVERE, e.getMessage());
	}
    }
}