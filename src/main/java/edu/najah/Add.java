package edu.najah;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Add {

    @FXML
    private AnchorPane a1;

    @FXML
    private Pane s1;

    @FXML
    private Pane s11;

    @FXML
    private Pane s111;

    @FXML
    private Pane s1111;

    @FXML
    private Button sign2;

    @FXML
    private Button sign21;
    @FXML
    private DialogPane dp;
    @FXML
    private Button sign211;
    private ObservableList<Person> appMainObservableList;
    @FXML
    private Button sign2111;
    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    public Add() {
    }

    @FXML
    void b1() {
        s1.setVisible(false);
        s11.setVisible(true);
        s111.setVisible(false);
        s1111.setVisible(false);
    }

    @FXML
    void b2() {
        s1.setVisible(false);
        s11.setVisible(false);
        s111.setVisible(false);
        s1111.setVisible(true);
    }

    @FXML
    void b3() {

    }

    @FXML
    void enter() {

    }

    @FXML
    void exit() {

    }

    @FXML
    void signup() {
        s1.setVisible(false);
        s11.setVisible(false);
        s111.setVisible(true);
        s1111.setVisible(false);
    }
    @FXML
    void btnAddPersonClicked(ActionEvent event) {
        System.out.println("btnAddPersonClicked");
        int id = Integer.valueOf(tfId.getText().trim());
        String name = tfName.getText().trim();
        int iAge = Integer.valueOf(tfAge.getText().trim());

        Person data = new Person(id, name, iAge);
        appMainObservableList.add(data);

        closeStage(event);
    }

    public void setAppMainObservableList(ObservableList<Person> tvObservableList) {
        this.appMainObservableList = tvObservableList;

    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
