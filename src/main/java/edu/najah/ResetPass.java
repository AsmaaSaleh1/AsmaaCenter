package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
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
    private PasswordField pass1;

    @FXML
    private Pane respn;

    @FXML
    private Button sign2;
    @FXML
    private Button back;

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
        }
    catch (Exception e){
        System.out.println("Error");
    }


        emailpn.setVisible(false);
        verpn.setVisible(true);
        respn.setVisible(false);
    }
    @FXML
    void tores() {
        if(ver.getText().equals(code)) {
            emailpn.setVisible(false);
            verpn.setVisible(false);
            respn.setVisible(true);
        }
        else System.out.println("Incorrect code");
    }
    @FXML
    void backtolog(ActionEvent event) throws IOException {
App.sho(event,"hello-view");
    }

    public void back(ActionEvent event)throws IOException {
        App.sho(event,"hello-view");
    }
}
