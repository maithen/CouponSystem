package CustomerPackage;

import CouponPackage.Coupon;


import java.sql.SQLException;
import java.util.Collection;

public interface CustomerDAO {

    void createCustomer(Customer c1) throws SQLException;
    void removeCustomer(Customer c1) throws Exception;
    void updateCustomer(Customer c1) throws SQLException;
    Customer getCustomer(long id) ;
    Customer getCustomer(String name, String password);
    Collection<Customer> getAllCustomers() ;
    Collection<Coupon> getCoupons(Customer c1);
    void purchaseCoupon(Coupon coupon, Customer customer) ;
    boolean login(String customerName, String password);
}
