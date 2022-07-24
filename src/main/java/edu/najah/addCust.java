package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addCust {

    @FXML
    private DatePicker Birthdate;

    @FXML
    private TextField email;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private AnchorPane p1;

    @FXML
    private AnchorPane p2;

    @FXML
    private PasswordField passfieled;

    @FXML
    private TextField phonenum;



    @FXML
    void add(ActionEvent event) throws SQLException {
String name=fname.getText()+" "+lname.getText();
        String[]split=email.getText().split("@");
        String un=split[0];
        Connection con=connection.connect();
        assert con != null;
        PreparedStatement prs=con.prepareStatement("insert into customer values (?,?,?,?,?,?)");
        prs.setString(1,un);
        prs.setString(2,name);
        prs.setDate(3, Date.valueOf(Birthdate.getValue()));
        prs.setString(4,email.getText());
        prs.setString(5,phonenum.getText());
        prs.setString(6,passfieled.getText());
         prs.executeUpdate();
        con.commit();
        con.close();
        System.out.println("Done");
        closeStage(event);
    }
    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void enter() {

    }

    @FXML
    void exit() {

    }

    @FXML
    void next() {
p1.setVisible(false);
p2.setVisible(true);
    }

}
