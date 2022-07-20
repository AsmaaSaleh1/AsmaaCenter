package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Employee implements Initializable {


    @FXML
    private TextField att;

    @FXML
    private ComboBox<String> attBox;

    @FXML
    private TableColumn<?, ?> birthdate;

    @FXML
    private TableColumn<?, ?> city;

    @FXML
    private TableColumn<?, ?> depNum;

    @FXML
    private ComboBox<Department> depbox;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableColumn<Emp, String> id;

    @FXML
    private TableColumn<?, ?> mNum;

    @FXML
    private TableColumn<Emp, String> fname;

    @FXML
    private TableColumn<Emp, String>  salary;



    @FXML
    private TableColumn<Emp, String> startdate;

    @FXML
    private TableColumn<Emp, String>  street;

    @FXML
    private TableView<Emp> t;

    @FXML
    private Text totNum;


ObservableList<Emp>emps= FXCollections.observableArrayList();
Connection con=null;

    @FXML
    void addEmployee() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/add.fxml"));
        Parent parent = fxmlLoader.load();
        Add dialogController = fxmlLoader.getController();
        dialogController.setAppMainObservableList(emps);
        t.refresh();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();


    }

    @FXML
    void deleteEmp() {
        int y=t.getSelectionModel().getSelectedItem().getId() ;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
            Statement statement = connection.createStatement();
            String q="delete from employee  where eid="+y ;
           statement.executeUpdate(q);
connection.commit();
            System.out.println("Done");

            connection.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        totNum.setText(String.valueOf(t.getItems().size()));

    }




    ObservableList<Department> o=FXCollections.observableArrayList();
    void save(ObservableList <Department>ob){
        depbox.setItems(ob);
        depbox.getItems().add(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mNum.setCellValueFactory(new PropertyValueFactory<>("mobNum"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        street.setCellValueFactory(new PropertyValueFactory<>("street"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        depNum.setCellValueFactory(new PropertyValueFactory<>("depNum"));

        att.textProperty().addListener((observable, oldDate, newDate) -> show(newDate));

        o=connection.getDepartment();
        depbox.setItems(o);
        emps=connection.getEmployee();
        tmp=connection.getEmployee();
        t.setItems(emps);
        for(Emp emp:emps){
            tmp.add(emp);
        }
        totNum.setText(String.valueOf(t.getItems().size()));
        tmp=FXCollections.observableArrayList();

        save(o);
        String[]attr={"All","ID","Name","Email","Mobile Number"};
        attBox.getItems().addAll(attr);

            }
    ObservableList<Emp>tmp;
    public void show(String st) {

            try {
                con=connection.connect();
                Statement statement = con.createStatement();
                String q = "select eid, fname, lname,email,mobilenum,salary,city,street, birthdate, startdate ,dname from employee, department where dnum=department.dnumber order by(eid)";
                ResultSet rs = statement.executeQuery(q);
                if(st.equals("")) {
                    emps.clear();
                    while (rs.next()) {
                        emps.add(new Emp(rs.getInt("eid"), rs.getString("fname")+" ", rs.getString("lname"), rs.getDate("birthdate").toLocalDate(), rs.getDate("startdate").toLocalDate(), rs.getString("email"), rs.getString("mobilenum"), rs.getString("city"), rs.getString("street"), rs.getInt("salary"), rs.getString("dname")));
                        t.refresh();
                    }
                }
                else {
                    emps.clear();
                    while (rs.next()) {

                        if (((rs.getString("fname").contains(st)||(rs.getString("lname").contains(st) )) &&attBox.getSelectionModel().getSelectedItem().equals("Name"))
||(rs.getString("email").contains(st) &&attBox.getSelectionModel().getSelectedItem().equals("Email"))
 ||(rs.getString("mobilenum").contains(st) &&attBox.getSelectionModel().getSelectedItem().equals("Mobile Number"))
 || (attBox.getSelectionModel().getSelectedItem().equals("ID")&& (rs.getInt("eid")==Integer.parseInt(st) ))
                        ) {
                            emps.add(new Emp(rs.getInt("eid"), rs.getString("fname")+" "+rs.getString("lname"), rs.getString("lname"), rs.getDate("birthdate").toLocalDate(), rs.getDate("startdate").toLocalDate(), rs.getString("email"), rs.getString("mobilenum"), rs.getString("city"), rs.getString("street"), rs.getInt("salary"), rs.getString("dname")));
                            t.refresh();
                        }

                    }
                }
con.close();
                totNum.setText(String.valueOf(t.getItems().size()));

            } catch (SQLException e) {
                System.out.println(e);
            }


    }


    @FXML
    void update() throws IOException {
        Emp employee=t.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/editEmp.fxml"));
        Parent parent = fxmlLoader.load();
        EditEmp dialogController = fxmlLoader.getController();
        dialogController.setData(employee);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void inDepartment() {
        if (!(depbox.getSelectionModel().getSelectedItem()==null)) {
    if(tmp.size()==0){
        for(Emp emp:emps){
            tmp.add(emp);
        }
    }
            emps.clear();
            System.out.println(tmp.size());
            for (Emp emp : tmp) {
                if (emp.getDepNum().equals(depbox.getSelectionModel().getSelectedItem().getName())) {
                    emps.add(emp);
                    t.refresh();
                }
            }

        }
        else{
            emps.clear();
            for (Emp emp : tmp) {
                    emps.add(emp);
                    t.refresh();
                }
        }
        totNum.setText(String.valueOf(t.getItems().size()));
    }


}