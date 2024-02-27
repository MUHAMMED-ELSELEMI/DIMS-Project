package org.example.dimsproject.controller;

import org.example.dimsproject.HelloApplication;
import org.example.dimsproject.Model.Adviser;
import org.example.dimsproject.repository.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdvisorController {
    @FXML
    private Label l4;

    @FXML
    private TextField txtfield1;

    @FXML
    private TextField txtfield2;

    @FXML
    private TextField txtfield3;

    @FXML
    private Button adv_button_fetch;

    @FXML
    private Button adv_button_close;

    @FXML
    private Button adv_button_delete;
    @FXML
    private Button adv_button_update;

    @FXML
    private Button adv_button_save;

    @FXML
    void clean(ActionEvent event) {
        txtfield1.setText("");
        txtfield2.setText("");
        txtfield3.setText("");
    }

    @FXML
    void getAdvisorView(ActionEvent event) throws IOException  {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("advisor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("Advisor");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void close(ActionEvent event) {
        //Platform.exit();
        Stage stage = (Stage) adv_button_close.getScene().getWindow();
        stage.close();
    }

    @FXML
    void fetch(ActionEvent event) throws Exception {
        AdviserRepository advisorRepository = new AdviserRepository();
        Adviser c = advisorRepository.getAdvisorById(Integer.parseInt(txtfield1.getText()));
        // System.out.println(c.toString());
        if(c.getId() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!", ButtonType.CLOSE);
            alert.showAndWait();
            txtfield1.setText("");
            txtfield1.requestFocus();
        }
        txtfield2.setText(c.getName());
        txtfield3.setText(c.getDepartment());
    }

    @FXML
    void save(ActionEvent event) throws Exception {
        AdviserRepository adviserRepository = new AdviserRepository();
        Adviser c = new Adviser();
        c.setId(Integer.parseInt(txtfield1.getText()));
        c.setName(txtfield2.getText());
        c.setDepartment(txtfield3.getText());
        adviserRepository.save(c);
        l4.setText("Recorded successfully!");
    }

    public void update(){
        AdviserRepository adviserRepository = new AdviserRepository();
        Adviser c = adviserRepository.getAdvisorById(Integer.parseInt(txtfield1.getText()));

        if(c.getId() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No such record!", ButtonType.CLOSE);
            alert.showAndWait();
            txtfield1.setText("");
            txtfield1.requestFocus();
        }
        adviserRepository.update(c);
        showSuccessPopup();
    }
    private void showSuccessPopup() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Your submission was successful!");
        alert.showAndWait();
    }
}
