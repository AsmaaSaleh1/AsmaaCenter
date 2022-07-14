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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private ObservableList<Serv> tvObservableList = FXCollections.observableArrayList(
            new Serv("Hair cut", "12", "45", "100",new Department(1,"Hair")),
            new Serv("Nail Design", "25", "66", "255",new Department(2,"Nail"))

    );


    @FXML
    private Text totNum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
   a.setCellValueFactory(new PropertyValueFactory<>("a"));
       b.setCellValueFactory(new PropertyValueFactory<>("serNum"));
        d.setCellValueFactory(new PropertyValueFactory<>("b"));
        c.setCellValueFactory(new PropertyValueFactory<>("serDur"));
        d1.setCellValueFactory(new PropertyValueFactory<>("department"));

        t.setItems(tvObservableList);
totNum.setText(String.valueOf(t.getItems().size()));
        t.getSelectionModel().selectFirst();
        FilteredList<Serv> filter = new FilteredList<>(tvObservableList, e -> true);
        search.textProperty().

                addListener((observable, oldValue, newValue)->

                {
                    filter.setPredicate(service -> {
                        if (newValue.isEmpty() || newValue == null) {
                            return true;
                        }
                        String st=newValue.toLowerCase();
                          if (service.getA().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }
                        if (service.getSerNum().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }
                        else if (service.getSerDur().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }

                        else if (service.getB().toLowerCase().indexOf(st)!=-1) {
                            return true;
                        }
                        else
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
    void removeServ(ActionEvent event) {
t.getItems().remove(t.getSelectionModel().getSelectedItem());
        totNum.setText(String.valueOf(t.getItems().size()));

    }
    private User user;
public void setUser(User user){
        this.user=user;
        if(user.getName().equals("Admin")&&user.getPass().equals("123")){
         pn.setVisible(true);

        }

}
    @FXML
    private Label serLable;
public void setLable(String s){
    serLable.setText(s);
}
}
