package com.easytaxi.usermgr.dao;

import com.easytaxi.bo.Taxi;
import com.easytaxi.common.dao.BaseJdbcDao;

/**
 * @author renmian
 */
public class TaxiDao extends BaseJdbcDao {
    final static String QUERY_TAXI_EMAIL = "select * from taxi where email=?";
    final static String QUERY_TAXI_PLATENUMBER = "select * from taxi where plate_number=?";

    public Taxi getTaxiByEmail(String email) {
        // todo
        return null;
    }

    public Taxi getTaxiByPlateNumber(String plateNumber) {
        // todo
        return null;
    }

}
