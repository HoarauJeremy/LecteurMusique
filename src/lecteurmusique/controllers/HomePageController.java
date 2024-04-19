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
import lecteurmusique.Connexion;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 */
public class HomePageController implements Initializable {
    
    @FXML
    private Button btnGenre, btnPlaylist, btnProfil; 

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
            showProfile(event);
        });
    }
    
    public void setUserInformation(String username) {
        
    }
    
    public void showPlaylist(ActionEvent event) {
        System.out.println("PLAYLIST");
        Connexion.showPlaylistList(event);
    }
    
    public void showGender(ActionEvent event) {
        System.out.println("GENRE");
        Connexion.showSongGender(event);
    }
    
    public void showProfile(ActionEvent event) {
        System.out.println("PROFILE");
        Connexion.showProfileUser(event, 1);
    }
    
}
