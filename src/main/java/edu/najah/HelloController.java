package edu.najah;


import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    public HelloController() {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Duration duration = Duration.millis(2500);
        //Create new rotate transition
        RotateTransition rotateTransition = new RotateTransition(duration, logo);
        rotateTransition.setByAngle(360);

        rotateTransition.play();
        ScaleTransition scaleTransition = new ScaleTransition(duration,logo);
        //Set how much X should enlarge
        scaleTransition.setByX(0.5);
        //Set how much Y should
        scaleTransition.setByY(0.7);
        scaleTransition.play();

    }
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label pp;
    @FXML
    void toSignUp(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("last.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    @FXML
 void checkPass(ActionEvent event){
        if(usern.getText().isBlank()==false && pass1.getText().isBlank()==false){
            validate();
        }
         else if(usern.getText().isBlank()){
            pp.setText("Pleas enter a user name");
        }
         else{
            pp.setText("Pleas enter the password");
        }

}

public void validate() {
    ArrayList<String> un = new ArrayList<>();
    ArrayList<String> pas = new ArrayList<>();
    un.add("Ali");
    un.add("Mohammad");
    pas.add("123");
    pas.add("456");
    int flag=0;
    for (int i = 0; i < un.size(); i++) {
        if ((usern.getText().equals(un.get(i))) && (pass1.getText().equals(pas.get(i)))){
            pp.setText("Signed up successfully");
            flag=1;
            break;
        }
    }
    if(flag==0){
       pp.setText("Invalid pass word");
    }
}
}
