/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lecteurmusique.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import lecteurmusique.Connexion;
import lecteurmusique.DatabaseConfig;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 */
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
            Connexion.changeScene(event, "View/homePage.fxml", DatabaseConfig.getAppName("Accueil"), null);
        });
        
        btnPlaylist.setOnAction((ActionEvent event) -> {
            Connexion.showPlaylistList(event);
        });
    }
    
    public void setGenre(HashMap<Integer, String> tab) {
        ObservableList<Button> buttons = FXCollections.observableArrayList();
        
        for (int i = 1; i < tab.size()+1; i++) {
            btn = new Button(tab.get(i));
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
