package com.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.admin.AdminFacade;
import com.company.Company;
import com.company.CompanyFCI;
import com.company.CompanyFacade;
import com.coupon.Coupon;
import com.coupon.couponTypes;
import com.main.CouponClientFacade;
import com.main.CouponSystem;
import com.main.clientType;

@Path("company")
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyServices {
	
	private static CompanyFacade companyFacade;
	
	
	@POST
	public void login(JSONObject login) throws JSONException {
	

		companyFacade = (CompanyFacade) CouponSystem.getInstance()
				.login(login.getString("name"), login.getString("password"), clientType.valueOf(login.getString("type")));
		
		 System.out.println(companyFacade.getCompanyInfo());

	}
	
	@POST
	@Path("createCoupon")
	public void createCoupon(Coupon coupon) {
		System.out.println("here");
		companyFacade.createCoupon(coupon);
		
	}
	@DELETE
	@Path("removeCoupon")
	public void removeCoupon(Coupon coupon) {
		companyFacade.removeCoupon(coupon);
		
	}

	@PUT
	@Path("updateCouponPrice")
	public void updateCouponPrice(@QueryParam("id") long id,@QueryParam("price") double price) {
		companyFacade.updateCouponPrice(id, price);
		
	}

	@PUT
	@Path("updateCouponEndDate")
	public void updateCouponEndDate(long id, int day, int month, int year) {
		// TODO Auto-generated method stub
		companyFacade.updateCouponEndDate(id, day, month, year);
	}
	
	@GET
	@Path("getCoupon")
	public Coupon getCoupon(long id) {
		// TODO Auto-generated method stub
		return companyFacade.getCoupon(id);
	}

	@GET
	@Path("getAllCoupons")
	public Collection<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return companyFacade.getAllCoupons();
	}
	
	@GET
	@Path("getCouponByType")
	public Collection<Coupon> getCouponByType(@QueryParam("couponType") String couponType) {
		return companyFacade.getCouponByType(couponTypes.valueOf(couponType));
	}
	
	@GET
	@Path("getCouponsUpToPrice")
	public Collection<Coupon> getCouponsUpToPrice(@QueryParam("price")double price) {
		// TODO Auto-generated method stub
		return companyFacade.getCouponsUpToPrice(price);
	}

	@GET
	@Path("getCouponsUpToDate")
	public Collection<Coupon> getCouponsUpToDate(@QueryParam("date")String date) {
		return companyFacade.getCouponsUpToDate(LocalDate.parse(date));
	}

	@GET
	@Path("CompanyInfo")
	public Company getCompanyInfo() {
		// TODO Auto-generated method stub
		return companyFacade.getCompanyInfo();
	}
		
	


}
