package edu.najah;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class EditEmp {

    @FXML
    private TextField age;

    @FXML
    private DatePicker bd;

    @FXML
    private TextField cit;

    @FXML
    private TextField depna;

    @FXML
    private TextField depnum;

    @FXML
    private TextField salary;
    @FXML
    private TextField fn;

    @FXML
    private TextField id;

    @FXML
    private TextField ln;

    @FXML
    private Button search;

    @FXML
    private TextField str;

    @FXML
    void enter(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {

    }

    public void setData(Emp emp) {
        this.emp=emp;
id.setText(String.valueOf(emp.getId()));
fn.setText(emp.getX());
ln.setText(emp.getY());
bd.setValue(emp.getBirthdate());
age.setText(String.valueOf(Period.between(LocalDate.now(),emp.getBirthdate())));
cit.setText(emp.getCity());
str.setText(emp.getStreet());
salary.setText(String.valueOf(emp.getSalary()));
depnum.setText(String.valueOf(emp.getDepNum()));

    }
Emp emp;
    @FXML
    void searchemp(MouseEvent event)throws IOException {
        Emp emp1=new Emp(Integer.parseInt(id.getText()),fn.getText(),ln.getText(),emp.getEmail(),emp.getMobNum(),cit.getText(),str.getText(),Integer.parseInt(salary.getText()),Integer.parseInt(depnum.getText()),emp.getBirthdate(),emp.getStartDate());
        FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/employee.fxml"));
        Parent parent = loader.load();
        Employee e=loader.getController();
        e.setE(emp1);
        closeStage(event);
    }
    private void closeStage(MouseEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
