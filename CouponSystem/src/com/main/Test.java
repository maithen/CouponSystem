package com.main;

import com.admin.AdminFacade;
import com.company.Company;
import com.company.CompanyFacade;
import com.coupon.Coupon;
import com.coupon.couponTypes;
import com.customer.CustomerFacade;
import com.dbutils.DButil;

import sun.security.pkcs11.Secmod;

public class Test  {

    public static void main(String[] args) {


        CustomerFacade cf = (CustomerFacade) CouponSystem.getInstance().login("Shimi","shimi",clientType.Customer);
        System.out.println(cf.getCustomerInfo());
        CompanyFacade companyFacade = (CompanyFacade) CouponSystem.getInstance().login("ahah","10914", clientType.Company);
        

    }
}