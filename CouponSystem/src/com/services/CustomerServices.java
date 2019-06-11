package com.services;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coupon.Coupon;
import com.coupon.couponTypes;
import com.customer.CustomerFCI;
import com.main.CouponClientFacade;
import com.main.clientType;

public class CustomerServices implements CustomerFCI,CouponClientFacade{
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void purchaseCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Coupon> getAllPurchasedCoupons() {
		// TODO Auto-generated method stub
		return null;
	}
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Coupon> getAllCouponsByType(couponTypes type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Coupon> getPurchasedCouponsByPrice(Double price) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Consumes
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public CouponClientFacade login(String name, String password, clientType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
