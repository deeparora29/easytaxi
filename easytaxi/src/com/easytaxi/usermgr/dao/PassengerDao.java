package com.easytaxi.usermgr.dao;

import com.easytaxi.bo.Passenger;
import com.easytaxi.common.dao.BaseJdbcDao;

public class PassengerDao extends BaseJdbcDao {
    final static String QUERY_PASSENGER_EMAIL = "select * from passenger where email=?";
    final static String QUERY_PASSENGER_PHONE = "select * from passenger where phone=?";


    public Passenger getPassengerByEmail(String email) {
        // todo
        return null;
    }

    public Passenger getPassengerByPhone(String phone) {
        // todo
        return null;
    }

}
