package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class StartBadCode {
    private static Stage primaryStage = new Stage();
    void start() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InvalidCode.fxml"));
        primaryStage.setTitle("Invalid Code");
        primaryStage.setScene(new Scene(root, 330, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    static void killProcess(){
        primaryStage.close();
    }
}