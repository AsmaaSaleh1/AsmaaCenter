package edu.najah;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
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
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/allAppo.fxml")));
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
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            a.setContentText("You are about log out, do you want to continue");
            Optional<ButtonType> op=a.showAndWait();
            if (op.get()==ButtonType.OK) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/hello-view.fxml"));
                Parent fxml = loader.load();
                App.sho(event,"hello-view");
            }
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




    private User user;


    public void setData(User user) {
        this.user = user;
        if (user.getName().equals("rubaqawareeq2")&&user.getPass().equals("ruba98")) {
            ab.setVisible(true);
            adap.setVisible(false);
            btAddApp.setVisible(false);
            btAccount.setVisible(false);
            accim.setVisible(false);
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
            ObservableList<Appo> tmp = connection.getAllApo();
            int c = 0,c1=0;

            for (Appo appo : tmp) {
                int day = appo.getAppoDate().getDayOfWeek().getValue() - LocalDate.now().getDayOfWeek().getValue();
                int month = appo.getAppoDate().getMonth().getValue() - LocalDate.now().getMonth().getValue();
                int year = appo.getAppoDate().getYear() - LocalDate.now().getYear();

                if ( LocalDate.now().equals(appo.getAppoDate())) {
                    c++;
                }
                if (year == 0 && month == 0 && day ==1) {
                    c1++;
                }
            }
            if(c>0){
                Notifications notifications = Notifications.create().title("  New Appointment")
                        .text("There are" + c + " appointments for today")
                        .darkStyle()
                        .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(10));
                notifications.showWarning();
            }
             if(c1>0){
                Notifications notifications = Notifications.create().title("  New Appointment")
                        .text("There are" + c1 + " appointments tomorrow")
                        .darkStyle()
                        .position(Pos.CENTER_RIGHT).hideAfter(Duration.seconds(10));
                notifications.showWarning();
            }

        } else {

            LocalDate l = LocalDate.now();
            LocalDate date = user.getBirthdate();
            Period diff = Period.between(l, date);
            int d = diff.getMonths();
            int m = diff.getDays();
            if (d == 0 && m == 0) {
                Notifications notifications = Notifications.create().title("   Happy birthday")
                        .text("Happy birth day "+user.getName()+" , which you all the best")
                        .graphic(new ImageView(new Image("C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\rsz_bd_2.png")))
                        .position(Pos.CENTER).hideAfter(Duration.seconds(10));
                notifications.show();
            }
        }

    }
    @FXML
    private Label addleble;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exit.setOnMouseClicked(event -> System.exit(0));
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
         addleble.setText("Hair Department");
         string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Hair Department' order by sid ";
        }
        if(event.getSource()==nail){
          string=  "Nail Department";
            addleble.setText("Nail Department");
            string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Nail Department' order by sid ";

        }
        if(event.getSource()==face){
            string="Face Department";
            addleble.setText("Face Department");
            string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Face Department' order by sid ";

        }
        if(event.getSource()==body){
            string="Body Department";
            addleble.setText("Body Department");
            string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Body Department' order by sid ";

        }
        if(event.getSource()==bride){
            string="Bride Department";
            addleble.setText("Bride Department");
            string2 = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum where dname='Bride Department' order by sid ";

        }

    }
    @FXML
    private JFXButton ab;
    @FXML
    void test() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba","123");
            JasperDesign jd = JRXmlLoader.load("t.jrxml");
            String q = "select dnumber,dname,count(eid) from department, employee WHERE  employee.dnum= dnumber group by dname, dnumber order by dnumber";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(q);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap<String, Object> param = new HashMap<>();
            ObservableList<Appo>appos= edu.najah.connection.getAllApo();
            int sum=0;
            for(Appo appo:appos){
                if(appo.getAppoDate().getMonth().getValue()==LocalDate.now().getMonthValue()){
                    sum+=appo.getTotal();
                }
            }
            param.put("month",String.valueOf(edu.najah.connection.getSrevices().size()));
            param.put("n",String.valueOf(edu.najah.connection.getAllApo().size()));
            param.put("e",String.valueOf(edu.najah.connection.getEmployee().size()));
            param.put("p",String.valueOf(sum));
            JasperPrint jp = JasperFillManager.fillReport(jr, param, connection);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("You Dont Select Appointment");
            alert.setHeaderText(null);
            alert.setContentText("Please select appointment to show the invoice ");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

            alert.showAndWait();
        }

    }
    }

