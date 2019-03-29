package sample;

import java.sql.*;

public class SystemLogic {
    private double tax, total, subTotal, itemPrice;

    public double calcTax(){
        return tax;
        // calculate tax and set a value
    }

    public boolean login(String userID, String pass){
        return true;
    }

    public boolean scan(String code){
        return true;
        // set a value for itemprice based off of if the code was found in db
    }

    public String calcSubTotal(){
        subTotal += itemPrice;
        return String.valueOf(subTotal);
    }

    public String calcTotal(){
        return String.valueOf(total); // if statement to deal with change
    }
}
