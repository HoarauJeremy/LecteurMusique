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
import javafx.scene.control.Button;
import lecteurmusique.Connexion;
import lecteurmusique.DatabaseConfig;

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class PlaylistListeController implements Initializable {
    
    @FXML
    private Button btnRetour, btnGenre;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /**
         * Retourne sur la page d'accueil.
         */
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.changeScene(event, "View/homePage.fxml", DatabaseConfig.getAppName("Accueil"), null);
        });
        
        /**
         * Retourne sur la page d'accueil.
         */
        btnGenre.setOnAction(Connexion::showSongGender);
    }    
    
}
