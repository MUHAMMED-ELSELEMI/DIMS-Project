package org.example.dimsproject.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.dimsproject.model.Study;
import org.example.dimsproject.repository.StudyRepository;

public class StudyController {

    @FXML
    private Button SAVEBUTTON;
    @FXML
    private Button UPDATEBUTTON;
    @FXML
    private Button DELETEBUTTON;
    @FXML
    private Button CLOSEBUTTON;
    @FXML
    private Button FETCHBUTTON;
    @FXML
    private TextField T1;
    @FXML
    private TextField T2;
    @FXML
    private TextField T3;

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) SAVEBUTTON.getScene().getWindow();
        stage.close();
        }
    @FXML
    void delete(ActionEvent event){
        T1.clear();
        T2.clear();
        T3.clear();

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