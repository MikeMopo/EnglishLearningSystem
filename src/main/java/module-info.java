module zw.co.els.englishlearningsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.json;


    opens zw.co.els.englishlearningsystem.controller to javafx.fxml;
    exports zw.co.els.englishlearningsystem.controller;

    opens zw.co.els.englishlearningsystem to javafx.fxml;
    exports zw.co.els.englishlearningsystem;

}