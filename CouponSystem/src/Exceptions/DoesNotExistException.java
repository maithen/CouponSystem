package Exceptions;

import CompanyPackage.Company;
import CouponPackage.Coupon;
import CustomerPackage.Customer;

import java.sql.SQLException;
import java.util.Collection;

public class DoesNotExistException extends RuntimeException{
    private String message;


    public DoesNotExistException(Company o){
        super(o.getClass().getSimpleName() + " Does Not Exist.");
        message = o.getClass().getSimpleName() + " Does Not Exist.";

    }

    public DoesNotExistException(String message){
        this.message=message;
    }
    public DoesNotExistException(Coupon o){
        super(o.getClass().getSimpleName() + " Does Not Exist.");
        message = o.getClass().getSimpleName() + " Does Not Exist.";
    }
    public DoesNotExistException(Customer o) {
        super(o.getClass().getSimpleName() + " Does Not Exist.");
        message = o.getClass().getSimpleName() + " Does Not Exist.";
    }
    public DoesNotExistException(Object o, long id){
        super(o.getClass().getSimpleName() + " Does Not Exist.");
        message = o.getClass().getSimpleName()+" ID:"+id + " Does Not Exist.";
    }

    public DoesNotExistException(Collection collection,String collectionName){
        super(collection.getClass().getSimpleName() + " Does Not Exist.");
        message = "There Are No "+collectionName+ " In The Database.";
    }



    @Override
    public String getMessage(){
        return message;
    }



}
