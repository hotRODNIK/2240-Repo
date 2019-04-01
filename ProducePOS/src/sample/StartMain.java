package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class StartMain {
    private static Stage primaryStage = new Stage();
    void start() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 343, 267));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    static void killProcess(){
        primaryStage.close();
    }
}