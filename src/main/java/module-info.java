module edu.najah {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.mail.api;
   // requires jasperreports;
requires ojdbc6;
    requires org.controlsfx.controls;
    requires java.sql;
    opens edu.najah to javafx.fxml;
    exports edu.najah;
}
