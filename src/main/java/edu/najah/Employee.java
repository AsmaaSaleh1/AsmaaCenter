package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Employee implements Initializable {

    @FXML
    private Button addEmp;

    @FXML
    private TextField att;

    @FXML
    private ComboBox<String> attBox;

    @FXML
    private TableColumn<?, ?> birthdate;

    @FXML
    private TableColumn<?, ?> city;

    @FXML
    private Button delEmp;

    @FXML
    private TableColumn<?, ?> depNum;

    @FXML
    private ComboBox<Department> depbox;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private GridPane g1;

    @FXML
    private TableColumn<Emp, String> id;

    @FXML
    private TableColumn<?, ?> mNum;

    @FXML
    private TableColumn<Emp, String> fname;
    @FXML
    private TableColumn<Emp, String>  lname;

    @FXML
    private TableColumn<Emp, String>  salary;

    @FXML
    private Button search;

    @FXML
    private TableColumn<Emp, String> startdate;

    @FXML
    private TableColumn<Emp, String>  street;

    @FXML
    private TableView<Emp> t;

    @FXML
    private Text totNum;
    @FXML
    private TextField de;
    @FXML
    private Button up;
    LocalDate date=LocalDate.of(2002,12,24);
ObservableList<Emp>emps= FXCollections.observableArrayList(
      /*  new Emp(1,"Ruba","Qawareeq","rubaqawareeq2@gmail.com","0569524417","Nablus","Awarta",3000,12,date,LocalDate.of(2019,7,5)),
         new Emp(3,"Asmaa","Saleh","asmaasaleh@gmail.com","0569524417","Nablus","Amman",3000,2,date,LocalDate.of(2020,9,20))
*/);


    @FXML
    void addEmployee(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/add.fxml"));
        Parent parent = fxmlLoader.load();
        Add dialogController = fxmlLoader.<Add>getController();
        dialogController.setAppMainObservableList(emps);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    void deleteEmp(ActionEvent event) {

        t.getItems().remove(t.getSelectionModel().getSelectedItem());
        totNum.setText(String.valueOf(t.getItems().size()));

    }

    @FXML
    void enter(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {

    }


    @FXML
    void searchEmp(ActionEvent event) {
        show(attBox.getSelectionModel().getSelectedItem());
      /*  FilteredList<Emp> filter = new FilteredList<>(emps, e -> true);
        att.textProperty().

                addListener((observable, oldValue, newValue)->

                {
                    filter.setPredicate(emp -> {
                        if (newValue.isEmpty() || newValue == null) {
                            return true;
                        }
                        String st=newValue.toLowerCase();

                        if (emp.getEmail().indexOf(st)!=-1) {
                            return true;
                        }
                        if (emp.getCity().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }
                        else if (emp.getMobNum().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }

                        else if (emp.getStreet().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }
                        else if (emp.getX().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }
                        else if (emp.getY().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }
                        else
                            return false;
                    });
                });
        SortedList<Emp> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(t.comparatorProperty());
        t.setItems(sort);*/
    }


    @FXML
    void selsect(ActionEvent event) {
        if(depbox.getSelectionModel().getSelectedItem()!=null)
de.setText(String.valueOf(depbox.getSelectionModel().getSelectedItem().getNum()));
    }
    ObservableList<Department> o=FXCollections.observableArrayList(
            new Department(12,"Hair"),
            new Department(2,"Face")
    );
    void save(ObservableList <Department>ob){
        depbox.getItems().add(null);
        depbox.getItems().addAll(ob);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<>("x"));
        lname.setCellValueFactory(new PropertyValueFactory<>("y"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mNum.setCellValueFactory(new PropertyValueFactory<>("mobNum"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        street.setCellValueFactory(new PropertyValueFactory<>("street"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        depNum.setCellValueFactory(new PropertyValueFactory<>("depNum"));

        t.setItems(emps);
        att.textProperty().addListener((observable, oldDate, newDate) -> {
            show(newDate);
        });
        try {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
        Statement statement = connection.createStatement();
        String q = "select * from employee";
        ResultSet rs = statement.executeQuery(q);

        while (rs.next()) {
                emps.add(new Emp(rs.getInt("eid"), rs.getString("fname"), rs.getString("lname"), rs.getDate("birthdate").toLocalDate(), rs.getDate("startdate").toLocalDate(), rs.getString("email"), rs.getString("mobilenum"), rs.getString("city"), rs.getString("street"), rs.getInt("salary"), rs.getInt("dnum")));
                t.refresh();
            }


        connection.commit();
        connection.close();
        totNum.setText(String.valueOf(t.getItems().size()));

    }
            catch (SQLException e) {
        System.out.println(e.toString());
    }
        save(o);
        String[]attr={"Id","FName","LName","Email","Mobile Number","City","Street","Department Number"};
        attBox.getItems().addAll(attr);


            }
    public void show(String st){

            try {
                emps.clear();
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
                Statement statement = connection.createStatement();
                String q = "select * from employee";
                ResultSet rs = statement.executeQuery(q);
                while (rs.next()) {
                    if(rs.getString("fname").equals(st)) {
                        emps.add(new Emp(rs.getInt("eid"), rs.getString("fname"), rs.getString("lname"), rs.getDate("birthdate").toLocalDate(), rs.getDate("startdate").toLocalDate(), rs.getString("email"), rs.getString("mobilenum"), rs.getString("city"), rs.getString("street"), rs.getInt("salary"), rs.getInt("dnum")));
                        t.refresh();
                    }
                }


                totNum.setText(String.valueOf(t.getItems().size()));

            }
            catch (SQLException e) {
                System.out.println(e.toString());
            }

        }



    @FXML
    void update(ActionEvent event) throws IOException {
        Emp employee=t.getSelectionModel().getSelectedItem();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/editEmp.fxml"));
        Parent parent = fxmlLoader.load();

        EditEmp dialogController = fxmlLoader.<EditEmp>getController();
        dialogController.setData(employee);
        dialogController.setAppMainObservableList(emps);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }


}