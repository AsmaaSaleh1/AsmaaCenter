package edu.najah;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PrimaryController implements Initializable {
    public Button primaryButton;

    @FXML
    private void switchToSecondary() throws IOException {
        primaryButton.setDisable(false);
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
