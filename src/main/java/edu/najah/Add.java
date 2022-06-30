package edu.najah;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Add {

    @FXML
    private AnchorPane a1;

    @FXML
    private Pane s1;

    @FXML
    private Pane s11;

    @FXML
    private Pane s111;

    @FXML
    private Pane s1111;

    @FXML
    private Button sign2;

    @FXML
    private Button sign21;
    @FXML
    private DialogPane dp;
    @FXML
    private Button sign211;

    @FXML
    private Button sign2111;

    @FXML
    void b1() {
        s1.setVisible(false);
        s11.setVisible(true);
        s111.setVisible(false);
        s1111.setVisible(false);
    }

    @FXML
    void b2() {
        s1.setVisible(false);
        s11.setVisible(false);
        s111.setVisible(false);
        s1111.setVisible(true);
    }

    @FXML
    void b3() {

    }

    @FXML
    void enter() {

    }

    @FXML
    void exit() {

    }

    @FXML
    void signup() {
        s1.setVisible(false);
        s11.setVisible(false);
        s111.setVisible(true);
        s1111.setVisible(false);
    }

}
