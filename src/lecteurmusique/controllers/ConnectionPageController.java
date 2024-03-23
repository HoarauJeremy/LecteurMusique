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

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class ConnectionPageController implements Initializable {
    
    @FXML
    private Button loginButton, signUpButton;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                if (tf_username.getText().trim().isEmpty() || pf_password.getText().trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Veuiller saisire toutes les information necessaire pour vous connecter.");
                    alert.show();
                } else {
                    Connexion.logInUser(event, tf_username.getText(), pf_password.getText());
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
