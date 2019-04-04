package Main;
import Admin.AdminFacade;
import CompanyPackage.*;


import CouponPackage.Coupon;
import CouponPackage.CouponDBDAO;
import CouponPackage.couponTypes;
import CustomerPackage.Customer;
import CustomerPackage.CustomerFacade;
import DButils.DButil;

import java.time.LocalDate;


public class Test  {

    public static void main(String[] args) {
        CouponSystem couponSystem = CouponSystem.getInstance();

      couponSystem.login("shimi","shimi",clientType.Customer);

    }
}