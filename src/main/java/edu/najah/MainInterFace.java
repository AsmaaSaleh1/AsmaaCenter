package edu.najah;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class MainInterFace {

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
    private AnchorPane depName;
    @FXML
    private ImageView i6;
    @FXML
    private Pane pane;


    @FXML
    private Label serlb;

    @FXML
    private Button btDEp;

    @FXML
    private TableView<?> tble;
    @FXML
    private ImageView i7;

    @FXML
    private ImageView i8;


    @FXML
    private AnchorPane sliper;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane pn;
    @FXML
    private Button btEmp;

    @FXML
    private Button btOut;

    @FXML
    private Button btSer;
    @FXML
    private Button btAppo;

    @FXML
    private void handleClicks(ActionEvent event)throws IOException  {
        if(event.getSource()==btEmp){
pn.setVisible(false);
grid.setVisible(true);
pane.setVisible(true);
serlb.setText("Employee");
        }
        if(event.getSource()==btAppo){
            pn.setVisible(false);
            grid.setVisible(true);
            pane.setVisible(true);
            serlb.setText("Appointment");
        }
        if(event.getSource()==btSer){
            pn.setVisible(false);
            grid.setVisible(true);
            pane.setVisible(true);
            serlb.setText("Services");
        }
        if(event.getSource()==btOut){
            App.setRoot("hello-view");
        }
        if(event.getSource()==btDEp){
            pn.setVisible(true);
            grid.setVisible(false);
            pane.setVisible(false);
        }

        }
    @FXML
    void showServices(MouseEvent event) throws IOException{

    }

}
