package com.example.lecteurmusique.Models;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NombreEcoute extends Musique {

    public int idEcoute, idMusique;
    public LocalDateTime dateAjout;

    public NombreEcoute(int idMusique, String nom, String lien, java.util.Date creationDate, int idGenre, int idArtiste, LocalDateTime dateAjout) {
        super(idMusique, nom, lien, creationDate, idGenre, idArtiste);
        this.dateAjout = dateAjout;
    }

    public static void ajoutNombreEcoute(int idMusique, Date dateAjout) {
        Connection connection = null;
        PreparedStatement psAjoutNombreEcoute = null;

        try {
            try {
                connection = DatabaseConnection.creerConnexion();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            psAjoutNombreEcoute = connection.prepareStatement("INSERT INTO nombreEcoute(idMusique, dateAjout) VALUES (?, ?);");
            psAjoutNombreEcoute.setInt(1, idMusique);
            psAjoutNombreEcoute.setDate(2, dateAjout);
            psAjoutNombreEcoute.executeUpdate();

            DatabaseConnection.fermerConnexion(connection, psAjoutNombreEcoute, null, null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static ResultSet recupererMusique() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement psNombreEcoute = null;
        ResultSet resultSet = null;

        connection = DatabaseConnection.creerConnexion();

        psNombreEcoute = connection.prepareStatement("SELECT * FROM v_nombreEcoute ORDER BY Nombre_Ecoute DESC LIMIT 50;");
        resultSet = psNombreEcoute.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("nom")+ " " + resultSet.getInt("Nombre_Ecoute"));
        };
        return resultSet;
    }

}
