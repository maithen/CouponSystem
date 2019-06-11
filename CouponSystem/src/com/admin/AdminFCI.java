package com.admin;

import java.util.Collection;

import com.company.Company;
import com.customer.Customer;
import com.exceptions.DoesNotExistException;

public interface AdminFCI {

    void addCompany(Company company);
    void removeCompany(Company company);
    void updateCompany(Company company);
    Collection<Company> getCompanies();
    Company getCompany(long id) throws DoesNotExistException;
    void addCustomer(Customer customer); //no identical customer names
    void removeCustomer(Customer customer);
    void updateCustomer(Customer customer, String password); //without customer name
    Collection<Customer> getCustomers();
    Customer getCustomer(long id);



}
