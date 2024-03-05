/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique;

import java.sql.*;

/**
 *
 * @author jerem
 */
public class Connexion {
    
    protected String url, user, password;

    public Connexion() {
        this.url = "jdbc:mysql://localhost:3306/dizzer&car";
        this.user = "jeremy";
        this.password = "jeremy";
    }
    
    public Connexion(String user, String password) {
        this.url = "jdbc:mysql://localhost:3306/dizzer&car";
        this.user = user;
        this.password = password;
    }

    public void getArtiste() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection cnct = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connection OK");
                
                Statement stmt = cnct.createStatement();
                
                String sql = "SELECT * FROM artiste";
                
                ResultSet res = stmt.executeQuery(sql);
                
                while (res.next()) {
                    int id = res.getInt(1);
                    String nom = res.getString(2);
                    System.out.println("ID Artiste: " + id +  " | Nom Artiste: " + nom);
                }
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }        
    }
    
    public void getSon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection cnct = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connection OK");
                
                Statement stmt = cnct.createStatement();
                
                String sql = "SELECT * FROM son";
                
                ResultSet res = stmt.executeQuery(sql);
                
                while (res.next()) {
                    int id = res.getInt("IdSon");
                    String nom = res.getString("NomSon");
                    Time duree = res.getTime("Duree");
                    String lien = res.getString("lien");
                    System.out.println("ID : " + id +  " | Nom : " + nom + " | Durée : " + duree + " | Lien : " + lien);
                }
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
    public void getGenre() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection cnct = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connection OK");
                
                Statement stmt = cnct.createStatement();
                
                String sql = "SELECT * FROM genre";
                
                ResultSet res = stmt.executeQuery(sql);
                
                while (res.next()) {
                    int id = res.getInt(1);
                    String nom = res.getString(2);
                    System.out.println("ID : " + id +  " | Nom : " + nom);
                }
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
}
