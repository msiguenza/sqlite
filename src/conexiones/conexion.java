package conexiones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class conexion{
 
    private static Connection conn;


    public static void inicializarConexion() {
  
        try {
  
   
            Class.forName("org.sqlite.JDBC");
   
            conn = DriverManager.getConnection("jdbc:sqlite:BD/LIBRERIA.db");
  
        } 
  
  
        catch (Exception e){
   
            e.printStackTrace();
  }

 }
 

    public static Connection getConexion() {
 
     
        try {
  
         
            if(conn==null || conn.isClosed()){
   
             inicializarConexion();
  
         }
  
        
            return conn;
 
    
        } catch (Exception e) {
  
         e.printStackTrace();
  
         return null;
 
     }
 
 
 }
 
 
    public static int executeNonQuery(String sql,Connection conn) throws Exception{
  
     
        try {
   
         Statement stmt=conn.createStatement();
   
         return stmt.executeUpdate(sql);
  
    
        } catch (Exception e) {
   
         e.printStackTrace();
   
         throw e;
  
     }
  
 }
 
 

    public static  int executeScalar(String sql,Connection conn) throws Exception{
  
    
        try {
   
     
            Statement stmt=conn.createStatement();
         
       
            ResultSet  rs=stmt.executeQuery(sql);
   
       
            rs.next();
   
       
            int resultado=rs.getInt(1);
   
      
            rs.close();
   
       
            stmt.close();
   
      
            conn.close();
   
      
            return resultado;
  
   
        } catch (Exception e) {
   
   
            e.printStackTrace();
   
   
            throw e;
          
        }
    }
}