module edu.najah {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.mail.api;

    opens edu.najah to javafx.fxml;
    exports edu.najah;
}
