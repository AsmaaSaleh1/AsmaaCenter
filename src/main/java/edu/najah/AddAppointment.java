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
import java.time.LocalDate;
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

    User user;
    @FXML
    private Text text;
    ObservableList<Integer> empID=FXCollections.observableArrayList();
    ObservableList<Integer> eID=FXCollections.observableArrayList();
    @FXML
    void dd()  throws SQLException{
        int count1=1;
        int count2=1;
        int countEmp=0;
        int flag=0;

        empID.clear();
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

        else {
            Connection con2 = connection.connect();
            ObservableList<Integer> emp = FXCollections.observableArrayList();
            int y = serviceCombo.getSelectionModel().getSelectedItem().getDepartment().getNum();
            Statement s2 = con2.createStatement();
            ResultSet result=s2.executeQuery("(select eid from employee where dnum="+y+") MINUS (select empid from r_s join employee on employee.eid=r_s.empid)");
            while (result.next()){
                empID.add(result.getInt(1));
            }
            String q = "select eid from employee where dnum=" + y;
            Statement statement = con2.createStatement();
            ResultSet resultSet = statement.executeQuery(q);
            while (resultSet.next()) {
                emp.add(resultSet.getInt(1));
            }
            countEmp = emp.size();
            count2 *= countEmp;
            con2.close();
            con2 = connection.connect();
            statement = con2.createStatement();

            ResultSet set = statement.executeQuery("select count(num)from r_s join service on service.sid=r_s.snum join appo on appo.apponum=r_s.apponum   WHERE appo.appodate = TO_DATE(' " + AppoDate.getValue() + "', 'YYYY-MM-DD') and dnum=" + y);
            while (set.next()) {
                count1=set.getInt(1);
            }
            if(empID.size()==0&&count1<3){
                empID=emp;
            }
            System.out.println(emp.size());

            if (count2 < count1 &&count1>10) {
                System.out.println("Yes");
                Alert zipAlert = new Alert(Alert.AlertType.WARNING);
                zipAlert.setTitle("Full Day");
                zipAlert.setContentText("Sorry");
                zipAlert.showAndWait();
            } else {
                eID.add(empID.get(0));
                if (t1.getItems().size() == 0) {
                    Connection con = connection.connect();
                    assert con != null;
                    PreparedStatement prs = con.prepareStatement("insert into appo values (appseq.nextval,?,?,?)");
                    prs.setDate(1, Date.valueOf(AppoDate.getValue()));
                    prs.setTime(2, Time.valueOf(t.getSelectionModel().getSelectedItem().toLocalTime()));
                    prs.setString(3, user.getUsername());
                    prs.executeUpdate();
                    con.commit();
                    con.close();


                }
            }

            dp.add(serviceCombo.getSelectionModel().getSelectedItem());

        }
    }
    @FXML
    void addCust()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/allCustomer.fxml"));
        Parent parent = fxmlLoader.load();
        AllCustomer customer = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        try {

            user = customer.getUser();
            text.setText(customer.getUser().toString());
        }
catch (Exception e){
    Alert zipAlert = new Alert(Alert.AlertType.WARNING);
    zipAlert.setContentText("Choose the customer");
    zipAlert.showAndWait();
}
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void conf(ActionEvent event)throws SQLException {
        int x=0;
        Connection connect=connection.connect();
        Statement st=connect.createStatement();
        ResultSet set1=st.executeQuery("(select appo.apponum from appo WHERE custpk=+'"+user.getUsername()+"'and appo.appodate=TO_DATE('"+AppoDate.getValue()+"', 'YYYY-MM-DD'))");
        ObservableList<Integer> tmp = FXCollections.observableArrayList();
        while (set1.next()){
            tmp.add(set1.getInt(1));
        }
        x=tmp.get(0);
        connect.close();
        Connection con=connection.connect();

        int i=0;
        for(Serv serv:t1.getItems()) {

            assert con != null;
            PreparedStatement prs2 = con.prepareStatement("insert into r_s values (i.nextval,?,?,?)");
            prs2.setInt(1,serv.getSerNum());
            prs2.setInt(2,x);
            prs2.setInt(3,eID.get(i));
            prs2.executeUpdate();
            t1.refresh();
            i++;
        }
        System.out.println("Done");
        Notifications notifications = Notifications.create()
                .text("Your appointment has been booked successfully")
                .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))
                .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
        notifications.show();
        t1.getItems().clear();
//AppoDate.setValue();
closeStage(event);

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
    @FXML
    void date() {
        if (AppoDate.getValue().getDayOfWeek().toString().equalsIgnoreCase("Monday")) {
            Alert zipAlert = new Alert(Alert.AlertType.WARNING);
            zipAlert.setTitle("Holiday Day");
            zipAlert.setContentText("Sorry, but Monday is our day off. Please choose another day");
            zipAlert.showAndWait();
        }
        if (AppoDate.getValue().getYear() <= LocalDate.now().getYear() & AppoDate.getValue().getMonth().getValue() <= LocalDate.now().getMonth().getValue() && AppoDate.getValue().getDayOfMonth() < LocalDate.now().getDayOfMonth()) {
            AppoDate.setValue(LocalDate.now());
            Alert zipAlert = new Alert(Alert.AlertType.WARNING);
           // zipAlert.setTitle("Holiday Day");
            zipAlert.setContentText("Sorry, but This date has passed");
            zipAlert.showAndWait();
        }
    }

}
