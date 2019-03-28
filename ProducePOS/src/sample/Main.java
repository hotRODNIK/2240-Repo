package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage = new Stage();
    public void start() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 450, 375));
        primaryStage.show();
    }

    static void killProcess(){
        primaryStage.hide();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
