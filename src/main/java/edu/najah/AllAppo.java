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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
    void deleteEmp() throws SQLException{
        int y=t.getSelectionModel().getSelectedItem().getNum();
        Connection con=connection.connect();
        assert con != null;
        Statement statement = con.createStatement();
        String q="delete from Appo  where apponum="+y ;
        statement.executeUpdate(q);
        con.commit();
        ob=connection.getAllApo();
        t.setItems(ob);
        t.refresh();
        System.out.println("Done");
        con.close();

        totNum.setText(String.valueOf(t.getItems().size()));
        filter();
    }

    @FXML
    void enter() {

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
    void addAppo()throws IOException {
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
    void addCust()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/addCust.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void getApp() {
        ob=FXCollections.observableArrayList();

     ob=connection.getAllApo();
     t.setItems(ob);
     t.refresh();
    System.out.println(t.getItems().size());
     past.setSelected(false);
     bd.setValue(null);
        totNum.setText(String.valueOf(t.getItems().size()));
filter();

    }
    @FXML
    void rowSelected() throws IOException, SQLException {
Appo appo=t.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/feedBack.fxml"));
        Parent parent = fxmlLoader.load();
        FeedBack f=fxmlLoader.getController();
        f.setAppo(appo);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void appInDate() {
        if(bd.getValue()==null){
            ob=connection.getAllApo();
            t.setItems(ob);
        }
        else {
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

    public  void filter(){
        FilteredList<Appo> filter = new FilteredList<>(ob, e -> true);
        att.textProperty().

                addListener((observable, oldValue, newValue)->

                        filter.setPredicate(emp -> {
                            if (newValue.isEmpty()) {
                                return true;
                            }
                            String st=newValue.toLowerCase();

                            if (emp.getUn().toLowerCase().contains(st)) {
                                return true;
                            }
                            if (emp.getAppoDate().toString().toLowerCase().contains(st)) {
                                return true;
                            }
                            else return emp.getTime().toString().contains(st);
                        }));
        SortedList<Appo> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(t.comparatorProperty());
        t.setItems(sort);
    }
}
