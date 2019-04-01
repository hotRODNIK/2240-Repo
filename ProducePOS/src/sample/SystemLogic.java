package sample;

import java.sql.*;
import java.text.DecimalFormat;

class SystemLogic {
    private static double total, subTotal, itemPrice;
    private static String name, lookName, lookPrice;

    static String getLookName(){ return lookName; }

    static String getLookPrice(){
        return lookPrice;
    }

    static double getTotal(){
        return total;
    }

    static double getSubTotal(){
        return subTotal;
    }

    static void voidAll(){
        total = 0.0;
        subTotal = 0.0;
        subTotal = 0.0;
        itemPrice = 0.0;
        name = null;
    }

    static String getName() {
        return name;
    }

    static double getItemPrice() {
        return itemPrice;
    }

    private static double calcTax(){
        return subTotal * 0.13;
    }

    static boolean login(String userID, String pass) throws Exception {
        boolean canLogin = false;
        try{
            // you will need to change this path based on what directory the database is saved in on your machine
            // i am not going to tech support you on this!!!
            Connection c = DriverManager.getConnection("jdbc:sqlite:EmployeeBase.db");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM empInfo");
            while(r.next() && !canLogin){
                String id = r.getString("Login");
                String passWord = r.getString("Pass");
                if (id.equals(userID) && pass.equals(passWord)){
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

    static boolean scan(String code) throws Exception {
        boolean isFound = false;
        try{
            // you will need to change this path based on what directory the database is saved in on your machine
            // i am not going to tech support you on this!!!
            Connection c = DriverManager.getConnection("jdbc:sqlite:ProductBase.db");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM pluTable");
            while(r.next() && !isFound){
                name = r.getString("prodName");
                String prodCode= r.getString("prodCode");
                itemPrice = Double.parseDouble(r.getString("price"));
                if (prodCode.equals(code)){
                    isFound = true;
                }
            }
            s.close();
            c.close();
        }
        catch (SQLException e){
            new StartError().start();
        }
        return isFound;
    }

    static double calcSubTotal(){
        subTotal += itemPrice;
        return subTotal;
    }

    static double calcTotal(){
        total = subTotal + calcTax();
        return total;
    }

    static String pay(double amt){
        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.parseDouble(df.format(total));
        if (amt > total){
            double change = amt - total;
            return df.format(change);
        }
        else if (amt == total)
            return "0.00";
        else
            return "Really!";
    }

    static boolean lookup(String query) throws Exception {
        boolean isFound = false;
        try{
            // you will need to change this path based on what directory the database is saved in on your machine
            // i am not going to tech support you on this!!!
            Connection c = DriverManager.getConnection("jdbc:sqlite:ProductBase.db");
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM pluTable");
            while(r.next() && !isFound){
                lookName = r.getString("prodName");
                String prodCode= r.getString("prodCode");
                lookPrice = r.getString("price");
                if (prodCode.equals(query)){
                    isFound = true;
                }
            }
            s.close();
            c.close();
        }
        catch (SQLException e){
            new StartError().start();
        }
        return isFound;
    }
}