package edu.najah;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddService  implements Initializable {


    @FXML
    private ComboBox<Department> depCombo;
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
    void btnAddPersonClicked(ActionEvent event)throws SQLException {
        Connection connection= edu.najah.connection.connect();
        PreparedStatement prs=connection.prepareStatement("insert into service values (incem.nextval,?,?,?,?)");

        prs.setString(1,tfName.getText());
        prs.setInt(2,Integer.parseInt(tfDur.getText()));
        prs.setInt(3,Integer.parseInt(pr.getText()));
        prs.setInt(4,depCombo.getSelectionModel().getSelectedItem().getNum());
        int z= prs.executeUpdate();
        connection.commit();
        connection.close();
        System.out.println("Done");

        closeStage(event);

    }

    public void setAppMainObservableList(ObservableList<Serv> tvObservableList) {
this.appMainObservableList=tvObservableList;
        }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       depCombo.setItems(connection.getDepartment());
    }


}
