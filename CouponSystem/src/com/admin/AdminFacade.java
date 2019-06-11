package com.admin;

import java.util.Collection;

import com.company.Company;
import com.company.CompanyDBDAO;
import com.customer.Customer;
import com.customer.CustomerDBDAO;
import com.exceptions.DoesNotExistException;
import com.main.Client;
import com.main.CouponClientFacade;
import com.main.clientType;

public class AdminFacade implements AdminFCI, CouponClientFacade {

    private CompanyDBDAO companyDBDAO = new CompanyDBDAO();
    private CustomerDBDAO customerDBDAO = new CustomerDBDAO();

    public AdminFacade(){
    }

    @Override
    public void addCompany (Company company){
                    companyDBDAO.createCompany(company);
                }

    @Override
    public void removeCompany (Company company){
                    companyDBDAO.removeCompany(company);

    }

    @Override
    public void updateCompany (Company company){
                    companyDBDAO.updateCompany(company);


    }

    @Override
    public Collection<Company> getCompanies () {
                    return companyDBDAO.getAllCompanies();
    }

    @Override
    public Company getCompany ( long id){
        return companyDBDAO.getCompany(id);

    }

    @Override
    public void addCustomer (Customer customer){
        customerDBDAO.createCustomer(customer);
    }

    @Override
    public void removeCustomer (Customer customer){
        customerDBDAO.removeCustomer(customer);
    }

    @Override
    public void updateCustomer (Customer customer, String password){
        customer.setPassword(password);
        customerDBDAO.updateCustomer(customer);
    }

    @Override
    public Collection<Customer> getCustomers () {
        return customerDBDAO.getAllCustomers();
    }

    @Override
    public Customer getCustomer ( long id){
        return customerDBDAO.getCustomer(id);
    }

    @Override
    public CouponClientFacade login (String name, String password, clientType type){

        if (name.equals("Admin") && password.equals("1234") && type.equals(clientType.Admin)) {
            System.out.println("login succesful");
            return new AdminFacade();
        }else{
            throw new DoesNotExistException("Admin account does not exist");
        }
    }

}