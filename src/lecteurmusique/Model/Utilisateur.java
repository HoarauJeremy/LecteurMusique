/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique.Model;

import com.password4j.Hash;
import com.password4j.Password;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lecteurmusique.Connexion;
import lecteurmusique.DatabaseConfig;

/**
 * Classe correspondante à la table utilisateur.
 *
 * @author Jérémy Hoarau
 */
public class Utilisateur extends DatabaseConnection {
    
    public int idUser;
    public String nom, email;    
    
    /**
     * Constructeur de la classe Utilisateur
     *
     * @param idUser de l'utilisateur
     * @param nom de l'utilisateur
     * @param email de l'utilisateur
     */
    public Utilisateur(int idUser, String nom, String email) {
        this.idUser = idUser;
        this.nom = nom;
        this.email = email;
    }

    /**
     *
     * @return l'id de l'utilisateur
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     *
     * @return le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return l'email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Fonction pour enregistrer un nouvelle utilisateur
     *
     * @param event
     * @param user_name nom que l'utilisateur à saisie
     * @param user_email email que l'utilisateur à saisie
     * @param user_password mot de passe que l'utilisateur à saisie
     */
    public static void signUp(ActionEvent event, String user_name, String user_email, String user_password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        Hash hash = null;
        
        try {
            connection = getConnection();
            psCheckUserExists = connection.prepareStatement("SELECT * FROM utilisateur WHERE nom = ?");
            psCheckUserExists.setString(1, user_name);
            resultSet = psCheckUserExists.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                Connexion.showAlert(Alert.AlertType.ERROR, "You cannot use this username.");
            } else {
                if (user_password.length() < 12) {
                    Connexion.showAlert(Alert.AlertType.ERROR, "Votre mot de passe doit contenir au minimun 12 caractères.");
                } else {
                    hash = Password.hash(user_password).withBcrypt();
                    psInsert = connection.prepareStatement("INSERT INTO utilisateur (nom, email, password) VALUES (?, ?, ?)");
                    psInsert.setString(1, user_name);
                    psInsert.setString(2, user_email);
                    psInsert.setString(3, hash.getResult());
                    psInsert.executeUpdate();

                    Connexion.changeScene(event, "View/homePage.fxml", DatabaseConfig.getAppName("Accueil"), user_name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection(connection, psInsert, psCheckUserExists, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Fonction pour se connecter à l'application
     *
     * @param event
     * @param user_email email que l'utilisateur à saisie
     * @param user_password mot de passe que l'utilisateur à saisie
     */
    public static void logIn(ActionEvent event, String user_email, String user_password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
            ps.setString(1, user_email);
            resultSet = ps.executeQuery();
            
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found !");
                Connexion.showAlert(Alert.AlertType.ERROR, "L'utilisateur n'a pas été trouver. Email ou Mot de passe incorecte.");
            } else {

                while (resultSet.next()) {
                    String retrievedName = resultSet.getString("nom");
                    String retrievedPassword = resultSet.getString("password");
                    
                    if (Password.check(user_password, retrievedPassword).withBcrypt()) {
                        Connexion.changeScene(event, "View/homePage.fxml", DatabaseConfig.getAppName("Accueil"), null);   
                    } else {
                        Connexion.showAlert(Alert.AlertType.ERROR, "L'utilisateur n'a pas été trouver. Email ou Mot de passe incorecte.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection(connection, ps, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
