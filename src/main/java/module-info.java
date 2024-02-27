module org.example.dimsproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.dimsproject to javafx.fxml;
    exports org.example.dimsproject;
}