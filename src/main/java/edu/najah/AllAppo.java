package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AllAppo implements Initializable {

    @FXML
    private TableColumn<?, ?> appdate;

    @FXML
    private TableColumn<?, ?> apptime;

    @FXML
    private TextField att;

    @FXML
    private ToggleGroup pastUp;
    @FXML
    private DatePicker bd;

    @FXML
    private TextField de;

    @FXML
    private Button delCust;

    @FXML
    private GridPane g1;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private RadioButton past;

    @FXML
    private TableColumn<?, ?> price;

   

    @FXML
    private Text totNum;

    @FXML
    private TableColumn<?, ?> totServ;

    @FXML
    private RadioButton up;


    @FXML
    private TableView<Reservation> t;
    private ObservableList<Reservation> ob= FXCollections.observableArrayList(
       //     new Reservation(new User("Manal","0597616010","Ruba@gmail.com", LocalDate.of(2002,12,24),"123","123")
                 //   new Appo(11,LocalDate.now(),"10:00 am"),3,450)
    );
    
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
    void search(ActionEvent event) {
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bd.setValue(LocalDate.now());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        apptime.setCellValueFactory(new PropertyValueFactory<>("time"));
        totServ.setCellValueFactory(new PropertyValueFactory<>("serNum"));
        price.setCellValueFactory(new PropertyValueFactory<>("totPrice"));
        t.setItems(ob);
        totNum.setText(String.valueOf(t.getItems().size()));

    }

    public void getApp(ActionEvent event) {
    }
}
