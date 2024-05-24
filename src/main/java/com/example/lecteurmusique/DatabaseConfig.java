package com.example.lecteurmusique;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static Properties properties = null;

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println("Le fichier de configuration 'config.properties' n'a pas été trouvé.");
                System.exit(1);  // Quitte l'application en cas d'erreur
            }

            properties = new Properties();
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUser() {
        return properties.getProperty("db.user");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}
