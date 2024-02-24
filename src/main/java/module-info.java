module org.example.dimsproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.dimsproject to javafx.fxml;
    exports org.example.dimsproject;
}