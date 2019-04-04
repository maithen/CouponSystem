package CouponPackage;

import CompanyPackage.Company;
import DButils.ConnectionPool;

import Exceptions.DoesNotExistException;
import Exceptions.DuplicateException;
import Exceptions.RemoveException;

import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CouponDBDAO implements CouponDAO {

    public CouponDBDAO(){
    }
    private ConnectionPool connection = ConnectionPool.getInstance();

    @Override
    public void createCoupon(Coupon c1, Company company)  {
        Connection myCon = null;
        PreparedStatement myStmt = null;
        try {
            for (Coupon c : getAllCoupons()){
                if(c.getTitle().toLowerCase().equals(c1.getTitle().toLowerCase())){
                    throw new DuplicateException(c1,c1.getTitle());

                }
            }
            myCon = connection.getConnection();
            String sql = "INSERT INTO coupondb.coupons VALUES (?,?,?,?,?,?,?,?,?); ";
            myStmt = myCon.prepareStatement(sql);

            myStmt.setLong(1, c1.getId());
            myStmt.setString(2, c1.getTitle());
            myStmt.setDate(3, Date.valueOf(c1.getStartDate()));
            myStmt.setDate(4, Date.valueOf(c1.getEndDate()));
            myStmt.setInt(5, c1.getAmount());
            myStmt.setString(6, c1.getType().toString());
            myStmt.setString(7, c1.getMessage());
            myStmt.setDouble(8, c1.getPrice());
            myStmt.setString(9, c1.getImage());
            myStmt.executeUpdate();

            sql = "INSERT INTO coupondb.company_coupons VALUES (?,?)";
            myStmt = myCon.prepareStatement(sql);
            myStmt.setLong(1,company.getId());
            myStmt.setLong(2,c1.getId());
            myStmt.executeUpdate();

            myStmt.close();
        }catch(SQLIntegrityConstraintViolationException sie){
            throw new DuplicateException(c1,c1.getId());
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
    public void removeCoupon(Coupon c1) {
            Connection myCon = null;
            PreparedStatement myStmt = null;
            try {
                myCon = connection.getConnection();
                String sql = "DELETE FROM coupons WHERE ID_=?;";
                myStmt = myCon.prepareStatement(sql);
                myStmt.setLong(1, c1.getId());
                int rowsAffected = myStmt.executeUpdate();
                if(rowsAffected==0){
                    throw new DoesNotExistException(c1);
                }
                System.out.println("Coupon deleted");
                myStmt.close();
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
    public void updateCoupon(Coupon c1){
        Connection myCon = null;
        PreparedStatement myStmt = null;
        try {
            myCon = connection.getConnection();

            String sql = "UPDATE coupons " +
                    "SET " +
                    "TITLE=?," +
                    "END_DATE=?," +
                    "AMOUNT=?," +
                    "TYPE=?," +
                    "MESSAGE=?," +
                    "PRICE=?," +
                    "IMAGE=? " +
                    "WHERE ID_=?;";
            myStmt = myCon.prepareStatement(sql);
            myStmt.setString(1, c1.getTitle());
            myStmt.setDate(2, Date.valueOf(c1.getEndDate()));
            myStmt.setInt(3, c1.getAmount());
            myStmt.setString(4, c1.getType().toString());
            myStmt.setString(5, c1.getMessage());
            myStmt.setDouble(6, c1.getPrice());
            myStmt.setString(7, c1.getImage());
            myStmt.setLong(8, c1.getId());
            int rowsAffected = myStmt.executeUpdate();

            if (rowsAffected == 0){
              throw new DoesNotExistException(c1);
            }

        } catch (SQLException e) {
            System.err.printf("Unexpcted Problem");
        } finally {
            connection.returnConnection(myCon);
            try {
                myStmt.close();
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public Coupon getCoupon(long id) {
        Coupon coupon = null;
        Connection myCon = null;
        Statement myStmt;
        ResultSet rs = null;

        try {
            myCon = connection.getConnection();
            String sql = "SELECT * FROM coupons WHERE ID_="+id;
            myStmt = myCon.createStatement();
            myStmt.executeQuery(sql);

            rs = myStmt.getResultSet();
            if(rs.isBeforeFirst()) {

                rs.next();
                long id_ = rs.getLong(1);
                String title = rs.getString(2);
                LocalDate startDate = rs.getDate(3).toLocalDate();
                LocalDate endDate = rs.getDate(4).toLocalDate();
                int amount = (rs.getInt(5));
                couponTypes type = couponTypes.valueOf(rs.getString(6));
                String message = rs.getString(7);
                double price = rs.getDouble(8);
                String image = rs.getString(9);
                coupon = new Coupon(id_, title, amount, type, message, price, image);
                coupon.setStartDate(startDate);
                coupon.setEndDate(endDate);

                rs.close();
            }else {

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
        return coupon;
    }

    @Override
    public Collection<Coupon> getAllCoupons()  {
        Set<Coupon> coupons = new HashSet<>();
        Coupon coupon;
        Connection myCon = null;
        Statement myStmt;


        try {
            myCon = connection.getConnection();
            String sql = "SELECT * FROM coupons";
            myStmt = myCon.createStatement();
            myStmt.executeQuery(sql);

            ResultSet rs = myStmt.getResultSet();
            while (rs.next()) {
                long id_ = rs.getLong(1);
                String title = rs.getString(2);
                LocalDate startDate = rs.getDate(3).toLocalDate();
                LocalDate endDate = rs.getDate(4).toLocalDate();
                int amount = (rs.getInt(5));
                couponTypes type = couponTypes.valueOf(rs.getString(6));
                String message = rs.getString(7);
                double price = rs.getDouble(8);
                String image = rs.getString(9);
                coupon = new Coupon(id_,title,amount,type,message,price,image);
                coupon.setStartDate(startDate);
                coupon.setEndDate(endDate);
                coupons.add(coupon);
            }
            rs.close();

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
        if(coupons.isEmpty()){
            throw new DoesNotExistException(coupons,"Coupons");
        }
        return coupons;
    }

    @Override
    public Collection<Coupon> getCouponByType(couponTypes type) {
        Set<Coupon> coupons = new HashSet<>();
        Coupon coupon;
        Connection myCon = null;

        try {
            myCon = connection.getConnection();
            String sql = "SELECT * FROM coupons WHERE TYPE='" + type+"';";
            Statement myStmt = myCon.createStatement();
            myStmt.executeQuery(sql);

            ResultSet rs = myStmt.getResultSet();
            while (rs.next()) {
                long id_ = rs.getLong(1);
                String title = rs.getString(2);
                LocalDate startDate = rs.getDate(3).toLocalDate();
                LocalDate endDate = rs.getDate(4).toLocalDate();
                int amount = (rs.getInt(5));
                couponTypes ctype = couponTypes.valueOf(rs.getString(6));
                String message = rs.getString(7);
                double price = rs.getDouble(8);
                String image = rs.getString(9);
                coupon = new Coupon(id_,title,amount,ctype,message,price,image);
                coupon.setStartDate(startDate);
                coupon.setEndDate(endDate);
                coupons.add(coupon);
            }
                rs.close();

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
        if(coupons.isEmpty()){
            throw new DoesNotExistException(coupons,"Coupons");
        }
        return coupons;
    }

    public void couponAmountCheck(Coupon coupon)  {
        Coupon c1 = getCoupon(coupon.getId());
        if(c1.getAmount()>0) {
            c1.setAmount(c1.getAmount() - 1);
            updateCoupon(c1);
        }else{
            System.out.println("Coupon Sold out!");
            return;
        }
    }
}