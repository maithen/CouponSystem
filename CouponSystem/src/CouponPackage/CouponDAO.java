package CouponPackage;

import CompanyPackage.Company;

import Exceptions.DuplicateException;

import java.sql.SQLException;
import java.util.Collection;

public interface CouponDAO {
    void createCoupon(Coupon c1, Company company) throws SQLException, DuplicateException;
    void removeCoupon(Coupon c1) throws Exception;
    void updateCoupon(Coupon c1) throws SQLException;
    Coupon getCoupon(long id) throws SQLException;
    Collection<Coupon> getAllCoupons() ;
    Collection<Coupon> getCouponByType(couponTypes type) ;
}
