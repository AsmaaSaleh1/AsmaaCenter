package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
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
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
emps=connection.getEmployee();
t.setItems(emps);
t.refresh();

    }

    @FXML
    void deleteEmp() {
        int y=t.getSelectionModel().getSelectedItem().getId() ;
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.setContentText("Are you sure you want to delete this customer?");
        Optional<ButtonType> op = a.showAndWait();
        if (op.get() == ButtonType.OK) {
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
                Statement statement = connection.createStatement();
                String q = "delete from employee  where eid=" + y;
                statement.executeUpdate(q);
                connection.commit();
                System.out.println("Done");

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            emps = connection.getEmployee();
            t.setItems(emps);
            t.refresh();
            totNum.setText(String.valueOf(t.getItems().size()));
            emps = connection.getEmployee();
            t.setItems(emps);
            t.refresh();
        }
    }




    ObservableList<Department> o=FXCollections.observableArrayList();

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
        depbox.getItems().add(new Department(0,"All"));
        emps=connection.getEmployee();
        tmp=connection.getEmployee();
        t.setItems(emps);
        tmp.addAll(emps);
        totNum.setText(String.valueOf(t.getItems().size()));
        tmp=FXCollections.observableArrayList();


        String[]attr={"All","ID","Name","Email","Mobile Number"};
        attBox.getItems().addAll(attr);

            }
    ObservableList<Emp>tmp;
    public void show(String st) {

            try {
                con=connection.connect();
                assert con != null;
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

                        if (((rs.getString("fname").toLowerCase().contains(st)||(rs.getString("lname").toLowerCase().contains(st) )) &&attBox.getSelectionModel().getSelectedItem().equals("Name"))
||(rs.getString("email").toLowerCase().contains(st) &&attBox.getSelectionModel().getSelectedItem().equals("Email"))
 ||(rs.getString("mobilenum").contains(st) &&attBox.getSelectionModel().getSelectedItem().equals("Mobile Number"))
 || (attBox.getSelectionModel().getSelectedItem().equals("ID")&& (rs.getInt("eid")==Integer.parseInt(st) ))
                        ) {
                            emps.add(new Emp(rs.getInt("eid"), rs.getString("fname")+" "+rs.getString("lname"), rs.getString("lname"), rs.getDate("birthdate").toLocalDate(), rs.getDate("startdate").toLocalDate(), rs.getString("email"), rs.getString("mobilenum"), rs.getString("city"), rs.getString("street"), rs.getInt("salary"), rs.getString("dname")));
                            t.refresh();
                        }
                        if(attBox.getSelectionModel().getSelectedItem().equals("All")&&
                                ( (rs.getString("fname").toLowerCase().contains(st)||(rs.getString("lname").toLowerCase().contains(st) ))||
                                        (rs.getString("email").toLowerCase().contains(st))||
                                        rs.getString("city").toLowerCase().contains(st)||rs.getString("street").toLowerCase().contains(st)
                        )){
                            emps.add(new Emp(rs.getInt("eid"), rs.getString("fname")+" "+rs.getString("lname"), rs.getString("lname"), rs.getDate("birthdate").toLocalDate(), rs.getDate("startdate").toLocalDate(), rs.getString("email"), rs.getString("mobilenum"), rs.getString("city"), rs.getString("street"), rs.getInt("salary"), rs.getString("dname")));
                            t.refresh();
                        }

                    }
                }
con.close();
                totNum.setText(String.valueOf(t.getItems().size()));


            } catch (SQLException e) {
                e.printStackTrace();
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
        emps=connection.getEmployee();
        t.setItems(emps);
        t.refresh();
    }

    @FXML
    void inDepartment() {
        if (!(depbox.getSelectionModel().getSelectedItem()==null)) {
            emps=connection.getEmployee();
            tmp=connection.getEmployee();
            t.setItems(emps);
            t.refresh();
            emps.clear();
            System.out.println(tmp.size());
            for (Emp emp : tmp) {
                if (emp.getDepNum().equals(depbox.getSelectionModel().getSelectedItem().getName())) {
                    emps.add(emp);
                    t.refresh();
                }
            }
            if(depbox.getSelectionModel().getSelectedItem().getName().equals("All")){
                emps=connection.getEmployee();
                t.setItems(emps);
                t.refresh();
            }

        }

        totNum.setText(String.valueOf(t.getItems().size()));
        tmp.clear();
    }


}