
package searchengine;

import java.sql.DriverManager;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class MyConnection {
    
    public static Connection getConnection()
    {
        Connection con = null;
        
        try {
    Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mpdatabase", "root","Deepak15@"); //Replace my mysql username and password by yours
     // System.out.println("heu");

}catch (Exception ex){
    System.out.println(ex.getMessage());
}
  return con;
}
}
