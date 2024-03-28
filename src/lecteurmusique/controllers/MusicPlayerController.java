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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author jerem
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
        
        songProgressBar.setOnDragDone((t) -> {
            //
        });
    }
    
    public void playMedia() {
        beginTimer();
        mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
        mediaPlayer.play();
    }
    
    public void pauseMedia() {
        
        cancelTimer();
        mediaPlayer.pause();
    }
    
    public void resetMedia() {
        
        songProgressBar.setProgress(0);
        mediaPlayer.seek(Duration.ZERO);
    }
    
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
    
    public void cancelTimer() {
        running = false;
        timer.cancel();
    }
    
}
