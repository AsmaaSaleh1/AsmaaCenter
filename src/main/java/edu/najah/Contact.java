package edu.najah;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Contact {
    @FXML
    private TextField un;

    @FXML
    private TextArea txt;



    @FXML
    void enter() {

    }

    @FXML
    void exit() {

    }


    @FXML
    void sendMessage() {

        final String user = "rubasalon5@gmail.com";
        final String password = "wntxcpwbkocnjjdm";

        String to = "rubaqawareeq2@gmail.com";
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
            message1.setSubject("Verification code");
            message1.setText("From: "+un.getText()+"\n"+
                    txt.getText());
            Transport.send(message1);
            Notifications notifications = Notifications.create()
                    .text("Email sent successfully.We will reply to you as soon as possible")
                    .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))
                    .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
            notifications.show();


        } catch (Exception e) {
            Notifications notifications = Notifications.create()
                    .text("We apologize, your message was not sent. Please make sure your password is correct")
                    .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
            notifications.showWarning();

      /*  final String user="rubasalon5@gmail.com";
        final String password="wntxcpwbkocnjjdm";

        String to="rubaqawareeq2@gmail.com";
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
            message1.setText("From: "+user2.getName()+"\n"+
                    txt.getText());
            Transport.send(message1);
            Notifications notifications = Notifications.create()
                    .text("Email sent successfully.We will reply to you as soon as possible")
                    .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))
                    .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
            notifications.show();

        }
        catch (Exception e){
            Notifications notifications = Notifications.create()
                    .text("We apologize, your message was not sent. Please make sure your password is correct")
                    //.graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\y (2).png")))

                    .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(5));
            notifications.showWarning();
        }*/
        }
    }
    public void setUser(User user){
        un.setText(user.getName());
    }

}
/*

 */
