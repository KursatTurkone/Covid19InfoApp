package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setTitle("Covid19");
        primaryStage.setScene(new Scene(root, 1800, 1000));
        primaryStage.show();



    }



    public static void main(String[] args) {
        launch(args);
    }
}
