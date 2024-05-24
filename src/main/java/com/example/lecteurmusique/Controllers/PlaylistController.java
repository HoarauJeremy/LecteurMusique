package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.Models.Musique;
import com.example.lecteurmusique.Models.Playlist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlaylistController implements Initializable {

    @FXML
    private Button btnPlaylist, btnGenre, btnRetour, btnModifierPlaylist;
    @FXML
    private Label messageLabel, nomPlaylist, datePlaylist;
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
         * Affiche la page des Playlist de musiques.
         */
        btnPlaylist.setOnAction(Connexion::afficherPlaylistList);

        btnModifierPlaylist.setOnAction((ActionEvent event) -> {
//            try {
//                this.updatePlaylist(4, 2, 1, "a");
//            } catch (SQLException ex) {
//                Logger.getLogger(PlaylistController.class.getName()).log(Level.SEVERE, null, ex);
//            }

        });
    }

    /**
     * Affiche les informations de la playlist (nom et date de creation)
     * et affiche une liste des musiques avec des boutton.
     *
     * @hidden Modifier la liste de bouton pour une liste de nom et mettre un bouton en haut de la playlist pour jouer la musique
     *
     * @param playlists liste d'objet de type <b>Playlist</b> {@link Playlist}
     * @param musiques liste d'objet de type <b>Musique</b> {@link Musique}
     */
    public void setInformationPlaylist(ArrayList<Playlist> playlists, ArrayList<Musique> musiques) {
        nomPlaylist.setText(playlists.getFirst().getNom());
        datePlaylist.setText(Playlist.formatDate(playlists.getFirst().getDateCreation()));

        if (musiques != null) {
            VBox buttonContainer = new VBox(); // Crée un conteneur vertical pour les boutons

            for (Musique musique : musiques) {
                Button button = new Button(musique.getNom()); // Crée un bouton avec le nom de la musique
                button.setId(Integer.toString(musique.getIdMusique()));
                button.setOnAction((ActionEvent event) -> {
                    // Logique à exécuter lorsque le bouton est cliqué
                    System.out.println("Bouton cliqué: " + musique.getIdMusique());
//                    this.playMedia();
                });
                buttonContainer.getChildren().add(button); // Ajoute le bouton au conteneur
                System.out.println(musique.getNom());
            }

            // Ajoute le conteneur de boutons à la ScrollPane
            scrollPane.setContent(buttonContainer);
        } else {
            System.out.println("La liste de musique est null.");
        }
    }

    /**
     * Affiche un message si la playlist est vide.
     *
     * @param message message à afficher
     */
    public void setMessageInformation(String message) {
        messageLabel.setText(message);
    }

    public void updatePlaylist(int idUser, int idPlaylist, int privee, String nomPlaylist) throws SQLException {
//        Playlist.updatePlaylist(idUser, idPlaylist, nomPlaylist, privee);
    }
}
