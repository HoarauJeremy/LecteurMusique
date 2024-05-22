/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package lecteurmusique.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import lecteurmusique.Model.Playlist;

/**
 * FXML Controller class
 *
 * @author jerem
 * Created: 22 mai 2024
 */
public class AjoutPlaylistController implements Initializable {
    
    @FXML
    private TextField nomPlaylist;
    @FXML
    private CheckBox checkBoxPrivee;
    @FXML
    private Button btnEnregistrer;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btnEnregistrer.setOnAction((event) -> {
            
//            Playlist.creerPlaylist(nomPlaylist, 0);
            
        });
    }    

}
