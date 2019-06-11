package com.services;


import java.util.Collection;

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
import javax.ws.rs.core.MediaType;

import com.admin.AdminFCI;
import com.admin.AdminFacade;
import com.company.Company;
import com.coupon.couponTypes;
import com.customer.Customer;
import com.exceptions.DoesNotExistException;
import com.main.CouponClientFacade;
import com.main.CouponSystem;
import com.main.clientType;


@Path("admin")
public class AdminService implements AdminFCI, CouponClientFacade {
			
	
	public AdminService() {
		
	}
	
	@POST
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCompany(Company company) {
		// TODO Auto-generated method stub
		
	}
	
	@DELETE
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeCompany(Company company) {
		// TODO Auto-generated method stub
		
	}
	@PUT
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCompany(Company company) {
		// TODO Auto-generated method stub
		
	}
	
	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Company> getCompanies() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompany(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@POST
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	@DELETE
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	
	@PUT
	@Override
	public void updateCustomer(Customer customer, String password) {
		// TODO Auto-generated method stub
		
	}
	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}
	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public CouponClientFacade login(String name, String password, clientType type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
