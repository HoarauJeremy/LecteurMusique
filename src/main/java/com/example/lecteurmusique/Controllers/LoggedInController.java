package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.AppUtils;
import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.Models.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Button btnRetour, btnPlaylist, btnGenre, btnModifcation, btnModificationMDP, btnLogout;

    @FXML
    private Label nomLabel, prenomLabel, pseudoLabel, emailLabel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.afficherPlaylistList(event);
        });

        btnGenre.setOnAction((ActionEvent event) -> {
            Connexion.afficherGenreMusique(event);
        });

        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.changerScenePourAccueil(event, null);
        });

        btnModifcation.setOnAction((ActionEvent event) -> {
            Connexion.changerScene(event, "View/UserInfoModification.fxml", AppUtils.getAppNameWithAction("Modification"));
        });

        btnModificationMDP.setOnAction((ActionEvent event) -> {
            Connexion.changerScene(event, "View/UserPasswordModification.fxml", AppUtils.getAppNameWithAction("Modification"));
        });

        btnLogout.setOnAction((ActionEvent event) -> {
            Utilisateur.deconnecterUtilisateur(event);
        });
    }

    /**
     *
     * @param nom
     * @param prenom
     * @param pseudo
     * @param email
     */
    public void setUserInformation(String nom, String prenom, String pseudo, String email) {
        nomLabel.setText(nom);
        prenomLabel.setText(prenom);
        pseudoLabel.setText(pseudo);
        emailLabel.setText(email);
    }

}
