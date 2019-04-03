package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class StartLookup {
    private static Stage primaryStage = new Stage();

    void start() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PLULookup.fxml"));
        primaryStage.setTitle("PLU Lookup");
        primaryStage.setScene(new Scene(root, 325, 300));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // This method hides the stage when invoked
    static void killProcess(){
        primaryStage.close();
    }
}