package sample;

import javafx.fxml.FXML;

public class Controller {
    @FXML
    void handleLoginExit(){
        System.exit(0);
    }

    @FXML void handleLogin() throws Exception{
        new StartMain().start();
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

    @FXML void quitError(){
        StartError.killProcess();
    }

    @FXML void quitBadCode(){
        StartBadCode.killProcess();
    }

    @FXML void quitBadLogin(){
        StartBadLogin.killProcess();
    }
}