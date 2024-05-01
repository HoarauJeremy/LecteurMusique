/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package lecteurmusique.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class MenuBarController extends AnchorPane {
    
    @FXML
    private MenuItem menuItemFermer;
    @FXML
    private MenuItem menuItemDeconnexion;
    @FXML
    private MenuItem menuItemLecture;
    @FXML
    private MenuItem menuItemPrecedent;
    @FXML
    private MenuItem menuItemSuivant;
    @FXML
    private MenuItem menuItemVolumePlus;
    @FXML
    private MenuItem menuItemVolumeMoins;
    @FXML
    private MenuItem menuItemVersion;
    @FXML
    private MenuItem menuItemAPropos;
    @FXML
    private AnchorPane AnchorPane;
   
    public MenuBarController() {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("View/menuBar.fxml"));
        fXMLLoader.setRoot(this);
        fXMLLoader.setController(this);
        
        try {
            fXMLLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        setupElement();
    }
    
    private void setupElement() {
        menuItemFermer.setOnAction(event -> {
            MenuBar.fermerApplication();
        });
        
        menuItemDeconnexion.setOnAction(event -> {
            MenuBar.deconnexionApplication(event);
        });
        
        menuItemVersion.setText(AppUtils.getAppName() + " - " + AppUtils.getAppVersion());
        
        menuItemAPropos.setOnAction(event -> {
            MenuBar.ouvrirPageWeb();
        });
    }

}
