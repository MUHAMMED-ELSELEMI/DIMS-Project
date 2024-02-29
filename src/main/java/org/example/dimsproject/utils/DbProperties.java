package org.example.dimsproject.utils;

public class DbProperties {

    private String BASE_URL = "jdbc:mysql://localhost:3306/";
    private String DATABASE_NAME = "dimsproject";
    private String URL = BASE_URL + DATABASE_NAME;
    private String USER = "root";
    private String PASSWORD = "123456";

    public DbProperties() {
    }

    public DbProperties(String BASE_URL, String DATABASE_NAME, String URL, String USER, String PASSWORD) {
        this.BASE_URL = BASE_URL;
        this.DATABASE_NAME = DATABASE_NAME;
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public String getDATABASE_NAME() {
        return DATABASE_NAME;
    }

    public void setDATABASE_NAME(String DATABASE_NAME) {
        this.DATABASE_NAME = DATABASE_NAME;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
