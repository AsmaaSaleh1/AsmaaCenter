package edu.najah;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

        public class HelloController implements Initializable {

            @FXML
            private Button login;

            @FXML
            private ImageView logo;


            @FXML
            private PasswordField pass1;

            @FXML
            private TextField usern;


            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {
                an.add(new User("Manal","057547878","dfgh",LocalDate.now(), "123","123"));
                Duration duration = Duration.millis(2500);
        RotateTransition rotateTransition = new RotateTransition(duration, logo);
        rotateTransition.setByAngle(360);
      //  rotateTransition.play();
        ScaleTransition scaleTransition = new ScaleTransition(duration,logo);
        scaleTransition.setByX(0.3);
        scaleTransition.setByY(0.3);
        scaleTransition.play();
        scaleTransition.setOnFinished(event -> {
            ScaleTransition scaleTransition2 = new ScaleTransition(duration,logo);
            scaleTransition2.setByX(-0.3);
            scaleTransition2.setByY(-0.3);
            scaleTransition2.play();
            scaleTransition2.setOnFinished(event1 -> scaleTransition.play());
        });

    }
ArrayList<User>an=new ArrayList<>();

    @FXML
    private Label pp;


    @FXML
    void toSignUp(ActionEvent event) throws IOException {
       App.sho(event,"signUp");

    }
            @FXML
            private ProgressIndicator pr;
    @FXML
    void checkPass(ActionEvent event)  {


            if (!usern.getText().isBlank() && !pass1.getText().isBlank()) {
                try {
                    validate(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (usern.getText().isBlank()) {
                pp.setText("Please enter a user name");
            } else {
                pp.setText("Please enter the password");
            }
        }


User user;
    int f=0;
    public void validate(ActionEvent event) throws  IOException{
        ObservableList<User> ob=connection.getCustomer();
        pr.setVisible(true);
        pr.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        RotateTransition r=new RotateTransition(Duration.seconds(2),pr);
        r.setRate(1);
        r.setCycleCount(1);
        r.setAutoReverse(false);
        r.setFromAngle(0);
        r.setToAngle(0);
        r.play();

        r.setOnFinished(event1 -> {

            if (usern.getText().equals("rubaqawareeq2") && pass1.getText().equals("ruba98")) {
                User u = new User("rubaqawareeq2", "ruba98");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainInterface.fxml"));
                Parent root;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                MainInterface m = loader.getController();
                m.setData(u);
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                f = 1;
                stage.show();
                System.out.println("Done");
            }

            else{
            pr.setVisible(false);
            Stage stage;
            for (User user1 : ob) {
                if ((user1.getUsername().equals(usern.getText())) && user1.getPass().equals(pass1.getText())) {
                    user = user1;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/mainInterface.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    MainInterface m = loader.getController();
                    m.setData(user);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    f = 1;
                    stage.show();
                    System.out.println("Done");
                }
            }
            }
            if (f == 0) {
                pp.setText("Incorrect password");
            }
        });
    }
private Emp emp;

    @FXML
    void resetPass(ActionEvent event) throws IOException{
     App.sho(event,"resetPass");
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