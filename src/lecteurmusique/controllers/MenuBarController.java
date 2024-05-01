/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package lecteurmusique.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import lecteurmusique.AppUtils;
import lecteurmusique.Model.MenuBar;

/**
 * FXML Controller class
 *
 * @author jerem
 * Created: 29 avr. 2024
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
        
        menuItemFermer.setOnAction(event -> MenuBar.fermerApplication());
        
        menuItemDeconnexion.setOnAction(event -> MenuBar.deconnexionApplication(event));
        
        menuItemAPropos.setOnAction(event -> MenuBar.ouvrirPageWeb());
        
        menuItemVersion.setText(AppUtils.getAppName() + " - " + AppUtils.getAppVersion());
        
        /*menuItemLecture.setOnAction(event -> {
            MusicPlayerController
        });*/
        
    }

}
