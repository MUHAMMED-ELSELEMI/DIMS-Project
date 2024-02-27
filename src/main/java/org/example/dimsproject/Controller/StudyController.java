package org.example.dimsproject.Controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    void fetch(ActionEvent event){

    }
    @FXML
    void save(ActionEvent event)
    {

    }
    @FXML
    void update(ActionEvent event)
    {}

    }