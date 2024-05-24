package com.example.lecteurmusique.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
