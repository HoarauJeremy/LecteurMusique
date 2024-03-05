/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique;

import com.sun.jdi.connect.spi.Connection;
import java.sql.*;

/**
 *
 * @author jerem
 */
public class Connnection {
    
    // = "jdbc:mysql://localhost:3306/dizzer&car"
    protected String url, user, password;

    public Connnection(String url, String user, String password) {
        this.url = "jdbc:mysql://localhost:3306/dizzer&car";
        this.user = user;
        this.password = password;
    }
    
    public void getData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection cnct = DriverManager.getConnection(url, user, password);
            
            
            
        } catch (Exception e) {
        }
    }    
    
}
