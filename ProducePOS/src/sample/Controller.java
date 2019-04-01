package sample;

import javafx.fxml.FXML;
import java.text.DecimalFormat;

public class Controller {
    public javafx.scene.control.TextField userIn;
    public javafx.scene.control.TextField passIn;
    public javafx.scene.control.TextField codeIn;
    public javafx.scene.control.TextField subTotal;
    public javafx.scene.control.TextField tax;
    public javafx.scene.control.TextField total;
    public javafx.scene.control.TextField paid;
    public javafx.scene.control.TextField change;
    public javafx.scene.control.TextField query;
    public javafx.scene.control.TextArea productNames;
    public javafx.scene.control.Button pay;
    public javafx.scene.control.Button voidAll;
    public javafx.scene.control.TextArea out;
    private static DecimalFormat df = new DecimalFormat("#.##");

    @FXML void handleLoginExit(){
        System.exit(0);
    }

    @FXML void handleLogin() throws Exception{
        String user = userIn.getText();
        String pass = passIn.getText();
        boolean login = SystemLogic.login(user, pass);
        if (login) {
            new StartMain().start();
            userIn.clear();
            passIn.clear();
        }
        else {
            new StartBadLogin().start();
            userIn.clear();
            passIn.clear();
        }
    }

    @FXML void handleScan() throws Exception{
        String code = codeIn.getText();
        codeIn.clear();
        boolean isFound = SystemLogic.scan(code);
        if (isFound){
            String info = SystemLogic.getName();
            double itemPrice = SystemLogic.getItemPrice();
            double sub = SystemLogic.calcSubTotal();
            productNames.setText(productNames.getText() + info + " " + itemPrice + "\n" );
            subTotal.setText(df.format(sub));
            pay.setDisable(false);
            voidAll.setDisable(false);
        }
        else
            new StartBadCode().start();
    }

    @FXML void voidAll(){
        SystemLogic.voidAll();
        subTotal.clear();
        productNames.clear();
        change.clear();
        paid.clear();
        tax.clear();
        total.clear();
        pay.setDisable(true);
        voidAll.setDisable(true);
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

    @FXML void handleFinish() throws Exception { // click finish once to display amount owed, click again to calculate change
        try {

            total.setText(df.format(SystemLogic.calcTotal()));
            tax.setText(df.format(SystemLogic.getTotal() - SystemLogic.getSubTotal()));
            if (paid.getText().equals(""))
                paid.setText("Please enter an amount");
            else
                change.setText(SystemLogic.pay(Double.parseDouble(paid.getText())));
        }
        catch (NumberFormatException e){
            new StartError().start();
        }
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

    @FXML void launchLookup() throws Exception {
        StartMain.killProcess();
        new StartLookup().start();
    }

    @FXML void handleLookupExit()throws Exception{
        StartLookup.killProcess();
        new StartMain().start();
    }

    @FXML void handleQuery() throws Exception {
        boolean found = SystemLogic.lookup(query.getText());
        if (found)
            out.setText(SystemLogic.getLookName() + " " + SystemLogic.getLookPrice());
        else
            out.setText("Not in the system");
    }
}