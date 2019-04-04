package Admin;

import CompanyPackage.Company;
import CompanyPackage.CompanyDBDAO;
import CustomerPackage.Customer;
import CustomerPackage.CustomerDBDAO;
import Exceptions.DoesNotExistException;

import java.util.Collection;

public class AdminFacade implements AdminFCI{

    CompanyDBDAO companyDBDAO = new CompanyDBDAO();
    CustomerDBDAO customerDBDAO = new CustomerDBDAO();


    @Override
    public boolean login(String username, String password) {
        if(username.equals("Admin") && password.equals("1234")){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public void addCompany(Company company) {
        companyDBDAO.createCompany(company);
    }

    @Override
    public void removeCompany(Company company) {
        companyDBDAO.removeCompany(company);

    }

    @Override
    public void updateCompany(Company company) {
        companyDBDAO.updateCompany(company);


    }

    @Override
    public Collection<Company> getCompanies() {
        return companyDBDAO.getAllCompanies();
    }

    @Override
    public Company getCompany(long id) {
        return companyDBDAO.getCompany(id);

    }

    @Override
    public void addCustomer(Customer customer) {
        customerDBDAO.createCustomer(customer);
    }

    @Override
    public void removeCustomer(Customer customer) {
        customerDBDAO.removeCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer, String password) {
        customer.setPassword(password);
        customerDBDAO.updateCustomer(customer);
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customerDBDAO.getAllCustomers();
    }

    @Override
    public Customer getCustomer(long id) {
        return customerDBDAO.getCustomer(id);
    }
}
