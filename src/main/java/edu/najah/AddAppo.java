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
public void setUser(User user){this.user=user;}

    @FXML
    void dd()  throws SQLException{
        int flag=0;

        for (int i = 0; i < t1.getItems().size(); i++){
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
            Connection con2=connection.connect();
                ObservableList<Integer>emp=FXCollections.observableArrayList();
                int y=serviceCombo.getSelectionModel().getSelectedItem().getDepartment().getNum();
                String q="select eid from employee where dnum="+y;
                Statement statement=con2.createStatement();
                ResultSet resultSet=statement.executeQuery(q);
                while(resultSet.next()){
                    emp.add(resultSet.getInt(1));
                }
               con2.close();
            con2=connection.connect();
            statement=con2.createStatement();
            ResultSet set=statement.executeQuery("select count(num)from r_s join service on service.sid=r_s.snum join appo on appo.apponum=r_s.apponum where appo.appodate= and dnum="+y);
            if(){

            }
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
            count.setText (String.valueOf(t1.getItems().size()));

        }
    }
    @FXML
    void date() {
        if(AppoDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("Monday")){
            Alert zipAlert = new Alert(Alert.AlertType.WARNING);
            zipAlert.setTitle("Holiday Day");
            zipAlert.setContentText("Sorry, but Monday is our day off. Please choose another day");
            zipAlert.showAndWait();
        }
    }

    @FXML
    void r() {
        t1.getItems().remove(t1.getSelectionModel().getSelectedItem());
        count.setText (String.valueOf(t1.getItems().size()));

    }
    int x = 0;
    public void conf()throws SQLException {
    Connection con=connection.connect();
Connection con2=connection.connect();
       for(Serv serv:t1.getItems()) {
           ObservableList<Integer>emp=FXCollections.observableArrayList();
           int y=serv.getDepartment().getNum();
           String q="select eid from employee where dnum="+y;
           Statement statement=con2.createStatement();
           ResultSet resultSet=statement.executeQuery(q);
           while(resultSet.next()){
               emp.add(resultSet.getInt(1));
           }
           System.out.println(emp.get(0));
           System.out.println(emp.get(1));
           assert con != null;
           PreparedStatement prs2 = con.prepareStatement("insert into r_s values (i.nextval,?,?,?)");
            prs2.setInt(1,serv.getSerNum());
            prs2.setInt(2,x);
            prs2.setInt(3,emp.get(1));
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
        private User user;

}
