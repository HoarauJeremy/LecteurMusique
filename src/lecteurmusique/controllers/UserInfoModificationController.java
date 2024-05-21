 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lecteurmusique.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lecteurmusique.AppUtils;
import lecteurmusique.Connexion;
import lecteurmusique.Model.Utilisateur;
import lecteurmusique.VerifierDonnees;

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class UserInfoModificationController implements Initializable {

    @FXML
    private Button btnPlaylist, btnGenre, btnRetour, btnEnregistrement;
    @FXML
    private TextField nomField, prenomField, pseudoField, emailField;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.afficherProfileUtilisateur(event, 1);
//            Connexion.afficherProfileUtilisateur(event, AppUtils.getIdUtilisateur());
        });
        
        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.afficherPlaylistList(event);
        });
        
        btnGenre.setOnAction((ActionEvent event) -> {
           Connexion.afficherGenreMusique(event);
        });
        
        btnEnregistrement.setOnAction((ActionEvent event) -> {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String pseudo = pseudoField.getText();
            String email = emailField.getText();
            
            if (VerifierDonnees.verifierNomUtilisateur(nom) && VerifierDonnees.verifierNomUtilisateur(prenom) && VerifierDonnees.verifierNomUtilisateur(pseudo) && VerifierDonnees.verifierEmail(email)) {
                Utilisateur.modifierInformationUtilisateur(nom, prenom, pseudo, email, AppUtils.getIdUtilisateur());
            } else {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, "Une erreur est souvenue.");
            }
        });
        
    }
    
}
