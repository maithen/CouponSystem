package Exceptions;

import CompanyPackage.Company;
import CouponPackage.Coupon;
import CustomerPackage.Customer;

public class DuplicateException extends RuntimeException {

    public DuplicateException(Company company){

        super("Company: " +company.getName()+", Already Exists.");
    }
    public DuplicateException(Object o, long id){

        super(o.getClass().getSimpleName()+" ID: " +id+ ", Already Exists.");
    }
    public DuplicateException(Object o, String name){

        super(o.getClass().getSimpleName()+" Name: " +name+ ", Already Exists.");
    }
    public DuplicateException(Coupon coupon){
        super("Coupon Title: "+coupon.getTitle()+ ", Already Exists.");
    }
    public DuplicateException(Customer customer){
        super("Customer: "+customer.getCustomerName()+ ", Already Exists.");
    }

}
