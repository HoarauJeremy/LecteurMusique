/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lecteurmusique.Model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.application.Platform;
import javafx.event.ActionEvent;

/**
 *
 * @author Jérémy Hoarau
 * Created: 26 avr. 2024
 */
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
     */
    public static void ouvrirPageWeb() {
        try {
            Desktop.getDesktop().browse(new URI("https://lecteurmusique.alwaysdata.net"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}