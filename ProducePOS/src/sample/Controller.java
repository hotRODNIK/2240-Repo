package sample;

import javafx.fxml.FXML;
import java.text.DecimalFormat;

public class Controller {
    // Fields which correspond to objects on the GUI or decimal formatting,
    // which are referenced to display output, get input or change properties
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
    public javafx.scene.control.TextArea out;
    public javafx.scene.control.Button pay;
    public javafx.scene.control.Button voidAll;
    public javafx.scene.control.Button scan;
    private static DecimalFormat df = new DecimalFormat("#.##");

    // When invoked, this closes the entire system
    @FXML public void handleLoginExit(){
        System.exit(0);
    }

    // When invoked, this logs a user into the system
    @FXML public void handleLogin() throws Exception{
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

    // When invoked, this handles the process involved in scanning an item
    @FXML public void handleScan() throws Exception{
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

    // When invoked, this voids an entire sale
    @FXML public void voidAll(){
        SystemLogic.voidAll();
        subTotal.clear();
        productNames.clear();
        change.clear();
        paid.clear();
        tax.clear();
        total.clear();
        pay.setDisable(true);
        voidAll.setDisable(true);
        scan.setDisable(false);
    }

    // Handles closure of the Main Screen
    @FXML public void handleMainScreenExit(){
        StartMain.killProcess();
    }

    // Handles the transition from the Main Screen to the Sale Screen
    @FXML public void handleSaleScreen()throws Exception{
        new StartSale().start();
        StartMain.killProcess();
    }

    // Handles transition from the Sale Screen to the Main Screen
    @FXML public void handleToMain()throws Exception{
        StartSale.killProcess();
        new StartMain().start();
    }

    // Handles everything involved in calculating amount owing, change and amount paid
    @FXML public void handleFinish() throws Exception{ // click finish once to display amount owed, click again to calculate change
        try {
            scan.setDisable(true);
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

    // Handles closure of the error form
    @FXML public void quitError(){ StartError.killProcess(); }

    // Handles closure of the bad PLU form
    @FXML public void quitBadCode(){
        StartBadCode.killProcess();
    }

    // Handles closure of the bad login form
    @FXML public void quitBadLogin(){
        StartBadLogin.killProcess();
    }

    // Handles transition from Main Screen to PLU Lookup
    @FXML public void launchLookup() throws Exception {
        StartMain.killProcess();
        new StartLookup().start();
    }

    // Handles closure of the PLU Lookup window
    @FXML public void handleLookupExit()throws Exception{
        StartLookup.killProcess();
        new StartMain().start();
    }

    // Handles everything involved in a PLU Lookup query
    @FXML public void handleQuery() throws Exception {
        boolean found = SystemLogic.lookup(query.getText());
        if (found)
            out.setText(SystemLogic.getLookName() + " " + SystemLogic.getLookPrice());
        else
            out.setText("Not in the system");
    }
}