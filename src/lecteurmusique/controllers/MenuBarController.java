/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package lecteurmusique.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author Jérémy Hoarau
 * Created: 25 avr. 2024
 */
public class MenuBarController implements Initializable {

    @FXML
    private MenuItem menuItemFermer, menuItemDeconnexion, menuItemLecture, menuItemPrecedent, menuItemSuivant, menuItemVolumePlus, menuItemVolumeMoins, menuItemVersion, menuItemAPropos;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        menuItemFermer.setOnAction(event -> {
            System.out.println(event.toString());
        });
        
        menuItemDeconnexion.setOnAction(event -> {
            System.out.println(event.toString());
        });
        
        menuItemLecture.setOnAction(event -> {
            System.out.println(event.toString());
        });
        
        menuItemPrecedent.setOnAction(event -> {
            System.out.println(event.toString());
        });
        
        menuItemSuivant.setOnAction(event -> {
            System.out.println(event.toString());
        });
        
        menuItemVolumePlus.setOnAction(event -> {
            System.out.println(event.toString());
        });
        
        menuItemVolumeMoins.setOnAction(event -> {
            System.out.println(event.toString());
        });
        
        menuItemVersion.setOnAction(event -> {
            System.out.println(event.toString());
        });
        
        menuItemAPropos.setOnAction(event -> {
            System.out.println(event.toString());
            openWebpage("https://lecteurmusique.alwaysdata.net");
        });
        
    }
    
    // Fonction pour ouvrir une page web dans le navigateur par défaut
    public static void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
