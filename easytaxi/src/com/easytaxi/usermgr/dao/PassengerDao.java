
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
    final static String QUERY_PASSENGER_USERID = "select * from passenger where userid=?";
    final static String INSERT_PASSENGER = "insert into passenger (userid, firstname, lastname,"
        + " nickname, phone, email, password, gender, picid, credit, agreement, descr, province, city,"
        + " register_time, modified_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now());";
    final static String UPDATE_PASSENGER_CREDIT = "update passenger set credit=? where userid=?";
    final static String UPDATE_PASSENGER_PHONE = "update passenger set phone=? where userid=?";
    final static String UPDATE_PASSENGER_LOGIN_TIME = "update passenger set modified_time=now() where userid=?";
    final static String Query_MODIFIED_INNER24_HOURS = "select * from taxi where modified_time < now()-86400";

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
            passenger.setNickname(rs.getString("nickname"));
            passenger.setPassword(rs.getString("password"));
            passenger.setPhone(rs.getString("phone"));
            passenger.setShare(rs.getString("agreement"));
            passenger.setUserid(rs.getString("userid"));
            passenger.setRegisterTime(rs.getTimestamp("register_time"));
            passenger.setModifiedTime(rs.getTimestamp("modified_time"));
            passenger.setCity(rs.getString("city"));
            passenger.setProvince(rs.getString("province"));
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

    /**
     * according to userid to get the passenger detail
     * 
     * @param userid
     * @return
     */
    public Passenger getPassengerByUserid(String userid) {
        List<Passenger> list = getJdbcTemplate().query(QUERY_PASSENGER_USERID, new Object[] { userid },
            new PassengerRowMapper());
        return (Passenger) getObjectFromList(list);
    }

    /**
     * @method: Resigter
     * @description: insert a passenger
     * @param passenger
     */
    public void doSavePassenger(Passenger passenger) {
        getJdbcTemplate().update(
            INSERT_PASSENGER,
            new Object[] { passenger.getUserid(), passenger.getFirstname(), passenger.getLastname(),
                passenger.getNickname(), passenger.getPhone(), passenger.getEmail(), passenger.getPassword(),
                passenger.getGender(), passenger.getPicid(), passenger.getCredit(), passenger.getAgreement(),
                passenger.getDescr(), passenger.getProvince(), passenger.getCity() });
    }
    
    public void doUpdatePassengerCredit(String userid, float credit) {
        getJdbcTemplate().update(UPDATE_PASSENGER_CREDIT, new Object[] { new Float(credit), userid });
    }
    
    
    public void doUpdatePassengerPhone(String userid, String phone) {
        getJdbcTemplate().update(UPDATE_PASSENGER_PHONE, new Object[] { new String(phone), userid });
    }
    
    
    public void doUpdatePassengerLoginTime() {
        getJdbcTemplate().update(UPDATE_PASSENGER_LOGIN_TIME);
    }
    
    public List<Passenger> getModifiedInner24HoursData() {
        List<Passenger> list = getJdbcTemplate().query(Query_MODIFIED_INNER24_HOURS , new PassengerRowMapper());
        return list;
    }
}
