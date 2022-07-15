package edu.najah;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;

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
    private TextField str;

    @FXML
    void enter(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {

    }
    private ObservableList<Emp> emps;
    public void setData(Emp emp) {
        this.emplo=emp;
id.setText(String.valueOf(emp.getId()));
fn.setText(emp.getX());
ln.setText(emp.getY());
bd.setValue(emp.getBirthdate());
age.setText(String.valueOf(LocalDate.now().getYear()-emp.getBirthdate().getYear()));
cit.setText(emp.getCity());
str.setText(emp.getStreet());
salary.setText(String.valueOf(emp.getSalary()));
depnum.setText(String.valueOf(emp.getDepNum()));

    }
Emp emplo;
    Emp emp1;

@FXML
    void searchemp(ActionEvent event) {
    Emp emp=new Emp(Integer.parseInt(id.getText()),fn.getText().trim(),ln.getText().trim(),depnum.getText(),age.getText()
            ,cit.getText(),str.getText(),Integer.parseInt(salary.getText()),Integer.parseInt(depnum.getText()), bd.getValue(),emplo.getStartDate()
    );
for(Emp emp2:emps){
    if(emp2.getId()==emp.getId()){
        emps.remove(emp2);
        emps.add(emp);
        break;
    }
}


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

}
