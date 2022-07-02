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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainInterface implements Initializable {
    @FXML
    private Button addEmp;
    @FXML
    private Button b1;
    @FXML
    private AnchorPane contact2;
    @FXML
    private Button b11;

    @FXML
    private Button b111;

    @FXML
    private Button b112;

    @FXML
    private Button b113;

    @FXML
    private ImageView i1;

    @FXML
    private ImageView i2;

    @FXML
    private ImageView i3;

    @FXML
    private ImageView i4;

    @FXML
    private ImageView i5;
    @FXML
    private AnchorPane depName;
    @FXML
    private ImageView i6;
    @FXML
    private Pane pane;
    @FXML
    private Label serlb;

    @FXML
    private Button btDEp;
    @FXML
    private AnchorPane contact;

    @FXML
    private TableView<?> tble;
    @FXML
    private ImageView i7;
    @FXML
    private Button sc;
    @FXML
    private ImageView i8;
    @FXML
    private DatePicker Birthdate;
    @FXML
    private AnchorPane myAccount;

    @FXML
    private AnchorPane sliper;
    @FXML
    private Button btcontact;
    @FXML
    private TableColumn<Serv,String>  about;
    @FXML
    private TableColumn <Serv,String> sduration;

    @FXML
    private TableView<Serv> serTbl;

    @FXML
    private TableColumn<Serv,String>  c2;
    @FXML
    private Button addSer;
    @FXML
    private TableColumn<Serv,String> c1;
    @FXML
    private TableColumn<Serv,String>  c3;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane pn;
    @FXML
    private Button btAccount;

    @FXML
    private Button btOut1;

    @FXML
    private Button btAddApp;
    @FXML
    private Button btAppo;
    @FXML
    private AnchorPane AnAddApp;
    @FXML
    private GridPane g1;
    @FXML
    private Button invoiceB;
    @FXML
    private DatePicker AppoDate;
    @FXML
    private PasswordField confirmpass;
    @FXML
    private TextField email;
    @FXML
    private TextField name;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField phonenum;
    @FXML
    private ComboBox<String> depCombo;
    @FXML
    private ComboBox<String> serviceCombo;
    @FXML
    public TextField timeNeeded;
    @FXML
    public AnchorPane myAppointments;
    @FXML
    private void handleClicks(ActionEvent event)throws IOException  {
        if(event.getSource()==btAccount){
            pn.setVisible(false);
            grid.setVisible(false);
            serlb.setVisible(false);
            pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
            myAccount.setVisible(false);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
        }
        if(event.getSource()==btAppo){

            grid.setVisible(true);
            serlb.setVisible(true);
            pn.setVisible(false);
            pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
            serlb.setText("My Appointment");
            myAccount.setVisible(false);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
        }
        if(event.getSource()==btAddApp){
            grid.setVisible(false);
            pn.setVisible(false);
            pane.setVisible(true);
            contact.setVisible(false);
            contact2.setVisible(false);
            myAccount.setVisible(false);
            serlb.setText("Add An Appointment");
            AnAddApp.setVisible(true);
            invoiceB.setVisible(true);
            serlb.setVisible(false);
        }
        if(event.getSource()==btOut1){
           App.setRoot("hello-view");

        }
        if(event.getSource()==btDEp){
            pn.setVisible(true);
          grid.setVisible(false);
            pane.setVisible(false);
            contact.setVisible(false);
            contact2.setVisible(false);
            myAccount.setVisible(false);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
            serlb.setVisible(false);
        }
        if(event.getSource()==btcontact){
            pn.setVisible(false);
            pane.setVisible(false);
            contact.setVisible(true);
            contact2.setVisible(true);
            myAccount.setVisible(false);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
            grid.setVisible(false);
            serlb.setVisible(false);
        }
        if(event.getSource()==btAccount){
            pn.setVisible(false);
            pane.setVisible(false);
            contact.setVisible(false);
            contact2.setVisible(false);
            myAccount.setVisible(true);
            AnAddApp.setVisible(false);
            invoiceB.setVisible(false);
            grid.setVisible(false);
            serlb.setVisible(false);

        }

        }
    @FXML
    void showServices(MouseEvent event)throws IOException {
        pn.setVisible(false);
        g1.setVisible(true);
        sliper.setVisible(false);
        back.setVisible(true);
        pane.setVisible(true);
        if(event.getSource()==b113) {
            lbS.setText("Body Department");
        }
        if(event.getSource()==b1) {
            lbS.setText("Face Department");
        }
        if(event.getSource()==b11) {
            lbS.setText("Hair Department");
        }
        if(event.getSource()==b111) {
            lbS.setText("Nail Department");
        }
        if(event.getSource()==b112) {
            lbS.setText("Bride Department");
        }

    }
    @FXML
    private Label lbS;

    @FXML
    void backToMain(ActionEvent event) {
        pn.setVisible(true);
        g1.setVisible(false);
        sliper.setVisible(true);
        lbS.setText("");
        back.setVisible(false);
    }

    @FXML
    private Button back;

    @FXML
    void addEmployee() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/add.fxml"));
        Parent parent = fxmlLoader.load();
        AddService dialogController = fxmlLoader.<AddService>getController();
        dialogController.setAppMainObservableList(tvObservableList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    @FXML
    private Button sign2;
    @FXML
    void enter() {
        addEmp.setScaleX(1.2);
        addEmp.setScaleY(1.2);
        sign2.setScaleX(1.2);
        sign2.setScaleY(1.2);
        sc.setScaleX(1.2);
        sc.setScaleY(1.2);
    }

    @FXML
    void exit() {
        addEmp.setScaleX(1);
        addEmp.setScaleY(1);
        sign2.setScaleX(1);
        sign2.setScaleY(1);
        sc.setScaleX(1);
        sc.setScaleY(1);
    }
    @FXML
    void saveChanges() {

    }
    private ObservableList<Serv> tvObservableList = FXCollections.observableArrayList(
            new Serv("Hair cut","12","45","100")
    );

    @FXML
    void addServ(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/addService.fxml"));
        Parent parent = fxmlLoader.load();
        AddService dialogController = fxmlLoader.<AddService>getController();
        dialogController.setAppMainObservableList(tvObservableList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void setData(User user){
this.name.setText(user.getName());
this.phonenum.setText(user.getNumber());
        this.email.setText(user.getEmail());
        this.pass.setText(user.getPass());
        this.Birthdate.setValue(user.getBirthdate().getValue());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c2.setCellValueFactory(new PropertyValueFactory<>("serName"));
        c1.setCellValueFactory(new PropertyValueFactory<>("serNum"));
        sduration.setCellValueFactory(new PropertyValueFactory<>("serDur"));
        c3.setCellValueFactory(new PropertyValueFactory<>("serPrice"));
        serTbl.setItems(tvObservableList);
    }
}

