package com.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.admin.AdminFCI;
import com.admin.AdminFacade;
import com.company.Company;
import com.coupon.couponTypes;
import com.customer.Customer;
import com.exceptions.DoesNotExistException;
import com.main.CouponClientFacade;
import com.main.CouponSystem;
import com.main.clientType;
import com.sun.jersey.spi.resource.Singleton;


@Path("admin")
@Consumes(MediaType.APPLICATION_JSON)
public class AdminService {
	
	
 
	@Context 
	HttpServletRequest request;
	

	
	private static AdminFacade adminFacade;
	
	
	public AdminService() {
		
	}
	
	
	@GET
	public void login(JSONObject login) throws JSONException {
	

		adminFacade = (AdminFacade) CouponSystem.getInstance().login(login.getString("name"), login.getString("password"), clientType.valueOf(login.getString("type")));
		 System.out.println(adminFacade.toString());


	
	}

	@POST
	@Path("addCompany")
	public void addCompany(Company company) {

		System.out.println(adminFacade.toString());
		adminFacade.addCompany(company);
		
	}

	@DELETE
	@Path("removeCompany")
	public void removeCompany(Company company) {
		adminFacade.removeCompany(company);
		
	}

	@PUT
	@Path("updateCompany")
	public void updateCompany(Company company) {
		adminFacade.updateCompany(company);
		
	}

	@GET
	@Path("getCompanies")
	public Collection<Company> getCompanies() {
		
		return adminFacade.getCompanies();
	}

	@GET
	@Path("getCompany")
	public Company getCompany(@QueryParam("id")long id) {
		// TODO Auto-generated method stub
		return adminFacade.getCompany(id);
	}

	@POST
	@Path("addCustomer")
	public void addCustomer(Customer customer) {
		adminFacade.addCustomer(customer);
		
	}

	@DELETE
	@Path("removeCustomer")
	public void removeCustomer(Customer customer) {
		adminFacade.removeCustomer(customer);
		
	}

	@PUT
	@Path("updateCustomer")
	public void updateCustomer(Customer customer) {
		adminFacade.updateCustomer(customer);
		
	}

	@GET
	@Path("getCustomers")
	public Collection<Customer> getCustomers() {
		return adminFacade.getCustomers();
	}
	

	@GET
	@Path("getCustomer")
	public Customer getCustomer(@QueryParam("id") long id) {
		return adminFacade.getCustomer(id);
	}
	
	



		

	
	

	
	
}
