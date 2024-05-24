package com.example.lecteurmusique.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Musique extends DatabaseConnection {
    private int idMusique;
    private String nom, lien, nomArtiste;
    private Date dateCreation;
    private int idGenre, idArtiste;

    public Musique(int idMusique, String nom, String nomArtiste, String lien) {
        this.idMusique = idMusique;
        this.nom = nom;
        this.nomArtiste = nomArtiste;
        this.lien = lien;
    }

    /**
     *
     * @param idMusique
     * @param nom
     * @param lien
     * @param creationDate
     * @param idGenre
     * @param idArtiste
     */
    public Musique(int idMusique, String nom, String lien, Date creationDate, int idGenre, int idArtiste) {
        this.idMusique = idMusique;
        this.nom = nom;
        this.lien = lien;
        this.dateCreation = creationDate;
        this.idGenre = idGenre;
        this.idArtiste = idArtiste;
    }

    /**
     *
     * @return l'id de la musique
     */
    public int getIdMusique() {
        return idMusique;
    }

    /**
     *
     * @return le nom de la musique
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return le nom de l'artiste
     */
    public String getNomArtiste() {
        return nomArtiste;
    }

    /**
     *
     * @return le lien de la musique
     */
    public String getLien() {
        return lien;
    }

    /**
     *
     * @return la date de creation de la musique
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     *
     * @return l'id du genre de la musique
     */
    public int getIdGenre() {
        return idGenre;
    }

    /**
     *
     * @return l'id de l'artiste de la musique
     */
    public int getIdArtiste() {
        return idArtiste;
    }

    /**
     * @return Les musiques.
     */
    public static ArrayList<Musique> recuperMusique() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Musique> musiques = new ArrayList<>();

        try {
            try {
                connection = creerConnexion();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ps = connection.prepareStatement("SELECT m.idMusique, m.nom, m.lien, a.nom as artiste FROM musique m "
                    + "INNER JOIN Artistes a ON m.idArtiste = a.idArtiste");
            resultSet = ps.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    musiques.add(new Musique(resultSet.getInt("idMusique"), resultSet.getString("nom"), resultSet.getString("artiste"), resultSet.getString("lien")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                fermerConnexion(connection, ps, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Musique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return musiques;
    }

    /**
     * @param playlistId Id de la playlist choisie par l'utilisateur.
     * @return Les musiques par rapport à une playlist.
     */
    public static ArrayList<Musique> recuperMusiqueParPlaylistID(int playlistId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Musique> musiques = new ArrayList<>();

        try {
            try {
                connection = creerConnexion();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            // Revoir la requete
            ps = connection.prepareStatement("SELECT m.idMusique, m.nom, m.lien, a.nom as artiste FROM musique m "
                    + "INNER JOIN artistes a ON m.idArtiste = a.idArtiste "
                    + "INNER JOIN Playlist_Chanson pc ON m.idMusique = pc.idMusique "
                    + "WHERE pc.idPlaylist = ?");
            ps.setInt(1, playlistId);
            resultSet = ps.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    musiques.add(new Musique(resultSet.getInt("idMusique"), resultSet.getString("nom"), resultSet.getString("artiste"), resultSet.getString("lien")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                fermerConnexion(connection, ps, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Musique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return musiques;
    }

    /**
     *
     * @param idGenre Id du genre choisie par l'utilisateur.
     * @return Les musiques par rapport à un genre.
     */
    public static ArrayList<Musique> recuperMusiqueParGenre(int idGenre) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Musique> musiques = new ArrayList<>();

        try {
            try {
                connection = creerConnexion();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ps = connection.prepareStatement("SELECT m.idMusique, m.nom, m.lien, a.nom as artiste, g.Nom FROM musique m "
                    + "INNER JOIN Artistes a ON m.idArtiste = a.idArtiste "
                    + "INNER JOIN Genre g ON m.idGenre = g.idGenre "
                    + "WHERE g.idGenre = ?;");
            ps.setInt(1, idGenre);
            resultSet = ps.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    musiques.add(new Musique(resultSet.getInt("idMusique"), resultSet.getString("nom"), resultSet.getString("artiste"), resultSet.getString("lien")));
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                fermerConnexion(connection, ps, null, resultSet);
            } catch (SQLException ex) {
                Logger.getLogger(Musique.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return musiques;
    }
}
