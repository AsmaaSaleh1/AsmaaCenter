package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {

    @FXML
    private DatePicker Birthdate;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private PasswordField passfieled;

    @FXML
    private TextField phonenum;

    @FXML
    void backtolog(ActionEvent event) throws IOException {
App.sho(event,"hello-view");
    }

    @FXML
    public void createAcc(ActionEvent event) throws SQLException ,IOException{
        if (!(email.getText().isBlank() || phonenum.getText().isBlank() || Birthdate.getValue()==null||name.getText().isBlank()||passfieled.getText().isBlank())) {
            String[] split = email.getText().split("@");
            String un = split[0];
            Connection con = connection.connect();
            assert con != null;
            PreparedStatement prs = con.prepareStatement("insert into customer values (?,?,?,?,?,?)");
            prs.setString(1, un);
            prs.setString(2, name.getText());
            prs.setDate(3, Date.valueOf(Birthdate.getValue()));
            prs.setString(4, email.getText());
            prs.setString(5, phonenum.getText());
            prs.setString(6, passfieled.getText());
            prs.executeUpdate();
            con.commit();
            con.close();
            System.out.println("Done");
            backtolog(event);
        }
        else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Not Completed");
            a.setContentText("Please fill all the field");
            a.showAndWait();
        }
    }
    @FXML
    void enter() {

    }

    @FXML
    void exit() {

    }

}
