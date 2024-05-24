package com.example.lecteurmusique.Models;

import com.example.lecteurmusique.AppUtils;
import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.VerifierDonnees;
import com.password4j.Hash;
import com.password4j.HashUpdate;
import com.password4j.Password;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @param nom nom que l'utilisateur Ã  saisie
     * @param prenom
     * @param pseudo
     * @param courriel email que l'utilisateur Ã  saisie
     * @param motDePasse mot de passe que l'utilisateur Ã  saisie
     */
    public static void inscriptionUtilisateur(ActionEvent event, String nom, String prenom, String pseudo, String courriel, String motDePasse) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            try {
                connection = creerConnexion();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            psCheckUserExists = connection.prepareStatement("SELECT * FROM utilisateur WHERE pseudo = ? AND email = ?");
            psCheckUserExists.setString(1, nom);
            psCheckUserExists.setString(2, courriel);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, "Nom d'utilisateur ou adresse email indisponible");
            } else {
                if (motDePasse.length() < 12) {
                    Connexion.afficherAlerte(Alert.AlertType.ERROR, "Votre mot de passe doit contenir au minimun 12 caractÃ¨res.");
                } else if (VerifierDonnees.verifierMotDePasse(motDePasse)) {
                    Hash hash = Password.hash(motDePasse).withBcrypt();
                    psInsert = connection.prepareStatement("INSERT INTO utilisateur (nom, prenom, pseudo, email, motDePasse) VALUES (?, ?, ?, ?, ?)");
                    psInsert.setString(1, nom);
                    psInsert.setString(2, prenom);
                    psInsert.setString(3, pseudo);
                    psInsert.setString(4, courriel);
                    psInsert.setString(5, hash.getResult());
                    psInsert.executeUpdate();

                    //AppUtils.setInformation(resultSet.getString("pseudo"), Date.from(Instant.now()), resultSet.getInt("idUser"));
                    Connexion.changerScenePourAccueil(event, resultSet.getString("pseudo"));
                } else {
                    Connexion.afficherAlerte(Alert.AlertType.ERROR, "Le mot de passe ne remplit pas les conditions nÃ©cessaires.");
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
     * Fonction pour se connecter Ã  l'application
     *
     * @param event
     * @param pseudo pseudo que l'utilisateur Ã  saisie
     * @param motDePasse mot de passe que l'utilisateur Ã  saisie
     */
    public static void connecterUtilisateur(ActionEvent event, String pseudo, String motDePasse) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String messageErreur = "Le pseudo ou le mot de passe saisi est invalide.";

        try {
            try {
                connection = creerConnexion();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM utilisateur WHERE pseudo = ?");
            preparedStatement.setString(1, pseudo);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, messageErreur);
            } else {

                while (resultSet.next()) {
                    String motDePasseRetrouver = resultSet.getString("motDePasse");

                    if (VerifierDonnees.verifierMotDePasse(motDePasse)) {
                        if (Password.check(motDePasse, motDePasseRetrouver).withBcrypt()) {
                            //AppUtils.setInformation(resultSet.getString("pseudo"), Date.from(Instant.now()), resultSet.getInt("idUser"));
                            Connexion.changerScenePourAccueil(event, resultSet.getString("pseudo"));
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
     * Fonction pour mettre Ã  jour le mot de passe d'un utilisateur
     *
     * @param event
     * @param email saisie par l'utilisateur
     * @param motDePasse saisie par l'utilisateur
     * @throws SQLException
     */
    public static void modifierMotDePasseUtilisateur(ActionEvent event, String email, String motDePasse) throws SQLException {
        Connection connection = null;
        PreparedStatement psCheckPassword = null;
        PreparedStatement psUpdatePassword = null;
        ResultSet resultSet = null;

        try {
            try {
                connection = creerConnexion();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            psCheckPassword = connection.prepareStatement("SELECT motDePasse FROM utilisateur WHERE email = ?;");
            psCheckPassword.setString(1, email);
            resultSet = psCheckPassword.executeQuery();

            if (resultSet.isBeforeFirst()) {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, "");
            } else {
                if (resultSet.first()) {
                    String retrievedPassword = resultSet.getString("motDePasse");
                    HashUpdate update = Password.check(motDePasse, retrievedPassword).andUpdate().addNewRandomSalt().withBcrypt();

                    if (update.isVerified()) {
                        Hash newHash = update.getHash();

                        psUpdatePassword = connection.prepareStatement("UPDATE utilisateur SET motDePasse = ? WHERE email = ?");
                        psUpdatePassword.setString(1, newHash.getResult());
                        psUpdatePassword.setString(2, email);
                        psUpdatePassword.executeUpdate();

                        Connexion.afficherProfileUtilisateur(event, 0);
                        //            Connexion.afficherProfileUtilisateur(event, AppUtils.getIdUtilisateur());

                    }
                }
            }

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            fermerConnexion(connection, psCheckPassword, psUpdatePassword, resultSet);
        }
    }

    public static void modifierInformationUtilisateur(String nom, String prenom, String pseudo, String courriel, int idUtilisateur) {
        Connection connection = null;
        PreparedStatement psVerifierInformation = null;
        PreparedStatement psModifierInformationUtilisateur = null;
        ResultSet resultSet = null;

        try {
            try {
                connection = creerConnexion();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            psVerifierInformation = connection.prepareStatement("SELECT * FROM utilisateur WHERE idUser = ?;");
            psVerifierInformation.setInt(1, idUtilisateur);
            resultSet = psVerifierInformation.executeQuery();

            if (resultSet.first()) {
                if (VerifierDonnees.verifierEmail(courriel) && VerifierDonnees.verifierNomUtilisateur(nom)) {
                    psModifierInformationUtilisateur = connection.prepareStatement("UPDATE utilisateur SET nom = ?, prenom = ?, psModifierInformationUtilisateureudo = ?, email = ? WHERE idUser = ?");
                    psModifierInformationUtilisateur.setString(1, nom);
                    psModifierInformationUtilisateur.setString(2, prenom);
                    psModifierInformationUtilisateur.setString(3, pseudo);
                    psModifierInformationUtilisateur.setString(4, courriel);
                    psModifierInformationUtilisateur.setString(5, "");
                    psModifierInformationUtilisateur.executeQuery();
                }
            }

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                fermerConnexion(connection, psVerifierInformation, psModifierInformationUtilisateur, null);
            } catch (SQLException ex) {
                Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void deconnecterUtilisateur(ActionEvent event) {
        //AppUtils.detruitInformation();
        Connexion.changerScene(event, "View/ConnectionPage.fxml", AppUtils.getAppNameWithAction("Connexion"));
    }
}
