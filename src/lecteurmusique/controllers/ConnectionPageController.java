/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lecteurmusique.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lecteurmusique.Connexion;
import lecteurmusique.VerifDonnees;

/**
 * FXML Controller class
 *
 * @author jerem
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
        loginButton.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                if (!tf_userEmail.getText().trim().isEmpty() && !pf_password.getText().trim().isEmpty()) {
                    if (VerifDonnees.verifEmail(tf_userEmail.getText().trim()) != false) {
                        Connexion.logInUser(event, tf_userEmail.getText(), pf_password.getText());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Veuiller saisire toutes les informations valide et necessaire pour vous connecter.");
                        alert.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Veuiller saisire toutes les information necessaire pour vous connecter.");
                    alert.show();
                }
            }
        });
        
        signUpButton.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                Connexion.changeScene(event, "components/sign-up.fxml", "Signup", null);
            }
        });
    }
}
