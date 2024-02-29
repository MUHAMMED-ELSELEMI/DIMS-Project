package org.example.dimsproject.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.dimsproject.HelloApplication;
import org.example.dimsproject.model.Study;
import org.example.dimsproject.repository.StudyRepository;

import java.io.IOException;

public class StudyController {

    public StudyController(){
        StudyRepository studyRepository = new StudyRepository();
    }

    @FXML
    private Button savebtn;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    @FXML
    private Button clearbtn;
    @FXML
    private Button advisorpage;
    @FXML
    private Button closebtn;
    @FXML
    private Button fetchbtn;
    @FXML
    private TextField T1;
    @FXML
    private TextField T2;
    @FXML
    private TextField T3;

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) savebtn.getScene().getWindow();
        stage.close();
        }
    @FXML
    void clear(ActionEvent event){
        T1.clear();
        T2.clear();
        T3.clear();

    }
    @FXML
    void delete(ActionEvent event)
    {
        StudyRepository studyRepository = new StudyRepository();
        studyRepository.deleteById(Integer.parseInt(T1.getText()));
    }

    @FXML
    void getAdvisorView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("advisor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        stage.setTitle("Advisor");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void fetch(ActionEvent event) throws Exception {
        StudyRepository studyRepository = new StudyRepository();
        Study s = studyRepository.getStudyById(Integer.parseInt(T1.getText()));
        if (s.getId()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!", ButtonType.CLOSE);
            alert.showAndWait();
            T1.setText("");
            T1.requestFocus();
        }
        T2.setText(s.getTitle());
        T3.setText(s.getDescription());

    }
    @FXML
    void save(ActionEvent event) throws Exception
    {
        StudyRepository studyRepository = new StudyRepository();
        Study s = new Study();
        s.setId(Integer.parseInt(T1.getText()));
        s.setTitle(T2.getText());
        s.setDescription(T3.getText());
        studyRepository.saveNewStudy(s);

    }
    @FXML
    void update(ActionEvent event) throws Exception
    {
        StudyRepository studyRepository = new StudyRepository();
        Study s = new Study();
        s.setId(Integer.parseInt(T1.getText()));
        s.setTitle(T2.getText());
        s.setDescription(T3.getText());
        studyRepository.updateStudy(s);

    }

    }