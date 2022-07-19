package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MyAccount {

    @FXML
    private DatePicker Birthdate;

    @FXML
    private PasswordField confirmpass;

    @FXML
    private TextField email;

    @FXML
    private AnchorPane myAccount;

    @FXML
    private TextField name;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField phonenum;

    @FXML
    private Button sc;

    @FXML
    void enter(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) throws SQLException {
        Connection con=connection.connect();
        Statement statement = con.createStatement();
        String q="update customer set MOB_NUM='"+phonenum.getText()+"',email='"+email.getText()+"',CPASSWORD='"+pass.getText() +"' where user_name ='"+name.getText()+"'";
        statement.executeUpdate(q);

        con.commit();
        con.close();

        System.out.println("Done");
    }
    public void setData(User user) {
name.setText(user.username);
email.setText(user.getEmail());
pass.setText(user.getPass());
phonenum.setText(user.getNumber());
Birthdate.setValue(user.getBirthdate());
    }

}
