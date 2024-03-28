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
import lecteurmusique.DatabaseConfig;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 */
public class LoggedInController implements Initializable {
    
    @FXML
    private Button btnRetour, btnPlaylist, btnGenre, btnModifcation, btnLogout;

    @FXML
    private Label name_label, Fname_label;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.showPlaylistUser(event, 1);
        });
        
        btnGenre.setOnAction((ActionEvent event) -> {
            Connexion.showSongGender(event);
        });
        
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.changeScene(event, "View/homePage.fxml", DatabaseConfig.getAppName("Accueil"), null);
        });
        
        btnModifcation.setOnAction((ActionEvent event) -> {
            Connexion.changeScene(event, "", DatabaseConfig.getAppName(""), null);
        });
        
        btnLogout.setOnAction((ActionEvent event) -> {
            this.logout(event);
        });
    }   
    
    /**
     *
     * @param username
     * @param Fname
     */
    public void setUserInformation(String username, String Fname) {
        name_label.setText(username);
        Fname_label.setText(Fname);
    }
    
    /**
     *
     * @param event
     */
    public void logout(ActionEvent event) {
        Connexion.changeScene(event, "View/ConnectionPage.fxml", "Log in!", null);
    }
    
}
