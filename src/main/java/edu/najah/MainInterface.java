package edu.najah;

import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class MainInterface implements Initializable {
    @FXML
    private Button body;
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im;
    @FXML
    private ImageView exit;
    @FXML
    private Button bride;
    @FXML
    private Button face;

    @FXML
    private Button hair;
    @FXML
    private Button nail;

    @FXML
    private Button btAccount;
    @FXML
    private ImageView conim;
    @FXML
    private ImageView serim;

    @FXML
    private AnchorPane drawerPane;
    @FXML
    private Button serv;
    @FXML
    private Button btAddApp;

    @FXML
    private Button empl;
    @FXML
    private ImageView accim;
    @FXML
    private ImageView fm;
    @FXML
    private ImageView adap;
    @FXML
    private Button btAppo1;
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
    private ImageView cusim;

    @FXML
    private Button cust;
    @FXML
    StackPane sp;



    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btDEp) {
            addleble.setText("Department");
            sp.getChildren().removeAll();
            sp.getChildren().setAll(an);

        }
        if (event.getSource() == btAddApp) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/addAppo.fxml"));
            addleble.setText("Add Appointment");
            Parent fxml = loader.load();
            AddAppo a = loader.getController();
            a.setUser(user);
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if (event.getSource() == btAppo) {
            addleble.setText("My Appointment");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/myAppo.fxml"));
            Parent fxml = loader.load();
            MyAppo myAppo=loader.getController();
            myAppo.setUser(user);
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if (event.getSource() == btAppo1) {
            addleble.setText("All Appointments");
            Parent fxml = FXMLLoader.load(getClass().getResource("fxml/allAppo.fxml"));
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if (event.getSource() == btAccount) {
            addleble.setText("My Account");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/myAccount.fxml"));
            Parent fxml = loader.load();
            MyAccount m = loader.getController();
            m.setData(user);

            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if (event.getSource() == btcontact) {
            addleble.setText("Contact Us");
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
            addleble.setText("Employee");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/employee.fxml"));
            Parent root = loader.load();

           sp.getChildren().removeAll();
           sp.getChildren().addAll(root);
        }
        if (event.getSource() == cust) {
            addleble.setText("Customer");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/customer.fxml"));
            Parent root = loader.load();
            sp.getChildren().removeAll();
            sp.getChildren().addAll(root);
        }
        if (event.getSource() == serv) {
            addleble.setText("Services");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/service.fxml"));
            Parent root = loader.load();
Service service=loader.getController();
service.setUser(user);
            sp.getChildren().removeAll();
            sp.getChildren().addAll(root);
        }

    }


    public void showServices(MouseEvent event) throws IOException, SQLException {
        showServiceInDep(event);
       FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxml/service.fxml"));
       Parent root=fxml.load();
        Service service=fxml.getController();
service.setLable(string,string2);
        sp.getChildren().removeAll();
        sp.getChildren().setAll(root);
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
        LocalDate date = user.getBirthdate();
        Period diff = Period.between(l, date);


        int d = diff.getMonths();
        int m = diff.getDays();
        if (d == 0 && m == 0) {
            Notifications notifications = Notifications.create().title("   Happy birthday")
                    .text("Happy birth day , which you all the best")
                    .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\rsz_bd_2.png")))
                    .position(Pos.CENTER).hideAfter(Duration.seconds(5));
            notifications.show();
        }
        if (user.getName().equals("Admin") && user.getPass().equals("123")) {
            adap.setVisible(false);
            btAddApp.setVisible(false);
            btAccount.setVisible(false);
           accim .setVisible(false);
            fm.setVisible(true);
            empl.setVisible(true);
            cusim.setVisible(true);
            cust.setVisible(true);
            conim.setVisible(false);
            btcontact.setVisible(false);
            serv.setVisible(true);
            serim.setVisible(true);
            btAppo.setVisible(false);
            btAppo1.setVisible(true);
        }


    }


    @FXML
    private Label addleble;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), drawerPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        im1.setOnMouseClicked(event -> {

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), drawerPane);
            translateTransition1.setByX(+600);
            im1.setVisible(false);
            im.setVisible(true);
            translateTransition1.play();
        });
        im.setOnMouseClicked(event -> {

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), drawerPane);
            translateTransition1.setByX(-600);
            im.setVisible(false);
            im1.setVisible(true);
            translateTransition1.play();
        });
    }
    private String string;
    private String string2;
    void showServiceInDep(MouseEvent event) {
        if(event.getSource()==hair){
            string="Hair Department";
         string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Hair Department' order by sid ";
        }
        if(event.getSource()==nail){
            string="Nail Department";
            string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Nail Department' order by sid ";

        }
        if(event.getSource()==face){
            string="Face Department";
            string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Face Department' order by sid ";

        }
        if(event.getSource()==body){
            string="Body Department";
            string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Body Department' order by sid ";

        }
        if(event.getSource()==bride){
            string="Bride Department";
            string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Bride Department' order by sid ";

        }

    }


}
