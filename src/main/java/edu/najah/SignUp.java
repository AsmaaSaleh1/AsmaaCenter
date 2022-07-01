package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp {

    public Button back;
    @FXML
    private DatePicker Birthdate;

    @FXML
    private PasswordField confirmpass;

    @FXML
    private TextField email;

    @FXML
    private ImageView i1;

    @FXML
    private ImageView i2;

    @FXML
    private ImageView i3;

    @FXML
    private ImageView i4;

    @FXML
    private ImageView i5;

    @FXML
    private TextField name;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField phonenum;

    @FXML
    private Button sign2;
    @FXML
    private Label lb;
    @FXML
    private Label lb2;

    @FXML
    void signup(ActionEvent event)throws IOException {
User user=new User(name.getText(),phonenum.getText(),email.getText(),Birthdate.getAccessibleText(),pass.getText(),confirmpass.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/hello-view.fxml"));
        root = loader.load();
        HelloController h=loader.getController();
        h.saveData(user);
        stage=new Stage();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private Parent root;
    private Stage stage=new Stage();
    private Scene scene;
    @FXML
    private Label pp;
    @FXML
    void backtolog()throws IOException {
      App.setRoot("hello-view");
    }
    @FXML
    void enter() {
        sign2.setScaleX(1.2);
        sign2.setScaleY(1.2);
    }

    @FXML
    void exit() {
        sign2.setScaleX(1);
        sign2.setScaleY(1);
    }



}
