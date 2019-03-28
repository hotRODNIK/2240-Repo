package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartReturn {
    private static Stage primaryStage = new Stage();
    void start() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ReturnScreen.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 375, 375));
        primaryStage.show();
    }

    static void killProcess(){
        primaryStage.close();
    }
}
