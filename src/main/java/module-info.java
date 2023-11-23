module com.alex.blockbuster {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.alex.blockbuster to javafx.fxml;
    exports com.alex.blockbuster;
    exports com.alex.blockbuster.controller;
    opens com.alex.blockbuster.controller to javafx.fxml;
}