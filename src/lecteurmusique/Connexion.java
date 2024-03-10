/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique;

import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lecteurmusique.controllers.LoggedInController;

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
        this.user = "jeremy";
        this.password = "jeremy";
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
    
    
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Connexion.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void signUpUser(ActionEvent event, String user_name, String user_email, String user_password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rezeed", "jeremy", "jeremy");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM utilisateur WHERE nom = ?");
            psCheckUserExists.setString(1, user_name);
            resultSet = psCheckUserExists.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists !");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO utilisateur (nom, email, password) VALUES (?, ?, ?)");
                psInsert.setString(1, user_name);
                psInsert.setString(2, user_email);
                psInsert.setString(3, user_password);
                psInsert.executeUpdate();
            
                changeScene(event, "logged-in.fxml", "Welcome!", user_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void logInUser(ActionEvent event, String user_email, String user_password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rezeed", "jeremy", "jeremy");
            ps = connection.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
            ps.setString(1, user_email);
            resultSet = ps.executeQuery();
            
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found !");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("L'utilisateur n'a pas été trouver. Email ou Mot de passe incorecte.");
                alert.show();
            } else {

                while (resultSet.next()) {
                    String retrievedName = resultSet.getString("nom");
                    String retrievedPassword = resultSet.getString("password");
                    
                    System.out.println(resultSet.getString("email"));

                    
                    if (retrievedPassword.equals(user_password)) {
                        changeScene(event, "logged-in.fxml", "Welcome!", retrievedName);   
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("L'utilisateur n'a pas été trouver. Email ou Mot de passe incorecte.");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
