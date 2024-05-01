/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lecteurmusique.Model.DatabaseConnection;
import lecteurmusique.Model.Genre;
import lecteurmusique.Model.Musique;
import lecteurmusique.Model.Playlist;
import lecteurmusique.controllers.GenreController;
import lecteurmusique.controllers.HomePageController;
import lecteurmusique.controllers.LoggedInController;
import lecteurmusique.controllers.PlaylistController;
import lecteurmusique.controllers.PlaylistListeController;

/**
 * 
 * @author Jérémy Hoarau
 */
public class Connexion {
    
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
     * Fontion qui va rediriger vers la page d'accueil
     * @hidden à Modifier!!
     *
     * @param event
     * @param fxmlFile
     * @param title
     * @param username
     */
    public static void changeSceneToHome(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                HomePageController homePageController = loader.getController();
                homePageController.setUserInformation(username);
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
    private static void changeSceneToPlaylist(ActionEvent event, String fxmlFile, String title, String message, ArrayList<Playlist> playlists, ArrayList<Musique> musiques) {
        Parent root = null;
        
        if (playlists != null && musiques != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                PlaylistController playlistController = loader.getController();
                playlistController.setInformationPlaylist(playlists, musiques);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                PlaylistController playlistController = loader.getController();
                playlistController.setMessageInformation(message);
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
    private static void changeSceneToGenre(ActionEvent event, String fxmlFile, String title, HashMap<Integer, Genre> tabGenre) {
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
    
    /**
     * 
     * @param event
     * @param fxmlFile
     * @param title
     * @param message 
     */
    private static void changeSceneToPlaylisList(ActionEvent event, String fxmlFile, String title, String message, ArrayList<Playlist> arrayListPlaylist) {
        Parent root = null;
        
        if (message != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                PlaylistListeController playlistListeController = loader.getController();
                playlistListeController.setMessageInformation(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(Connexion.class.getResource(fxmlFile));
                root = loader.load();
                PlaylistListeController playlistListeController = loader.getController();
                playlistListeController.setPlaylistList(arrayListPlaylist);
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
     * Fonction qui va récupérer toutes les infotmations d'un utilisateur.
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
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM utilisateur WHERE idUser = ?");
            ps.setInt(1, user_id);
            resultSet = ps.executeQuery();
            
            if (!resultSet.isBeforeFirst()) {
                afficherAlerte(Alert.AlertType.ERROR, "msg temporaire");
            } else {
                while (resultSet.next()) {
                    user_name = resultSet.getString("nom");
                    user_email = resultSet.getString("email");
                }
                
                changeSceneToProfile(event, "View/logged-in.fxml", AppUtils.getAppNameWithAction("Profile de " + user_name), user_name, user_email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeConnection(connection, ps, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
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
        ArrayList<Playlist> playlists = new ArrayList<>();
        
        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM playlist;");
            //ps = connection.prepareStatement("SELECT * FROM playlist WHERE idUser = ?;");
            resultSet = ps.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {                    
                    playlists.add(
                        new Playlist(
                            resultSet.getInt("PlaylistID"),
                            resultSet.getInt("idUser"),
                            resultSet.getString("Nom"),
                            resultSet.getTime("dateCreation"),
                            resultSet.getInt("privee")
                        )
                    );
                }
            } else {
                message = "Aucune playlist disponible";
            }
            
            changeSceneToPlaylisList(event, "View/Playlist-Liste.fxml", AppUtils.getAppNameWithAction("PLaylist"), message, playlists);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeConnection(connection, ps, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Fonction qui va récupérer toutes les données de la table Playlist en fonction de l'id de l'utilisateur et les affichées sur une nouvelle scène.
     * 
     * @param event
     * @param playlistId
     */
    public static void showPlaylistUser(ActionEvent event, int playlistId) {
        Connection connection = null;
        PreparedStatement ps = null; 
        ResultSet resultSet = null;
        String message = null;
        ArrayList<Playlist> playlists = new ArrayList<>();
        ArrayList<Musique> musiques = new ArrayList<>();
        
        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement("SELECT p.*, m.* FROM playlist p INNER JOIN playlist_chanson pC ON p.PlaylistID = pC.PlaylistID INNER JOIN musique m ON pC.idMusique = m.idMusique WHERE p.PlaylistID = ?");
            ps.setInt(1, playlistId);
            resultSet = ps.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    playlists.add(
                        new Playlist(
                            resultSet.getInt(1), 
                            resultSet.getInt(4), 
                            resultSet.getString(2), 
                            resultSet.getDate(3),
                            resultSet.getInt("privee")
                        )
                    );
                    musiques.add(new Musique(resultSet.getInt("idMusique"), resultSet.getString(6), resultSet.getString("lien"), resultSet.getDate("creationDate"), resultSet.getInt("idGenre"), resultSet.getInt("idArtiste")));
                }
            } else {
                message = "Aucune Musique dans cette Playlist";
            }
            
            changeSceneToPlaylist(event, "View/Playlist.fxml", AppUtils.getAppNameWithAction("Playlist"), message, playlists, musiques);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeConnection(connection, ps, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void showUpdatePlaylist(ActionEvent event) {
        changeScene(event, "View/", AppUtils.getAppNameWithAction("Modification de la playlist"), null);
    }
    
    /**
     * Fonction qui va récupérer toutes les données de la table genre et les affichées sur une nouvelle scène.
     * @param event
     */
    public static void showSongGender(ActionEvent event) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        HashMap<Integer, Genre> tab = new HashMap<>();

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement("SELECT * FROM genre");
            resultSet = ps.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    tab.put(resultSet.getInt(1), new Genre(resultSet.getInt(1), resultSet.getString(2)));
                }
                changeSceneToGenre(event, "View/genre.fxml", AppUtils.getAppNameWithAction("Genre"), tab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseConnection.closeConnection(connection, ps, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    /**
     * Function pour afficher des alertes.
     * 
     * @param typeAlerte Definit le type de l'alerte (<i><strong>CONFIRMATION, ERROR, INFORMATION, NONE, WARNING</strong></i>).
     * @param message Affiche un message sur l'alerte.
     */
    public static void afficherAlerte(Alert.AlertType typeAlerte, String message) {
        Alert alert = new Alert(typeAlerte);
        //alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    
}
