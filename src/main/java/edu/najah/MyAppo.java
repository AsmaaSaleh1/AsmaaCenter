package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class MyAppo implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private ToggleGroup pastUp;
    @FXML
    private TableColumn<?, ?> sduration1;
    @FXML
    private RadioButton up;


    @FXML
    private TextField search1;

    @FXML
    private TableColumn<?, ?> sname1;

    @FXML
    private TableColumn<Appo,Integer> snum1;

    @FXML
    private TableColumn<?, ?> sprice1;

    @FXML
    private RadioButton past;
    @FXML
    private TableColumn<?, ?> sprice11;

    @FXML
    private TableView<Appo> tble11;
    LocalDate d=LocalDate.now();
    ObservableList<Appo> appo= FXCollections.observableArrayList(
           // new Appo(1,d,"8",new User("Ali","1","dfg",LocalDate.of(2022,12,5),"",""))

    );
    public void setData(ObservableList<Appo> appo){

this.appo=appo;
        System.out.println(this.appo.get(0).getNum());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        snum1.setCellValueFactory(new PropertyValueFactory<Appo,Integer>("num"));
        sname1.setCellValueFactory(new PropertyValueFactory<>("appoDate"));
        sduration1.setCellValueFactory(new PropertyValueFactory<>("time"));
        tble11.setItems(appo);

    }
ObservableList<Appo> pob=FXCollections.observableArrayList();
    ObservableList<Appo> upob=FXCollections.observableArrayList();
    @FXML
    void getApp(ActionEvent event) {
        for (int i = 0; i < 1; i++) {
            int day = Period.between(LocalDate.now(), appo.get(i).getAppoDate()).getDays();
            int month = Period.between(LocalDate.now(), appo.get(i).getAppoDate()).getMonths();
            int year = Period.between(LocalDate.now(), appo.get(i).getAppoDate()).getYears();
            if(year<0||month<0||day<0){
                pob.add(appo.get(i));
            }
            else{
                upob.add(appo.get(i));
            }

        }

    }
    private User user;
    public void setUser(User user){
        this.user=user;
        Connection con=connection.connect();
        try {
            System.out.println(user.getUsername());
            Statement statement = con.createStatement();
            String q="select *from appo where custpk='"+user.getUsername()+"' ";
            statement.executeQuery(q);
            ResultSet rs = statement.executeQuery(q);
            while (rs.next()) {
                appo.add(new Appo(rs.getInt(1),rs.getDate(2).toLocalDate(),rs.getTime(3).toLocalTime(),user));
                tble11.refresh();
                System.out.println("y");
            }

            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tble11.setItems(appo);
    }
}
