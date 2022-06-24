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


public class MainInterface {
    @FXML
    private Button addEmp;
    @FXML
    private Button b1;
    @FXML
    private AnchorPane contact2;
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
    private AnchorPane contact;

    @FXML
    private TableView<?> tble;
    @FXML
    private ImageView i7;

    @FXML
    private ImageView i8;


    @FXML
    private AnchorPane sliper;
    @FXML
    private Button btcontact;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane pn;
    @FXML
    private Button btAccount;

    @FXML
    private Button btOut1;

    @FXML
    private Button btSer;
    @FXML
    private Button btAppo;

    @FXML
    private void handleClicks(ActionEvent event)throws IOException  {
        if(event.getSource()==btAccount){
pn.setVisible(false);
grid.setVisible(true);
pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
serlb.setText("Employee");
        }
        if(event.getSource()==btAppo){
            pn.setVisible(false);
            grid.setVisible(true);
            pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
            serlb.setText("Appointment");
        }
        if(event.getSource()==btSer){
            pn.setVisible(false);
            grid.setVisible(true);
            pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
            serlb.setText("Services");
        }
        if(event.getSource()==btOut1){
            App.setRoot("hello-view");
        }
        if(event.getSource()==btDEp){
            pn.setVisible(true);
            grid.setVisible(false);
            pane.setVisible(false);
            contact.setVisible(false);
            contact2.setVisible(false);
        }
        if(event.getSource()==btcontact){
            pn.setVisible(false);
            grid.setVisible(false);
            pane.setVisible(false);
            contact.setVisible(true);
            contact2.setVisible(true);
        }


        }
    @FXML
    void showServices(MouseEvent event) throws IOException{

    }
    @FXML
    void addEmployee(ActionEvent event) throws IOException{
App.setRoot("add");
    }
    @FXML
    void enter(MouseEvent event) {
        addEmp.setScaleX(1.2);
        addEmp.setScaleY(1.2);
    }

    @FXML
    void exit(MouseEvent event) {
        addEmp.setScaleX(1);
        addEmp.setScaleY(1);
    }
}

