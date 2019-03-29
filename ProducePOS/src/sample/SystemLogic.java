package sample;

import java.sql.*;

public class SystemLogic {
    private double total, subTotal, itemPrice;

    private double calcTax(){
        return subTotal * 0.13;
    }

    static boolean login(String userID, String pass) throws Exception {
        boolean idNotFound = false, passNotFound = false, canLogin = false;
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Nick\\Desktop\\2240-Repo\\ProducePOS\\src\\sample\\EmployeeBase.db");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM empInfo");
            while(r.next() && !idNotFound && !passNotFound){
                String id = r.getString("Login");
                String passWord = r.getString("Pass");
                if (id.equals(userID) && pass.equals(passWord)){
                    idNotFound = true;
                    passNotFound = true;
                    canLogin = true;
                }
            }
            s.close();
            c.close();

        }
        catch (SQLException e){
            new StartError().start();
        }
        return canLogin;
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