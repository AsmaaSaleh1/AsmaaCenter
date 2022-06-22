package edu.najah;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class S1 implements Initializable {
@FXML
private AnchorPane sl;
    @FXML
    private Button b1;

    @FXML
    private Button b11;

    @FXML
    private Button b111;

    @FXML
    private Button b112;

    @FXML
    private Button b113;

    @FXML
    private ImageView i1;

    @FXML
    private ImageView i2;

    @FXML
    private ImageView i3;

    @FXML
    private ImageView i4;

    @FXML
    private ImageView i5;

    @FXML
    private ImageView i6;

    @FXML
    private ImageView i7;

    @FXML
    private ImageView i8;


    void sr(MouseEvent event) {
        System.out.println("Hi");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
