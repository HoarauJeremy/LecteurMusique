module com.example.lecteurmusique {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.logging;
    requires java.sql;
    requires java.desktop;
    requires password4j;
    requires com.fasterxml.jackson.dataformat.xml;
    requires mysql.connector.j;

    opens com.example.lecteurmusique to javafx.fxml;
    exports com.example.lecteurmusique;

    opens com.example.lecteurmusique.Controllers to javafx.fxml;
    exports com.example.lecteurmusique.Controllers;
}