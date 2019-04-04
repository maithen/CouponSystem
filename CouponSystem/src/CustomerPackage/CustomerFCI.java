package CustomerPackage;

import CouponPackage.Coupon;
import CouponPackage.couponTypes;


import java.util.Collection;

public interface CustomerFCI {

    void purchaseCoupon(Coupon coupon) ;
    Collection<Coupon> getAllPurchasedCoupons() ;
    Collection<Coupon> getAllCouponsByType(couponTypes type) ;
    Collection<Coupon> getPurchasedCouponsByPrice(Double price);

    boolean login(String custName, String password) ;
}
