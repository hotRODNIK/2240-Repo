package sample;

import java.sql.*;
import java.text.DecimalFormat;

class SystemLogic {
    // Fields which hold monetary amounts and product names
    private static double total, subTotal, itemPrice;
    private static String name, lookName, lookPrice;

    // Returns product name for PLU Lookup
    static String getLookName(){ return lookName; }

    // Returns item price for PLU Lookup
    static String getLookPrice(){
        return lookPrice;
    }

    // Returns the total amount owed
    static double getTotal(){
        return total;
    }

    // Returns the subtotal
    static double getSubTotal(){
        return subTotal;
    }

    // Returns the item name for Sale Mode
    static String getName() {
        return name;
    }

    // Returns the item price for Sale Mode
    static double getItemPrice() {
        return itemPrice;
    }

    // Sets every field back to zero or to null
    static void voidAll(){
        // Reset all values
        total = 0.0;
        subTotal = 0.0;
        subTotal = 0.0;
        itemPrice = 0.0;
        name = null;
    }

    // Calculates tax, is called within calcTotal
    private static double calcTax(){
        return subTotal * 0.13;
    }

    // Login logic
    static boolean login(String userID, String pass) throws Exception {
        // Holds whether or not the user can login
        boolean canLogin = false;
        try{
            // Connect to the database, and create a new query
            Connection c = DriverManager.getConnection("jdbc:sqlite:EmployeeBase.db");
            Statement s = c.createStatement();

            // Search the database to see if the userID and password are valid
            ResultSet r = s.executeQuery("SELECT * FROM empInfo");
            while(r.next() && !canLogin){
                String id = r.getString("Login");
                String passWord = r.getString("Pass");
                if (id.equals(userID) && pass.equals(passWord)){
                    canLogin = true;
                }
            }
            // Clean up when finished
            s.close();
            c.close();
        }
        catch (SQLException e){
            // Handle any SQL errors
            new StartError().start();
        }
        return canLogin;
    }

    // Scan logic
    static boolean scan(String code) throws Exception {
        // Holds whether or not the code is valid
        boolean isFound = false;
        try{
            // Connect to the database
            Connection c = DriverManager.getConnection("jdbc:sqlite:ProductBase.db");
            Statement s = c.createStatement();

            // Search the database to see if the code is in the system
            ResultSet r = s.executeQuery("SELECT * FROM pluTable");
            while(r.next() && !isFound){
                name = r.getString("prodName");
                String prodCode= r.getString("prodCode");
                itemPrice = Double.parseDouble(r.getString("price"));
                if (prodCode.equals(code)){
                    isFound = true;
                }
            }
            // Clean up when finished
            s.close();
            c.close();
        }
        catch (SQLException e){
            // Handle any SQL errors
            new StartError().start();
        }
        return isFound;
    }

    // Calculate the subtotal
    static double calcSubTotal(){
        subTotal += itemPrice;
        return subTotal;
    }

    // Calculate the total
    static double calcTotal(){
        total = subTotal + calcTax();
        return total;
    }

    // Logic of payment
    static String pay(double amt){
        DecimalFormat df = new DecimalFormat("#.##");
        total = Double.parseDouble(df.format(total));
        if (amt > total){
            // If the amount paid is more than the total, calculate change due
            double change = amt - total;
            return df.format(change);
        }
        else if (amt == total) // If amount and total are the same, change due is $0.00
            return "0.00";
        else
            return "Really!"; // If amount paid is less than total, let the user know
    }

    // Lookup logic
    static boolean lookup(String query) throws Exception {
        // Holds if the lookup query returned a value
        boolean isFound = false;
        try{
            // Connect to the database and create a statement
            Connection c = DriverManager.getConnection("jdbc:sqlite:ProductBase.db");
            Statement s = c.createStatement();

            // Search the database to see if the PLU exists
            ResultSet r = s.executeQuery("SELECT * FROM pluTable");
            while(r.next() && !isFound){
                lookName = r.getString("prodName");
                String prodCode= r.getString("prodCode");
                lookPrice = r.getString("price");
                if (prodCode.equals(query)){
                    isFound = true;
                }
            }
            // Cleanup when finished
            s.close();
            c.close();
        }
        catch (SQLException e){
            // Handle any SQL errors
            new StartError().start();
        }
        return isFound;
    }
}