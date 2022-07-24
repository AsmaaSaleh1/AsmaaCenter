package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MyAppo implements Initializable {


    @FXML
    private TableColumn<?, ?> sduration1;
    @FXML
    private RadioButton up;




    @FXML
    private TableColumn<?, ?> sname1;

    @FXML
    private TableColumn<Appo,Integer> snum1;



    @FXML
    private RadioButton past;

    @FXML
    private TableView<Appo> tble11;
    ObservableList<Appo> appo= FXCollections.observableArrayList();
    ObservableList<Appo> tmp=FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        snum1.setCellValueFactory(new PropertyValueFactory<>("num"));
        sname1.setCellValueFactory(new PropertyValueFactory<>("appoDate"));
        sduration1.setCellValueFactory(new PropertyValueFactory<>("time"));
        tble11.setItems(appo);

    }
    @FXML
    void getApp(ActionEvent event) {
        if(tmp.size()==0){
            tmp.addAll(appo);
        }
        appo.clear();
    if(event.getSource()==all){

          for(Appo appo1:tmp){
                  System.out.println(appo);
                  appo.add(appo1);
                  tble11.refresh();
          }
      }
      if(event.getSource()==past){
          for(Appo appo1:tmp){
              if(appo1.getAppoDate().getYear()<=LocalDate.now().getYear()&&appo1.getAppoDate().getMonth().getValue()<=LocalDate.now().getMonth().getValue()&&appo1.getAppoDate().getDayOfMonth()<LocalDate.now().getDayOfMonth()){
                  System.out.println("yes");
                  appo.add(appo1);
                  tble11.refresh();
              }

          }
      }
        if(event.getSource()==up) {
            for (Appo appo1 : tmp) {
                if (appo1.getAppoDate().getYear()>LocalDate.now().getYear()||(appo1.getAppoDate().getMonth().getValue() > LocalDate.now().getMonth().getValue()||((appo1.getAppoDate().getMonth().getValue() == LocalDate.now().getMonth().getValue())&&appo1.getAppoDate().getDayOfMonth()>=LocalDate.now().getDayOfMonth()))) {
                    System.out.println("yes");
                    appo.add(appo1);
                    tble11.refresh();
                }

            }
        }
    }

    @FXML
    private RadioButton all;

    public void setUser(User user){
        Connection con=connection.connect();
        try {
            System.out.println(user.getUsername());
            assert con != null;
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
