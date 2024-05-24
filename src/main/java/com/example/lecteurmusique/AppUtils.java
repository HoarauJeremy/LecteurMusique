package com.example.lecteurmusique;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class AppUtils {
    private static Properties properties = null;
    private static final String APPLICATION_PROPERTIES = "com/example/lecteurmusique/application.properties";

    static {

        try (InputStream input = AppUtils.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
            if (input == null) {
                System.err.println("Le fichier de configuration 'application.properties' n'a pas été trouvé.");
                System.err.println(new Exception().getMessage());
                System.exit(1);  // Quitte l'application en cas d'erreur
            }

            properties = new Properties();
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Donne le nom actuel de l'application
     * @return le nom de l'application avec l'action ou le nom de la scene
     */
    public static String getAppName() {
        return properties.getProperty("APP.Name");
    }

    /**
     *
     * @param action nom de l'action ou de la scene à afficher
     * @return le nom de l'application avec l'action ou le nom de la scene
     */
    public static String getAppNameWithAction(String action) {
        return properties.getProperty("APP.Name").concat(" - " + action);
    }

    /**
     * Donne la version actuel de l'application
     * @return la version de l'application.
     */
    public static String getAppVersion() {
        return properties.getProperty("APP.Version");
    }

    /**
     *
     * @return le logo de l'application
     */
    public static String getAppLogo() {
        return properties.getProperty("APP.Logo");
    }

    /**
     *
     * @return
     */
    public static String getUtilisateurConnecter() {
        return properties.getProperty("USER.connection");
    }

    /**
     *
     * @return
     */
    public static String getDateConnecter() {
        return properties.getProperty("USER.date");
    }

    /**
     *
     * @return
     */
    public static int getIdUtilisateur() {
        return Integer.parseInt(properties.getProperty("USER.id"));
    }

    /**
     * Definit les informations permetant de savoir si un utilisateur est connecter
     *
     * @param connection
     * @param date
     * @param idUtilisateur
     */
    /*public static void setInformation(String connection, Date date, int idUtilisateur) {
        try (OutputStream output = new FileOutputStream(APPLICATION_PROPERTIES)) {
            Properties prop = new Properties();
            prop.setProperty("USER.connection", connection);
            prop.setProperty("USER.date", date.toString());
            prop.setProperty("USER.id", Integer.toString(idUtilisateur));
            prop.store(output, null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }*/

    /**
     * Supprime les informations de l'utilisateur du fichier properties
     *
     */
    /*public static void detruitInformation() {
        try (OutputStream output = new FileOutputStream(APPLICATION_PROPERTIES)) {
            Properties prop = new Properties();
            prop.setProperty("USER.connection", "");
            prop.setProperty("USER.date", "");
            prop.setProperty("USER.id", "");
            prop.store(output, null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }*/
}
