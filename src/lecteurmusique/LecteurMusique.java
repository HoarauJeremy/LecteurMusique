/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package lecteurmusique;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Jérémy Hoarau
 */
public class LecteurMusique extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            
//            String fxmlFile;
//            
//            if (!AppUtils.getUtilisateurConnecter().isEmpty()) {
//                fxmlFile = "View/homePage.fxml";
//            } else {
//                fxmlFile = "View/";
//            }
            
            Parent root = FXMLLoader.load(getClass().getResource("View/homePage.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle(AppUtils.getAppName());
            primaryStage.getIcons().add(new Image(AppUtils.getAppLogo()));
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

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
