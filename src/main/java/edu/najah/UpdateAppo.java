package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.sql.*;

public class UpdateAppo {
    final private Time[]times={Time.valueOf( "09:00:00"),Time.valueOf( "09:30:00"),Time.valueOf( "10:00:00"),Time.valueOf( "10:30:00"),
            Time.valueOf( "11:00:00"),Time.valueOf( "11:30:00"),Time.valueOf( "12:00:00"),Time.valueOf( "12:30:00"),Time.valueOf( "13:00:00"),
            Time.valueOf( "14:00:00"),Time.valueOf( "15:00:00"), Time.valueOf( "16:00:00"),Time.valueOf( "17:00:00"),Time.valueOf( "18:00:00")
    };
    @FXML
    private DatePicker AppoDate;

    @FXML
    private ComboBox<Time> t;

    @FXML
    void date(ActionEvent event) {

    }

    @FXML
    void done(ActionEvent event) throws SQLException {
        Connection con=connection.connect();
        PreparedStatement prs=con.prepareStatement("update Appo set appodate=?,appotime=? where apponum="+appo.getNum());
prs.setDate(1,Date.valueOf(AppoDate.getValue()));
prs.setTime(2,t.getSelectionModel().getSelectedItem());
prs.executeUpdate();
        con.commit();
        con.close();

        System.out.println("Done");
        close(event);
    }

    void close(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    Appo appo;
public void setAppo(Appo appo){
    this.appo=appo;
    t.getItems().addAll(times);
    t.getSelectionModel().select(Time.valueOf(appo.getTime()));
    AppoDate.setValue(appo.getAppoDate());
}
}
