package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

public class ResetPass {
    @FXML
    private TextField ver;
    @FXML
    private TextField email;

    @FXML
    private Pane emailpn;

    @FXML
    private PasswordField pass;
    @FXML
    private Pane respn;

    @FXML
    private Button sign2;
    @FXML
    private Button sign21;

    @FXML
    private Button sign211;

    @FXML
    private Pane verpn;

    @FXML
    void enter() {
        sign2.setScaleX(1.2);
        sign2.setScaleY(1.2);
        sign21.setScaleX(1.2);
        sign21.setScaleY(1.2);
        sign211.setScaleX(1.2);
        sign211.setScaleY(1.2);

    }

    @FXML
    void exit() {
        sign2.setScaleX(1);
        sign2.setScaleY(1);
        sign21.setScaleX(1);
        sign21.setScaleY(1);
        sign211.setScaleX(1);
        sign211.setScaleY(1);
    }

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    String code=getRandom();
    @FXML
    void toVar() {

        final String user="rubasalon5@gmail.com";
        final String password="wntxcpwbkocnjjdm";

        String to=email.getText();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });
        try {

            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(user));
            message1.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message1.setSubject("Verification code");
            message1.setText(code);
            Transport.send(message1);
            System.out.println("Done");
            emailpn.setVisible(false);
            verpn.setVisible(true);
            respn.setVisible(false);
        }
    catch (Exception e){
        Alert zipAlert = new Alert(Alert.AlertType.WARNING);
        zipAlert.setTitle("Incorrect email");
        zipAlert.setContentText("Please make sure that the entered email is valid");
        zipAlert.show();
    }


    }
    @FXML
    void tores() {
        if(ver.getText().equals(code)) {
            emailpn.setVisible(false);
            verpn.setVisible(false);
            respn.setVisible(true);

        }
        Alert zipAlert = new Alert(Alert.AlertType.WARNING);
        zipAlert.setTitle("Incorrect Code");
        zipAlert.setContentText("The entered code incorrect");
        zipAlert.show();
    }
    @FXML
    void backtolog(ActionEvent event) throws IOException {
App.sho(event,"hello-view");
    }

    public void back(ActionEvent event) throws SQLException, IOException {
        Connection con=connection.connect();
        assert con != null;
        Statement statement = con.createStatement();
        String q="update customer set CPASSWORD='"+pass.getText() +"' where email ='"+email.getText()+"'";
        statement.executeUpdate(q);

        con.commit();
        con.close();

        System.out.println("Done");
        Notifications notifications = Notifications.create()
                .text("The Password has been reset successfully")
                .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))
                .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(3));
        notifications.show();
        App.sho(event,"hello-view");

    }
}
