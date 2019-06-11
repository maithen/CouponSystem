package com.exceptions;

import com.company.Company;
import com.coupon.Coupon;
import com.customer.Customer;

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
