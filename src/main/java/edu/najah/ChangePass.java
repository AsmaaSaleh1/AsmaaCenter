package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePass {

    @FXML
    private TextField confPass;

    @FXML
    private TextField curPass;

    @FXML
    private TextField newPass;
private User user;

    public void setUser(User user) {
        this.user = user;
    }
private String pass;
    @FXML
    void done(ActionEvent event) {
        if (user.getPass().equals(curPass.getText())) {
            if (newPass.getText().equals(confPass.getText())) {
                pass = newPass.getText();
                closeStage(event);
            }
        }

    }
    public String getPass(){
        return pass;
    }
        private void closeStage(ActionEvent event) {
            Node source = (Node)  event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }


