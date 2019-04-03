package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class StartSale {
    private static Stage primaryStage = new Stage();
    void start() throws Exception {
        // Load the FXML file, create a stage and show it
        Parent root = FXMLLoader.load(getClass().getResource("SaleScreen.fxml"));
        primaryStage.setTitle("ProducePOS:Sale");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // This method hides the stage when invoked
    static void killProcess(){
        primaryStage.close();
    }
}