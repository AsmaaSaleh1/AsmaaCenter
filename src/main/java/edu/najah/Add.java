package edu.najah;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private TextField id;

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
    private ComboBox<?> serv;

    @FXML
    private Button sign2;

    @FXML
    private Button sign21;

    @FXML
    private Button sign211;

    @FXML
    private Button sign2111;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField street;
private ObservableList<Emp> emps;
    public Add() {
    }

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
    @FXML
    void b3(ActionEvent event) throws IOException{
Emp emp=new Emp(Integer.parseInt(id.getText()),fname.getText().trim(),lname.getText().trim(),email.getText(),mob.getText()
,city.getText(),street.getText(),Integer.parseInt(salary.getText()),dep.getSelectionModel().getSelectedItem().getNum(), bdate.getValue(),startDate.getValue()
);

     emps.add(emp);

        closeStage(event);

    }

    public void setAppMainObservableList(ObservableList<Emp> tvObservableList) {
     emps = tvObservableList;

    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dep.getItems().add(new Department(1,"Hair"));
        dep.getItems().add(new Department(2,"Face"));

    }
}
