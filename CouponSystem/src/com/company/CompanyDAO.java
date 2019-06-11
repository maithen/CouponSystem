package com.company;
import java.sql.SQLException;
import java.util.Collection;

import com.coupon.*;
import com.exceptions.DoesNotExistException;
import com.exceptions.DuplicateException;

public interface CompanyDAO {

    void createCompany(Company c1) throws SQLException, DuplicateException;
    void removeCompany(Company c1) throws Exception;
    void updateCompany(Company c1);
    Company getCompany(long id);
    Collection<Company> getAllCompanies() ;
    Collection<Coupon> getAllCoupons(Company c1) ;
    boolean login(String companyName, String password);
}
