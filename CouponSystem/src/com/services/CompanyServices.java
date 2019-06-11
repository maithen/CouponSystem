package com.services;

import java.time.LocalDate;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.Company;
import com.company.CompanyFCI;
import com.coupon.Coupon;
import com.coupon.couponTypes;
import com.main.CouponClientFacade;
import com.main.clientType;

@Path("company")
public class CompanyServices implements CompanyFCI,CouponClientFacade {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void createCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		
	}
	
	@DELETE
	@Consumes (MediaType.APPLICATION_JSON)
	@Override
	public void removeCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void updateCouponPrice(long id, double price) {
		// TODO Auto-generated method stub
		
	}
	
	@PUT
	@Consumes
	@Override
	public void updateCouponEndDate(long id, int day, int month, int year) {
		// TODO Auto-generated method stub
		
	}
	
	@GET
	@Consumes
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Coupon getCoupon(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Consumes
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Consumes
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Coupon> getCouponByType(couponTypes couponType) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Consumes
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Coupon> getCouponsUpToPrice(double price) {
		// TODO Auto-generated method stub
		return null;
	}
	@GET
	@Consumes
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Coupon> getCouponsUpToDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Company getCompanyInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public CouponClientFacade login(String name, String password, clientType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
