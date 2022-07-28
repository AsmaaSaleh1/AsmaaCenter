package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class AllAppo implements Initializable {

    @FXML
    private TableColumn<?, ?> appdate;

    @FXML
    private TableColumn<?, ?> apptime;

    @FXML
    private TextField att;


    @FXML
    private DatePicker bd;


    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> id1;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private RadioButton past;

    @FXML
    private TableColumn<?, ?> price;


    @FXML
    private Text totNum;

    @FXML
    private TableColumn<?, ?> totServ;


    @FXML
    private TableView<Appo> t;
    private ObservableList<Appo> ob = FXCollections.observableArrayList();

    @FXML
    void deleteEmp() throws SQLException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.setContentText("Are you sure you want to delete this customer?");
        Optional<ButtonType> op = a.showAndWait();
        if (op.get() == ButtonType.OK) {
        int y = t.getSelectionModel().getSelectedItem().getNum();
        Connection con = connection.connect();
        assert con != null;
        Statement statement = con.createStatement();
        String q = "delete from Appo  where apponum=" + y;
        statement.executeUpdate(q);
        con.commit();
        ob = connection.getAllApo();
        t.setItems(ob);
        t.refresh();
        System.out.println("Done");
        con.close();

        totNum.setText(String.valueOf(t.getItems().size()));
        filter();}
    }

    @FXML
    void enter() {

    }
    @FXML
    void update() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/updateAppo.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateAppo u=fxmlLoader.getController();
        u.setAppo(t.getSelectionModel().getSelectedItem());
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        t.refresh();
        totNum.setText(String.valueOf(t.getItems().size()));
    }


    @FXML
    void exit() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<>("un"));
        id1.setCellValueFactory(new PropertyValueFactory<>("num"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appdate.setCellValueFactory(new PropertyValueFactory<>("appoDate"));
        apptime.setCellValueFactory(new PropertyValueFactory<>("time"));
        totServ.setCellValueFactory(new PropertyValueFactory<>("numOfSer"));
        price.setCellValueFactory(new PropertyValueFactory<>("total"));
        ob = connection.getAllApo();
        t.setItems(ob);
        totNum.setText(String.valueOf(t.getItems().size()));
        filter();
    }

    @FXML
    void addAppo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/addAppointment.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        t.refresh();
        totNum.setText(String.valueOf(t.getItems().size()));
    }


    @FXML
    public void getApp() {
        ob = FXCollections.observableArrayList();

        ob = connection.getAllApo();
        t.setItems(ob);
        t.refresh();
        System.out.println(t.getItems().size());
        past.setSelected(false);
        bd.setValue(null);
        totNum.setText(String.valueOf(t.getItems().size()));
        filter();

    }



    @FXML
    void appInDate() {
        if (bd.getValue() == null) {
            ob = connection.getAllApo();
            t.setItems(ob);
        } else {
            ob.clear();
            ObservableList<Appo> tmp = connection.getAllApo();
            for (Appo appo : tmp) {
                if (appo.getAppoDate().equals(bd.getValue())) {
                    ob.add(appo);
                    t.refresh();
                }
            }
        }
        totNum.setText(String.valueOf(t.getItems().size()));
        filter();
    }

    public void filter() {
        FilteredList<Appo> filter = new FilteredList<>(ob, e -> true);
        att.textProperty().

                addListener((observable, oldValue, newValue) ->

                        filter.setPredicate(emp -> {
                            if (newValue.isEmpty()) {
                                return true;
                            }
                            String st = newValue.toLowerCase();

                            if (emp.getUn().toLowerCase().contains(st)) {
                                return true;
                            }
                            if (emp.getAppoDate().toString().toLowerCase().contains(st)) {
                                return true;
                            } else return emp.getTime().toString().contains(st);
                        }));
        SortedList<Appo> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(t.comparatorProperty());
        t.setItems(sort);
    }

    @FXML
    void invoice() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba","123");
Appo appo=t.getSelectionModel().getSelectedItem();
            JasperDesign jd = JRXmlLoader.load("finalPro.jrxml");
            String q = "select sid,sname,price,durat from r_s join service on service.sid=r_s.snum where apponum="+appo.getNum();
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(q);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap<String, Object> param = new HashMap<>();
            param.put("cname", appo.getUser().getName());
            param.put("totalPrice", String.valueOf(appo.getTotal()));
            JasperPrint jp = JasperFillManager.fillReport(jr, param, connection);
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("You Dont Select Appointment");
            alert.setHeaderText(null);
            alert.setContentText("Please select appointment to show the invoice ");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

            alert.showAndWait();
        }
    }
}