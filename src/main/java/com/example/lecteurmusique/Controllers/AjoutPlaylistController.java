package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.Models.Playlist;
import com.example.lecteurmusique.XmlUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AjoutPlaylistController implements Initializable {
    @FXML
    private TextField nomPlaylist;
    @FXML
    private CheckBox checkBoxPrivee;
    @FXML
    private Button btnEnregistrer;

    XmlUtils.UserInfo userInfo = XmlUtils.getInformation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        btnEnregistrer.setOnAction((event) -> {

            Playlist.creerPlaylist(nomPlaylist.getText().trim(), new Date(), userInfo.getUserId());

        });
    }
}
