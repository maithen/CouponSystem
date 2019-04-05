package Admin;

import CompanyPackage.Company;
import CompanyPackage.CompanyDBDAO;
import CustomerPackage.Customer;
import CustomerPackage.CustomerDBDAO;
import Exceptions.DoesNotExistException;
import Main.Client;
import Main.CouponClientFacade;
import Main.clientType;

import java.util.Collection;

public class AdminFacade extends Client implements AdminFCI, CouponClientFacade {

    CompanyDBDAO companyDBDAO = new CompanyDBDAO();
    CustomerDBDAO customerDBDAO = new CustomerDBDAO();

    public AdminFacade(String name, String password, clientType type){
        login(name,password,type);

    }
    @Override
    public void addCompany (Company company){
                    companyDBDAO.createCompany(company);
                }
    @Override
    public void removeCompany (Company company){
                    companyDBDAO.removeCompany(company);

    }@Override
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
    public void login (String name, String password, clientType type){

        if (name.equals("Admin") && password.equals("1234") && type.equals(clientType.Admin)) {
            System.out.println("login succesful");
        }else{
            throw new DoesNotExistException("Admin account does not exist");
        }
    }

}