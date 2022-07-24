package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddService  implements Initializable {


    @FXML
    private ComboBox<Department> depCombo;



    @FXML
    void enter() {

    }
    @FXML
    private TextField pr;

    @FXML
    private TextField tfDur;



    @FXML
    private TextField tfName;


    @FXML
    void exit() {

    }


    @FXML
    void btnAddPersonClicked(ActionEvent event)throws SQLException {
        Connection connection= edu.najah.connection.connect();
        assert connection != null;
        PreparedStatement prs=connection.prepareStatement("insert into service values (incem.nextval,?,?,?,?)");

        prs.setString(1,tfName.getText());
        prs.setInt(2,Integer.parseInt(tfDur.getText()));
        prs.setInt(3,Integer.parseInt(pr.getText()));
        prs.setInt(4,depCombo.getSelectionModel().getSelectedItem().getNum());
         prs.executeUpdate();
        connection.commit();
        connection.close();
        System.out.println("Done");

        closeStage(event);

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
