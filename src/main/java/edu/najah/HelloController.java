package edu.najah;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

        public class HelloController implements Initializable {

            @FXML
            private Label fp;

            @FXML
            private Button frgPass;


            @FXML
            private Label lb;

            @FXML
            private Button login;

            @FXML
            private ImageView logo;

            @FXML
            private ImageView pass;

            @FXML
            private PasswordField pass1;

            @FXML
            private Button signup;

            @FXML
            private ImageView un;

            @FXML
            private TextField usern;
            @FXML
            private DatePicker Birthdate;

            @FXML
            private PasswordField confirmpass;

            @FXML
            private TextField email;


            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {
                an.add(new User("Manal","057547878","dfgh",LocalDate.now(), "123","123"));
                Duration duration = Duration.millis(2500);
        RotateTransition rotateTransition = new RotateTransition(duration, logo);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
        ScaleTransition scaleTransition = new ScaleTransition(duration,logo);
        scaleTransition.setByX(0.5);
        scaleTransition.setByY(0.5);
        scaleTransition.play();
    }
ArrayList<User>an=new ArrayList<>();

    @FXML
    private Label pp;
            @FXML
            private AnchorPane p1;

            @FXML
            private AnchorPane p2;

            @FXML
            private AnchorPane p3;

            @FXML
            private AnchorPane pane;
    @FXML
    void toSignUp(ActionEvent event)  {
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), p1);
        translateTransition1.setByX(+600);
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5), pane);
        translateTransition2.setByX(-400);
        translateTransition1.play();
        translateTransition2.play();
        p2.setVisible(false);
        p3.setVisible(true);

    }
    @FXML
    void checkPass(ActionEvent event) throws  IOException{

        if(usern.getText().equals("Admin")&&pass1.getText().equals("123")){
            User us=new User("Admin","","",LocalDate.now(),"123","","");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainInterface.fxml"));
            Parent root = loader.load();
            MainInterface m = loader.getController();
            m.setData(us);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            f=1;
            stage.show();
        }
else {

            if (!usern.getText().isBlank() && !pass1.getText().isBlank()) {
                validate(event);
            } else if (usern.getText().isBlank()) {
                pp.setText("Please enter a user name");
            } else {
                pp.setText("Please enter the password");
            }
        }
    }
User user;
    int f=0;
    public void validate(ActionEvent event) throws  IOException{
        ObservableList<User> ob=connection.getCustomer();
        Stage stage;
        for(User user1:ob){
        if ((user1.getUsername().equals(usern.getText()))&&user1.getPass().equals(pass1.getText())) {
            user=user1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainInterface.fxml"));
            Parent root = loader.load();
            MainInterface m = loader.getController();
            m.setData(user);
             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            f=1;
            stage.show();
            System.out.println("Done");
        }
        }
        if(f==0){
            pp.setText("Incorrect password");
        }

    }


    @FXML
    void resetPass(ActionEvent event) throws IOException{
     App.sho(event,"resetPass");
       }
    public void saveData(User user){
        this.user=user;
an.add(user);
    }
            @FXML
            void enter() {
                login.setScaleX(1.2);
                login.setScaleY(1.2);
            }

            @FXML
            void exit() {
                login.setScaleX(1);
                login.setScaleY(1);
            }

            public void backtolog(ActionEvent event) {
                TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), p1);
                translateTransition1.setByX(-600);
                TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5), pane);
                translateTransition2.setByX(+400);
                translateTransition1.play();
                translateTransition2.play();
                p2.setVisible(true);
                p3.setVisible(false);
            }

            public void createAcc(ActionEvent event) throws SQLException {
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
            private TextField name;
            @FXML
            private TextField phonenum;
            @FXML
            private TextField passfieled;
        }