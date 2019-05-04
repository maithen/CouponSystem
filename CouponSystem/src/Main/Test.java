package Main;

import Admin.AdminFacade;
import CompanyPackage.Company;
import CompanyPackage.CompanyFacade;
import CouponPackage.Coupon;
import CouponPackage.couponTypes;
import CustomerPackage.CustomerFacade;
import DButils.DButil;
import sun.security.pkcs11.Secmod;

public class Test  {

    public static void main(String[] args) {


        CustomerFacade cf = (CustomerFacade) CouponSystem.getInstance().login("Shimi","shimi",clientType.Customer);
        System.out.println(cf.getCustomerInfo());



    }
}