package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddAppo implements Initializable {


    @FXML
    private DatePicker AppoDate;

    @FXML
    private TableColumn<?, ?> a;



    @FXML
    private TableColumn<?, ?> b;

    @FXML
    private TableColumn<?, ?> c;

    @FXML
    private Text count;

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
    ObservableList<Appo>appos=FXCollections.observableArrayList();
 final private Time[]times={Time.valueOf( "09:00:00"),Time.valueOf( "09:30:00"),Time.valueOf( "10:00:00"),Time.valueOf( "10:30:00"),
          Time.valueOf( "11:00:00"),Time.valueOf( "11:30:00"),Time.valueOf( "12:00:00"),Time.valueOf( "12:30:00"),Time.valueOf( "13:00:00"),
          Time.valueOf( "14:00:00"),Time.valueOf( "15:00:00"), Time.valueOf( "16:00:00"),Time.valueOf( "17:00:00"),Time.valueOf( "18:00:00")
  };


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


    @FXML
    void showPrice( ) {

    }
    @FXML
    void sho( ) {
    }

    @FXML
    void dd() {
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

            dp.add(serviceCombo.getSelectionModel().getSelectedItem());
       //     Appo a=new Appo(1,AppoDate.getValue(),t.getSelectionModel().getSelectedItem().toString(),new User("Ali","1","dfg",AppoDate.getValue(),"",""));
         //   appos.add(a);
            count.setText (String.valueOf(t1.getItems().size()));
        }
    }


    @FXML
    void r() {
        t1.getItems().remove(t1.getSelectionModel().getSelectedItem());
        count.setText (String.valueOf(t1.getItems().size()));
    }
    public void conf()throws SQLException {
        Connection con=connection.connect();
        PreparedStatement prs=con.prepareStatement("insert into appo values (appseq.nextval,?,?,?)");
        prs.setDate(1,Date.valueOf(AppoDate.getValue()));
        prs.setTime(2,Time.valueOf(t.getSelectionModel().getSelectedItem().toLocalTime()));
prs.setString(3,user.getUsername());
        int z= prs.executeUpdate();
        con.commit();
        con.close();
        System.out.println("Done");
        Notifications notifications = Notifications.create()
                .text("Your appointment has been booked successfully. Thank you for choosing our salon")
                .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))
                .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
        notifications.show();
t1.getItems().clear();
AppoDate.setValue(null);


    }

    @FXML
    void showServ() {
int x=depCombo.getSelectionModel().getSelectedItem().getNum();
Connection con=connection.connect();
        try {
            serviceCombo.getItems().clear();
            Statement statement = con.createStatement();
            if (x == 0) {
                String q = "select * FROM service";

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
        private User user;
        public void setUser(User user){
        this.user=user;
        }

}
