package com.main;

import javax.xml.bind.annotation.XmlRootElement;

import com.admin.AdminFacade;
import com.company.CompanyFacade;
import com.customer.CustomerFacade;
import com.dbutils.ConnectionPool;

@XmlRootElement
public class CouponSystem implements CouponClientFacade{

    private static CouponSystem couponSystem = new CouponSystem();
    private Thread couponDelete = new Thread(new DailyCouponExpirationTask());

    private CouponSystem() {
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

    public void shutdown(){
        ConnectionPool.getInstance().closeAllConnections();
        couponDelete.interrupt();
   }


}
