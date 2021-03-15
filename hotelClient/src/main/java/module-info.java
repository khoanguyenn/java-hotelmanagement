module fraus.javaproject {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires com.google.gson;

    requires java.naming;
    requires java.sql;
    requires java.net.http;
    requires java.base;

    opens fraus.javaproject.controller to javafx.fxml;
    opens fraus.javaproject.model to javafx.base, com.google.gson;

    opens fraus.javaproject to javafx.graphics;

    exports fraus.javaproject;
}