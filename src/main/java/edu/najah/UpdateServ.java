package edu.najah;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateServ {

    @FXML
    private TextField pr;

    @FXML
    private TextField tfDur;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfName1;

    @FXML
    void done(ActionEvent event) throws SQLException {
        int dur=Integer.parseInt(tfDur.getText());
        int price=Integer.parseInt(pr.getText());
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ruba", "123");
        Statement statement = con.createStatement();
        String q="update service set sname='"+tfName.getText()+"',durat='"+dur+"',price=' "+price+" ' where sid='"+Integer.parseInt(tfName1.getText())+"'" ;
        statement.executeUpdate(q);

        con.commit();
        con.close();

        System.out.println("Done");
        closeStage(event);

    }

    @FXML
    void enter() {

    }
    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    @FXML
    void exit() {

    }
    public void setText(Serv serv){
        tfName1.setText(String.valueOf(serv.getSerNum()));
        tfName.setText(serv.getA());
        tfDur.setText(String.valueOf(serv.getSerDur()));
        pr.setText(String.valueOf(serv.getB()));
    }

}
