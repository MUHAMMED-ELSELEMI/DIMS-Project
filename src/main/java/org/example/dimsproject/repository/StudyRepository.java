package org.example.dimsproject.repository;

import org.example.dimsproject.model.Study;

import java.sql.*;

public class StudyRepository {

    // JDBC parameters
    private final String URL = "jdbc:mysql://127.0.0.1:3306/dimsproject";
    private final String USER = "root";
    private final String PASSWORD = "123456";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * FETCH button
     * @param studyId
     * @return
     * @throws SQLException
     */
    public Study getStudyById(int studyId) throws SQLException {
        connection = DriverManager.getConnection(URL,USER,PASSWORD);
        String query = "SELECT * FROM studies WHERE id =" + studyId +";";
        statement =connection.createStatement();
        resultSet = statement.executeQuery(query);
        Study study = new Study();
        while(resultSet.next()){
            study.setId(resultSet.getInt(1));
            study.setTitle(resultSet.getString(2));
            study.setDescription(resultSet.getString(3));
        }

        connection.close();
        return study;

    }

    /**
     * SAVE Button
     * @param study
     * @throws SQLException
     */
    public void saveNewStudy(Study study) throws SQLException {
        connection = DriverManager.getConnection(URL,USER,PASSWORD);
        statement = connection.createStatement();
        String query = "INSERT INTO studies VALUES (" + study.getId() +",'" + study.getTitle() + "','" + study.getDescription() +"');";
        statement.executeUpdate(query);
    }

    /**
     * UPDATE Button
     * @param study
     * @throws SQLException
     */
    public void updateStudy(Study study) throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();

        String title = study.getTitle().replaceAll("'", "''");
        String description = study.getDescription().replaceAll("'", "''");

        String query = "UPDATE studies SET title='" + title + "', description='" + description + "' WHERE id=" + study.getId() + ";";

        statement.executeUpdate(query);
    }




}
