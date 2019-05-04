package Main;

import CouponPackage.Coupon;
import CouponPackage.CouponDBDAO;
import Exceptions.DoesNotExistException;

import java.time.LocalDate;

public class DailyCouponExpirationTask implements Runnable {
    private CouponDBDAO cpd = new CouponDBDAO();

    @Override
    public void run() {

            for (Coupon coupon : this.cpd.getAllCoupons()) {
                if(this.cpd.getAllCoupons().isEmpty()){
                    break;
                }
                if (coupon.getEndDate().equals(LocalDate.now())) {
                    this.cpd.removeCoupon(coupon);
                }
            }

            try {
                Thread.sleep(60 * 24 * 24 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    public void stopTask(){
        Thread.currentThread().interrupt();
    }
}
