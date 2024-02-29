package org.example.dimsproject.controller;

import javafx.stage.Window;
import org.example.dimsproject.HelloApplication;
import org.example.dimsproject.model.Adviser;
import org.example.dimsproject.repository.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.example.dimsproject.utils.PopUp;

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
        //Platform.exit();
        Stage stage = (Stage) adv_button_save.getScene().getWindow();
        stage.close();
    }

    @FXML
    void fetch(ActionEvent event) throws Exception {
        if (txtfield1.getText().isBlank()){
            PopUp.showPopup("Fields!","All fields mandatory!",AlertType.ERROR);
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
    void save(ActionEvent event) throws Exception {
        Adviser adviser = getAdviser();
        if (adviser == null) return;
        adviserRepository.save(adviser);
        PopUp.showPopup("Success!","Advisor is created! :"+adviser.getId(),AlertType.INFORMATION);
    }

    @FXML
    public void update(ActionEvent event){
        Adviser adviser = getAdviser();
        if (adviser == null) return;
        adviserRepository.update(adviser);
        PopUp.showPopup("Success!","Advisor is updated successfully! :"+adviser.getId(),AlertType.INFORMATION);
    }

    @FXML
    public void delete(ActionEvent event){
        if(txtfield1.getText().isBlank()){
            PopUp.showPopup("Warning!","Id is mandatory!",AlertType.ERROR);
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

}
