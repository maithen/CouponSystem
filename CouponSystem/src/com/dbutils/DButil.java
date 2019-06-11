package com.dbutils;

import com.exceptions.ConnectionError;
import com.exceptions.DoesNotExistException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;

public class DButil {

    private static String conURL = "jdbc:mysql://localhost:3306/coupondb?&serverTimezone=Israel&autoReconnect=true";
    private static String conUSER = "company";
    private static String conPASS = "123123";



    public static Connection dbConnection()  {
        Connection myCon=null;

        try {
            myCon = DriverManager.getConnection(conURL,"matan","123123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myCon;
    }


    public static void createCompanyTable() {

        Statement myStmt = null;
        try {
            myStmt = dbConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql;


        sql = "create table if not exists Companies (" +
                "ID_ BIGINT NOT NULL, " +
                "company_name TEXT(50) NOT NULL, " +
                "Password TEXT NOT NULL, " +
                "Email TEXT NOT NULL,PRIMARY KEY (ID_),UNIQUE (company_name(50)));";

        try {
            myStmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Companies table successfully created, or has been already created.");


    }

    public static void createCustomerTable(){
        Statement myStmt = null;
        try {
            myStmt = dbConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;

        sql = "create table Customers (" +
                "ID_ BIGINT PRIMARY KEY NOT NULL, " +
                "Customer_name TEXT NOT NULL, " +
                "Password TEXT NOT NULL, UNIQUE(Customer_name(50)))";
        try {
            myStmt.executeUpdate(sql);
            System.out.println("Customers table successfully created.");

        } catch (SQLException sqlex) {
            System.out.println("Customers table already created.");
        }

    }

    public static void createCouponTable(){
        Statement myStmt = null;
        Connection myCon;
        try {
            myCon = DriverManager.getConnection(conURL,"matan","123123");
            myStmt = myCon.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql;

        sql = "create table coupons( " +
                "ID_ BIGINT PRIMARY KEY NOT NULL, " +
                "TITLE TEXT NOT NULL, " +
                "START_DATE DATE NOT NULL, " +
                "END_DATE DATE NOT NULL, " +
                "AMOUNT INT NOT NULL, " +
                "TYPE TEXT NOT NULL, " +
                "MESSAGE TEXT NOT NULL, " +
                "PRICE DOUBLE NOT NULL, " +
                "IMAGE TEXT NOT NULL)";
        try {
            myStmt.executeUpdate(sql);
            System.out.println("Coupons table successfully created.");

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public static void createCompanyCouponTable(){
        Statement myStmt=null;
        Connection myCon;
        try {
            myCon = DriverManager.getConnection(conURL,"matan","123123");
            myStmt = myCon.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "CREATE TABLE IF NOT EXISTS company_coupons(companyID_ BIGINT NOT NULL, couponID_ BIGINT NOT NULL PRIMARY KEY," +
                " FOREIGN KEY (companyID_) REFERENCES companies(ID_) ON DELETE CASCADE," +
                " FOREIGN KEY (couponID_) REFERENCES coupons(ID_) ON DELETE CASCADE);" ;

        try {
            myStmt.executeUpdate(sql);
            System.out.println("company_coupons table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createCustomerCouponTable(){
        Statement myStmt = null;
        Connection myCon ;
        try {
            myCon = DriverManager.getConnection(conURL,"matan","123123");
            myStmt = myCon.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "CREATE TABLE IF NOT EXISTS customer_coupons(customerID_ BIGINT NOT NULL, couponID_ BIGINT NOT NULL, PRIMARY KEY (customerID_,couponID_), " +
                "FOREIGN KEY (customerID_) REFERENCES customers(ID_) ON DELETE CASCADE);";
        try {
            myStmt.executeUpdate(sql);
            System.out.println("company_coupons table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getConPASS() {
        return conPASS;
    }

    public static String getConUSER() {
        return conUSER;
    }
    public static String connectionURL(){
        return conURL;
    }

    public static void closingConnection(Connection connection){

            if(connection!=null){
                try{
                    connection.close();
                }catch (SQLException e){
                    System.err.println("Problem closing the connection... please try again");
                }catch (Throwable ex){
                    new ConnectionError("There was a problem closing the connection! \n " +
                            "Please try again :)");
                }

            }

    }
}
