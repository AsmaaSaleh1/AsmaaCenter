package edu.najah;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddService  {

    @FXML
    private Button addImg;

    @FXML
    private Button addSer1;
    @FXML
    private ImageView img;
    @FXML
    void addImage(ActionEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().clear();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*"));
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            img.setImage(new Image(file.toURI().toString()));
        }
    }

    @FXML
    void enter(MouseEvent event) {

    }
    @FXML
    private TextField pr;

    @FXML
    private TextField tfDur;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;
    private ObservableList<Serv> appMainObservableList;


    @FXML
    void exit(MouseEvent event) {

    }


    @FXML
    void btnAddPersonClicked(ActionEvent event)throws IOException {

        Serv data = new Serv(tfName.getText().trim(),tfId.getText().trim(),tfDur.getText().trim(),pr.getText().trim(),"test");
        appMainObservableList.add(data);

        FXMLLoader fxml=new FXMLLoader(getClass().getResource("fxml/mainInterface.fxml"));
        Parent root=fxml.load();


        closeStage(event);

    }

    public void setAppMainObservableList(ObservableList<Serv> tvObservableList) {
this.appMainObservableList=tvObservableList;
        }
public ObservableList<Serv>getAppMainObservableList(){
        return appMainObservableList;
}



    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
