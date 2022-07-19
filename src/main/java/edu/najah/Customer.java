package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
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
    void deleteEmp(ActionEvent event) {
        String un=t.getSelectionModel().getSelectedItem().getUsername();
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
            Statement statement = connection.createStatement();
            String q="delete from customer  where user_name='"+un+"' ";
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

    @FXML
    void enter(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {

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
    void byBdate(ActionEvent event) {
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
    void refresh(MouseEvent event) {
        ob=FXCollections.observableArrayList();
        Connection con=connection.connect();
        ob=connection.getCustomer();
        t.setItems(ob);

    }



}
