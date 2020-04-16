package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Derkian de Sousa Freitas
 */
public class ConnectionFactory {
    
        private static final String DRIVER  = "com.mysql.jdbc.Driver";                                      //driver do MySQL
        private static final String URL    = "jdbc:mysql://localhost:3306/balada";                          //URI do Banco de Dados da aplicacao
        private static final String USER   = "root";                                                       //Usuario do Banco de Dados
        private static final String PASS   = "";                                                           //Senha/PassWord do Banco de Dados
        
    
        public static Connection getConnection(){
            
            try {
               Class.forName(DRIVER);
                return DriverManager.getConnection(URL, USER, PASS);
                
            } catch (ClassNotFoundException | SQLException ex) {                
                throw new RuntimeException("Erro na Conexao: ",ex);
                
            }            
        }
        
        
        public static void closeConnection(Connection con ){                                        //fecha a conexao Connection
                
            try {
                if(con != null){
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        
        public static void closeConnection(Connection con , PreparedStatement stmt){                                        //fecha a conexao Connection
                
            closeConnection(con);
            
            try {
                if(stmt != null){
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        
        public static void closeConnection(Connection con , PreparedStatement stmt , ResultSet rs){                                        //fecha a conexao Connection
                
            closeConnection(con,stmt);
            
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }

}
        
        
    

