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
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecteurMusique extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {

            String fxmlFile = "/com/example/lecteurmusique/Views/ConnectionPage.fxml";

            // Initialiser le fichier de configuration si nécessaire
            XmlUtils.initializeConfigFile();

            // Récupérer les informations de l'utilisateur
            XmlUtils.UserInfo userInfo = XmlUtils.getInformation();

            if (userInfo != null) {
                if (userInfo.getConnection().equals("connected")) {
                    fxmlFile = "/com/example/lecteurmusique/Views/homePage.fxml";
                } else {
                    fxmlFile = "/com/example/lecteurmusique/Views/ConnectionPage.fxml";
                }
            } else {
                System.out.println("Aucune information utilisateur trouvée.");
            }

            //Parent root = FXMLLoader.load(getClass().getResource("/com/example/lecteurmusique/Views/homePage.fxml"));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle(AppUtils.getAppName());
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(AppUtils.getAppLogo())));
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