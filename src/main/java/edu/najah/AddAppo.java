package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
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
    ObservableList<Appo>appos=FXCollections.observableArrayList();
private String []timearr={"9:00 am","10:00 am","11:00 am","12:00 pm","1:00 pm","2:00 pm","3:00 pm","4:00 pm","5:00 pm","6:00 pm"};
    private String [] department={"Hair","Bridal","Nail","Body","Face"};
   private ObservableList<Serv> servs;
    ObservableList<Serv> d2= FXCollections.observableArrayList(
            new Serv("1","makeup","45","100",new Department(1,"Hair"))
    );
    ObservableList<Serv> dp= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a.setCellValueFactory(new PropertyValueFactory<>("a"));
        b.setCellValueFactory(new PropertyValueFactory<>("serNum"));
        d.setCellValueFactory(new PropertyValueFactory<>("b"));
        c.setCellValueFactory(new PropertyValueFactory<>("serDur"));
        t1.setItems(dp);
        t.getItems().addAll(timearr);
        depCombo.getItems().addAll(department);
        serviceCombo.getItems().addAll(d2);
    }


    @FXML
    void showPrice(ActionEvent event) {

    }
    @FXML
    void sho(ActionEvent event) {
    }

    @FXML
    void dd(ActionEvent event) {
        int flag=0;

        for (int i = 0; i < t1.getItems().size(); i++) {
            if (serviceCombo.getSelectionModel().getSelectedItem().equals(t1.getItems().get(i))) {
                flag = 1;
                break;
            }
        }
        System.out.println(AppoDate.getValue().getDayOfWeek().toString());
        if(AppoDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("Monday")){
            Alert zipAlert = new Alert(Alert.AlertType.WARNING);
            zipAlert.setTitle("Holiday DAy");
            zipAlert.setContentText("Sorry, but Monday is our day off. Please choose another day");
            zipAlert.showAndWait();
        }

       else if(flag==1){
            Alert zipAlert = new Alert(Alert.AlertType.WARNING);
            zipAlert.setTitle("Already Selected");
            zipAlert.setContentText("This Service is already selected in this appointment");
            zipAlert.showAndWait();

        }
        else{

            dp.add(serviceCombo.getSelectionModel().getSelectedItem());
            Appo a=new Appo(1,AppoDate.getValue(),t.getSelectionModel().getSelectedItem(),new User("Ali","1","dfg",AppoDate.getValue(),"",""));
            appos.add(a);
            count.setText (String.valueOf(t1.getItems().size()));
        }
    }


    @FXML
    void r(ActionEvent event) {
        t1.getItems().remove(t1.getSelectionModel().getSelectedItem());
        count.setText (String.valueOf(t1.getItems().size()));
    }
    public void conf(ActionEvent event)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/myAppo.fxml"));
        Parent parent = fxmlLoader.load();
        MyAppo m=fxmlLoader.getController();
        m.setData(appos);
        Notifications notifications = Notifications.create()
                .text("Your appointment has been booked successfully. Thank you for choosing our salon")
                .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))
                .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
        notifications.show();


    }
}
