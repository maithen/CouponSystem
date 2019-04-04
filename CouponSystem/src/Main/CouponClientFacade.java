package Main;

import Admin.AdminFacade;
import CompanyPackage.Company;
import CompanyPackage.CompanyFacade;
import CouponPackage.Coupon;
import CustomerPackage.Customer;
import CustomerPackage.CustomerFacade;


public interface CouponClientFacade {


    public void login(String name, String password, clientType type) ;

}
