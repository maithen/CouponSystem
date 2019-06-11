package com.company;

import java.time.LocalDate;
import java.util.Collection;

import com.coupon.Coupon;
import com.coupon.couponTypes;

public interface CompanyFCI  {
    void createCoupon(Coupon coupon);
    void removeCoupon(Coupon coupon);
    void updateCouponPrice(long id, double price);
    void updateCouponEndDate(long id, int day, int month, int year);
    Coupon getCoupon(long id);
    Collection<Coupon> getAllCoupons();
    Collection<Coupon> getCouponByType(couponTypes couponType);
    Collection<Coupon> getCouponsUpToPrice(double price);
    Collection<Coupon> getCouponsUpToDate(LocalDate date);
    boolean login(String name, String password);
    Company getCompanyInfo();
}
