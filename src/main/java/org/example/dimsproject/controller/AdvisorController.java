package org.example.dimsproject.controller;

import javafx.application.Platform;
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
import org.example.dimsproject.model.Adviser;
import org.example.dimsproject.repository.AdviserRepository;
import org.example.dimsproject.utils.PopUp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class AdvisorController {

    @FXML
    private TextField txtfield1;

    @FXML
    private TextField txtfield2;

    @FXML
    private TextField txtfield3;

    @FXML
    private Button adv_button_save;
    private final AdviserRepository adviserRepository;

    public AdvisorController() {
        this.adviserRepository = new AdviserRepository();
    }

    @FXML
    void clean(ActionEvent event) {
        txtfield1.setText("");
        txtfield2.setText("");
        txtfield3.setText("");
    }

    @FXML
    void getStudyView(ActionEvent event) throws IOException  {
        Stage existingStage = getExistingStudyStage();

        if (existingStage != null) {
            existingStage.show();
            existingStage.toFront();
            return;
        }
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("study.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550,400);
        stage.setTitle("Study");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private Stage getExistingStudyStage() {
        for (Window window : Window.getWindows()) {
            if (window instanceof Stage) {
                Stage stage = (Stage) window;
                if ("Study".equals(stage.getTitle())) {
                    return stage;
                }
            }
        }
        return null;
    }

    @FXML
    void close(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("CLOSING PROGRAM!!!");
        alert.setHeaderText("are you sure close the program");
        alert.setContentText("press OK to close, or CANCEL to continue program");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
        }
    }

    @FXML
    void fetch(ActionEvent event) throws Exception {
        if (txtfield1.getText().isBlank()){
            PopUp.showPopup("Fields!","All fields mandatory!",AlertType.ERROR);
            return;
        }
        if (!txtfield1.getText().matches("^[0-9]*$")){
            PopUp.showPopup("Id!","Id must be integer number!",AlertType.ERROR);
            return;
        }
        Optional<Adviser> c = adviserRepository.getAdvisorById(Integer.parseInt(txtfield1.getText()));
        if(c.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!", ButtonType.CLOSE);
            alert.showAndWait();
            txtfield1.setText("");
            txtfield1.requestFocus();
        }else {
            txtfield2.setText(c.get().getName());
            txtfield3.setText(c.get().getDepartment());
        }
    }

    @FXML
    void save(ActionEvent event) {
        Adviser adviser = getAdviser();
        if (adviser == null) return;

        if(adviserRepository.getAdvisorById(adviser.getId()).isPresent()){
            PopUp.showPopup("ID!", "ID already exists in the database.", Alert.AlertType.ERROR);
            return;
        }

        adviserRepository.save(adviser);
        PopUp.showPopup("Success!","Advisor is created! :"+adviser.getId(),AlertType.INFORMATION);
    }

    @FXML
    public void update(ActionEvent event){
        Adviser adviser = getAdviser();
        if (adviser == null) return;
        if (adviserRepository.getAdvisorById(adviser.getId()).isEmpty()){
            PopUp.showPopup("Error!","Advisor not found! :"+adviser.getId(),AlertType.ERROR);
            return;
        }
        adviserRepository.update(adviser);
        PopUp.showPopup("Success!","Advisor is updated successfully! :"+adviser.getId(),AlertType.INFORMATION);
    }

    @FXML
    public void delete(ActionEvent event){
        if(txtfield1.getText().isBlank()){
            PopUp.showPopup("Warning!","Id is mandatory!",AlertType.ERROR);
            return;
        }
        if (adviserRepository.getAdvisorById(Integer.parseInt(txtfield1.getText())).isEmpty()){
            PopUp.showPopup("Error!","Advisor not found! :"+txtfield1.getText(),AlertType.ERROR);
            return;
        }
         adviserRepository.deleteById(Integer.parseInt(txtfield1.getText()));
         PopUp.showPopup("Success!","Adviser is deleted successfully!: "+txtfield1.getText(),AlertType.WARNING);
    }

    private Adviser getAdviser() {
        Adviser adviser;
        if(txtfield1.getText().isBlank() || txtfield2.getText().isBlank() || txtfield3.getText().isBlank()){
            PopUp.showPopup("Fields!","All fields mandatory!",AlertType.WARNING);
            return null;
        }
        adviser = new Adviser();
        adviser.setId(Integer.parseInt(txtfield1.getText()));
        adviser.setName(txtfield2.getText());
        adviser.setDepartment(txtfield3.getText());
        return adviser;
    }

    @FXML
    public void initialize() {
        TextFormatter<BigDecimal> formatter = new TextFormatter<>(new BigDecimalStringConverter());
        txtfield1.setTextFormatter(formatter);

        formatter.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                txtfield1.setText("");
            }
        });

        txtfield1.textProperty().addListener((obs, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                txtfield1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }


}
