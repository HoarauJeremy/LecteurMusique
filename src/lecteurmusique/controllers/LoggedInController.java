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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lecteurmusique.Connexion;
import lecteurmusique.AppUtils;
import lecteurmusique.Model.Utilisateur;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 */
public class LoggedInController implements Initializable {
    
    @FXML
    private Button btnRetour, btnPlaylist, btnGenre, btnModifcation, btnModificationMDP, btnLogout;

    @FXML
    private Label nomLabel, prenomLabel, pseudoLabel, emailLabel;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.afficherPlaylistList(event);
        });
        
        btnGenre.setOnAction((ActionEvent event) -> {
            Connexion.afficherGenreMusique(event);
        });
        
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.changerScenePourAccueil(event, null);
        });
        
        btnModifcation.setOnAction((ActionEvent event) -> {
            Connexion.changerScene(event, "View/UserInfoModification.fxml", AppUtils.getAppNameWithAction("Modification"));
        });
        
        btnModificationMDP.setOnAction((ActionEvent event) -> {
            Connexion.changerScene(event, "View/UserPasswordModification.fxml", AppUtils.getAppNameWithAction("Modification"));
        });
        
        btnLogout.setOnAction((ActionEvent event) -> {
            Utilisateur.deconnecterUtilisateur(event);
        });
    }   
    
    /**
     *
     * @param nom
     * @param prenom
     * @param pseudo
     * @param email
     */
    public void setUserInformation(String nom, String prenom, String pseudo, String email) {
        nomLabel.setText(nom);
        prenomLabel.setText(prenom);
        pseudoLabel.setText(pseudo);
        emailLabel.setText(email);
    }
    
}
