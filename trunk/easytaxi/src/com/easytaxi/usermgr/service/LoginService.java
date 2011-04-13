
package com.easytaxi.usermgr.service;

import com.easytaxi.bo.Passenger;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.usermgr.bo.LoginRecord;
import com.easytaxi.usermgr.dao.LoginRecordDao;
import com.easytaxi.usermgr.dao.PassengerDao;
import com.easytaxi.usermgr.dao.TaxiDao;

public class LoginService extends BaseService {
    private PassengerDao passengerDao;
    private TaxiDao taxiDao;
    private LoginRecordDao loginRecordDao;

    public void setPassengerDao(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    public PassengerDao getPassengerDao() {
        return this.passengerDao;
    }

    public void setTaxiDao(TaxiDao taxiDao) {
        this.taxiDao = taxiDao;
    }

    public TaxiDao getTaxiDao() {
        return this.taxiDao;
    }

    private Taxi getValidTaxi(String account, String password) {
        boolean isEmailCheck = account.contains("@");
        Taxi taxi = null;
        try {
            taxi = isEmailCheck ? taxiDao.getTaxiByEmail(account) : taxiDao.getTaxiByPlateNumber(account);
            if (taxi != null && taxi.getPassword().equals(password)) {
                return taxi;
            } else
                taxi = null;
        } catch (Exception e) {
            logger.error("Taxi check: account[" + account + "] password[" + password + "] error: ", e);
        }
        return taxi;
    }

    private Passenger getValidPassenger(String account, String password) {
        boolean isEmailCheck = account.contains("@");
        Passenger passenger = null;
        try {

            passenger = isEmailCheck ? passengerDao.getPassengerByEmail(account) : passengerDao
                .getPassengerByPhone(account);
            if (passenger != null && passenger.getPassword().equals(password)) {
                return passenger;
            } else
                passenger = null;
        } catch (Exception e) {
            logger.error("Taxi check: account[" + account + "] password[" + password + "] error: ", e);
        }
        return passenger;
    }

    public String getValidUserid(String type, String account, String password) {
        String userid = "";
        if (account == null || account.equals(""))
            return userid;
        try {
            if (type.equals("taxi")) {
                Taxi taxi = getValidTaxi(account, password);
                if (taxi != null)
                    userid = taxi.getUserid();
            } else {
                Passenger passenger = getValidPassenger(account, password);
                if (passenger != null)
                    userid = passenger.getUserid();
            }
            // if login successed, record login
            if (!userid.equals(""))
                recordLogin(userid, account);
        } catch (Exception e) {
            logger.error("check valid user error:", e);
        }
        return userid;
    }

    protected void recordLogin(String userid, String account) {
        LoginRecord loginRecord = new LoginRecord(userid, account);
        getLoginRecordDao().doSaveLoginRecord(loginRecord);
    }

    /**
     * T010 get taxi detai info
     * 
     * @param userid
     * @return
     */
    public Taxi getTaxiDetailInfo(String userid) {
        return getTaxiDao().getTaxiByUserid(userid);
    }

    /**
     * P010 get Passenger detail info
     * 
     * @param userid
     * @return
     */
    public Passenger getPassengerDetailInfo(String userid){
        return getPassengerDao().getPassengerByUserid(userid);
    }

    public void setLoginRecordDao(LoginRecordDao loginRecordDao) {
        this.loginRecordDao = loginRecordDao;
    }

    public LoginRecordDao getLoginRecordDao() {
        return loginRecordDao;
    }


}
