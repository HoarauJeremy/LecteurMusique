/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lecteurmusique.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import lecteurmusique.Connexion;
import lecteurmusique.DatabaseConfig;
import lecteurmusique.Model.Playlist;

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class PlaylistListeController implements Initializable {
    
    @FXML
    private Button btnRetour, btnGenre, btnAjoutPlaylist;
    
    @FXML
    private Label messageLabel;
    
    @FXML
    private ScrollPane scrollPane;

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
         * Affiche la page des genres de musiques.
         */
        btnGenre.setOnAction(Connexion::showSongGender);
        
        /**
         * Affiche la page de création de playlist
         */
        btnAjoutPlaylist.setOnAction((ActionEvent event) -> {
            //Connexion.changeScene(event, "View/", DatabaseConfig.getAppName("Playlist"), null);
        });        
    }
    
    /**
     *
     * @param message
     */
    public void setMessageInformation(String message) {
        messageLabel.setText(message);
    }
    
    public void setPlaylistList(ArrayList<Playlist> playlists) {
        if (playlists != null) {
            VBox buttonContainer = new VBox(); // Crée un conteneur vertical pour les boutons

            // Ajoute un bouton pour chaque playlist à la liste
            for (Playlist playlist : playlists) {
                Button button = new Button(playlist.getNom()); // Crée un bouton avec le nom de la playlist
                button.setOnAction((ActionEvent event) -> {
                    // Logique à exécuter lorsque le bouton est cliqué
                    System.out.println("Bouton cliqué: " + playlist.getPlaylistId());
                    Connexion.showPlaylistUser(event, playlist.getPlaylistId());
                });
                buttonContainer.getChildren().add(button); // Ajoute le bouton au conteneur
            }

            // Ajoute le conteneur de boutons à la ScrollPane
            scrollPane.setContent(buttonContainer);
        } else {
            System.out.println("La liste de playlists est null.");
        }
    }
    
}
