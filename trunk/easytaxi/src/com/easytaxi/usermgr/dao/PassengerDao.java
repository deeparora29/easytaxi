
package com.easytaxi.usermgr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.bo.Passenger;
import com.easytaxi.common.dao.BaseJdbcDao;

public class PassengerDao extends BaseJdbcDao {
    final static String QUERY_PASSENGER_EMAIL = "select * from passenger where email=?";
    final static String QUERY_PASSENGER_PHONE = "select * from passenger where phone=?";
    // todo : register to save the passenger data
    final static String INSERT_PASSENGER = "insert into passenger (...) values (?,?,?)";

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

    /**
     * according to email get Passenger
     * 
     * @param email
     * @return
     */
    public Passenger getPassengerByEmail(String email) {
        List<Passenger> list = getJdbcTemplate().query(QUERY_PASSENGER_EMAIL, new Object[] { email },
            new PassengerRowMapper());
        return (Passenger) getObjectFromList(list);
    }

    /**
     * according to phone get Passenger
     * 
     * @param phone
     * @return
     */
    public Passenger getPassengerByPhone(String phone) {
        List<Passenger> list = getJdbcTemplate().query(QUERY_PASSENGER_PHONE, new Object[] { phone },
            new PassengerRowMapper());
        return (Passenger) getObjectFromList(list);
    }

    public void doSavePassenger(Passenger passenger) {
        getJdbcTemplate().update(INSERT_PASSENGER, new Object[] {...});
    }

}
