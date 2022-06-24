package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ResetPass {

    @FXML
    private TextField email;

    @FXML
    private Pane emailpn;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField pass1;

    @FXML
    private Pane respn;

    @FXML
    private Button sign2;

    @FXML
    private Button sign21;

    @FXML
    private Button sign211;

    @FXML
    private Pane verpn;

    @FXML
    void enter(MouseEvent event) {
        sign2.setScaleX(1.2);
        sign2.setScaleY(1.2);
        sign21.setScaleX(1.2);
        sign21.setScaleY(1.2);
        sign211.setScaleX(1.2);
        sign211.setScaleY(1.2);

    }

    @FXML
    void exit(MouseEvent event) {
        sign2.setScaleX(1);
        sign2.setScaleY(1);
        sign21.setScaleX(1);
        sign21.setScaleY(1);
        sign211.setScaleX(1);
        sign211.setScaleY(1);
    }
    @FXML
    void back(ActionEvent event)throws IOException {
App.setRoot("hello-view");

    }
    @FXML
    void toVar(ActionEvent event) {
emailpn.setVisible(false);
verpn.setVisible(true);
respn.setVisible(false);
    }
    @FXML
    void tores(ActionEvent event) {
        emailpn.setVisible(false);
        verpn.setVisible(false);
        respn.setVisible(true);
    }

}
