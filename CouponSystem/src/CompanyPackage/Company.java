package CompanyPackage;

import CouponPackage.Coupon;

import java.util.*;


public class Company {

    private long id;
    private String name;
    private String password;
    private String email;
    private List<Coupon> couponList = new ArrayList<>();

    /**
     * constructor for class,
     * @param id = companys ID
     * @param name  = company's name
     * @param email = company's email
     * @param password = company's password.
     */
    public Company(long id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        setPassword(password);
        this.email = email;

    }

    public Company() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void coupons(Coupon coupon){

        couponList.add(coupon);
    }

    /**
     * @return returns details, password censored.
     */
    @Override
    public String toString() {
        return String.format(
                "Company (id=%s, name=%s, password=********, email=%s)", this.id, this.name, this.email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                Objects.equals(name, company.name) &&
                Objects.equals(password, company.password) &&
                Objects.equals(email, company.email) &&
                Objects.equals(couponList, company.couponList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, couponList);
    }
}
