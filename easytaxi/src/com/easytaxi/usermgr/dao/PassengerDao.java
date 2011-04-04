package com.easytaxi.usermgr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.bo.Passenger;
import com.easytaxi.common.dao.BaseJdbcDao;

public class PassengerDao extends BaseJdbcDao {
    final static String QUERY_PASSENGER_EMAIL = "select * from passenger where email=?";
    final static String QUERY_PASSENGER_PHONE = "select * from passenger where phone=?";

    class PassengerRowMapper
        implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            Passenger passenger = new Passenger();
            passenger.setAgreement(rs.getString("agreement"));
            passenger.setDescr(rs.getString("descr"));
            passenger.setEmail(rs.getString("email"));
            passenger.setFirstname(rs.getString("firstname"));
            passenger.setLastname(rs.getString("lastname"));
            passenger.setGender(rs.getString("gender"));
            passenger.setNickName(rs.getString("nickname"));
            passenger.setPassword(rs.getString("password"));
            passenger.setPhone(rs.getString("phone"));
            passenger.setShare(rs.getString("share"));
            passenger.setUserid(rs.getString("userid"));
            passenger.setRegisterTime(rs.getDate("register_time"));
            passenger.setModifiedTime(rs.getDate("modified_time"));
            return passenger;
        }
    }

    public Passenger getPassengerByEmail(String email) {
        // todo
        return null;
    }

    public Passenger getPassengerByPhone(String phone) {
        // todo
        return null;
    }


}
