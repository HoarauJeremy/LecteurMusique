package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.AppUtils;
import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.Models.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.lecteurmusique.Connexion.cheminVue;

public class PlaylistListeController implements Initializable {

    @FXML
    private Button btnRetour, btnGenre, btnAjoutPlaylist;

    @FXML
    private Label messageLabel;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /**
         * Retourne sur la page d'accueil.
         */
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.changerScenePourAccueil(event, null);
        });

        /**
         * Affiche la page des genres de musiques.
         */
        btnGenre.setOnAction(Connexion::afficherGenreMusique);

        /**
         *
         * Affiche la page de création de playlist
         */
        btnAjoutPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.changerScene(event, cheminVue("ajoutPlaylist.fxml"), AppUtils.getAppNameWithAction("Playlist"));
        });
    }

    /**
     * Affiche un message si la liste des playlist est vide.
     *
     * @param message message à afficher
     */
    public void setMessageInformation(String message) {
        messageLabel.setText(message);
    }

    /**
     * Affiche les playlist d'un utilisateur.
     *
     * @param playlists Liste de playlist Ã  afficher
     */
    public void setPlaylistList(ArrayList<Playlist> playlists) {
        if (playlists != null) {
            VBox buttonContainer = new VBox(); // Crée un conteneur vertical pour les boutons

            // Ajoute un bouton pour chaque playlist Ã  la liste
            for (Playlist playlist : playlists) {
                Button button = new Button(playlist.getNom()); // CrÃ©e un bouton avec le nom de la playlist
                button.setId(Integer.toString(playlist.getPlaylistId()));
                button.setOnAction((ActionEvent event) -> {
                    Connexion.afficherPlaylistUtilisateur(event, playlist.getPlaylistId());
                });
                buttonContainer.getChildren().add(button); // Ajoute le bouton au conteneur
            }

            // Ajoute le conteneur de boutons Ã  la ScrollPane
            scrollPane.setContent(buttonContainer);
        } else {
            System.out.println("La liste de playlists est null.");
        }
    }

}
