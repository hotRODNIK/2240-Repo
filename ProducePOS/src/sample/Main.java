package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 400, 330));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    // What u need to login... userid: bsmith password: lorem, alt check the db... any login/pass will work
    public static void main(String[] args) {
        launch(args);
    }
}