package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AllCustomer implements Initializable {

    @FXML
    private TextField att;

    @FXML
    private TableColumn<?, ?> bidate;

    @FXML
    private TableColumn<?, ?> email;

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
    ObservableList<User> ob= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("username"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        bidate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        mNum.setCellValueFactory(new PropertyValueFactory<>("number"));
        passCol.setCellValueFactory(new PropertyValueFactory<>("pass"));
        ob = FXCollections.observableArrayList();
        ob = connection.getCustomer();
        t.setItems(ob);
        filter();


    }
    private User user;
    @FXML
    void done(ActionEvent event) {
user=t.getSelectionModel().getSelectedItem();
closeStage(event);
    }
    public  void filter(){
        FilteredList<User> filter = new FilteredList<>(ob, e -> true);
        att.textProperty().

                addListener((observable, oldValue, newValue)->

                        filter.setPredicate(emp -> {
                            if (newValue.isEmpty()) {
                                return true;
                            }
                            String st=newValue.toLowerCase();

                            if (emp.getEmail().contains(st)) {
                                return true;
                            }
                            if (emp.getName().toLowerCase().contains(st)) {
                                return true;
                            }
                            else if (String.valueOf(emp.getId()).toLowerCase().contains(st)) {
                                return true;
                            }

                            else if (emp.getEmail().toLowerCase().contains(st)) {
                                return true;
                            }
                            else return emp.getNumber().toLowerCase().contains(st);
                        }));
        SortedList<User> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(t.comparatorProperty());
        t.setItems(sort);
    }
    public User getUser(){return user;}
    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
