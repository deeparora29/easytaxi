package com.easytaxi.usermgr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.bo.Taxi;
import com.easytaxi.common.dao.BaseJdbcDao;

/**
 * @author renmian
 */
public class TaxiDao extends BaseJdbcDao {
    final static String QUERY_TAXI_EMAIL = "select * from taxi where email=?";
    final static String QUERY_TAXI_PLATENUMBER = "select * from taxi where plate_number=?";

    class TaxiRowMapper
        implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            Taxi taxi = new Taxi();
            taxi.setCarModel(rs.getString("car_model"));

            return taxi;
        }

    }

    public Taxi getTaxiByEmail(String email) {
        // todo
        return null;
    }

    public Taxi getTaxiByPlateNumber(String plateNumber) {
        // todo
        return null;
    }

}
