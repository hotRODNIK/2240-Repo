package sample;

import java.sql.*;

public class SystemLogic {
    private double total, subTotal, itemPrice;

    private double calcTax(){
        return subTotal * 0.13;
    }

    public boolean login(String userID, String pass){
        return true;
        // connect to database
    }

    public boolean scan(String code){
        return true;
        // set a value for itemPrice based off of if the code was found in db
    }

    public String calcSubTotal(){
        subTotal += itemPrice;
        return String.valueOf(subTotal);
    }

    public String calcTotal(){
        total = subTotal + calcTax();
        return String.valueOf(total);
    }

    public String pay(String amt){
        double tender = Double.parseDouble(amt);
        double change = tender - total;
        if (change == 0.00)
            return "Have a nice day";
        else
            return String.valueOf(change);
    }
}