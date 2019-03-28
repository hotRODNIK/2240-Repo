package sample;

import java.sql.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Controller {
    @FXML
    void handleLoginExit(){
        System.exit(0);
    }

    @FXML void handleLogin() throws Exception {
        new StartMain().start();
        new StartBadLogin().start();
        new StartBadCode().start();
        new StartError().start();
    }

    @FXML void handleMainScreenExit(){
        StartMain.killProcess();
    }

    @FXML void handleSaleScreen()throws Exception{
        new StartSale().start();
        StartMain.killProcess();
    }

    @FXML void handleToMain()throws Exception{
        StartSale.killProcess();
        new StartMain().start();
    }

    @FXML void launchReturn() throws Exception{
        new StartReturn().start();
        StartMain.killProcess();
    }

    @FXML void quitReturn() throws Exception{
        new StartMain().start();
        StartReturn.killProcess();
    }
}