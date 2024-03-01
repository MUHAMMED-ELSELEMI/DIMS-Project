package org.example.dimsproject.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

   private static final String PROPERTIES_FILE = "application.properties";

    private String url;
    private String user;
    private String password;

    public DatabaseConfig() {
        Properties properties = loadProperties();

        this.url = properties.getProperty("db.url");
        this.user = properties.getProperty("db.user");
        this.password = properties.getProperty("db.password");
    }

    public String getURL() {
        return url;
    }

    public String getUSER() {
        return user;
    }

    public String getPASSWORD() {
        return password;
    }

    private Properties loadProperties() {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            if (input == null) {
                System.out.println("Unable to find " + PROPERTIES_FILE);
                return properties;
            }
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            return new Properties();
        }
    }
}
