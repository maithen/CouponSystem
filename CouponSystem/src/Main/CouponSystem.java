package Main;

import Admin.AdminFacade;
import CompanyPackage.CompanyFacade;
import CustomerPackage.Customer;
import CustomerPackage.CustomerFacade;
import DButils.ConnectionPool;
import Exceptions.DoesNotExistException;


public class CouponSystem implements CouponClientFacade{
    private static Client client;
    private static CouponSystem couponSystem = new CouponSystem();


    private CouponSystem() {

    }


    @Override
    public void login(String name, String password, clientType type)  {

        switch (type) {
            case Admin:
                 client = new AdminFacade(name,password,type);
                break;
            case Company:
               client = new CompanyFacade(name, password);
                break;
            case Customer:
                client = new CustomerFacade(name, password);
                break;
        }
    }



    public static CouponSystem getInstance(){
        return couponSystem;
    }

   private void shutdown(){
        ConnectionPool.getInstance().closeAllConnections();
   }

    public static AdminFacade getAdminFacade(){
        return (AdminFacade) client;

    } public static CustomerFacade getCustomerFacade(){
        return (CustomerFacade) client;

    } public static CompanyFacade getCompanyFacade(){
        return (CompanyFacade) client;
    }
}
