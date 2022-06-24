package edu.najah;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
        //Create new rotate transition
        RotateTransition rotateTransition = new RotateTransition(duration, logo);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
        ScaleTransition scaleTransition = new ScaleTransition(duration,logo);
        scaleTransition.setByX(0.5);
        scaleTransition.setByY(0.7);
        scaleTransition.play();
    }

    @FXML
    private Label pp;
    @FXML
    void toSignUp(ActionEvent event) throws IOException {
            App.setRoot("signUp");
    }
    @FXML
 void checkPass(ActionEvent event) throws  IOException{
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

public void validate() throws  IOException{
        ArrayList<String> un = new ArrayList<>();
    ArrayList<String> pas = new ArrayList<>();
    un.add("Ali");
    un.add("Mohammad");
    pas.add("123");
    pas.add("456");
    int flag=0;

for (int i = 0; i < un.size(); i++) {
        if ((usern.getText().equals("Ali")) && (pass1.getText().equals("123"))){
            App.setRoot("mainInterface");
            flag=1;
            break;
        }
    }
    if(flag==0){
       pp.setText("Incorrect password");
    }
}
    @FXML
    void resetPass(ActionEvent event) throws IOException{
App.setRoot("resetPass");
    }
}
