package CustomerPackage;

import CouponPackage.Coupon;
import CouponPackage.CouponDBDAO;
import CouponPackage.couponTypes;
import Main.Client;


import java.util.ArrayList;
import java.util.Collection;

public class CustomerFacade extends Client implements CustomerFCI {

    CustomerDBDAO customerDBDAO = new CustomerDBDAO();
    CouponDBDAO couponDBDAO = new CouponDBDAO();
    Customer customer= new Customer();

    public CustomerFacade(String name, String password) {
        if(login(name,password)){
         customer = customerDBDAO.getCustomer(name,password);
        }

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
    public boolean login(String custName, String password)  {
        return customerDBDAO.login(custName,password);
    }
}
