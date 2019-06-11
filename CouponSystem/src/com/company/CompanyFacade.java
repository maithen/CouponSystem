package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.coupon.Coupon;
import com.coupon.CouponDBDAO;
import com.coupon.couponTypes;
import com.main.Client;
import com.main.CouponClientFacade;
import com.main.clientType;

public class CompanyFacade  implements CompanyFCI,CouponClientFacade {

    private CouponDBDAO couponDBDAO = new CouponDBDAO();
    private Company company ;
    private CompanyDBDAO companyDBDAO = new CompanyDBDAO();

    public CompanyFacade(String name, String password){
        company = companyDBDAO.getCompany(name,password);
    }

    @Override
    public Company getCompanyInfo() {
        return company;
    }

    @Override
    public void createCoupon(Coupon coupon) {
        couponDBDAO.createCoupon(coupon,company);
    }

    @Override
    public void removeCoupon(Coupon coupon) {
            couponDBDAO.removeCoupon(coupon);
    }

    @Override
    public void updateCouponPrice(long id, double price) {
        Coupon coupon = couponDBDAO.getCoupon(id);
        coupon.setPrice(price);
        couponDBDAO.updateCoupon(coupon);
    }

    @Override
    public void updateCouponEndDate(long id, int day, int month, int year) {
            Coupon coupon = couponDBDAO.getCoupon(id);
            coupon.setEndDate(LocalDate.of(year,month,day));
            couponDBDAO.updateCoupon(coupon);
    }

    @Override
    public Coupon getCoupon(long id) {
       return couponDBDAO.getCoupon(id);
    }

    @Override
    public Collection<Coupon> getAllCoupons() {
        return companyDBDAO.getAllCoupons(company);
    }

    @Override
    public Collection<Coupon> getCouponByType(couponTypes couponType) {
       return companyDBDAO.getCouponByType(couponType,company);
    }

    @Override
    public Collection<Coupon> getCouponsUpToPrice(double price) {
        return companyDBDAO.getCouponsUpToPrice(price,company);
    }

    @Override
    public Collection<Coupon> getCouponsUpToDate(LocalDate date) {
        return companyDBDAO.getCouponsUpToDate(date,company);
    }


    @Override
    public boolean login(String name, String password) {
        return companyDBDAO.login(name,password);
    }


    @Override
    public CouponClientFacade login(String name, String password, clientType type) {
        if(login(name,password) && type.equals(clientType.Company)){
            return new CompanyFacade(name,password);
        }
        return null;
    }
}
