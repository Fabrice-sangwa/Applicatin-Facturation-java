package facturation;

import java.sql.*;
import javax.swing.JOptionPane;

public class connection {

    Connection conn = null;

    public static Connection connectbd() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/facturation", "root", "");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

}
