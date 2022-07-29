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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class Service implements Initializable {

    @FXML
    private TableColumn<?, ?> a;
    @FXML
    private TableColumn<Serv, Department> d1;

    @FXML
    private TableColumn<?, ?> b;

    @FXML
    private TableColumn<?, ?> c;

    @FXML
    private TableColumn<?, ?> d;


    @FXML
    private TextField search;

    @FXML
    private TableView<Serv> t;
    private ObservableList<Serv> tvObservableList = FXCollections.observableArrayList();


    @FXML
    private Text totNum;
    @FXML
    private ComboBox<Department> depbox;

    public Service() {
    }

    @FXML
    void showInDep() {
tvObservableList=connection.getSrevices();
t.setItems(tvObservableList);
t.refresh();
       tmp=connection.getSrevices();
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
        tmp.clear();
        totNum.setText(String.valueOf(t.getItems().size()));
        filter();
    }
    @FXML
    void refresh() {
        tvObservableList=FXCollections.observableArrayList();
Connection con=connection.connect();
        try {
            assert con != null;
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
           e.printStackTrace();
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
        tmp.addAll(tvObservableList);
        t.setItems(tvObservableList);
totNum.setText(String.valueOf(t.getItems().size()));

filter();

    }

    @FXML
    void addServ() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/addService.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        tvObservableList=connection.getSrevices();
        t.setItems(tvObservableList);
        t.refresh();
        filter();
    }


    @FXML
    void enter() {

    }
    @FXML
    void enter1() {

    }
    @FXML
    void exit1() {
    }
    @FXML
    void exit() {
    }

    @FXML
    private Pane pn;
    @FXML
    private ImageView imslide;
    @FXML
    void removeServ()throws SQLException {
        int y = t.getSelectionModel().getSelectedItem().getSerNum();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.setContentText("Are you sure you want to delete this customer?");
        Optional<ButtonType> op = a.showAndWait();
        if (op.get() == ButtonType.OK) {
            Connection con = connection.connect();
            assert con != null;
            Statement statement = con.createStatement();
            String q = "delete from service  where sid=" + y;
            statement.executeUpdate(q);
            con.commit();
            con.close();
            tvObservableList = connection.getSrevices();
            t.setItems(tvObservableList);
            t.refresh();
            System.out.println("Done");
            filter();
        }
    }

    @FXML
    private ImageView ref;
public void setUser(User user){

    if(user.getName().equals("rubaqawareeq2")){
         pn.setVisible(true);
         depbox.setVisible(true);

        }

}
    public  void filter(){
        FilteredList<Serv> filter = new FilteredList<>(tvObservableList, e -> true);
        search.textProperty().

                addListener((observable, oldValue, newValue)->

                        filter.setPredicate(service -> {
                            if (newValue.isEmpty()) {
                                return true;
                            }
                            try {
                                String st = newValue.toLowerCase();
                                if (service.getA().toLowerCase().contains(st)) {
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
                            catch (NumberFormatException ignored){

                            }

                            return false;
                        }));
        SortedList<Serv> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(t.comparatorProperty());
        t.setItems(sort);
    }

public void setLable(String s,String x)throws SQLException{
    imslide.setVisible(true);
    if(s.equals("Nail Department")) {
        InnerClass innerClass = new InnerClass(nailImages);
        innerClass.start();
    }
    if(s.equals("Hair Department")){
        InnerClass innerClass = new InnerClass(hairImages);
        innerClass.start();
    }
    showSer(x);
    filter();
}
    @FXML
    void updateSer()throws IOException {
        try {

            Serv serv = t.getSelectionModel().getSelectedItem();
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
            tvObservableList = connection.getSrevices();
            t.setItems(tvObservableList);
            t.refresh();
            filter();
        }
        catch (Exception e){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Warning");
            a.setContentText("Please select the row you want to update");
            a.showAndWait();
        }
    }

    private void showSer(String st)throws SQLException{

    tvObservableList=FXCollections.observableArrayList();
        Connection con=connection.connect();
        assert con != null;
        Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(st);
            while (rs.next()) {
                tvObservableList.add(new Serv(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),new Department(rs.getInt(5), rs.getString(6))));
            }
            t.setItems(tvObservableList);
            t.refresh();
            con.commit();
            con.close();
filter();
    }
    String[] nailImages={"C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\rsz_4n1.jpg",
            "C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\rsz_1n2.jpg",
            "C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\rsz_1n3.jpg"};
    String[] hairImages={"C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\h1.jpg",
            "C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\h2.jpg",
            "C:\\Users\\Ruba\\IdeaProjects\\AsmaaCenter\\src\\main\\resources\\edu\\najah\\images\\h3.jpg"};
    class InnerClass extends Thread{
int i=0;
private final String[]images;
public InnerClass(String[]images){
    this.images=images;
}
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000);
                    if(i>images.length-1)
                        i=0;

                    imslide.setImage(new Image(images[i]));
                    i++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    }

