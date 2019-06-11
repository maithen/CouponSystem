package com.customer;

import java.util.Collection;

import com.coupon.Coupon;
import com.coupon.couponTypes;

public interface CustomerFCI {

    void purchaseCoupon(Coupon coupon) ;
    Collection<Coupon> getAllPurchasedCoupons() ;
    Collection<Coupon> getAllCouponsByType(couponTypes type) ;
    Collection<Coupon> getPurchasedCouponsByPrice(Double price);

    boolean login(String custName, String password) ;
}
