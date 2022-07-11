package edu.najah;

import javafx.application.Preloader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAppo implements Initializable {

    @FXML
    private AnchorPane AnAddApp;

    @FXML
    private DatePicker AppoDate;

    @FXML
    private TableColumn<?, ?> a;

    @FXML
    private GridPane appGrid;

    @FXML
    private TableColumn<?, ?> b;

    @FXML
    private TableColumn<?, ?> c;

    @FXML
    private Text count;

    @FXML
    private TableColumn<?, ?> d;

    @FXML
    private ComboBox<String> depCombo;

    @FXML
    private Button invoiceB;

    @FXML
    private Button invoiceB1;

    @FXML
    private TextField search;

    @FXML
    private ComboBox<Serv> serviceCombo;

    @FXML
    private ComboBox<String> t;

    @FXML
    private TableView<Serv> t1;
private String []timearr={"9:00 am","10:00 am","11:00 am","12:00 pm","1:00 pm","2:00 pm","3:00 pm","4:00 pm","5:00 pm","6:00 pm"};
    private String [] department={"Hair","Bridal","Nail","Body","Face"};
    @FXML
    void showPrice(ActionEvent event) {

    }
    @FXML
    void sho(ActionEvent event) {


    }
    Service service=new Service();
    ObservableList<Serv> d2;
public void addser(Serv serv){
d2.add(serv);
    serviceCombo.getItems().addAll(d2);
    Preloader.PreloaderNotification notification;

}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a.setCellValueFactory(new PropertyValueFactory<>("a"));
        b.setCellValueFactory(new PropertyValueFactory<>("serNum"));
        d.setCellValueFactory(new PropertyValueFactory<>("b"));
        c.setCellValueFactory(new PropertyValueFactory<>("serDur"));
        t1.setItems(tvObservableList);
        d2= service.getTvObservableList();

        //t1.getSelectionModel().selectFirst();
        t.getItems().addAll(timearr);
        depCombo.getItems().addAll(department);

    }
    int serCount=0;
    private ObservableList<Serv> tvObservableList= FXCollections.observableArrayList();
    @FXML
    void dd(ActionEvent event) {
        int flag=0;
        for (int i=0;i<t1.getItems().size();i++) {
            if (serviceCombo.getSelectionModel().getSelectedItem().equals(t1.getItems().get(i))) {
                flag = 1;
                break;
            }
        }
        if(flag==1){
            Alert zipAlert = new Alert(Alert.AlertType.WARNING);
            zipAlert.setTitle("Already Selected");
            zipAlert.setContentText("This Service is already selected in this appointment");
            zipAlert.showAndWait();

        }
        else{
            serCount++;
            tvObservableList.add(serviceCombo.getSelectionModel().getSelectedItem());

            count.setText(String.valueOf(serCount));
        }
    }


}
