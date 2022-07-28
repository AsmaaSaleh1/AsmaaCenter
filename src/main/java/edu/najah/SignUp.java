package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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
        try {

            if (!(email.getText().isBlank() || phonenum.getText().isBlank() || Birthdate.getValue() == null || name.getText().isBlank() || passfieled.getText().isBlank())) {
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
                sendEmail();
                System.out.println("Done");
                backtolog(event);
            } else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Not Completed");
                a.setContentText("Please fill all the field");
                a.showAndWait();
            }
        }
        catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Not Completed");
            a.setContentText("This Email is already used. Please try another one");
            a.showAndWait();
        }
    }
    @FXML
    void enter() {

    }
public void sendEmail() {
    final String user = "rubasalon5@gmail.com";
    final String password = "wntxcpwbkocnjjdm";
    String to = email.getText();
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.starttls.enable", "true");
    Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
    try {
        Message message1 = new MimeMessage(session);
        message1.setFrom(new InternetAddress(user));
        message1.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message1.setSubject("Sign Up");
        message1.setText("Hello "+name.getText()+"\n" +
                "You are logged in to Asmaa Beauty Center \nِ"+"Welcome to our application");
        Transport.send(message1);
        System.out.println("Send");

    } catch (Exception e) {
        Notifications notifications = Notifications.create()
                .text("We apologize, your message was not sent. Please make sure your Email is correct")
                .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
        notifications.showWarning();
    }
}
    @FXML
    void exit() {

    }

}
