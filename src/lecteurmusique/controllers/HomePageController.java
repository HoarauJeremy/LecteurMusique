/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lecteurmusique.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lecteurmusique.Connexion;

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class HomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void showPlaylist(ActionEvent event) {
        System.out.println("PLAYLIST");
        Connexion.showPlaylistUser(event, 0);
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
