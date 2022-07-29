package edu.najah;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class EditEmp {

    @FXML
    private TextField age;

    @FXML
    private TextField cit;

    @FXML
    private TextField em;

    @FXML
    private TextField salary;
    @FXML
    private TextField fn;

    @FXML
    private TextField id;

    @FXML
    private TextField ln;

    @FXML
    private TextField mb;

    @FXML
    private TextField str;
    @FXML
    private ComboBox<Department> depna;

    public EditEmp() {
    }

    @FXML
    void enter() {

    }

    @FXML
    void exit() {

    }

    Department dp=null;
    public void setData(Emp emp) {

        ObservableList<Department>dep=connection.getDepartment();
        depna.setItems(dep);
        for(Department d:dep){
            if(d.getName().equals(emp.getDepNum())){
                dp=d;
                break;
            }
        }
        this.emplo=emp;
id.setText(String.valueOf(emp.getId()));
fn.setText(emp.getX());
ln.setText(emp.getY());
age.setText(String.valueOf(LocalDate.now().getYear()-emp.getBirthdate().getYear()));
cit.setText(emp.getCity());
str.setText(emp.getStreet());
salary.setText(String.valueOf(emp.getSalary()));
depna.getSelectionModel().select(dp);
mb.setText(emp.getMobNum());
em.setText(emp.getEmail());
    }
Emp emplo;

    @FXML
    void searchemp(ActionEvent event) {

    int x=Integer.parseInt(id.getText());
    int y=Integer.parseInt(salary.getText());
    try {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
        Statement statement = connection.createStatement();
     String q="update employee set dnum='"+depna.getSelectionModel().getSelectedItem().getNum()+"',fname='"+fn.getText()+"',lname='"+ln.getText()+"',city='"+cit.getText()+"',street ='"+str.getText()+"',salary='"+y+"',email ='"+em.getText()+"' ,mobilenum ='"+mb.getText().trim()+"' where eid='"+x+"'" ;
       statement.executeUpdate(q);

        connection.commit();
        connection.close();

        System.out.println("Done");
    }
    catch (SQLException e) {
e.printStackTrace();
    }
    closeStage(event);
}

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
