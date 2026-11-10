module org.example.inventarioappg1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires mysql.connector.j;
    requires java.naming;

    opens cesur.dam.inventarioappg1 to javafx.fxml;
    exports cesur.dam.inventarioappg1;
    exports cesur.dam.inventarioappg1.models;
    opens cesur.dam.inventarioappg1.models to javafx.fxml;
    exports cesur.dam.inventarioappg1.controllers;
    opens cesur.dam.inventarioappg1.controllers to javafx.fxml;
}