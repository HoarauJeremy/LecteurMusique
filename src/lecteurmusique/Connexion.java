/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique;

import java.sql.*;

/**
 * Class de connexion à une base de donnée <b>MySQL</b>, pour une application
 * d'ecoute de musique.
 *
 * @author jerem
 */
public class Connexion {
    
    protected String url, user, password;

    /**
     * Constructs a connection with the database.
     */
    public Connexion() {
        this.url = "jdbc:mysql://localhost:3306/dizzer&car";
        this.user = "user";
        this.password = "password";
    }
    
    /**
     * Construct a connection with the database for a specifics user.
     *
     * @param user Name of the user
     * @param password Password of the user
     */
    public Connexion(String user, String password) {
        this.url = "jdbc:mysql://localhost:3306/dizzer&car";
        this.user = user;
        this.password = password;
    }

    /**
     * Fonction pour récuperée les artistes dans la base de données <b>MySQL</b>.
     */
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
    
    /**
     * Fonction pour récuperée les musiques dans la base de données <b>MySQL</b>.
     */
    public void getMusique() {
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
    
    /**
     * Fonction pour récuperée les genres de musique dans la base de données <b>MySQL</b>.
     * 
     * @return
     */
    public Object getGenre() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection cnct = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connection OK");
                
                Statement stmt = cnct.createStatement();
                
                String sql = "SELECT * FROM genre";
                
                ResultSet res = stmt.executeQuery(sql);
                /*
                while (res.next()) {
                    int id = res.getInt(1);
                    String nom = res.getString(2);
                    System.out.println("ID : " + id +  " | Nom : " + nom);
                    String [] tab;
                }*/
                
                return res;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        return null;
    }
    
}
