package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.AppUtils;
import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.Models.Utilisateur;
import com.example.lecteurmusique.VerifierDonnees;
import com.example.lecteurmusique.XmlUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoModificationController implements Initializable {

    @FXML
    private Button btnPlaylist, btnGenre, btnRetour, btnEnregistrement;
    @FXML
    private TextField nomField, prenomField, pseudoField, emailField;

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

        btnEnregistrement.setOnAction((ActionEvent event) -> {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String pseudo = pseudoField.getText();
            String email = emailField.getText();

            if (!nom.trim().isEmpty() && !prenom.trim().isEmpty() && !pseudo.trim().isEmpty() && !email.trim().isEmpty()) {
                if (VerifierDonnees.verifierNomUtilisateur(nom) && VerifierDonnees.verifierNomUtilisateur(prenom) && VerifierDonnees.verifierNomUtilisateur(pseudo) && VerifierDonnees.verifierEmail(email)) {
                    Utilisateur.modifierInformationUtilisateur(nom, prenom, pseudo, email, userInfo.getUserId());
                } else {
                    Connexion.afficherAlerte(Alert.AlertType.ERROR, "Une erreur est survenue.");
                }
            } else {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, "Merci de compléter tous les champs.");
            }
        });

    }

}
