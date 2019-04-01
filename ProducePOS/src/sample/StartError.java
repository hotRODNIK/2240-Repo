package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class StartError {
    private static Stage primaryStage = new Stage();

    void start() throws Exception {
        // Load the FXML file, create a stage and show it
        Parent root = FXMLLoader.load(getClass().getResource("ErrorWindow.fxml"));
        primaryStage.setTitle("Error");
        primaryStage.setScene(new Scene(root, 350, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // This method hides the stage when invoked
    static void killProcess(){
        primaryStage.close();
    }
}