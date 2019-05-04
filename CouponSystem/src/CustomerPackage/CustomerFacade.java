package CustomerPackage;

import CouponPackage.Coupon;
import CouponPackage.CouponDBDAO;
import CouponPackage.couponTypes;
import Main.Client;
import Main.CouponClientFacade;
import Main.clientType;


import java.util.ArrayList;
import java.util.Collection;

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
    public boolean login(String custName, String password)  {
        return customerDBDAO.login(custName,password);
    }

    @Override
    public CouponClientFacade login(String name, String password, clientType type) {
        if(login(name,password) && type.equals(clientType.Customer)){
            return new CustomerFacade(name,password);
        }
        return null;
    }
}
