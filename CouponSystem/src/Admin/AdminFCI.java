package Admin;

import CompanyPackage.Company;
import CustomerPackage.Customer;
import Exceptions.DoesNotExistException;

import java.util.Collection;

public interface AdminFCI {

    boolean login(String username, String password);
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
