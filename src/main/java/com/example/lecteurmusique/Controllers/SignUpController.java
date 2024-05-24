package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.Models.Utilisateur;
import com.example.lecteurmusique.VerifierDonnees;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.lecteurmusique.Connexion.cheminVue;

public class SignUpController implements Initializable {

    @FXML
    private Button signupButton, LoginButton;

    @FXML
    private TextField nom, prenom, pseudo, email;

    @FXML
    private PasswordField motDePasse;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signupButton.setOnAction((ActionEvent event) -> {
            if (!email.getText().trim().isEmpty() && !pseudo.getText().trim().isEmpty() && !motDePasse.getText().trim().isEmpty()) {
                if (VerifierDonnees.verifierEmail(email.getText().trim()) && VerifierDonnees.verifierNomUtilisateur(pseudo.getText().trim())) {
                    Utilisateur.inscriptionUtilisateur(event, nom.getText(), prenom.getText(), pseudo.getText(), email.getText(), motDePasse.getText());
                } else {
                    Connexion.afficherAlerte(Alert.AlertType.ERROR, "Veuiller saisire toutes les informations valide et necessaire pour vous connecter.");
                }
            } else {
                Connexion.afficherAlerte(Alert.AlertType.ERROR, "Veuiller saisire toutes les information necessaire pour vous connecter.");
            }
        });

        LoginButton.setOnAction((ActionEvent event) -> {
            Connexion.changerScene(event, cheminVue("ConnectionPage.fxml"), "Connexion");
        });
    }

}
