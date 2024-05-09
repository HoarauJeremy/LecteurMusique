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
import lecteurmusique.Model.Musique;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 */
public class MusicPlayerController implements Initializable {
    
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
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ArrayList<Musique> musiques = Musique.recuperMusique();
        
        songs = new ArrayList<>();
        
        if (musiques != null) {
            for (Musique musique : musiques) {
                System.out.println(musique.getLien());
                songs.add(new File(musique.getLien()));
            }
        }
            
        try {           
            System.out.println("http://LecteurMusique/" + songs.get(songNumber));
            media = new Media("http://LecteurMusique/" + songs.get(songNumber).toURI().toString());
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
        
//        btnNext.setGraphic(new ImageView("lecteurmusique/resources/icons/arrow-right-336-svgrepo-com.png"));
        
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
    }
    
    /**
     * Fonction qui va lire la musique
     */
    public void playMedia() {
        beginTimer();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        mediaPlayer.play();
    }
    
    /**
     * Fonction qui va mettre la musique sur pause
     */
    public void pauseMedia() {
        
        cancelTimer();
        mediaPlayer.pause();
    }
    
    /**
     * Fonction qui va redémarer la musique
     */
    public void resetMedia() {
        
        songProgressBar.setProgress(0);
        mediaPlayer.seek(Duration.ZERO);
    }
    
    /**
     * Fonction qui va passer à la musique suivante
     */
    public void previousMedia() {
        if (songNumber > 0) {
            songNumber--;
            
            mediaPlayer.stop();
            
            if (running) {
                cancelTimer();
            }
            
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            
            songName.setText(songs.get(songNumber).getName());
            
            playMedia();
        } else {
            songNumber = songs.size() - 1;
            
            mediaPlayer.stop();
            
            if (running) {
                cancelTimer();
            }
            
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            
            songName.setText(songs.get(songNumber).getName());
            
            playMedia();
        }
    }
    
    /**
     * Fonction qui va revenir à la musique précedente
     */
    public void nextMedia() {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            
            mediaPlayer.stop();
            
            if (running) {
                cancelTimer();
            }
            
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            
            songName.setText(songs.get(songNumber).getName());
            
            playMedia();
        } else {
            songNumber = 0;
            
            mediaPlayer.stop();
            
            if (running) {
                cancelTimer();
            }
            
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            
            songName.setText(songs.get(songNumber).getName());
            
            playMedia();
        }
    }
    
    /**
     * Fonction qui remplir la ProgressBar en fonction de l'avancer de la musique
     */
    public void beginTimer() {
        timer = new Timer();
        
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current/end);
                
                if (current/end == 1) {
                    cancelTimer();
                }
            }
        };
                
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    /**
     * Fonction qui réinitialiser la ProgressBar
     */    
    public void cancelTimer() {
        running = false;
        timer.cancel();
    }
    
}
