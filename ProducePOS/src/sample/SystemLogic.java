package sample;

import java.sql.*;

public class SystemLogic {
    private double total, subTotal, itemPrice;

    public String calcTax(){
        double tax;
        tax = subTotal * 0.13;
        total = subTotal + tax;
        return String.valueOf(tax);
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
        return String.valueOf(total);
        // if statement to deal with change
    }
}