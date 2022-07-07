package edu.najah;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainInterface   {

    @FXML
    private Button btAccount;

    @FXML
    private Button btAddApp;

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

    public StackPane getSp() {
        return sp;
    }
    @FXML
    void handleClicks(ActionEvent event)throws IOException {
        if(event.getSource()==btDEp) {
            sp.getChildren().removeAll();
            sp.getChildren().setAll(an);

        }
        if(event.getSource()==btAddApp) {
            Parent fxml = FXMLLoader.load(getClass().getResource("fxml/addAppo.fxml"));
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if(event.getSource()==btAppo) {
            Parent fxml = FXMLLoader.load(getClass().getResource("fxml/myAppo.fxml"));
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if(event.getSource()==btAccount) {
            Parent fxml = FXMLLoader.load(getClass().getResource("fxml/myAccount.fxml"));
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if(event.getSource()==btcontact) {
            Parent fxml = FXMLLoader.load(getClass().getResource("fxml/contact.fxml"));
            sp.getChildren().removeAll();
            sp.getChildren().setAll(fxml);
        }
        if(event.getSource()==btOut1){
            App.setRoot("hello-view");
        }


    }


    public void showServices(MouseEvent event)throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("fxml/service.fxml"));
        sp.getChildren().removeAll();
        sp.getChildren().setAll(fxml);
    }
    private ObservableList<Serv> observableList;
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

    public void setData(User user) {

    }
}
