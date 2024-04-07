/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lecteurmusique.Model.DatabaseConnection;
import lecteurmusique.controllers.GenreController;
import lecteurmusique.controllers.LoggedInController;
import lecteurmusique.controllers.PlaylistListeController;

/**
 * Class de connexion à une base de donnée <b>MySQL</b>, pour une application
 * d'ecoute de musique.
 *
 * @author Jérémy Hoarau
 */
public class Connexion {
    
    private static final String JDBC_URL = DatabaseConfig.getDbUrl();
    private static final String USER = DatabaseConfig.getDbUser();
    private static final String PASSWORD = DatabaseConfig.getDbPassword();
    
    /**
     *
     * @param event
     * @param fxmlFile
     * @param title
     * @param username
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Connexion.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     *
     * @param event
     * @param fxmlFile
     * @param title
     * @param user_name
     * @param user_email
     */
    private static void changeSceneToProfile(ActionEvent event, String fxmlFile, String title, String user_name, String user_email) {
        Parent root = null;
        
        if (user_name != null && user_email != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(user_name, user_email);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Connexion.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     *
     * @param event
     * @param fxmlFile
     * @param title
     * @param idPlaylist
     * @param nom
     */
    private static void changeSceneToPlaylist(ActionEvent event, String fxmlFile, String title, int idPlaylist, String nom) {
        Parent root = null;
        
        if (nom != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                PlaylistListeController playlistListeController = loader.getController();
                //PlaylistListeController.setPlaylistInformation(idPlaylist, nom);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Connexion.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     * 
     * @param event
     * @param fxmlFile
     * @param title
     * @param tabGenre 
     */
    private static void changeSceneToGenre(ActionEvent event, String fxmlFile, String title, HashMap<Integer, String> tabGenre) {
        Parent root = null;

        if (tabGenre != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                GenreController genreController = loader.getController();
                genreController.setGenre(tabGenre);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Connexion.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    private static void changeSceneToPlaylisList(ActionEvent event, String fxmlFile, String title, String message) {
        Parent root = null;
        
        if (message != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                PlaylistListeController playlistListeController = loader.getController();
                playlistListeController.setInformationMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                PlaylistListeController playlistListeController = loader.getController();
                playlistListeController.setPlaylistList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    /**
     *
     * @param event
     * @param user_id
     */
    public static void showProfileUser(ActionEvent event, int user_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        String user_email = null;
        String user_name = null;
        
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM utilisateur WHERE idUser = ?");
            ps.setInt(1, user_id);
            resultSet = ps.executeQuery();
            
            if (!resultSet.isBeforeFirst()) {
                showAlert(Alert.AlertType.ERROR, "msg temporaire");
            } else {
                while (resultSet.next()) {
                    user_name = resultSet.getString("nom");
                    user_email = resultSet.getString("email");
                }
                
                changeSceneToProfile(event, "View/logged-in.fxml", DatabaseConfig.getAppName("Profile de " + user_name), user_name, user_email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Fonction qui va récupérer toutes les Playlist et les affichées sur une nouvelle scène.
     * 
     * @param event
     */
    public static void showPlaylistList(ActionEvent event) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String message = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM playlist;");
            resultSet = ps.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {                    
                    
                }
            } else {
                message = "Aucune playliste disponible";
            }
            
            changeSceneToPlaylisList(event, "View/Playlist-Liste.fxml", DatabaseConfig.getAppName("PLaylist"), message);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    DatabaseConnection.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Fonction qui va récupérer toutes les données de la table Playlist en fonction de l'id de l'utilisateur et les affichées sur une nouvelle scène.
     * 
     * @param event
     * @param user_id
     */
    public static void showPlaylistUser(ActionEvent event, int user_id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT p.* FROM utilisateur u INNER JOIN playlist p ON u.idUser = p.idUser WHERE u.idUser = ?");
            ps.setInt(1, user_id);
            resultSet = ps.executeQuery();
            
            if (!resultSet.isBeforeFirst()) {
                showAlert(Alert.AlertType.ERROR, "msg temporaire");
            } else {
                while (resultSet.next()) {
                    String idPlaylist = resultSet.getString(1);
                    String nom = resultSet.getString(2);
                }
                
                changeSceneToPlaylist(event, "View/Playlist-Liste.fxml", DatabaseConfig.getAppName("Playlist"), 0, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Fonction qui va récupérer toutes les données de la table genre et les affichées sur une nouvelle scène.
     * @param event
     */
    public static void showSongGender(ActionEvent event) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        HashMap<Integer, String> tab = new HashMap<>();

        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM genre");
            resultSet = ps.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    tab.put(resultSet.getInt(1), resultSet.getString(2));
                }
                changeSceneToGenre(event, "View/genre.fxml", DatabaseConfig.getAppName("Genre"), tab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    /**
     * Function pour afficher des alertes.
     * 
     * @param alertType Definit le type de l'alerte (<i><strong>CONFIRMATION, ERROR, INFORMATION, NONE, WARNING</strong></i>).
     * @param message Affiche un message sur l'alerte.
     */
    public static void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        //alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
