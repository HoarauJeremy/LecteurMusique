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
import javafx.scene.control.PasswordField;
import lecteurmusique.Connexion;
import lecteurmusique.VerifierDonnees;

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class UserPasswordModificationController implements Initializable {

    @FXML
    private Button btnPlaylist, btnGenre, btnRetour, btnModification;
    @FXML
    private PasswordField motDePasseField1, motDePasseField2;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.afficherProfileUtilisateur(event, 1);
            // Connexion.afficherProfileUtilisateur(event, AppUtils.getIdUtilisateur());
        });
        
        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.afficherPlaylistList(event);
        });
        
        btnGenre.setOnAction((ActionEvent event) -> {
           Connexion.afficherGenreMusique(event);
        });
        
        btnModification.setOnAction((ActionEvent event) -> {
            
            if (VerifierDonnees.verifierMotDePasse(motDePasseField1.getText().trim())) {
                if (motDePasseField1.getText().trim().equals(motDePasseField2.getText().trim())) {
//                    Utilisateur.updatePassword(event, email, motDePasseField1);
                }
            }
        });
        
    }
    
}
