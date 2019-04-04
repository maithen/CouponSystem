package CompanyPackage;

import CouponPackage.Coupon;
import CouponPackage.couponTypes;

import java.time.LocalDate;
import java.util.Collection;

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
