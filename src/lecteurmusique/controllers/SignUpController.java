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
import lecteurmusique.VerifDonnees;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 */
public class SignUpController implements Initializable {
    
    @FXML
    private Button signupButton, LoginButton;
    
    @FXML
    private TextField tf_username, tf_email;
    
    @FXML
    private PasswordField pf_password;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signupButton.setOnAction((ActionEvent event) -> {
            if (!tf_email.getText().trim().isEmpty() && !tf_username.getText().trim().isEmpty() && !pf_password.getText().trim().isEmpty()) {
                if (VerifDonnees.verifEmail(tf_email.getText().trim()) != false && VerifDonnees.verifNomUtilisateur(tf_username.getText().trim()) != false) {
                    Connexion.signUpUser(event, tf_username.getText(), tf_email.getText(), pf_password.getText());                
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
        });
        
        LoginButton.setOnAction((ActionEvent event) -> {
            Connexion.changeScene(event, "components/ConnectionPage.fxml", "Login", null);
        });
    }    
    
}
