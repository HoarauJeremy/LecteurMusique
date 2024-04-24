/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Class pour les playlist
 * 
 * @author Jérémy Hoarau
 */
public class Playlist extends DatabaseConnection {
    
    public int playlistId, idUser;
    public String nom;
    public Date dateCreation;
    public int privee;

    /**
     *
     * @param playlistId
     * @param idUser
     * @param nom
     * @param dateCreation
     * @param privee
     */
    public Playlist(int playlistId, int idUser, String nom, Date dateCreation, int privee) {
        this.playlistId = playlistId;
        this.idUser = idUser;
        this.nom = nom;
        this.dateCreation = dateCreation;
        this.privee = privee;
    }

    /**
     *
     * @return l'id de la playlist
     */
    public int getPlaylistId() {
        return playlistId;
    }

    /**
     *
     * @return l'id de l'utilisateur qui a créer la playlist
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     *
     * @return le nom de la playlist
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return la date de creation de la playlist
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     *
     * @return si la playlist est privée ou non
     */
    public int getPrivee() {
        return privee;
    }

    /**
     * Permet de definir le status de la playlist (<i>Privee</i> ou <i>Public</i>)
     *
     * @param privee
     */
    public void setPrivee(int privee) {
        this.privee = privee;
    }
    
    /**
     * Formate la date de creation d'une playlist 
     *
     * @param datePlaylist Recuperer la date de creation d'une playlist
     * @return la date au format <i>JJ/MM/AAAA</i>
     */
    public static String formatDate(Date datePlaylist) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(datePlaylist);
        System.out.println(date);
        return date;
  }
    
    /**
     * Fonction pour créer une playlist
     *
     * @param nomPlaylist
     * @param dateCreation
     * @param idUser
     */
    public void createPlaylist(String nomPlaylist, Date dateCreation, int idUser) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckPlaylistExists = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            psCheckPlaylistExists = connection.prepareStatement("SELECT * FROM playlist WHERE nom = ? AND idUser = ?");
            psCheckPlaylistExists.setString(1, nomPlaylist);
            psCheckPlaylistExists.setInt(1, idUser);
            resultSet = psCheckPlaylistExists.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Playlist Already exist");
                alert.setHeaderText(null);
                alert.setContentText("You cannot use " + nomPlaylist + " .");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO playlist (Nom, dateCreation, idUser, privee) VALUES (?, ?, ?, ?)");
                psInsert.setString(1, nomPlaylist);
                psInsert.setDate(2, (java.sql.Date) dateCreation);
                psInsert.setInt(3, idUser);
                psInsert.setBoolean(4, true);
                psInsert.executeUpdate();
            }
            
            closeConnection(connection, psCheckPlaylistExists, psInsert, resultSet);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Fonction pour supprimer une playlist 
     * 
     * @param idUser
     * @param idPlaylist
     */
    public static void deletePlaylist(int idUser, int idPlaylist) {
        Connection connection = null;
        PreparedStatement psCheckPlaylist = null;
        PreparedStatement psDeletePlaylist = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            psCheckPlaylist = connection.prepareStatement("SELECT * FROM playlist WHERE PlaylistID = ? AND idUser = ?");
            psCheckPlaylist.setInt(1, idPlaylist);
            psCheckPlaylist.setInt(2, idUser);
            resultSet = psCheckPlaylist.executeQuery();
            
            if (resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                //alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sur de vouloirs de supprimer cette playlist ?");
                alert.showAndWait().filter(ButtonType.OK::equals).isPresent();
                if (alert.getResult() == ButtonType.OK) {
                    psDeletePlaylist = connection.prepareStatement("DELETE FROM playlist WHERE PlaylistID = ? AND idUser = ?");
                    psDeletePlaylist.setInt(1, idPlaylist);
                    psDeletePlaylist.setInt(2, idUser);
                    psDeletePlaylist.executeUpdate();
                }
            }
            
            closeConnection(connection, psCheckPlaylist, psDeletePlaylist, resultSet);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
    }
    
    /**
     * Permet de mettre à jour une playlist.
     *
     * @param idUser Id de l'utilisateur qui à créer la playlist.
     * @param idPlaylist Id de la playlist.
     * @param nomPlaylist Definit le nom de la playlist.
     * @param privee Definit le statut (1 pour <i>Public</i> ou 0 pour <i>Privée</i>) d'une Playlist.
     * @throws SQLException
     */
    public static void updatePlaylist(int idUser, int idPlaylist, String nomPlaylist, int privee) throws SQLException {
        Connection connection = null;
        PreparedStatement psUpdatePlaylist = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            psUpdatePlaylist = connection.prepareStatement("UPDATE playlist SET nom = ?, privee = ? WHERE PlaylistID = ? AND nom = ?");
            psUpdatePlaylist.setString(1, nomPlaylist);
            psUpdatePlaylist.setInt(2, privee);
            psUpdatePlaylist.setInt(3, idPlaylist);
            psUpdatePlaylist.setInt(4, idUser);
            psUpdatePlaylist.executeUpdate();
            
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            closeConnection(connection, psUpdatePlaylist, null, resultSet);
        }
    }
    
}
