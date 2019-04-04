package CompanyPackage;
import CouponPackage.*;
import DButils.ConnectionPool;

import Exceptions.ConnectionError;
import Exceptions.DoesNotExistException;
import Exceptions.DuplicateException;
import Exceptions.RemoveException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CompanyDBDAO implements CompanyDAO {




    public CompanyDBDAO() {
      
    }

    @Override
    public boolean login(String companyName, String password) {

        try {

        Connection myCon = ConnectionPool.getInstance().getConnection();
        String sQL = " SELECT company_name,password FROM companies WHERE company_name='"+companyName+"' and Password='"+password+"';";
            Statement myStmt = myCon.createStatement();

            ResultSet rs = myStmt.executeQuery(sQL);
            rs.next();
            if (rs.getString("company_name").toLowerCase().equals(companyName.toLowerCase())
                    &&
                    rs.getString("Password").equals(password)) {
                System.out.println("Connected.");
                ConnectionPool.getInstance().returnConnection(myCon);
                myCon.close();
                return true;


            }
        } catch (SQLException e){
            System.err.printf("Username or Password invalid. \n");
            return false;
           // e.printStackTrace();
        }

        System.err.println("Password is case sensitive.");
        return false;
    }


    @Override
    public void createCompany(Company c1)  {

                Connection myCon = ConnectionPool.getInstance().getConnection();
                String sql = "INSERT INTO companies (ID_, company_name, Password, Email) VALUES(?,?,?,?)";

                try {

                        PreparedStatement myPstmt = myCon.prepareStatement(sql);
                        myPstmt.setLong(1, c1.getId());
                        myPstmt.setString(2, c1.getName());
                        myPstmt.setString(3, c1.getPassword());
                        myPstmt.setString(4, c1.getEmail());
                        myPstmt.executeUpdate();
                        System.out.println("Company: " + c1.getName() + ", inserted into table");

                } catch (SQLIntegrityConstraintViolationException ex) {
                   if(ex.getLocalizedMessage().contains("for key 'PRIMARY'")){
                       throw new DuplicateException(c1,c1.getId());
                   }else{
                       throw new DuplicateException(c1,c1.getName());
                   }


                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                  ConnectionPool.getInstance().returnConnection(myCon);
                    try {
                        myCon.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }


        }


    @Override
    public void removeCompany(Company c1)  {
        Connection myCon = ConnectionPool.getInstance().getConnection();
        String pre1 = "DELETE FROM companies WHERE ID_=? && company_name=?";

        try (PreparedStatement pstm1 = myCon.prepareStatement(pre1)) {

            myCon.setAutoCommit(false);
            pstm1.setLong(1, c1.getId());
            pstm1.setString(2,c1.getName());
            int rowsAffected = pstm1.executeUpdate();
            myCon.commit();


            if (rowsAffected == 0) {
                throw new DoesNotExistException(c1);
            } else {
                System.out.printf("Company: %s, removed. \n", c1.getName());
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void updateCompany(Company c1) {
        Connection myCon = ConnectionPool.getInstance().getConnection();
        Statement myStmt;
        try  {

            myStmt = myCon.createStatement();
            String sql = "UPDATE companies " + " SET Password='" + c1.getPassword()
                    + "', Email='" + c1.getEmail()
                    + "' WHERE ID_=" + c1.getId();

            int rowsAffected = myStmt.executeUpdate(sql);

            if(rowsAffected ==0){
                throw new DoesNotExistException(c1);
            }else {
                System.out.printf("CompanyID: %d,Updated \n", c1.getId());
            }
        } catch (SQLException e) {
            try {
                throw new SQLException("Updating company failed because of bad connection");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }finally {
                ConnectionPool.getInstance().returnConnection(myCon);
                try {
                    myCon.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    @Override
    public Company getCompany(long id) {
        Company company = new Company();
        Connection myCon = null;

        String sql = "SELECT * FROM companies WHERE ID_=" + id;
        try {
            myCon = ConnectionPool.getInstance().getConnection();
            Statement statement = myCon.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
                if(resultSet.isBeforeFirst()){
                resultSet.next();
                company.setId(resultSet.getLong("ID_"));
                company.setName(resultSet.getString("company_name"));
                company.setPassword(resultSet.getString("Password"));
                company.setEmail(resultSet.getString("Email"));}
                else{
                    throw new DoesNotExistException(company);
                }

        }catch (SQLException dn){
            dn.getMessage();
        } finally{
                ConnectionPool.getInstance().returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        return company;
    }

    public Company getCompany(String username, String password) {
        Company company = new Company();
        Connection myCon = ConnectionPool.getInstance().getConnection();

        String sql = "SELECT * FROM companies WHERE company_name='"+username+"' and Password='"+password+"';";
        try {
            Statement statement = myCon.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                company.setId(resultSet.getLong("ID_"));
                company.setName(resultSet.getString("company_name"));
                company.setPassword(resultSet.getString("Password"));
                company.setEmail(resultSet.getString("Email"));}
            else {
                throw new DoesNotExistException(company);
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            ConnectionPool.getInstance().returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return company;
    }

    @Override
    public Collection<Company> getAllCompanies() {
        Connection myCon = ConnectionPool.getInstance().getConnection();


        Collection<Company> companies = new ArrayList<>();

        String sql = "SELECT * FROM companies";
        try (Statement stm = myCon.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                long id = rs.getLong("ID_");
                String name = rs.getString("company_name");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                companies.add(new Company(id, name, password, email));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(("Cannot Reach Companies."));

        } finally {
                ConnectionPool.getInstance().returnConnection(myCon);
            try {
                myCon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if(companies.isEmpty()){
            throw new DoesNotExistException(companies,"Companies");
        }

        return companies;
    }


    @Override
    public Collection<Coupon> getAllCoupons(Company company) {

            Connection myCon = null;
            Statement myStmt;
            Collection<Coupon> coupons = new ArrayList<>();

            String sql = "SELECT coupons.* FROM coupons " +
                    "INNER JOIN company_coupons on company_coupons.couponID_=coupons.ID_ " +
                    "WHERE companyID_="+company.getId()+";";

            try {
                myCon = ConnectionPool.getInstance().getConnection();
                myStmt = myCon.createStatement();
                ResultSet rs = myStmt.executeQuery(sql);

                while (rs.next()){
                    long id = rs.getLong(1);
                    String title = rs.getString(2);
                    LocalDate start_date = rs.getDate(3).toLocalDate();
                    LocalDate end_date = rs.getDate(4).toLocalDate();
                    int amount = rs.getInt(5);
                    String type = rs.getString(6);
                    String message = rs.getString(7);
                    double price = rs.getDouble(8);
                    String image = rs.getString(9);
                    Coupon coupon = new Coupon(id, title, amount, couponTypes.valueOf(type), message, price, image);
                    coupon.setStartDate(start_date);
                    coupon.setEndDate(end_date);
                    coupons.add(coupon);

                }
            } catch (SQLException exc) {
                System.out.println(exc);
            } finally {
                    ConnectionPool.getInstance().returnConnection(myCon);
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


    public Collection<Coupon> getCouponsUpToPrice(double price ,Company company)  {

        Collection<Coupon> coupons = new ArrayList<>();
        for (Coupon c :
                getAllCoupons(company)) {
            if (c.getPrice()<=price) {
                coupons.add(c);
            }
        }
        if(coupons.isEmpty()){
            throw new DoesNotExistException(coupons,"Coupons");
        }
        return coupons;
    }

    public Collection<Coupon> getCouponsUpToDate(LocalDate date,Company company)  {

        Collection<Coupon> coupons = new ArrayList<>();
        for (Coupon c :
                getAllCoupons(company)) {
            if (c.getEndDate().isBefore(date)) {
                coupons.add(c);
            }
        }
        if(coupons.isEmpty()){
            throw new DoesNotExistException(coupons,"Coupons");
        }
        return coupons;
    }

    public Collection<Coupon> getCouponByType(couponTypes couponTypes,Company company)  {
        Collection<Coupon> couponByType = new ArrayList<>();
        for (Coupon c :
                getAllCoupons(company)) {
            if(c.getType().equals(couponTypes)){
                ((ArrayList<Coupon>) couponByType).add(c);
            }
        }
        if(couponByType.isEmpty()){
            throw new DoesNotExistException(couponByType,"Coupons");
        }
        return couponByType;

    }

}
