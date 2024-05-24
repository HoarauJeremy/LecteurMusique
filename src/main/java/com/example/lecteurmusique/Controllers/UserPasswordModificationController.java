package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.VerifierDonnees;
import com.example.lecteurmusique.XmlUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPasswordModificationController implements Initializable {
    @FXML
    private Button btnPlaylist, btnGenre, btnRetour, btnModification;
    @FXML
    private PasswordField motDePasseField1, motDePasseField2;

    XmlUtils.UserInfo userInfo = XmlUtils.getInformation();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.afficherProfileUtilisateur(event, userInfo.getUserId());
        });

        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.afficherPlaylistList(event);
        });

        btnGenre.setOnAction((ActionEvent event) -> {
            Connexion.afficherGenreMusique(event);
        });

        btnModification.setOnAction((ActionEvent event) -> {

            if (VerifierDonnees.verifierMotDePasse(motDePasseField1.getText().trim())) {
                if (motDePasseField1.getText().trim().equals(motDePasseField2.getText().trim())) {
//                    Utilisateur.updatePassword(event, email, motDePasseField1);
                }
            }
        });

    }
}
