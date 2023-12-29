module com.example.projectdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projectdb to javafx.fxml;
    exports com.example.projectdb;
}