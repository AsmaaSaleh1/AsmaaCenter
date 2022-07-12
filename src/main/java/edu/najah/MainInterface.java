package edu.najah;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class MainInterface implements Initializable {

    @FXML
    private Button btAccount;

    @FXML
    private Button btAddApp;

    @FXML
    private Button empl;

    @FXML
    private ImageView fm;
    @FXML
    private ImageView adap;
    @FXML
    private Button addSer;
    @FXML
    private Button btAppo;

    @FXML
    private Button btDEp;

    @FXML
    private Button btOut1;

    @FXML
    private Button btcontact;
    @FXML
    private AnchorPane an;
    @FXML
    private AnchorPane sliper;

    @FXML
    StackPane sp;
    private ObservableList<Serv> observableList;

    public StackPane getSp() {
        return sp;
    }

    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btDEp) {
            sp.getChildren().removeAll();
            sp.getChildren().setAll(an);

        }
        if (event.getSource() == btAddApp) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addAppo.fxml"));
            Parent fxml = loader.load();
            AddAppo a = loader.getController();
            a.addser(box);
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if (event.getSource() == btAppo) {
            Parent fxml = FXMLLoader.load(getClass().getResource("fxml/myAppo.fxml"));
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if (event.getSource() == btAccount) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/myAccount.fxml"));
            Parent fxml = loader.load();
            MyAccount m = loader.getController();
            m.setData(user);

            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if (event.getSource() == btcontact) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/contact.fxml"));
            Parent fxml = loader.load();
            Contact c = loader.getController();
            c.setUser(user);
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if (event.getSource() == btOut1) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/hello-view.fxml"));
            Parent root = loader.load();
            HelloController h = loader.getController();

            h.saveData(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        if (event.getSource() == empl) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/employee.fxml"));
            Parent root = loader.load();

           sp.getChildren().removeAll();
           sp.getChildren().addAll(root);
        }


    }


    public void showServices(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("fxml/service.fxml"));
        sp.getChildren().removeAll();
        sp.getChildren().setAll(fxml);
    }

    @FXML
    void addServ(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/addService.fxml"));
        Parent parent = fxmlLoader.load();
        AddService dialogController = fxmlLoader.<AddService>getController();
        dialogController.setAppMainObservableList(observableList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void enter() {
        addSer.setScaleX(1.2);
        addSer.setScaleY(1.2);
    }

    @FXML
    void exit() {
        addSer.setScaleX(1);
        addSer.setScaleY(1);
    }

    private User user;
    private ObservableList<Serv> box;

    public void setData(User user) {
        this.user = user;
        LocalDate l = LocalDate.now();
        LocalDate date = user.getBirthdate().getValue();
        Period diff = Period.between(l, date);


        int d = diff.getMonths();
        int m = diff.getDays();
        if (d == 0 && m == 0) {
            Notifications notifications = Notifications.create().title("   Happy birthday")
                    .text("Happy birth day , which you all the best")
                    .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\rsz_bd_2.png")))
                    .position(Pos.BOTTOM_CENTER).hideAfter(Duration.INDEFINITE);
            notifications.show();
        }
        if (user.getName().equals("Admin") && user.getPass().equals("123")) {
            adap.setVisible(false);
            btAddApp.setVisible(false);
            fm.setVisible(true);
            empl.setVisible(true);
        }


    }

    public void setServ(ObservableList<Serv> box) {
        this.box = box;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
