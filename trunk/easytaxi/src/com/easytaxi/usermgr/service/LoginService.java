package com.easytaxi.usermgr.service;

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

    public boolean isValidUser(String type, String account, String password) {
        // todo: check
        return true;
    }

}
