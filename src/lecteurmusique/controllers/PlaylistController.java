/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lecteurmusique.controllers;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import lecteurmusique.Connexion;
import lecteurmusique.AppUtils;
import lecteurmusique.Model.Musique;
import lecteurmusique.Model.Playlist;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 */
public class PlaylistController implements Initializable {

    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button btnPlaylist, btnGenre, btnRetour, btnModifierPlaylist, btnReset, btnPause, btnPlay, btnPrevious, btnNext;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label songName, messageLabel, nomPlaylist, datePlaylist;
    @FXML
    private ScrollPane scrollPane;

    private Media media;
    private MediaPlayer mediaPlayer;

    private File directory;
    private File[] files;
    
    private ArrayList<File> songs;
    
    private int songNumber;

    private Timer timer;
    private TimerTask task;
    private boolean running;

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
            Connexion.changeSceneToHome(event, "View/homePage.fxml", AppUtils.getAppNameWithAction("Accueil"), null);
        });
        
        /**
         * Affiche la page des genres de musiques.
         */
        btnGenre.setOnAction(Connexion::showSongGender);   
        
        /**
         * Affiche la page des Playlist de musiques.
         */
        btnPlaylist.setOnAction(Connexion::showPlaylistList);
        
        btnModifierPlaylist.setOnAction((ActionEvent event) -> {
//            try {
//                this.updatePlaylist(4, 2, 1, "a");
//            } catch (SQLException ex) {
//                Logger.getLogger(PlaylistController.class.getName()).log(Level.SEVERE, null, ex);
//            }

        });
    }
    
    /**
     * Affiche les informations de la playlist (nom et date de creation)
     * et affiche une liste des musiques avec des boutton.
     * 
     * @hidden Modifier la liste de bouton pour une liste de nom et mettre un bouton en haut de la playlist pour jouer la musique
     *
     * @param playlists liste d'objet de type <b>Playlist</b> {@link lecteurmusique.Model.Playlist}
     * @param musiques liste d'objet de type <b>Musique</b> {@link lecteurmusique.Model.Musique}
     */
    public void setInformationPlaylist(ArrayList<Playlist> playlists, ArrayList<Musique> musiques) {
        nomPlaylist.setText(playlists.getFirst().getNom());
        datePlaylist.setText(Playlist.formatDate(playlists.getFirst().getDateCreation()));
        
        if (musiques != null) {
            VBox buttonContainer = new VBox(); // Crée un conteneur vertical pour les boutons
            
            for (Musique musique : musiques) {
                Button button = new Button(musique.getNom()); // Crée un bouton avec le nom de la musique
                button.setId(Integer.toString(musique.getIdMusique()));
                button.setOnAction((ActionEvent event) -> {
                    // Logique à exécuter lorsque le bouton est cliqué
                    System.out.println("Bouton cliqué: " + musique.getIdMusique());
//                    this.playMedia();
                });
                buttonContainer.getChildren().add(button); // Ajoute le bouton au conteneur
                System.out.println(musique.getNom());
            }

            // Ajoute le conteneur de boutons à la ScrollPane
            scrollPane.setContent(buttonContainer);
        } else {
            System.out.println("La liste de musique est null.");
        }
    }
    
    /**
     * Affiche un message si la playlist est vide.
     *
     * @param message message à afficher
     */
    public void setMessageInformation(String message) {
        messageLabel.setText(message);
    }
    
    public void updatePlaylist(int idUser, int idPlaylist, int privee, String nomPlaylist) throws SQLException {
//        Playlist.updatePlaylist(idUser, idPlaylist, nomPlaylist, privee);
    }
}
