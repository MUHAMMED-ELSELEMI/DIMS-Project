package org.example.dimsproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.BigDecimalStringConverter;
import org.example.dimsproject.HelloApplication;
import org.example.dimsproject.model.Study;
import org.example.dimsproject.repository.StudyRepository;
import org.example.dimsproject.utils.PopUp;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

public class StudyController {


    @FXML
    private Button savebtn;

    @FXML
    private TextField T1;
    @FXML
    private TextField T2;
    @FXML
    private TextField T3;

    private final StudyRepository studyRepository;
    public StudyController(){
         this.studyRepository = new StudyRepository();
    }
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
    public void delete(ActionEvent event) throws SQLException {
        if (T1.getText().isBlank()){
            PopUp.showPopup("Warning!","Id is mandatory!", Alert.AlertType.ERROR);
            return;
        }
        if (studyRepository.getStudyById(Integer.parseInt(T1.getText())) == null){
            PopUp.showPopup("Error!","Study not found! :"+T1.getText(),AlertType.ERROR);
            return;
        }
        studyRepository.deleteById(Integer.parseInt(T1.getText()));
        PopUp.showPopup("Success!","Study is deleted successfully!: "+T1.getText(), Alert.AlertType.WARNING);

    }

    private Study getStudy()
    {
        Study study;
        if (T1.getText().isBlank() || T2.getText().isBlank() || T3.getText().isBlank())
        {
            PopUp.showPopup("Fields!","All fields mandatory!", Alert.AlertType.WARNING);
            return null;
        }
        study = new Study();
        study.setId(Integer.parseInt(T1.getText()));
        study.setTitle(T2.getText());
        study.setDescription(T3.getText());
        return study;
    }

    @FXML
    void getAdvisorView(ActionEvent event) throws IOException {
        Stage existingStage = getExistingAdvisorStage();

        if (existingStage != null) {
            existingStage.show();
            existingStage.toFront();
            return;
        }

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("advisor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 400);
        stage.setTitle("Advisor");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Stage getExistingAdvisorStage() {
        for (Window window : Window.getWindows()) {
            if (window instanceof Stage) {
                Stage stage = (Stage) window;
                if ("Advisor".equals(stage.getTitle())) {
                    return stage;
                }
            }
        }
        return null;
    }


    @FXML
    void fetch(ActionEvent event) throws Exception {
        if (T1.getText().isBlank()){
            PopUp.showPopup("Fields!", "All fields mandatory!", Alert.AlertType.ERROR);
            return;
        }
        Optional<Study> c = Optional.ofNullable(studyRepository.getStudyById(Integer.parseInt(T1.getText())));
        if (c.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!",ButtonType.CLOSE);
            alert.showAndWait();
            T1.setText("");
            T1.requestFocus();
        }
        else {
            T2.setText(c.get().getTitle());
            T3.setText(c.get().getDescription());
        }

    }
    @FXML
    void save(ActionEvent event) throws Exception
    {
        Study study = getStudy();
        if (study == null) return;
        if(studyRepository.getStudyById(study.getId()) != null){
            PopUp.showPopup("ID!", "ID already exists in the database.", Alert.AlertType.ERROR);
            return;
        }
        studyRepository.saveNewStudy(study);
        PopUp.showPopup("Success!","Study is created! :"+study.getId(), Alert.AlertType.INFORMATION);
    }
    @FXML
    void update(ActionEvent event) throws Exception
    {
        Study study = getStudy();
        if (study == null) return;
        if (studyRepository.getStudyById(study.getId())==null){
            PopUp.showPopup("Error!","Study not found! :"+study.getId(),AlertType.ERROR);
            return;
        }
        studyRepository.updateStudy(study);
        PopUp.showPopup("Success!","Study is updated successfully! :"+study.getId(), Alert.AlertType.INFORMATION);

    }
    @FXML
    public void initialize() {
        TextFormatter<BigDecimal> formatter = new TextFormatter<>(new BigDecimalStringConverter());
        T1.setTextFormatter(formatter);

        formatter.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                T1.setText("");
            }
        });

        T1.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                T1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    }