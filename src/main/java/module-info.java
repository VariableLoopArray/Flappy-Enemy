module TP {
    requires javafx.controls;
    requires javafx.fxml;


    opens model to javafx.fxml;
    opens view to javafx.fxml;
    exports model;
    exports view;
    exports controller;
    exports Main;
}