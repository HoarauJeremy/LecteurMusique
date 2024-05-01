/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lecteurmusique;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Jérémy Hoarau
 * Created: 26 avr. 2024
 */
public class AppUtils {

    private static Properties properties = null;
          
    static {
        try (InputStream input = AppUtils.class.getClassLoader().getResourceAsStream("lecteurmusique/application.properties")) {
            if (input == null) {
                System.err.println("Le fichier de configuration 'application.properties' n'a pas été trouvé.");
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
     * Definit les informations permetant de savoir si un utilisateur est connecter
     *
     * @param connection
     * @param device
     * @param date
     */
    public static void setInformation(String connection, String device, Date date) {
        try (OutputStream output = new FileOutputStream("lecteurmusique/application.properties")) {
            Properties prop = new Properties();
            prop.setProperty("USER.connection", connection);
            prop.setProperty("USER.device", device);
            prop.setProperty("USER.date", date.toString());
            prop.store(output, null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
}
    