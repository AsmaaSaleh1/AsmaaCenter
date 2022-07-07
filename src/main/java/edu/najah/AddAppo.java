package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class AddAppo {

    @FXML
    private AnchorPane AnAddApp;

    @FXML
    private DatePicker AppoDate;

    @FXML
    private GridPane appGrid;

    @FXML
    private TableView<?> appTable;

    @FXML
    private ComboBox<?> depCombo;

    @FXML
    private Button invoiceB;

    @FXML
    private ComboBox<?> serviceCombo;

    @FXML
    private TableColumn<?, ?> setApp;

    @FXML
    private TableColumn<?, ?> time;

    @FXML
    private TextField timeNeeded;

    @FXML
    void showPrice(ActionEvent event) {

    }

}
