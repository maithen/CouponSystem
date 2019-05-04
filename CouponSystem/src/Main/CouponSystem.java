package Main;

import Admin.AdminFacade;
import CompanyPackage.CompanyFacade;
import CustomerPackage.CustomerFacade;
import DButils.ConnectionPool;


public class CouponSystem implements CouponClientFacade{

    private static CouponSystem couponSystem = new CouponSystem();


    private CouponSystem() {
        Thread couponDelete = new Thread(new DailyCouponExpirationTask());
        couponDelete.start();
    }

    @Override
    public CouponClientFacade login(String name, String password, clientType type)  {

        switch (type) {
            case Admin:
                return new AdminFacade().login(name,password,type);
            case Company:
               return new CompanyFacade(name,password).login(name,password,type);
            case Customer:
                return new CustomerFacade(name,password).login(name, password,type);
        }
        return null;
    }



    public static CouponSystem getInstance(){
        return couponSystem;
    }

    private void shutdown(){
        ConnectionPool.getInstance().closeAllConnections();
   }


}
