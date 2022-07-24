package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {

    @FXML
    private DatePicker AppoDate;

    @FXML
    private TableColumn<?, ?> a;

    @FXML
    private TableColumn<?, ?> b;

    @FXML
    private TableColumn<?, ?> c;


    @FXML
    private TableColumn<?, ?> d;



    @FXML
    private ComboBox<Department> depCombo;

    @FXML
    private ComboBox<Serv> serviceCombo;

    @FXML
    private ComboBox<Time> t;

    @FXML
    private TableView<Serv> t1;
    final private Time[]times={Time.valueOf( "09:00:00"),Time.valueOf( "09:30:00"),Time.valueOf( "10:00:00"),Time.valueOf( "10:30:00"),
            Time.valueOf( "11:00:00"),Time.valueOf( "11:30:00"),Time.valueOf( "12:00:00"),Time.valueOf( "12:30:00"),Time.valueOf( "13:00:00"),
            Time.valueOf( "14:00:00"),Time.valueOf( "15:00:00"), Time.valueOf( "16:00:00"),Time.valueOf( "17:00:00"),Time.valueOf( "18:00:00")
    };
    @FXML
    void r() {
        t1.getItems().remove(t1.getSelectionModel().getSelectedItem());
    }
    ObservableList<Serv> dp= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        a.setCellValueFactory(new PropertyValueFactory<>("a"));
        b.setCellValueFactory(new PropertyValueFactory<>("serNum"));
        d.setCellValueFactory(new PropertyValueFactory<>("b"));
        c.setCellValueFactory(new PropertyValueFactory<>("serDur"));
        t1.setItems(dp);
        t.getItems().addAll(times);
        serviceCombo.setItems(connection.getSrevices());
        depCombo.setItems(connection.getDepartment());
        depCombo.getItems().add(new Department(0,"All"));
    }
    int x=0;
    User user;
    @FXML
    private Text text;
    @FXML
    void dd()  throws SQLException {
        int flag=0;

        for (int i = 0; i < t1.getItems().size(); i++){
            if (serviceCombo.getSelectionModel().getSelectedItem().equals(t1.getItems().get(i))) {
                flag = 1;
                break;
            }
        }
        System.out.println(AppoDate.getValue().getDayOfWeek().toString());
        if(AppoDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("Monday")){
            Alert zipAlert = new Alert(Alert.AlertType.WARNING);
            zipAlert.setTitle("Holiday Day");
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
            if(t1.getItems().size()==0){
                Connection con = connection.connect();
                assert con != null;
                PreparedStatement prs = con.prepareStatement("insert into appo values (appseq.nextval,?,?,?)");
                prs.setDate(1, Date.valueOf(AppoDate.getValue()));
                prs.setTime(2, Time.valueOf(t.getSelectionModel().getSelectedItem().toLocalTime()));
                prs.setString(3, user.getUsername());
                 prs.executeUpdate();
                con.commit();
                con.close();
                ObservableList<Appo> tmp = connection.getAllApo();

                for (Appo appo : tmp) {
                    if (appo.getAppoDate().equals(AppoDate.getValue()) && appo.getUn().equals(user.getUsername())) {
                        x = appo.getNum();
                        System.out.println(x);
                        break;
                    }
                }
            }

            dp.add(serviceCombo.getSelectionModel().getSelectedItem());

        }
    }
    @FXML
    void addCust()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/allCustomer.fxml"));
        Parent parent = fxmlLoader.load();
        AllCustomer customer=fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        user=customer.getUser();
        text.setText(customer.getUser().toString());
    }

    public void conf(ActionEvent event)throws SQLException {
        Connection con=connection.connect();

        for(Serv serv:t1.getItems()) {
            assert con != null;
            PreparedStatement prs2 = con.prepareStatement("insert into r_s values (i.nextval,?,?,?)");
            prs2.setInt(1,serv.getSerNum());
            prs2.setInt(2,x);
            prs2.setInt(3,20);
            prs2.executeUpdate();
            t1.refresh();

        }


        System.out.println("Done");
        Notifications notifications = Notifications.create()
                .text("Your appointment has been booked successfully. Thank you for choosing our salon")
                .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))
                .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
        notifications.show();
        t1.getItems().clear();
        AppoDate.setValue(null);
        closeStage(event);


    }
    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void showServ() {
        int x=depCombo.getSelectionModel().getSelectedItem().getNum();
        Connection con=connection.connect();
        try {
            serviceCombo.getItems().clear();
            assert con != null;
            Statement statement = con.createStatement();
            if (x == 0) {

                serviceCombo.setItems(connection.getSrevices());
            } else {
                String q = "select * FROM service where dnum='" + x + "'";
                statement.executeQuery(q);
                ResultSet rs = statement.executeQuery(q);
                while (rs.next()) {
                    serviceCombo.getItems().add(new Serv(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), depCombo.getSelectionModel().getSelectedItem()));
                    System.out.println("y");
                }

                con.close();
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

}
