package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {

    @FXML
    private DatePicker Birthdate;

    @FXML
    private PasswordField confirmpass;

    @FXML
    private TextField email;

    @FXML
    private ImageView logo;

    @FXML
    private TextField name;

    @FXML
    private AnchorPane p1;

    @FXML
    private AnchorPane p3;

    @FXML
    private PasswordField passfieled;

    @FXML
    private TextField phonenum;

    @FXML
    private Button sback;

    @FXML
    private Button sign2;

    @FXML
    void backtolog(ActionEvent event) throws IOException {
App.sho(event,"hello-view");
    }

    @FXML
    public void createAcc(ActionEvent event) throws SQLException ,IOException{
        String[]split=email.getText().split("@");
        String un=split[0];
        User user=new User(name.getText(),phonenum.getText(),email.getText(), Birthdate.getValue(),passfieled.getText(),confirmpass.getText(),un);
        Connection con=connection.connect();
        PreparedStatement prs=con.prepareStatement("insert into cust values (?,?,?,?,?,?)");
        prs.setString(1,un);
        prs.setString(2,name.getText());
        prs.setDate(3, Date.valueOf(Birthdate.getValue()));
        prs.setString(4,email.getText());
        prs.setString(5,phonenum.getText());
        prs.setString(6,passfieled.getText());
        int z= prs.executeUpdate();
        con.commit();
        con.close();
        System.out.println("Done");
        backtolog(event);
    }
    @FXML
    void enter(MouseEvent event) {

    }

    @FXML
    void exit(MouseEvent event) {

    }

}
