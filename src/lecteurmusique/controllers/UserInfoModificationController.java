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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lecteurmusique.Connexion;

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class UserInfoModificationController implements Initializable {

    @FXML
    private Button btnPlaylist, btnGenre, btnRetour;
    @FXML
    private TextField nomField, emailField;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.showProfileUser(event, 1);
        });
        
        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.showPlaylistList(event);
        });
        
        btnGenre.setOnAction((ActionEvent event) -> {
           Connexion.showSongGender(event);
        });
    }
    
    
    
}
