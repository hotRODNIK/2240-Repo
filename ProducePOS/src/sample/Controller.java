package sample;

import javafx.fxml.FXML;

public class Controller {
    @FXML
    void handleLoginExit(){
        System.exit(0);
    }

    @FXML void handleLogin() throws Exception {
        //Main.killProcess();
        new StartMain().start();
    }

    @FXML void handleMainScreenExit(){
        StartMain.killProcess();
    }

    @FXML void handleSaleScreen()throws Exception{
        new StartSale().start();
        StartMain.killProcess();
    }


}