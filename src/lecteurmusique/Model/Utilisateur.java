/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique.Model;

import com.password4j.Hash;
import com.password4j.HashUpdate;
import com.password4j.Password;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lecteurmusique.AppUtils;
import lecteurmusique.Connexion;
import lecteurmusique.VerifierDonnees;

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
     * @param nom nom que l'utilisateur à saisie
     * @param courriel email que l'utilisateur à saisie
     * @param motDePasse mot de passe que l'utilisateur à saisie
     */
    public static void inscriptionUtilisateur(ActionEvent event, String nom, String courriel, String motDePasse) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        
        try {
            connection = creerConnexion();
            psCheckUserExists = connection.prepareStatement("SELECT * FROM utilisateur WHERE nom = ?");
            psCheckUserExists.setString(1, nom);
            resultSet = psCheckUserExists.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, "You cannot use this username.");
            } else {
                if (motDePasse.length() < 12) {
                    Connexion.afficherAlerte(Alert.AlertType.ERROR, "Votre mot de passe doit contenir au minimun 12 caractères.");
                } else if (VerifierDonnees.verifierMotDePasse(motDePasse)) {
                    Hash hash = Password.hash(motDePasse).withBcrypt();
                    psInsert = connection.prepareStatement("INSERT INTO utilisateur (nom, email, password) VALUES (?, ?, ?)");
                    psInsert.setString(1, nom);
                    psInsert.setString(2, courriel);
                    psInsert.setString(3, hash.getResult());
                    psInsert.executeUpdate();

                    Connexion.changeSceneToHome(event, "View/homePage.fxml", AppUtils.getAppNameWithAction("Accueil"), nom);
                } else {
                    Connexion.afficherAlerte(Alert.AlertType.ERROR, "Le mot de passe ne correspond pas au demande exiger");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                fermerConnexion(connection, psInsert, psCheckUserExists, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Fonction pour se connecter à l'application
     *
     * @param event
     * @param courriel email que l'utilisateur à saisie
     * @param motDePasse mot de passe que l'utilisateur à saisie
     */
    public static void connecterUtilisateur(ActionEvent event, String courriel, String motDePasse) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String messageErreur = "L'adresse e-mail ou le mot de passe saisi est invalide.";
        
        try {
            connection = creerConnexion();
            preparedStatement = connection.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
            preparedStatement.setString(1, courriel);
            resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.isBeforeFirst()) {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, messageErreur);
            } else {

                while (resultSet.next()) {
                    String nomRetrouver = resultSet.getString("nom");
                    String motDePasseRetrouver = resultSet.getString("password");
                    
                    if (VerifierDonnees.verifierMotDePasse(motDePasse)) {
                        if (Password.check(motDePasse, motDePasseRetrouver).withBcrypt()) {
//                          AppUtils.setInformation(courriel, "", Date.from(Instant.now()));
                            Connexion.changeScene(event, "View/homePage.fxml", AppUtils.getAppNameWithAction("Accueil"), null);   
                        } else {
                            Connexion.afficherAlerte(Alert.AlertType.ERROR, messageErreur);
                        }
                    } else {
                        Connexion.afficherAlerte(Alert.AlertType.ERROR, messageErreur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        } finally {
            try {
                fermerConnexion(connection, preparedStatement, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Fonction pour mettre à jour le mot de passe d'un utilisateur
     *
     * @param event
     * @param email saisie par l'utilisateur
     * @param password saisie par l'utilisateur
     * @throws SQLException
     */
    public static void modifierMotDePasseUtilisateur(ActionEvent event, String email, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement psCheckPassword = null;
        PreparedStatement psUpdatePassword = null;
        ResultSet resultSet = null;
        
        try {
            connection = creerConnexion();
            psCheckPassword = connection.prepareStatement("SELECT password FROM utilisateur WHERE email = ?;");
            psCheckPassword.setString(1, email);
            resultSet = psCheckPassword.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, "");
            } else {
                if (resultSet.first()) {
                    String retrievedPassword = resultSet.getString("password");
                    HashUpdate update = Password.check(password, retrievedPassword).andUpdate().addNewRandomSalt().withBcrypt();
                    
                    if (update.isVerified()) {
                        Hash newHash = update.getHash();
                        
                        psUpdatePassword = connection.prepareStatement("UPDATE utilisateur SET password = ? WHERE email = ?");
                        psUpdatePassword.setString(1, newHash.getResult());
                        psUpdatePassword.setString(2, email);
                        psUpdatePassword.executeUpdate();
                        
                        Connexion.showProfileUser(event, 0);
                    }
                }
            }
            
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            fermerConnexion(connection, psCheckPassword, psUpdatePassword, resultSet);
        }
    }
    
    public static void modifierInformationUtilisateur(String nom, String courriel) {
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            
            if (VerifierDonnees.verifierEmail(courriel) && VerifierDonnees.verifierNomUtilisateur(nom)) {
                
            }
            connection = creerConnexion();
            ps = connection.prepareStatement("UPDATE utilisateur SET nom = ? WHERE email = ?");
            ps.setString(1, "");
            ps.setString(2, courriel);
            ps.executeQuery();
            
        } catch (Exception e) {
        } finally {
        }
    }
    
    public static void deconnecterUtilisateur(ActionEvent event) {
        Connexion.changeScene(event, "View/ConnectionPage.fxml", AppUtils.getAppNameWithAction("Connexion"), null);
    }
    
}
