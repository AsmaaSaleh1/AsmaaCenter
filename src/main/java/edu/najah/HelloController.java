package edu.najah;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
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

            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {
                Duration duration = Duration.millis(2500);
        RotateTransition rotateTransition = new RotateTransition(duration, logo);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
        ScaleTransition scaleTransition = new ScaleTransition(duration,logo);
        scaleTransition.setByX(0.5);
        scaleTransition.setByY(0.7);
        scaleTransition.play();
    }
ArrayList<User>an=new ArrayList<>();

    @FXML
    private Label pp;
    @FXML
    void toSignUp() throws IOException {
        App.setRoot("signUp");
    }
    @FXML
    void checkPass() throws  IOException{
        if(!usern.getText().isBlank() && !pass1.getText().isBlank()){
            validate();
        }
        else if(usern.getText().isBlank()){
            pp.setText("Please enter a user name");
        }
        else{
            pp.setText("Please enter the password");
        }

    }
User user;
    int f=0;
    public void validate() throws  IOException{
        for(int i=0;i<an.size();i++){
        if ((an.get(i).getName().equals(usern.getText()))&&an.get(i).getPass().equals(pass1.getText())) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainInterface.fxml"));
            Parent root = loader.load();
            MainInterface m = loader.getController();
            m.setData(user);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            f=1;
            break;
        }
        }
        if(f==0){
            pp.setText("Incorrect password");
        }
    }


    @FXML
    void resetPass() throws IOException{
        App.setRoot("resetPass");
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

        }