package Exceptions;

import CompanyPackage.Company;
import CouponPackage.Coupon;
import CustomerPackage.Customer;

public class RemoveException extends Exception {


    public RemoveException(Company c){
        super("Failed To Remove Company: "+c.getName());
    }
    public RemoveException(Coupon c){
        super("Failed To Remove Coupon: "+c.getTitle());
    }
    public RemoveException(Customer c){
        super("Failed To Remove Customer: "+c.getCustomerName());
    }
}
