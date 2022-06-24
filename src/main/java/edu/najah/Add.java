package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
    private Button sign211;

    @FXML
    private Button sign2111;

    @FXML
    void b1(ActionEvent event) {

s1.setVisible(false);
s11.setVisible(true);
s111.setVisible(false);
s1111.setVisible(false);
    }

    @FXML
    void b2(ActionEvent event) {
        s1.setVisible(false);
        s11.setVisible(false);
        s111.setVisible(false);
        s1111.setVisible(true);
    }

    @FXML
    void b3(ActionEvent event) {

    }

    @FXML
    void enter(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {

    }

    @FXML
    void signup(ActionEvent event) {
        s1.setVisible(false);
        s11.setVisible(false);
        s111.setVisible(true);
        s1111.setVisible(false);
    }

}
