package CustomerPackage;

import CouponPackage.Coupon;
import CouponPackage.CouponDBDAO;
import CouponPackage.couponTypes;
import DButils.*;
import Exceptions.DoesNotExistException;
import Exceptions.DuplicateException;


import javax.sound.midi.Soundbank;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerDBDAO implements CustomerDAO{

    ConnectionPool connection = ConnectionPool.getInstance();
    CouponDBDAO couponDBDAO = new CouponDBDAO();

    @Override
    public void createCustomer(Customer c1){
        Connection myCon = null;
        String sql = "INSERT INTO customers (ID_, Customer_name, Password) VALUES(?,?,?)";

        try {

                myCon = connection.getConnection();
                PreparedStatement myPstmt = myCon.prepareStatement(sql);
                myPstmt.setLong(1, c1.getId());
                myPstmt.setString(2, c1.getCustomerName());
                myPstmt.setString(3, c1.getPassword());
                myPstmt.executeUpdate();
                System.out.println("Customer: " + c1.getCustomerName() + ", inserted into table");



        } catch (SQLIntegrityConstraintViolationException e) {
            if(e.getLocalizedMessage().contains("for key 'PRIMARY'")){
                throw new DuplicateException(c1,c1.getId());
            }else{
                throw new DuplicateException(c1,c1.getCustomerName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                connection.returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void removeCustomer(Customer c1) {
        Connection myCon = null;
        PreparedStatement myStmt = null;
        try{
            myCon = connection.getConnection();
            String sql = "DELETE FROM customers WHERE ID_=? && Customer_name=? && password=?";

            myStmt = myCon.prepareStatement(sql);
            myStmt.setLong(1,c1.getId());
            myStmt.setString(2,c1.getCustomerName());
            myStmt.setString(3,c1.getPassword());
            System.out.println(myStmt);
            int rowsAffected = myStmt.executeUpdate();
            if(rowsAffected!=0) {
                System.out.println(String.format("Customer ID:%d, Deleted.", c1.getId()));
            } else {
                throw new DoesNotExistException(c1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            connection.returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateCustomer(Customer c1) {
        Connection myCon = null;
        PreparedStatement myStmt = null;
        try{
            myCon = connection.getConnection();
            String sql = "UPDATE customers SET ID_=?, Customer_name=?, Password=? WHERE ID_=?";

            myStmt = myCon.prepareStatement(sql);
            myStmt.setLong(1,c1.getId());
            myStmt.setString(2,c1.getCustomerName());
            myStmt.setString(3,c1.getPassword());
            myStmt.setLong(4,c1.getId());

            System.out.println(myStmt);
            int rowsAffected = myStmt.executeUpdate();
            if(rowsAffected!=0)
                System.out.println(String.format("Customer ID:%d , Updated.", c1.getId()));
            else
                throw new DoesNotExistException(c1);
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            connection.returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Customer getCustomer(long id) {
        Connection myCon = null;
        Statement myStmt = null;
        Customer customer = new Customer();
        try{
            myCon = connection.getConnection();
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
                throw new DoesNotExistException(customer,id);
            }

        }catch (SQLException e){
            new SQLException("SERIOUS ISSUE ABORT");
        }finally {

            connection.returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

    @Override
    public Customer getCustomer(String name, String password){
        Connection myCon = null;
        Statement myStmt = null;
        Customer customer = new Customer();
        try{
            myCon = connection.getConnection();
            String sql = "SELECT * FROM customers WHERE Customer_name='"+name+"' and Password='"+password+"';";
            myStmt = myCon.createStatement();
            ResultSet rs = myStmt.executeQuery(sql);
            if(rs.isBeforeFirst()) {
                rs.next();
                customer.setId(rs.getLong(1));
                customer.setCustomerName(rs.getString(2));
                customer.setPassword(rs.getString(3));
                rs.close();
                System.out.println(String.format("Customer Loaded"));
            }else{
                throw new DoesNotExistException(customer);
            }

        }catch (SQLException e){
            new SQLException("SERIOUS ISSUE ABORT");
        }finally {

            connection.returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        Customer customer = new Customer();
        Collection<Customer> customers = new ArrayList<>();
        Connection myCon = null;
        Statement myStmt = null;

        try{
            myCon = connection.getConnection();
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

            connection.returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(customers.isEmpty()){
            throw new DoesNotExistException(customers,"Customers");
        }
        return customers;

    }

    @Override
    public void purchaseCoupon(Coupon coupon, Customer customer){
        Connection myCon = connection.getConnection();
        String sql = "INSERT INTO customer_coupons (customerID_, couponID_) VALUES (?,?)";
        PreparedStatement myStmt = null;
        try {
            myStmt = myCon.prepareStatement(sql);
            myStmt.setLong(1, customer.getId());
            myStmt.setLong(2, coupon.getId());
            couponDBDAO.couponAmountCheck(coupon);
            myStmt.executeUpdate();

            myStmt.close();
        }catch (SQLIntegrityConstraintViolationException e){
            throw new DuplicateException(coupon);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Collection<Coupon> getCoupons(Customer customer) {
        Collection<Coupon> coupons = new ArrayList<>();
        Connection myCon = null;
        Statement myStmt;

        try{

            myCon = connection.getConnection();
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

            connection.returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(coupons.isEmpty()){
            throw new DoesNotExistException(coupons,"Coupons");
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
            throw new DoesNotExistException(couponType,"Coupons");
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
            throw new DoesNotExistException(couponPrice,"Coupons");
        }
        return couponPrice;
    }

    @Override
    public boolean login(String customerName, String password) {
        Connection myCon = null;
        try {

            myCon = connection.getConnection();
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
            throw new DoesNotExistException("Username or Password Incorrect.");

            // e.printStackTrace();
        }finally {
            connection.returnConnection(myCon);
            if(myCon!=null)
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        throw new DoesNotExistException("Password is Case Sensitive.");

    }
}
