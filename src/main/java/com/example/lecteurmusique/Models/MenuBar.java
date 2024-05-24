package com.example.lecteurmusique.Models;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuBar {
    //menuItemDeconnexion, menuItemLecture, menuItemPrecedent, menuItemSuivant, menuItemVolumePlus, menuItemVolumeMoins;

    /**
     * Action pour fermer l'application
     *
     */
    public static void fermerApplication() {
        Platform.exit();
    }

    /**
     * Action pour deconnecter l'utilisateur
     *
     * @param event
     */
    public static void deconnexionApplication(ActionEvent event) {
        Utilisateur.deconnecterUtilisateur(event);
    }

    /**
     * Fonction pour ouvrir une page web dans le navigateur par défaut
     *
     * @param page
     */
    public static void ouvrirPageWeb(String page) {
        String lien;
        try {
            if (page != null) {
                lien = "https://lecteurmusique.alwaysdata.net/" + page;
            } else {
                lien = "https://lecteurmusique.alwaysdata.net";
            }
            Desktop.getDesktop().browse(new URI(lien));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
