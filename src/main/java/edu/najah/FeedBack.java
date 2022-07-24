package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeedBack {


    @FXML
    private ListView<String> list;

    @FXML
    private Text txt;
public void setAppo(Appo appo) throws SQLException {
    ObservableList<String> o= FXCollections.observableArrayList();
    txt.setText(appo.getUn());
    Connection con=connection.connect();

    Statement statement = con.createStatement();

    String q="select sname, price ,fname from r_s " +
            "join service on service.sid=r_s.snum " +
            "join appo on appo.apponum=r_s.apponum " +
            "join employee on employee.eid=r_s.empid"+
            " where appo.apponum ="+appo.getNum();
    ResultSet rs = statement.executeQuery(q);
    while (rs.next()){
        o.add(rs.getString(1)+"\t\t\t\t\t\t\t  "+rs.getInt(2)+"\t\t\t\t"+rs.getString(3)+"\n");
        list.setItems(o);
    }

}
    @FXML
    void close(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }



}
