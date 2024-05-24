package com.example.lecteurmusique;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class XmlUtils {

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String FILE_PATH = USER_HOME + "/.LecteurMusique/config/user_info.xml";
    private static XmlMapper xmlMapper = new  XmlMapper();

    public static class UserInfo {
        private String connection;
        private Date date;
        private int userId;

        // Constructeurs, getters et setters
        public UserInfo() {}

        public UserInfo(String connection, Date date, int userId) {
            this.connection = connection;
            this.date = date;
            this.userId = userId;
        }

        public String getConnection() {
            return connection;
        }

        public void setConnection(String connection) {
            this.connection = connection;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    // Méthode pour initialiser le fichier XML si nécessaire
    public static void initializeConfigFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();  // Crée le répertoire si nécessaire
            setInformation("", new Date(), 0);  // Crée un fichier XML avec des valeurs par défaut
        }
    }

    public static void setInformation(String connection, Date date, int userId) {
        UserInfo userInfo = new UserInfo(connection, date, userId);
        try {
            xmlMapper.writeValue(new File(FILE_PATH), userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserInfo getInformation() {
        try {
            return xmlMapper.readValue(new File(FILE_PATH), UserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
