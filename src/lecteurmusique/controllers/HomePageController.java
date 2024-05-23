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
 * @author Jérémy Hoarau
 */
public class HomePageController implements Initializable {
    
    @FXML
    private Button btnGenre, btnPlaylist, btnProfil;
    
    @FXML
    private Label pseudoLabel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnPlaylist.setOnAction((ActionEvent event) -> {
            this.showPlaylist(event);
        });
        
        btnGenre.setOnAction((ActionEvent event) -> {
            this.showGender(event);
        });
        
        btnProfil.setOnAction((ActionEvent event) -> {
            this.showProfile(event);
        });
        
    }
    
    public void setUserInformation(String pseudo) {
        pseudoLabel.setText("Bonjour, " + pseudo);
    }
    
    public void showPlaylist(ActionEvent event) {
        System.out.println("PLAYLIST");
        Connexion.afficherPlaylistList(event);
    }
    
    public void showGender(ActionEvent event) {
        System.out.println("GENRE");
        Connexion.afficherGenreMusique(event);
    }
    
    public void showProfile(ActionEvent event) {
        System.out.println("PROFILE");
        Connexion.afficherProfileUtilisateur(event, 1);
        // Connexion.afficherProfileUtilisateur(event, AppUtils.getIdUtilisateur());
    }
    
}
