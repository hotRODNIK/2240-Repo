package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartBadCode {
    private static Stage primaryStage = new Stage();
    public void start() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InvalidCode.fxml"));
        primaryStage.setTitle("Invalid Code");
        primaryStage.setScene(new Scene(root, 330, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void killProcess(){
        primaryStage.close();
    }
}
