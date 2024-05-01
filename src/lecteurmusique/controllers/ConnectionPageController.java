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
    private TextField tf_userEmail;
    @FXML
    private PasswordField pf_password;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setOnAction((ActionEvent event) -> {
            if (!tf_userEmail.getText().trim().isEmpty() && !pf_password.getText().trim().isEmpty()) {
                if (VerifierDonnees.verifierEmail(tf_userEmail.getText().trim()) != false) {
                    Utilisateur.connecterUtilisateur(event, tf_userEmail.getText(), pf_password.getText());
                } else {
                    Connexion.afficherAlerte(Alert.AlertType.ERROR, "Veuiller saisire toutes les informations valide et necessaire pour vous connecter.");
                }
            } else {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, "Veuiller saisire toutes les information necessaire pour vous connecter.");
            }
        });
        
        signUpButton.setOnAction((ActionEvent event) -> {
            Connexion.changeScene(event, "View/sign-up.fxml", "Signup", null);
        });
    }
}
