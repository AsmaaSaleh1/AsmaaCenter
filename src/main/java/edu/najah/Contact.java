package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private PasswordField pass;
    @FXML
    private AnchorPane contact;

    @FXML
    private AnchorPane contact2;
    @FXML
    private TextArea txt;

    @FXML
    private Button sign2;

    @FXML
    void enter(MouseEvent event) {

    }
private User user2;
    @FXML
    void exit(MouseEvent event) {

    }
    @FXML
    void sendMessage(ActionEvent event) {
        final String user=user2.getEmail();
        final String password=pass.getText();
//qua@897145
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
            Notifications notifications = Notifications.create().title("   Happy birthday")
                    .text("Email sent successfully.We will reply to you as soon as possible")
                    .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\true.jpg")))
                    .position(Pos.BOTTOM_RIGHT).hideAfter(Duration.INDEFINITE);
            notifications.show();

        }
        catch (Exception e){
            System.out.println("Error");
        }
    }
    public void setUser(User user){
        this.user2=user;
        un.setText(user.getName());
    }

}
