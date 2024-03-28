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
     * Fonction pour récuperée les artistes dans la base de données <b>MySQL</b>.
     */
    public void getArtiste() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection cnct = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                System.out.println("Connection OK");
                
                Statement stmt = cnct.createStatement();
                
                String sql = "SELECT * FROM artiste";
                
                ResultSet res = stmt.executeQuery(sql);
                
                while (res.next()) {
                    int id = res.getInt(1);
                    String nom = res.getString(2);
                    System.out.println("ID Artiste: " + id +  " | Nom Artiste: " + nom);
                }
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }        
    }
    
    /**
     * Fonction pour récuperée les musiques dans la base de données <b>MySQL</b>.
     */
    public void getMusique() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection cnct = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                System.out.println("Connection OK");
                
                Statement stmt = cnct.createStatement();
                
                String sql = "SELECT * FROM son";
                
                ResultSet res = stmt.executeQuery(sql);
                
                while (res.next()) {
                    int id = res.getInt("IdSon");
                    String nom = res.getString("NomSon");
                    Time duree = res.getTime("Duree");
                    String lien = res.getString("lien");
                    System.out.println("ID : " + id +  " | Nom : " + nom + " | Durée : " + duree + " | Lien : " + lien);
                }
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Fonction pour récuperée les genres de musique dans la base de données <b>MySQL</b>.
     * 
     * @return
     */
    public Object getGenre() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            try (Connection cnct = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                System.out.println("Connection OK");
                
                Statement stmt = cnct.createStatement();
                
                String sql = "SELECT * FROM genre";
                
                ResultSet res = stmt.executeQuery(sql);
                /*
                while (res.next()) {
                    int id = res.getInt(1);
                    String nom = res.getString(2);
                    System.out.println("ID : " + id +  " | Nom : " + nom);
                    String [] tab;
                }*/
                
                return res;
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        return null;
    }
    
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
    
    /**
     *
     * @param event
     * @param user_name
     * @param user_email
     * @param user_password
     */
    public static void signUpUser(ActionEvent event, String user_name, String user_email, String user_password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            psCheckUserExists = connection.prepareStatement("SELECT * FROM utilisateur WHERE nom = ?");
            psCheckUserExists.setString(1, user_name);
            resultSet = psCheckUserExists.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                showAlert(Alert.AlertType.ERROR, "You cannot use this username.");
            } else {
                if (user_password.length() < 12) {
                    showAlert(Alert.AlertType.ERROR, "Votre mot de passe doit contenir au minimun 12 caractères.");
                } else {
                    psInsert = connection.prepareStatement("INSERT INTO utilisateur (nom, email, password) VALUES (?, ?, ?)");
                    psInsert.setString(1, user_name);
                    psInsert.setString(2, user_email);
                    psInsert.setString(3, user_password);
                    psInsert.executeUpdate();

                    changeScene(event, "View/homePage.fxml", DatabaseConfig.getAppName("Accueil"), user_name);
                }
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
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
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
     *
     * @param event
     * @param user_email
     * @param user_password
     */
    public static void logInUser(ActionEvent event, String user_email, String user_password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            ps = connection.prepareStatement("SELECT * FROM utilisateur WHERE email = ?");
            ps.setString(1, user_email);
            resultSet = ps.executeQuery();
            
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found !");
                showAlert(Alert.AlertType.ERROR, "L'utilisateur n'a pas été trouver. Email ou Mot de passe incorecte.");
            } else {

                while (resultSet.next()) {
                    String retrievedName = resultSet.getString("nom");
                    String retrievedPassword = resultSet.getString("password");
                    
                    if (retrievedPassword.equals(user_password)) {
                        changeScene(event, "View/homePage.fxml", DatabaseConfig.getAppName("Accueil"), null);   
                    } else {
                        showAlert(Alert.AlertType.ERROR, "L'utilisateur n'a pas été trouver. Email ou Mot de passe incorecte.");
                    }
                }
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
            
            if (resultSet.isBeforeFirst()) {
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
     * @param alertType Definit le type de l'alerte (<i><strong>CONFIRMATION, ERROR, INFORMATION, NONE, WARNING<strong></i>).
     * @param message Affiche un message sur l'alerte.
     */
    private static void showAlert(Alert.AlertType alertType, String message) {
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
