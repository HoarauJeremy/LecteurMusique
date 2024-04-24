/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique.Model;

import java.sql.*;
import lecteurmusique.DatabaseConfig;

/**
 * Class de connexion à la base de donnée <i><strong>MySQL</strong></i>
 * 
 * @author Jérémy Hoarau
 */
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
    public static Connection getConnection() throws SQLException {
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
    public  static void closeConnection(Connection connection, PreparedStatement preparedStatement, PreparedStatement preparedStatement1, ResultSet resultset) throws SQLException {
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
