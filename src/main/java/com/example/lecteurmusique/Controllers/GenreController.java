package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.Connexion;
import com.example.lecteurmusique.Models.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GenreController implements Initializable {

    @FXML
    private ListView<Button> listView;
    @FXML
    private Button btn, btnRetour, btnPlaylist;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //gridPane.add(new Button("NOM DU GENRE"), 0, 0);
        btnRetour.setOnAction((ActionEvent event) -> {
            Connexion.changerScenePourAccueil(event, null);
        });

        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.afficherPlaylistList(event);
        });
    }

    /**
     * Affiche la liste des genres de musique avec un boutton.
     *
     * @param tab Collection qui recuppere les id et les noms des genres.
     */
    public void setGenre(HashMap<Integer, Genre> tab) {
        ObservableList<Button> buttons = FXCollections.observableArrayList();

        for (int i = 1; i < tab.size()+1; i++) {
            btn = new Button(tab.get(i).getNom());
            btn.setId(Integer.toString(i));

            final int buttonId = i;

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("ID du bouton : " + buttonId);
                }
            });

            buttons.add(btn);
        }

        listView.setItems(buttons);
    }

}
