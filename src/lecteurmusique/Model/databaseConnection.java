/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lecteurmusique.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lecteurmusique.DatabaseConfig;

/**
 *
 * @author Jérémy Hoarau
 */
public class databaseConnection {
    
    private static final String JDBC_URL = DatabaseConfig.getDbUrl();
    private static final String USER = DatabaseConfig.getDbUser();
    private static final String PASSWORD = DatabaseConfig.getDbPassword();
    
    /**
     * Créer une connexion avec la base de données
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    
    /**
     * Ferme la connexion avec la base de données
     *
     * @throws SQLException
     */
    public  static void closeConnection() throws SQLException {
        getConnection().close();
    }
}
