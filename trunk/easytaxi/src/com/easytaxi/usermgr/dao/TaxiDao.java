
package com.easytaxi.usermgr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.bo.Taxi;
import com.easytaxi.common.dao.BaseJdbcDao;

/**
 * @author renmian
 */
public class TaxiDao extends BaseJdbcDao {
    final static String QUERY_TAXI_EMAIL = "select * from taxi where email=?";
    final static String QUERY_TAXI_PLATENUMBER = "select * from taxi where plate_number=?";
    // todo: register save the taxi info.
    final static String INSERT_TAXI = "insert into (...) values (?,?,?)";

    class TaxiRowMapper
        implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            Taxi taxi = new Taxi();
            taxi.setCarModel(rs.getString("car_model"));
            taxi.setChargeModel(rs.getString("charge_model"));
            taxi.setCompany(rs.getString("company"));
            taxi.setCredit(rs.getFloat("credit"));
            taxi.setDescr(rs.getString("descr"));
            taxi.setDriver0(rs.getString("contact_person0"));
            taxi.setPhone0(rs.getString("contact_phone0"));
            taxi.setDriver1(rs.getString("contact_person1"));
            taxi.setPhone1(rs.getString("contact_phone1"));
            taxi.setEmail(rs.getString("email"));
            taxi.setLicense(rs.getString("license"));
            taxi.setPassword(rs.getString("password"));
            taxi.setPlateNumber(rs.getString("plate_number"));
            taxi.setStatus(rs.getInt("status"));
            taxi.setUserid(rs.getString("userid"));
            taxi.setModifiedTime(rs.getDate("modified_time"));
            taxi.setRegisterTime(rs.getDate("register_time"));
            return taxi;
        }

    }

    /**
     * according to email get detail taxi info
     * 
     * @param email
     * @return
     */
    public Taxi getTaxiByEmail(String email) {
        List<Taxi> list = getJdbcTemplate().query(QUERY_TAXI_EMAIL, new Object[] { email }, new TaxiRowMapper());
        return (Taxi) getObjectFromList(list);
    }

    /**
     * according to plateNumber get detail taxi info
     * 
     * @param plateNumber
     * @return
     */
    public Taxi getTaxiByPlateNumber(String plateNumber) {
        List<Taxi> list = getJdbcTemplate().query(QUERY_TAXI_PLATENUMBER, new Object[] { plateNumber },
            new TaxiRowMapper());
        return (Taxi) getObjectFromList(list);
    }

    public void doSaveTaxi(Taxi taxi) {
        getJdbcTemplate().update(INSERT_TAXI, new Object[]{...});
    }

}
