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

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class LoggedInController implements Initializable {
    
    @FXML
    private Button logoutButton;

    @FXML
    private Label name_label;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    /**
     *
     * @param username
     */
    public void setUserInformation(String username) {
        name_label.setText(username);
    }
    
    /**
     *
     * @param event
     */
    public void logout(ActionEvent event) {
        Connexion.changeScene(event, "ConnectionPage.fxml", "Log in!", null);
    }
    
}
