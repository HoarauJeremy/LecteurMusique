package com.example.lecteurmusique.Controllers;

import com.example.lecteurmusique.AppUtils;
import com.example.lecteurmusique.Models.MenuBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {

    @FXML
    private MenuItem menuItemFermer, menuItemDeconnexion, menuItemLecture, menuItemPrecedent, menuItemSuivant, menuItemVolumePlus, menuItemVolumeMoins, menuItemVersion, menuItemAPropos, menuItemMusiques;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        menuItemFermer.setOnAction(event -> MenuBar.fermerApplication());

        menuItemDeconnexion.setOnAction(event -> MenuBar.deconnexionApplication(event));

        menuItemAPropos.setOnAction(event -> MenuBar.ouvrirPageWeb(null));

        menuItemMusiques.setOnAction(event -> MenuBar.ouvrirPageWeb("musique.php"));

        //menuItemVersion.setText(AppUtils.getAppName() + " - " + AppUtils.getAppVersion());

//        menuItemLecture.setOnAction(event -> {
//            MusicPlayerController controller = new MusicPlayerController();
//            controller.playMedia();
//        });

    }
}
