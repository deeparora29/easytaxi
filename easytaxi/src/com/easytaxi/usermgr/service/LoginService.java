
package com.easytaxi.usermgr.service;

import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.usermgr.dao.PassengerDao;
import com.easytaxi.usermgr.dao.TaxiDao;

public class LoginService extends BaseService {
    private PassengerDao passengerDao;
    private TaxiDao taxiDao;

    public void setPassengerDao(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    public void setTaxiDao(TaxiDao taxiDao) {
        this.taxiDao = taxiDao;
    }

    public String getValidUserid(String type, String account, String password) {
        String userid = "";
        if (account == null || account.equals(""))
            return userid;
        try {
            boolean isEmailCheck = account.contains("@");
            if (type.equals("taxi")) {
                Taxi taxi = null;
                taxi = isEmailCheck ? taxiDao.getTaxiByEmail(account) : taxiDao.getTaxiByPlateNumber(account);
                if (taxi != null && taxi.getPassword().equals(password))
                    userid = taxi.getUserid();
            } else {
                Passenger passenger = null;
                passenger = isEmailCheck ? passengerDao.getPassengerByEmail(account) : passengerDao
                    .getPassengerByPhone(account);
                if (passenger != null && passenger.getPassword().equals(password))
                    userid = passenger.getUserid();
            }
        } catch (Exception e) {
            logger.error("check valid user error:", e);
        }
        return userid;
    }

    public void recordLogin(String type, String account, String password) {

    }


}
