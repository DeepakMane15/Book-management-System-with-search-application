
package searchengine;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RecordEntry {
    
    public void InsertDeleteData (char operation, String Bname,Integer ID, String Aname, String Price)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        //i for insertion
        
        if(operation == 'i')
        {
             try {
                ps = con.prepareStatement("insert into books values(?,?,?,?)");
                ps.setString(1, Bname);
                ps.setInt(2, ID);
                ps.setString(3,Aname);
                ps.setString(4, Price);
                if (ps.executeUpdate() >0)
                        {
                           JOptionPane.showMessageDialog(null, "New Data Added"); 
                        }
            } catch (SQLException ex) {
                Logger.getLogger(RecordEntry.class.getName()).log(Level.SEVERE, null, ex);
                    
    } 

            
        }
        
       
        if(operation == 'd') {
            try {
                
                ps = con.prepareStatement("DELETE FROM `books` WHERE `ID` = ?");
                ps.setInt(1, ID);
                if (ps.executeUpdate() >0)
                        {
                           JOptionPane.showMessageDialog(null, "Data Deleted!"); 
                        }
            } catch (SQLException ex) {
                Logger.getLogger(RecordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(operation == 'u') {
            try {
                 ps = con.prepareStatement("UPDATE `books` SET `Name` = ?, `Author` = ?, `Price` = ? WHERE `ID` = ?");
                 ps.setString(1, Bname);
                 ps.setString(2, Aname);
                 ps.setString(3, Price);
                 ps.setInt(4, ID);
                 
                 if (ps.executeUpdate() >0)
                        {
                           JOptionPane.showMessageDialog(null, "Data Updated!"); 
                        }
                 
        } catch (SQLException ex) {
                Logger.getLogger(RecordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static int countData(String tablename) {
         
            int total = 0;
            Connection con = MyConnection.getConnection();
            Statement st;
        
            try {
            
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(ID) AS total from " +tablename);
            
            while( rs.next())
            {
                total = rs.getInt(1);
                total++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecordEntry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public void fillJTable (JTable table, String valueToSearch)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
         try {
                ps = con.prepareStatement("SELECT * FROM `books` WHERE CONCAT (Name, ID, Author, Price) like ?;");

                ps.setString(1, "%" +valueToSearch+ "%");

                
                ResultSet rs = ps.executeQuery();

                
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                
                Object row[];
                
                while(rs.next()) {
                    
                    row = new Object[4];
                    
                    row[0] = rs.getString(1);
                    row[1] = rs.getInt(2);
                    row[2] = rs.getString(3);
                    row[3] = rs.getInt(4);
                    
                    model.addRow(row);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(RecordEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static boolean verifyEntry(String value) throws SQLException {
        //Dublicate entry
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
         ps = con.prepareStatement("SELECT USERNAME FROM users where USERNAME = ?");   
        ps.setString(1, value);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
         JOptionPane.showMessageDialog(null,"Username already exist!");
         Signup_Page.clearFields();
        return false;
} 
        
        else
            return true;
        
        
        
    }
    
   
}
