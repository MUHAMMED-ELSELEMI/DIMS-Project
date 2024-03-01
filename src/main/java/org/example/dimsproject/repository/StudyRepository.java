package org.example.dimsproject.repository;

import org.example.dimsproject.model.Study;
import org.example.dimsproject.utils.DatabaseConfig;


import java.sql.*;

public class StudyRepository {

    private final DatabaseConfig databaseConfig;

    public StudyRepository() {
        this.databaseConfig = new DatabaseConfig();
    }
    private Connection connection =null;
    private Statement statement =null;
    private ResultSet resultSet =null;

    /**
     * FETCH button
     * @param studyId
     * @return
     * @throws SQLException
     */
    public Study getStudyById(int studyId) throws SQLException {
        connection = DriverManager.getConnection(databaseConfig.getURL(), databaseConfig.getUSER(), databaseConfig.getPASSWORD());
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
        connection = DriverManager.getConnection(databaseConfig.getURL(), databaseConfig.getUSER(), databaseConfig.getPASSWORD());
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
        connection = DriverManager.getConnection(databaseConfig.getURL(), databaseConfig.getUSER(), databaseConfig.getPASSWORD());
        statement = connection.createStatement();

        String title = study.getTitle().replaceAll("'", "''");
        String description = study.getDescription().replaceAll("'", "''");

        String query = "UPDATE studies SET title='" + title + "', description='" + description + "' WHERE id=" + study.getId() + ";";

        statement.executeUpdate(query);
    }

    /**
     *  DELETE
     * @param id
     */
    public void deleteById(int id){
        try {
            connection = DriverManager.getConnection(databaseConfig.getURL(), databaseConfig.getUSER(), databaseConfig.getPASSWORD());
            statement = connection.createStatement();
            String query = "DELETE FROM studies WHERE id="+id+";";
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
