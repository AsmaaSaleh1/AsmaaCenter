module edu.najah {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.najah to javafx.fxml;
    exports edu.najah;
}
