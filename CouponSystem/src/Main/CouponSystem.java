package Main;

import Admin.AdminFacade;
import CompanyPackage.CompanyFacade;
import CouponPackage.Coupon;
import CustomerPackage.CustomerFacade;
import DButils.ConnectionPool;


public class CouponSystem implements CouponClientFacade {
    private AdminFacade adminFacade = null;
    private CompanyFacade companyFacade = null;
    private CustomerFacade customerFacade = null;
    private static CouponSystem couponSystem = new CouponSystem();
    private clientType client;


    @Override
    public void login(String name, String password, clientType type)  {
        this.client=type;
        switch (type) {
            case Admin:
                adminFacade = new AdminFacade();
                break;
            case Company:
                companyFacade = new CompanyFacade(name, password);
                break;
            case Customer:
                customerFacade = new CustomerFacade(name, password);
                break;
        }

    }

    private CouponSystem(){

    }

    public static CouponSystem getInstance(){
        return couponSystem;
    }

   private void shutdown(){
        ConnectionPool.getInstance().closeAllConnections();
   }


}
