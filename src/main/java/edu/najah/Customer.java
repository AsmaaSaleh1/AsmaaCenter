package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class Customer implements Initializable {

    @FXML
    private Button addCust;

    @FXML
    private Button addCust1;

    @FXML
    private TextField att;

    @FXML
    private DatePicker bd;

    @FXML
    private TableColumn<?, ?> bidate;

    @FXML
    private TextField de;

    @FXML
    private Button delCust;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private GridPane g1;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> mNum;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> passCol;

    @FXML
    private TableView<User> t;

    @FXML
    private Text totNum;


    @FXML
    void deleteEmp() {
        String un = t.getSelectionModel().getSelectedItem().getUsername();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.setContentText("Are you sure you want to delete this customer?");
        Optional<ButtonType> op = a.showAndWait();
        if (op.get() == ButtonType.OK) {
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
                Statement statement = connection.createStatement();
                String q = "delete from customer  where user_name='" + un + "' ";
                statement.executeUpdate(q);
                connection.commit();
                System.out.println("Done");

                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }

            totNum.setText(String.valueOf(t.getItems().size()));
        }
    }
    @FXML
    void enter() {

    }

    @FXML
    void exit() {

    }
ObservableList<User> ob= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("username"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
      email.setCellValueFactory(new PropertyValueFactory<>("email"));
        bidate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        mNum.setCellValueFactory(new PropertyValueFactory<>("number"));
        passCol.setCellValueFactory(new PropertyValueFactory<>("pass"));
        ob= FXCollections.observableArrayList();
        ob=connection.getCustomer();
        t.setItems(ob);
        t.refresh();
        totNum.setText(String.valueOf(t.getItems().size()));
filter();

    }

    @FXML
    void byBdate() {
        ObservableList<User> tmp=FXCollections.observableArrayList();
        System.out.println(bd.getValue());
        if (!(bd.getValue()==null)) {
            if(tmp.size()==0){
                for(User user:ob){
                    tmp.add(user);
                }
            }
            ob.clear();
            System.out.println(tmp.size());
            for(User user: tmp) {
                if (user.getBirthdate().equals(bd.getValue())) {
                    ob.add(user);
                    t.refresh();
                }
            }

        }
        else{
            ob=FXCollections.observableArrayList();
            ob=connection.getCustomer();
            t.setItems(ob);
        }
        totNum.setText(String.valueOf(t.getItems().size()));
        filter();

    }
public  void filter(){
    FilteredList<User> filter = new FilteredList<>(ob, e -> true);
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
                    if (emp.getName().toLowerCase().indexOf(st)!=-1) {
                        return true;
                    }
                    else if (String.valueOf(emp.getId()).toLowerCase().indexOf(st)!=-1) {
                        return true;
                    }

                    else if (emp.getEmail().toLowerCase().indexOf(st)!=-1) {
                        return true;
                    }
                    else if (emp.getNumber().toLowerCase().indexOf(st)!=-1) {
                        return true;
                    }

                    else
                        return false;
                });
            });
    SortedList<User> sort = new SortedList<>(filter);
    sort.comparatorProperty().bind(t.comparatorProperty());
    t.setItems(sort);
}

    @FXML
    public void getApp() {
        ob=FXCollections.observableArrayList();
        ob=connection.getCustomer();
        t.setItems(ob);
        t.refresh();
        System.out.println(t.getItems().size());
        past.setSelected(false);
        bd.setValue(null);
        filter();

    }

    @FXML
    private RadioButton past;


}
