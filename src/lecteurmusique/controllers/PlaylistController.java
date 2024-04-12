/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lecteurmusique.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import lecteurmusique.DatabaseConfig;
import lecteurmusique.Model.Musique;
import lecteurmusique.Model.Playlist;

/**
 * FXML Controller class
 *
 * @author jerem
 */
public class PlaylistController extends MusicPlayerController implements Initializable {

    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button btnPlaylist, btnGenre, btnRetour, btnReset, btnPause, btnPlay, btnPrevious, btnNext;
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
        
        songs = new ArrayList<File>();
        
        directory = new File("music");
        
        files = directory.listFiles();
        
        if (files != null) {
            
            for(File file : files) {
                
                songs.add(file);
            }
        }
            
        try {           
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songName.setText(songs.get(songNumber).getName());
        } catch (MediaException me) {
            me.getStackTrace();
        }
        
        volumeSlider.valueProperty().addListener(new ChangeListener<Number> () {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {    
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
        
        songProgressBar.setStyle("-fx-accent: #00ff00");
        
        btnPlay.setOnAction((ActionEvent event) -> {
            this.playMedia();
        });
        
        btnPause.setOnAction((ActionEvent event) -> {
            this.pauseMedia();
        });
        
        btnPrevious.setOnAction((ActionEvent event) -> {
            this.previousMedia();
        });
        
        btnNext.setOnAction((ActionEvent event) -> {
            this.nextMedia();
        });
        
        btnReset.setOnAction((ActionEvent event) -> {
            this.resetMedia();
        });
        
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
         * Affiche la page des Playlist de musiques.
         */
        btnPlaylist.setOnAction(Connexion::showPlaylistList);   
    }
    
    public void setInformationPlaylist(ArrayList<Playlist> playlists, ArrayList<Musique> musiques) {
        nomPlaylist.setText(playlists.get(0).getNom());
        datePlaylist.setText(Playlist.formatDate(playlists.getFirst().getDateCreation()));
        
        if (musiques != null) {
            VBox buttonContainer = new VBox(); // Crée un conteneur vertical pour les boutons
            
            for (Musique musique : musiques) {
                Button button = new Button(musique.getNom()); // Crée un bouton avec le nom de la musique
                button.setId(Integer.toString(musique.getIdMusique()));
                button.setOnAction((ActionEvent event) -> {
                    // Logique à exécuter lorsque le bouton est cliqué
                    System.out.println("Bouton cliqué: " + musique.getIdMusique());
                    this.playMedia();
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
    
    public void setMessageInformation(String message) {
        messageLabel.setText(message);
    }
}
