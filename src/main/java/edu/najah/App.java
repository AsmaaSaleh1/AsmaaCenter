package edu.najah;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("hello-view"));
        stage.setScene(scene);
        String css= Objects.requireNonNull(this.getClass().getResource("css/app.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.show();
    }




    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/"+fxml + ".fxml") );
        return fxmlLoader.load();
    }
public static void sho(ActionEvent event,String fxml)throws IOException{
    FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/"+fxml+".fxml"));
    Parent root = loader.load();
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();    }

    public static void main(String[] args) {
        launch();
    }

}