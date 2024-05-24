package com.example.lecteurmusique;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecteurMusique extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            /*
            // Initialiser le fichier de configuration si nécessaire
            XmlUtils.initializeConfigFile();

            // Définir les informations de l'utilisateur
            XmlUtils.setInformation("connected", new Date(), 12345);

            // Récupérer les informations de l'utilisateur
            XmlUtils.UserInfo userInfo = XmlUtils.getInformation();
            if (userInfo != null) {
                System.out.println("Connection: " + userInfo.getConnection());
                System.out.println("Date: " + userInfo.getDate());
                System.out.println("User ID: " + userInfo.getUserId());
            } else {
                System.out.println("Aucune information utilisateur trouvée.");
            }*/

//            String fxmlFile;
//
//            if (!AppUtils.getUtilisateurConnecter().isEmpty()) {
//                fxmlFile = "View/homePage.fxml";
//            } else {
//                fxmlFile = "View/ConnectionPage.fxml";
//            }

            /*File file = new File("");
            if (file.exists()) System.out.println(file.getName());
            else System.out.println("fichier existe pas");*/

            Parent root = FXMLLoader.load(getClass().getResource("/com/example/lecteurmusique/Views/homePage.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("APP.Name");
            //primaryStage.getIcons().add(new Image("com/example/lecteurmusique/Icons/music-1005-svgrepo-com.png"));
            primaryStage.show();

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(LecteurMusique.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}