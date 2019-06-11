package com.customer;

import java.util.ArrayList;
import java.util.Collection;

import com.coupon.Coupon;
import com.coupon.CouponDBDAO;
import com.coupon.couponTypes;
import com.main.Client;
import com.main.CouponClientFacade;
import com.main.clientType;

public class CustomerFacade implements CustomerFCI, CouponClientFacade {

    private CustomerDBDAO customerDBDAO = new CustomerDBDAO();
    private CouponDBDAO couponDBDAO = new CouponDBDAO();
    private Customer customer;

    public CustomerFacade(String name, String password) {
            customer = customerDBDAO.getCustomer(name,password);

    }

    public Customer getCustomerInfo(){
        return customer;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) {
            customerDBDAO.purchaseCoupon(coupon,customer);
    }

    @Override
    public Collection<Coupon> getAllPurchasedCoupons()  {
            return customerDBDAO.getCoupons(customer);

    }

    @Override
    public Collection<Coupon> getAllCouponsByType(couponTypes type) {

        return customerDBDAO.getCouponsByType(customer,type);

    }

    @Override
    public Collection<Coupon> getPurchasedCouponsByPrice(Double price)  {

        return customerDBDAO.getCouponsByPrice(customer,price);
    }


    @Override
    public CouponClientFacade login(String name, String password, clientType type) {
        if(customerDBDAO.login(name,password) && type.equals(clientType.Customer)){
            return new CustomerFacade(name,password);
        }
        return null;
    }
}
