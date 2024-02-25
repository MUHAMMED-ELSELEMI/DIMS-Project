package org.example.dimsproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PageController {
    @FXML
    public TextField advisorId;
    @FXML
    public TextField advisorName;
    @FXML
    public TextField advisorDepartment;
    @FXML
    public TextField studyId;
    @FXML
    public TextField studyTitle;
    @FXML
    public TextField studyDesc;

    @FXML
    protected void AdvisorSave(){
        System.out.println("Advisor saved!");
    }
    @FXML
    protected void AdvisorFetch(){
        System.out.println("Advisor fetched!");
    }
    @FXML
    protected void AdvisorUpdate(){
        System.out.println("Advisor updated!");
    }
    @FXML
    protected void AdvisorDeleteFields(){
        System.out.println("Advisor fields resetted!");
        advisorId.clear();
        advisorName.clear();
        advisorDepartment.clear();
    }
    @FXML
    protected void AdvisorClose(){
        System.out.println("Advisor closed!");
    }

    @FXML
    protected void StudySave(){
        System.out.println("Study saved!");
    }
    @FXML
    protected void StudyFetch(){
        System.out.println("Study fetched!");
    }
    @FXML
    protected void StudyUpdate(){
        System.out.println("Study updated!");
    }
    @FXML
    protected void StudyDeleteFields(){
        System.out.println("Study fields resetted!");
        studyId.clear();
        studyDesc.clear();
        studyTitle.clear();
    }
    @FXML
    protected void StudyClose(){
        System.out.println("Study closed!");
    }
}
