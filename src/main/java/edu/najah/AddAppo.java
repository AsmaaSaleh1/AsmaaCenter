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
   ObservableList<Integer> empID=FXCollections.observableArrayList();
    ObservableList<Integer> eID=FXCollections.observableArrayList();
public void setUser(User user){this.user=user;}

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

            if (count2 < count1 &&count1>2) {
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
                    Connection connect=connection.connect();
                    Statement st=connect.createStatement();
                    ResultSet set1=st.executeQuery("(select appo.apponum from appo WHERE custpk=+'"+user.getUsername()+"')minus (select r_s.apponum from r_s join appo on appo.apponum=r_s.apponum where appo.appodate=TO_DATE('"+AppoDate.getValue()+"', 'YYYY-MM-DD'))");
                    ObservableList<Integer> tmp = FXCollections.observableArrayList();
while (set1.next()){
tmp.add(set1.getInt(1));
}
x=tmp.get(0);

                    }
                }

                dp.add(serviceCombo.getSelectionModel().getSelectedItem());
                count.setText(String.valueOf(t1.getItems().size()));

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

int i=0;
       for(Serv serv:t1.getItems()) {

           assert con != null;
           Statement s=con.createStatement();
           s.executeUpdate("update appo set appodate =TO_DATE(' "+AppoDate.getValue()+"', 'YYYY-MM-DD')where apponum= "+x);
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
                .text("Your appointment has been booked successfully. Thank you for choosing our salon")
                .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))
                .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
        notifications.show();
t1.getItems().clear();
//AppoDate.setValue();


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
