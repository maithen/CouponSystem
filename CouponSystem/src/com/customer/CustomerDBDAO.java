package com.customer;

import javax.sound.midi.Soundbank;
import javax.swing.plaf.nimbus.State;

import com.coupon.Coupon;
import com.coupon.CouponDBDAO;
import com.coupon.couponTypes;
import com.dbutils.*;
import com.exceptions.DoesNotExistException;
import com.exceptions.DuplicateException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerDBDAO implements CustomerDAO{


    private CouponDBDAO couponDBDAO = new CouponDBDAO();

    @Override
    public void createCustomer(Customer c1){
        Connection myCon = null;
        String sql = "INSERT INTO customers (ID_, Customer_name, Password) VALUES(?,?,?)";

        try {

                myCon = ConnectionPool.getInstance().getConnection();
                PreparedStatement myPstmt = myCon.prepareStatement(sql);
                myPstmt.setLong(1, c1.getId());
                myPstmt.setString(2, c1.getCustomerName());
                myPstmt.setString(3, c1.getPassword());
                myPstmt.executeUpdate();
                System.out.println("Customer: " + c1.getCustomerName() + ", inserted into table");



        } catch (SQLIntegrityConstraintViolationException e) {
            if(e.getLocalizedMessage().contains("for key 'PRIMARY'")){
                System.out.println(new DuplicateException(c1,c1.getId()).getMessage());
            }else{
            	 System.out.println(new DuplicateException(c1,c1.getCustomerName()).getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }

    }

    @Override
    public void removeCustomer(Customer c1) {
        Connection myCon = null;
        PreparedStatement myStmt;
        try{
            myCon = ConnectionPool.getInstance().getConnection();
            String sql = "DELETE FROM customers WHERE ID_=? && Customer_name=? && password=?";

            myStmt = myCon.prepareStatement(sql);
            myStmt.setLong(1,c1.getId());
            myStmt.setString(2,c1.getCustomerName());
            myStmt.setString(3,c1.getPassword());
            int rowsAffected = myStmt.executeUpdate();
            if(rowsAffected!=0) {
                System.out.println(String.format("Customer ID:%d, Deleted.", c1.getId()));
            } else {
            	 System.out.println(new DoesNotExistException(c1).getMessage());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }
    }

    @Override
    public void updateCustomer(Customer c1) {
        Connection myCon = null;
        PreparedStatement myStmt;
        try{
            myCon = ConnectionPool.getInstance().getConnection();
            String sql = "UPDATE customers SET ID_=?, Password=? WHERE ID_=?";

            myStmt = myCon.prepareStatement(sql);
            myStmt.setLong(1,c1.getId());
            myStmt.setString(2,c1.getPassword());
            myStmt.setLong(3,c1.getId());

            System.out.println(myStmt);
            int rowsAffected = myStmt.executeUpdate();
            if(rowsAffected!=0)
                System.out.println(String.format("Customer ID:%d , Updated.", c1.getId()));
            else
            	 System.out.println(new DoesNotExistException(c1).getMessage());
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }
    }

    @Override
    public Customer getCustomer(long id) {
        Connection myCon = null;
        Statement myStmt;
        Customer customer = new Customer();
        try{
            myCon = ConnectionPool.getInstance().getConnection();
            myStmt = myCon.createStatement();
            String sql = "SELECT * FROM customers WHERE ID_="+id;
            ResultSet rs = myStmt.executeQuery(sql);
            if(rs.isBeforeFirst()) {
                rs.next();
                customer.setId(rs.getLong(1));
                customer.setCustomerName(rs.getString(2));
                customer.setPassword(rs.getString(3));
                rs.close();
            }else{
            	 System.out.println(new DoesNotExistException(customer,id).getMessage());
            }

        }catch (SQLException e){
            new SQLException("SERIOUS ISSUE ABORT");
        }finally {

            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }

        return customer;
    }

    @Override
    public Customer getCustomer(String name, String password){
        Connection myCon = null;
        Statement myStmt;
        Customer customer = new Customer();
        try{
            myCon = ConnectionPool.getInstance().getConnection();
            String sql = "SELECT * FROM customers WHERE Customer_name='"+name+"' and Password='"+password+"';";
            myStmt = myCon.createStatement();
            ResultSet rs = myStmt.executeQuery(sql);
            if(rs.isBeforeFirst()) {
                rs.next();
                customer.setId(rs.getLong(1));
                customer.setCustomerName(rs.getString(2));
                customer.setPassword(rs.getString(3));
                rs.close();
            }else{
            	 System.out.println(new DoesNotExistException(customer).getMessage());
            }

        }catch (SQLException e){
            new SQLException("SERIOUS ISSUE ABORT");
        }finally {

            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }

        return customer;
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        Customer customer = new Customer();
        Collection<Customer> customers = new ArrayList<>();
        Connection myCon = null;
        Statement myStmt;

        try{
            myCon = ConnectionPool.getInstance().getConnection();
            myStmt = myCon.createStatement();
            String sql = "SELECT * FROM customers";
            ResultSet rs = myStmt.executeQuery(sql);

            while(rs.next()) {
               customers.add(new Customer(
                       rs.getLong(1),
                       rs.getString(2),
                       rs.getString(3)));
                }
            rs.close();

        }catch (SQLException e){
            new SQLException("SERIOUS ISSUE ABORT");
        }finally {

            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }
        if(customers.isEmpty()){
        	 System.out.println(new DoesNotExistException(customers,"Customers").getMessage());
        }
        return customers;

    }

    @Override
    public void purchaseCoupon(Coupon coupon, Customer customer){
        Connection myCon = ConnectionPool.getInstance().getConnection();
        String sql = "INSERT INTO customer_coupons (customerID_, couponID_) VALUES (?,?)";
        PreparedStatement myStmt;
        try {
            myStmt = myCon.prepareStatement(sql);
            myStmt.setLong(1, customer.getId());
            myStmt.setLong(2, coupon.getId());
            couponDBDAO.couponAmountCheck(coupon);
            myStmt.executeUpdate();

            myStmt.close();
        }catch (SQLIntegrityConstraintViolationException e){
        	 System.out.println(new DuplicateException(coupon).getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }

    }

    @Override
    public Collection<Coupon> getCoupons(Customer customer) {
        Collection<Coupon> coupons = new ArrayList<>();
        Connection myCon = null;
        Statement myStmt;

        try{

            myCon = ConnectionPool.getInstance().getConnection();
            myStmt = myCon.createStatement();

            String sql = "SELECT coupons.* FROM coupons " +
                    "INNER JOIN customer_coupons on customer_coupons.couponID_=coupons.ID_ " +
                    "WHERE customerID_="+customer.getId()+";";
            ResultSet rs = myStmt.executeQuery(sql);


            while(rs.next()) {
                long id = rs.getLong(1);
                String title = rs.getString(2);
                Date start_date = rs.getDate(3);
                Date end_date = rs.getDate(4);
                int amount = rs.getInt(5);
                String type = rs.getString(6);
                String message = rs.getString(7);
                double price = rs.getDouble(8);
                String image = rs.getString(9);
                Coupon coupon = new Coupon(id, title, amount, couponTypes.valueOf(type), message, price, image);
                coupons.add(coupon);
            }

        }catch (SQLException e){
            new SQLException("SERIOUS ISSUE ABORT");
        }finally {

            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }
        if(coupons.isEmpty()){
        	 System.out.println(new DoesNotExistException(coupons,"Coupons").getMessage());
        }
        return coupons;
    }

    public Collection<Coupon> getCouponsByType(Customer customer, couponTypes type){
        Collection<Coupon> couponType = new ArrayList<>();
        for (Coupon c : getCoupons(customer)) {
            if(c.getType().equals(type)){
                couponType.add(c);
            }
        }
        if(couponType.isEmpty()){
        	 System.out.println(new DoesNotExistException(couponType,"Coupons").getMessage());
        }
        return couponType;
    }

    public Collection<Coupon> getCouponsByPrice(Customer customer, double price){
        Collection<Coupon> couponPrice = new ArrayList<>();

        for (Coupon c : getCoupons(customer)) {
            if(c.getPrice()<=price){
                couponPrice.add(c);
            }
        }
        if(couponPrice.isEmpty()){
        	 System.out.println( new DoesNotExistException(couponPrice,"Coupons").getMessage());
        }
        return couponPrice;
    }

    @Override
    public boolean login(String customerName, String password) {
        Connection myCon = null;
        try {

            myCon = ConnectionPool.getInstance().getConnection();
            String sql = " SELECT Customer_name,password FROM customers WHERE Customer_name='"+customerName+"' and Password='"+password+"';";
            Statement myStmt = myCon.createStatement();
            ResultSet rs = myStmt.executeQuery(sql);
            rs.next();
            if (rs.getString("Customer_name").toLowerCase().equals(customerName.toLowerCase())
                    &&
                    rs.getString("Password").equals(password)){
                System.out.println("Connected.");
                return true;

            }
        } catch (SQLException e){
        	 System.out.println(new DoesNotExistException("Username or Password Incorrect.").getMessage());

            // e.printStackTrace();
        }finally {
            ConnectionPool.getInstance().returnConnection(myCon);
            DButil.closingConnection(myCon);
        }

        System.out.println(new DoesNotExistException("Password is Case Sensitive."));
        return false;
    }
}
