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
        // Get username and password
        String user = userIn.getText();
        String pass = passIn.getText();
        boolean login = SystemLogic.login(user, pass); // returns true if found, false if not
        if (login) {
            // If the login is found, let the user have access to the system
            new StartMain().start();
            userIn.clear();
            passIn.clear();
        }
        else {
            // Else, notify the user they are not allowed access
            new StartBadLogin().start();
            userIn.clear();
            passIn.clear();
        }
    }

    // When invoked, this handles the process involved in scanning an item
    @FXML public void handleScan() throws Exception{
        // Get the PLU
        String code = codeIn.getText();
        codeIn.clear();
        boolean isFound = SystemLogic.scan(code); // returns true if found, false if not
        if (isFound){
            // Get the prices, product name and calculate subtotal
            String info = SystemLogic.getName();
            double itemPrice = SystemLogic.getItemPrice();
            double sub = SystemLogic.calcSubTotal();

            // Output results and enable buttons
            productNames.setText(productNames.getText() + info + " " + itemPrice + "\n" );
            subTotal.setText(df.format(sub));
            pay.setDisable(false);
            voidAll.setDisable(false);
        }
        else
            new StartBadCode().start(); // Let the user know the code isn't in the system
    }

    // When invoked, this voids an entire sale
    @FXML public void voidAll(){
        // Reset all values, clear all outputs, and reset buttons
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
            // Disable the scan button, and display tax
            scan.setDisable(true);
            total.setText(df.format(SystemLogic.calcTotal()));
            tax.setText(df.format(SystemLogic.getTotal() - SystemLogic.getSubTotal()));
            if (paid.getText().equals(""))
                paid.setText("Please enter an amount"); // If no amount has been entered, prompt an amount
            else
                change.setText(SystemLogic.pay(Double.parseDouble(paid.getText()))); // Else display the change due
        }
        catch (NumberFormatException e){
            // Handle any invalid inputs
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
        // Returns true if found, false if not
        boolean found = SystemLogic.lookup(query.getText());
        if (found)
            out.setText(SystemLogic.getLookName() + " " + SystemLogic.getLookPrice()); // If found, display product info
        else
            out.setText("Not in the system"); // Else, notify the user their query wasn't found
    }
}