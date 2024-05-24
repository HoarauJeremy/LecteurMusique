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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.lecteurmusique.Connexion.cheminVue;

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
            Connexion.changerScene(event, cheminVue("UserInfoModification.fxml"), AppUtils.getAppNameWithAction("Modification"));
        });

        btnModificationMDP.setOnAction((ActionEvent event) -> {
            Connexion.changerScene(event, cheminVue("UserPasswordModification.fxml"), AppUtils.getAppNameWithAction("Modification"));
        });

        btnLogout.setOnAction((ActionEvent event) -> {
            Utilisateur.deconnecterUtilisateur(event);
        });
    }

    /**
     * @param utilisateurs
     */
    public void setUserInformation(ArrayList<Utilisateur> utilisateurs) {
        for (Utilisateur utilisateur : utilisateurs) {
            nomLabel.setText(utilisateur.getNom());
            prenomLabel.setText(utilisateur.getPrenom());
            pseudoLabel.setText(utilisateur.getPseudo());
            emailLabel.setText(utilisateur.getEmail());
        }
    }

}
