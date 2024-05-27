package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.Models.NombreEcoute;
import com.example.lecteurmusique.XmlUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Button btnGenre, btnPlaylist, btnProfil, btnTop50;

    @FXML
    private Label pseudoLabel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnPlaylist.setOnAction(this::showPlaylist);

        btnGenre.setOnAction((ActionEvent event) -> {
            this.showGender(event);
        });

        btnProfil.setOnAction((ActionEvent event) -> {
            this.showProfile(event);
        });

        btnTop50.setOnAction(event -> {
            //Connexion.changerScene(event, "/com/example/lecteurmusique/Views/Top50.fxml", "TOP 50");
            try {
                NombreEcoute.recupererMusique();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void setUserInformation(String pseudo) {
        pseudoLabel.setText("Bonjour, " + pseudo);
    }

    public void showPlaylist(ActionEvent event) {
        Connexion.afficherPlaylistList(event);
    }

    public void showGender(ActionEvent event) {
        Connexion.afficherGenreMusique(event);
    }

    public void showProfile(ActionEvent event) {
        XmlUtils.UserInfo userInfo = XmlUtils.getInformation();
        Connexion.afficherProfileUtilisateur(event, userInfo.getUserId());
    }

}
