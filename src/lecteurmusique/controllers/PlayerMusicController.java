/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lecteurmusique.controllers;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
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
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import lecteurmusique.Model.Lecteur;
import lecteurmusique.Model.Musique;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 */
public class PlayerMusicController implements Initializable {
    
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button btnReset, btnPause, btnPlay, btnPrevious, btnNext;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label songName;

    private Media media;
    private MediaPlayer mediaPlayer;
    
    private ArrayList<File> songs;
    
    private int songNumber;

    private Timer timer;
    private TimerTask task;
    private boolean running;
    
    private Lecteur lecteurMusique;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lecteurMusique = Lecteur.getInstance();
        lecteurMusique.chargerPlaylist(Musique.recuperMusique());
        
        String cheminMusique = lecteurMusique.getPlaylist().get(0).getLien();
        String cheminMusiqueFormate = cheminMusique.replace("\\", "/");

        URI uri;
        try {
            uri = new URI("file:///" + cheminMusiqueFormate);
        } catch (URISyntaxException e) {
            // Gérer l'exception en cas de syntaxe d'URI incorrecte
            uri = null; // Définir une URI null comme alternative
        }
        
        if (uri != null) { // Vérifier si l'URI est créé avec succès
            mediaPlayer = new MediaPlayer(new Media(uri.toString()));
        }
//        mediaPlayer = new MediaPlayer(new Media(lecteurMusique.getPlaylist().get(0).getLien()));
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            songProgressBar.setProgress(newValue.toMillis() / mediaPlayer.getTotalDuration().toMillis());
        });

        songName.setText(lecteurMusique.getPlaylist().get(0).getNom());

        btnPause.setOnAction(event -> lecteurMusique.pause());
        btnReset.setOnAction(event -> {
            lecteurMusique.jouer();
            mediaPlayer.seek(Duration.ZERO);
        });
        btnPrevious.setOnAction(event -> lecteurMusique.precedent());
        btnPlay.setOnAction(event -> lecteurMusique.jouer());
        btnNext.setOnAction(event -> lecteurMusique.suivant());

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(newValue.doubleValue() / 100.0);
            }
        });
    }

}
