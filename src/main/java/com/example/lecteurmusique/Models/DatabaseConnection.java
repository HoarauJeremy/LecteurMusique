package com.example.lecteurmusique.Models;

import com.example.lecteurmusique.DatabaseConfig;

import java.sql.*;

public class DatabaseConnection {
    private static final String JDBC_URL = DatabaseConfig.getDbUrl();
    private static final String USER = DatabaseConfig.getDbUser();
    private static final String PASSWORD = DatabaseConfig.getDbPassword();

    /**
     * Créer une connexion avec la base de données
     *
     * @return la connexion à la base de donnée
     * @throws SQLException
     */
    public static Connection creerConnexion() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    /**
     * Ferme la connexion avec la base de données
     *
     * @param connection
     * @param preparedStatement
     * @param preparedStatement1
     * @param resultset
     * @throws SQLException
     */
    public  static void fermerConnexion(Connection connection, PreparedStatement preparedStatement, PreparedStatement preparedStatement1, ResultSet resultset) throws SQLException {
        try {
            if (resultset != null) {
                resultset.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (preparedStatement1 != null) {
                preparedStatement1.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }
}
