package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Add implements Initializable {

    @FXML
    private DatePicker bdate;

    @FXML
    private TextField city;

    @FXML
    private ComboBox<Department> dep;

    @FXML
    private TextField email;

    @FXML
    private TextField fname;


    @FXML
    private TextField lname;

    @FXML
    private TextField mob;

    @FXML
    private Pane s1;

    @FXML
    private Pane s11;

    @FXML
    private Pane s111;

    @FXML
    private Pane s1111;

    @FXML
    private TextField salary;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField street;

    public Add() {
    }

    @FXML
    void b1() {
if(!(fname.getText().isBlank()||lname.getText().isBlank())) {
    s1.setVisible(false);
    s11.setVisible(true);
    s111.setVisible(false);
    s1111.setVisible(false);
}
         else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Not Completed");
            a.setContentText("Please fill all the field");
            a.showAndWait();
        }
    }

    @FXML
    void b2() {
        if (!(email.getText().isBlank() || mob.getText().isBlank() || startDate.getValue()==null)){
            s1.setVisible(false);
        s11.setVisible(false);
        s111.setVisible(false);
        s1111.setVisible(true);
    }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Not Completed");
            a.setContentText("Please fill all the field");
            a.showAndWait();
        }
    }



    @FXML
    void enter() {

    }

    @FXML
    void exit() {

    }

    @FXML
    void signup() {
        if (!(city.getText().isBlank() || street.getText().isBlank() || bdate.getValue() == null)) {
            s1.setVisible(false);
            s11.setVisible(false);
            s111.setVisible(true);
            s1111.setVisible(false);
        }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Not Completed");
            a.setContentText("Please fill all the field");
            a.showAndWait();
        }

    }
    @FXML
    void b3(ActionEvent event) {
if(!(salary.getText().isBlank()||dep.getSelectionModel().getSelectedItem()==null)) {

    try {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
        PreparedStatement prs = connection.prepareStatement("insert into employee values (incem.nextval,?,?,?,?,?,?,?,?,?,?)");

        prs.setString(1, fname.getText());
        prs.setString(2, lname.getText());
        prs.setDate(3, Date.valueOf(bdate.getValue()));
        prs.setDate(4, Date.valueOf(startDate.getValue()));
        prs.setString(5, email.getText() + "@gmail.com");
        prs.setString(6, mob.getText());
        prs.setInt(7, Integer.parseInt("05" + salary.getText()));
        prs.setString(8, city.getText());
        prs.setString(9, street.getText());
        prs.setInt(10, dep.getSelectionModel().getSelectedItem().getNum());
        prs.executeUpdate();
        connection.commit();
        connection.close();
        System.out.println("Done");
    } catch (SQLException e) {
        e.printStackTrace();
    }

    closeStage(event);
}
else {
    Alert a = new Alert(Alert.AlertType.WARNING);
    a.setTitle("Not Completed");
    a.setContentText("Please fill all the field");
    a.showAndWait();
}
    }


    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       dep.setItems(connection.getDepartment());

    }
}
