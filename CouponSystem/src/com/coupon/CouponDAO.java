package com.coupon;

import java.sql.SQLException;
import java.util.Collection;

import com.company.Company;
import com.exceptions.DuplicateException;

public interface CouponDAO {
    void createCoupon(Coupon c1, Company company) throws SQLException, DuplicateException;
    void removeCoupon(Coupon c1) throws Exception;
    void updateCoupon(Coupon c1) throws SQLException;
    Coupon getCoupon(long id) throws SQLException;
    Collection<Coupon> getAllCoupons() ;
    Collection<Coupon> getCouponByType(couponTypes type) ;
}
