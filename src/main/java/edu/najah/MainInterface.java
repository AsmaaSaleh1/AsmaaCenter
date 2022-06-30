package edu.najah;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
    private Button sc;
    @FXML
    private ImageView i8;

    @FXML
    private AnchorPane myAccount;

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
    private Button btAddApp;
    @FXML
    private Button btAppo;
    @FXML
    private AnchorPane AnAddApp;
    @FXML
    private Button invoiceB;
    @FXML
    private DatePicker AppoDate;
    @FXML
    private ComboBox<String> depCombo;
    @FXML
    private ComboBox<String> serviceCombo;
    @FXML
    public TextField timeNeeded;
    @FXML
    private void handleClicks(ActionEvent event)throws IOException  {
        if(event.getSource()==btAccount){
            pn.setVisible(false);
            grid.setVisible(true);
            pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
            serlb.setText("Employee");
            myAccount.setVisible(false);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
        }
        if(event.getSource()==btAppo){
            pn.setVisible(false);
            grid.setVisible(true);
            pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
            serlb.setText("My Appointment");
            myAccount.setVisible(false);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
        }
        if(event.getSource()==btAddApp){
            pn.setVisible(false);
            grid.setVisible(false);
            pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
            myAccount.setVisible(false);
            serlb.setText("Add An Appointment");
            AnAddApp.setVisible(true);
            invoiceB.setVisible(true);
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
            myAccount.setVisible(false);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
        }
        if(event.getSource()==btcontact){
            pn.setVisible(false);
            grid.setVisible(false);
            pane.setVisible(false);
            contact.setVisible(true);
            contact2.setVisible(true);
            myAccount.setVisible(false);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
        }
        if(event.getSource()==btAccount){
            pn.setVisible(false);
            grid.setVisible(false);
            pane.setVisible(false);
            contact.setVisible(false);
            contact2.setVisible(false);
            myAccount.setVisible(true);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
        }

        }
    @FXML
    void showServices() {

    }
    @FXML
    void addEmployee() throws IOException{
        App.setRoot("add");
    }
    @FXML
    private Button sign2;
    @FXML
    void enter() {
        addEmp.setScaleX(1.2);
        addEmp.setScaleY(1.2);
        sign2.setScaleX(1.2);
        sign2.setScaleY(1.2);
        sc.setScaleX(1.2);
        sc.setScaleY(1.2);
    }

    @FXML
    void exit() {
        addEmp.setScaleX(1);
        addEmp.setScaleY(1);
        sign2.setScaleX(1);
        sign2.setScaleY(1);
        sc.setScaleX(1);
        sc.setScaleY(1);
    }
    @FXML
    void saveChanges() {

    }

}

