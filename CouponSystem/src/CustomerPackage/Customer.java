package CustomerPackage;

import CouponPackage.Coupon;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private long id;
    private String customerName;
    private String password;
    private List<Coupon> couponList = new ArrayList();

    public Customer(long id, String customerName, String password) {
        setId(id);
        setCustomerName(customerName);
        setPassword(password);
    }

    public Customer() {}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        for (char p : password.toCharArray()){
            if(Character.isWhitespace(p)) {
                System.err.println("No spaces allowed in password.");
                return;

            }
        }
        this.password = password;
    }

    public void addCoupon(Coupon coupon){
        couponList.add(coupon);
    }

    @Override
    public String toString() {
        return String.format(
                "Customer (id=%s, customerName=%s, password=**********)", this.id, this.customerName);
    }
}
