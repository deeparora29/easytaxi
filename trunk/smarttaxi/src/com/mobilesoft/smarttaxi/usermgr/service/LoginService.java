
package com.mobilesoft.smarttaxi.usermgr.service;

import com.mobilesoft.smarttaxi.bo.Passenger;
import com.mobilesoft.smarttaxi.bo.Taxi;
import com.mobilesoft.smarttaxi.common.service.BaseService;
import com.mobilesoft.smarttaxi.usermgr.bo.LoginRecord;
import com.mobilesoft.smarttaxi.usermgr.dao.LoginRecordDao;
import com.mobilesoft.smarttaxi.usermgr.dao.PassengerDao;
import com.mobilesoft.smarttaxi.usermgr.dao.TaxiDao;

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

    /**
     * 检查账号是否存在
     * 
     * @param cab
     * @return
     */
    public Taxi getTaxiBy(String account) {
        boolean isEmailCheck = account.contains("@");
        try {
            return isEmailCheck ? taxiDao.getTaxiByEmail(account) : taxiDao.getTaxiByPlateNumber(account);
        } catch (Exception e) {
            logger.error("Taxi check: account[" + account + "] error: ", e);
        }
        return null;

    }

    /**
     * @param account
     * @return
     */
    public Passenger getPassengerBy(String account) {
        boolean isEmailCheck = account.contains("@");
        try {
            return isEmailCheck ? passengerDao.getPassengerByEmail(account) : passengerDao.getPassengerByPhone(account);
        } catch (Exception e) {
            logger.error("Passenger check: account[" + account + "] error: ", e);
        }
        return null;
    }

    public Taxi getValidTaxi(String account, String password) {
        Taxi taxi = null;
        try {
            taxi = getTaxiBy(account);
            if (taxi != null && taxi.getPassword().equals(password)) {
                return taxi;
            } else
                taxi = null;
        } catch (Exception e) {
            logger.error("Taxi check: account[" + account + "] password[" + password + "] error: ", e);
        }
        return taxi;
    }

    public Passenger getValidPassenger(String account, String password) {
        Passenger passenger = null;
        try {

            passenger = getPassengerBy(account);
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

    public boolean registerPassenger(Passenger passenger) {
        try {
            Passenger dbPassenger = getPassengerBy(passenger.getEmail());
            if (dbPassenger == null)
                dbPassenger = getPassengerBy(passenger.getPhone());
            if (dbPassenger != null) {
                logger.info("Passenger has registered!Userid=[" + dbPassenger.getUserid() + "]");
                return false;
            }
            String userid = passengerDao.getSerialNum("p_user_id", 5, "false");
            passenger.setUserid(userid);
            passengerDao.doSavePassenger(passenger);
            return true;
        } catch (Exception e) {
            logger.error("Register Passenger error: ", e);
        }
        return false;
    }

    public boolean registerTaxi(Taxi taxi) {
        try {
            Taxi dbTaxi = getTaxiBy(taxi.getCab());
            if (dbTaxi != null) {
                logger.info("Passenger has registered!Userid=[" + dbTaxi.getUserid() + "]");
                return false;
            }
            String userid = taxiDao.getSerialNum("t_user_id", 5, "false");
            taxi.setUserid(userid);
            taxiDao.doSaveTaxi(taxi);
            return true;
        } catch (Exception e) {
            logger.error("Register Passenger error: ", e);
        }
        return false;
    }

    public void setLoginRecordDao(LoginRecordDao loginRecordDao) {
        this.loginRecordDao = loginRecordDao;
    }

    public LoginRecordDao getLoginRecordDao() {
        return loginRecordDao;
    }


}
