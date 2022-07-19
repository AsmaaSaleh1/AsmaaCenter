package edu.najah;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Service implements Initializable {

    @FXML
    private TableColumn<?, ?> a;
    @FXML
    private TableColumn<Serv, Department> d1;
    @FXML
    private Button addSer;

    @FXML
    private TableColumn<?, ?> b;

    @FXML
    private TableColumn<?, ?> c;

    @FXML
    private TableColumn<?, ?> d;

    @FXML
    private GridPane g1;

    @FXML
    private TextField search;

    @FXML
    private TableView<Serv> t;
    private ObservableList<Serv> tvObservableList = FXCollections.observableArrayList();


    @FXML
    private Text totNum;
    @FXML
    private ComboBox<Department> depbox;
    @FXML
    void showInDep() {
t.refresh();
        if (tmp.size() == 0) {
            for (Serv serv : tvObservableList) {
                tmp.add(serv);
            }
        }
        tvObservableList.clear();
        System.out.println(tmp.size());
        for (Serv serv : tmp) {
            if (serv.getDepartment().getName().equals(depbox.getSelectionModel().getSelectedItem().getName())) {
                tvObservableList.add(serv);
                t.refresh();
            }
        }

        if(depbox.getSelectionModel().getSelectedItem().getName().equals("All"))
        {
            tvObservableList.clear();
            for (Serv serv : tmp) {
                tvObservableList.add(serv);
                t.refresh();
            }
        }
        totNum.setText(String.valueOf(t.getItems().size()));
    }
    @FXML
    void refresh(MouseEvent event) {
        tvObservableList=FXCollections.observableArrayList();
Connection con=connection.connect();
        try {
            Statement statement = con.createStatement();
            String q = "select sid ,sname,durat,price,dnum,dname from service join department on department.dnumber=dnum order by sid";
            ResultSet rs = statement.executeQuery(q);
            while (rs.next()) {
                tvObservableList.add(new Serv(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),new Department(rs.getInt(5), rs.getString(6))));
            }
            con.commit();
            con.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
t.setItems(tvObservableList);
t.refresh();
    }
ObservableList<Serv> tmp=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
   a.setCellValueFactory(new PropertyValueFactory<>("serNum"));
       b.setCellValueFactory(new PropertyValueFactory<>("a"));
        d.setCellValueFactory(new PropertyValueFactory<>("b"));
        c.setCellValueFactory(new PropertyValueFactory<>("serDur"));
        d1.setCellValueFactory(new PropertyValueFactory<>("department"));
        depbox.setItems(connection.getDepartment());
        depbox.getItems().add(new Department(0,"All"));
tvObservableList= connection.getSrevices();
for(Serv serv:tvObservableList){
    tmp.add(serv);
}
        t.setItems(tvObservableList);
totNum.setText(String.valueOf(t.getItems().size()));


     FilteredList<Serv> filter = new FilteredList<>(tvObservableList, e -> true);
        search.textProperty().

                addListener((observable, oldValue, newValue)->

                {
                    filter.setPredicate(service -> {
                        if (newValue.isEmpty() || newValue == null) {
                            return true;
                        }
                      try {
                          String st = newValue.toLowerCase();
                          if (service.getA().toLowerCase().indexOf(st) != -1) {
                              return true;
                          }
                          if (service.getSerNum() == Integer.parseInt(st)) {
                              return true;
                          } else if (service.getSerDur() == Integer.parseInt(st)) {
                              return true;
                          } else if (service.getB() == Integer.parseInt(st)) {
                              return true;
                          }
                      }
                      catch (NumberFormatException e){

                      }

                            return false;
                    });
                });
        SortedList<Serv> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(t.comparatorProperty());
        t.setItems(sort);
    }

    @FXML
    void addServ(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/addService.fxml"));
        Parent parent = fxmlLoader.load();
        AddService dialogController = fxmlLoader.<AddService>getController();
        dialogController.setAppMainObservableList(tvObservableList);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        MouseEvent event1=null;
        refresh(event1);
        t.refresh();
    }


    @FXML
    void enter() {
        addSer.setScaleX(1.2);
        addSer.setScaleY(1.2);
    }
    @FXML
    void enter1() {
        addSer1.setScaleX(1.2);
        addSer1.setScaleY(1.2);
    }
    @FXML
    void exit1() {
        addSer1.setScaleX(1);
        addSer1.setScaleY(1);
    }
    @FXML
    void exit() {
        addSer.setScaleX(1);
        addSer.setScaleY(1);

    }

    @FXML
    private Button addSer1;
    @FXML
    private Pane pn;

    @FXML
    void removeServ(ActionEvent event)throws SQLException {
        int y=t.getSelectionModel().getSelectedItem().getSerNum();
        Connection con=connection.connect();
        Statement statement = con.createStatement();
        String q="delete from service  where sid="+y ;
        statement.executeUpdate(q);
        con.commit();
        con.close();
        MouseEvent event1=null;
        refresh(event1);
        System.out.println("Done");



    }
    private User user;
    @FXML
    private ImageView ref;
public void setUser(User user){
        this.user=user;
        if(user.getName().equals("Admin")&&user.getPass().equals("123")){
         pn.setVisible(true);
         depbox.setVisible(true);
ref.setVisible(true);
        }

}
    @FXML
    private Label serLable;
public void setLable(String s,String x)throws SQLException{
    serLable.setText(s);
    showSer(x);
}
    @FXML
    void updateSer(ActionEvent event)throws IOException {
        Serv serv=t.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/updateServ.fxml"));
        Parent parent = fxmlLoader.load();
        UpdateServ dialogController = fxmlLoader.getController();
        dialogController.setText(serv);
        t.refresh();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        MouseEvent event1=null;
        refresh(event1);

    }
    private void showSer(String st)throws SQLException{

    tvObservableList=FXCollections.observableArrayList();
        Connection con=connection.connect();
        Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(st);
            while (rs.next()) {
                tvObservableList.add(new Serv(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),new Department(rs.getInt(5), rs.getString(6))));
            }
            t.setItems(tvObservableList);
            t.refresh();
            con.commit();
            con.close();

    }
    }

