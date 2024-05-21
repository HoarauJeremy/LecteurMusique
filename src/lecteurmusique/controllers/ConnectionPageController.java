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
import lecteurmusique.Connexion;
import lecteurmusique.Model.Utilisateur;
import lecteurmusique.VerifierDonnees;

/**
 * FXML Controller class
 *
 * @author jeremu Hoarau
 */
public class ConnectionPageController implements Initializable {
    
    @FXML
    private Button loginButton, signUpButton;
    @FXML
    private TextField pseudo;
    @FXML
    private PasswordField motDePasse;
    
    private String message;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setOnAction((ActionEvent event) -> {
            message = "Veuillez saisir toutes les informations nécessaires pour vous connecter.";
            if (!pseudo.getText().trim().isEmpty() && !motDePasse.getText().trim().isEmpty()) {
                if (VerifierDonnees.verifierNomUtilisateur(pseudo.getText().trim()) && VerifierDonnees.verifierMotDePasse(motDePasse.getText().trim())) {
                    Utilisateur.connecterUtilisateur(event, pseudo.getText(), motDePasse.getText());
                } else {
                    Connexion.afficherAlerte(Alert.AlertType.ERROR, message);
                }
            } else {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, message);
            }
        });
        
        signUpButton.setOnAction((ActionEvent event) -> {
            Connexion.changerScene(event, "View/sign-up.fxml", "Signup");
        });
    }
}
