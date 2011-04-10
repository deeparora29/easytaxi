
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
    final static String QUERY_TAXI_USERID = "select * from taxi where userid=?";
    final static String INSERT_TAXI = "insert into taxi (userid, plate_number, password, license, company,"
        + " car_model, charge_model, email, contact_person0, contact_phone0, contact_person1, contact_phone1,"
        + " status, descr, credit, register_time, modified_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now())";
    final static String UPDATE_TAXI_CREDIT = "update taxi set credit=? where userid=?";

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
            taxi.setCab(rs.getString("plate_number"));
            taxi.setStatus(rs.getInt("status"));
            taxi.setUserid(rs.getString("userid"));
            taxi.setModifiedTime(rs.getTimestamp("modified_time"));
            taxi.setRegisterTime(rs.getTimestamp("register_time"));
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

    /**
     * according to userid get detail taxi info
     * 
     * @param userid
     * @return
     */
    public Taxi getTaxiByUserid(String userid) {
        List<Taxi> list = getJdbcTemplate().query(QUERY_TAXI_USERID, new Object[] { userid }, new TaxiRowMapper());
        return (Taxi) getObjectFromList(list);
    }

    /**
     * @method: register
     * @desciption: insert a taxi user
     * @param taxi
     */
    public void doSaveTaxi(Taxi taxi) {
        getJdbcTemplate().update(
            INSERT_TAXI,
            new Object[] { taxi.getUserid(), taxi.getCab(), taxi.getPassword(), taxi.getLicense(), taxi.getCompany(),
                taxi.getCarModel(), taxi.getChargeModel(), taxi.getEmail(), taxi.getDriver0(), taxi.getPhone0(),
                taxi.getDriver1(), taxi.getPhone1(), taxi.getStatus(), taxi.getDescr(), taxi.getCredit() });
    }

    public void doUpdateTaxiCredit(String userid, float credit) {
        getJdbcTemplate().update(UPDATE_TAXI_CREDIT, new Object[] { new Float(credit), userid });
    }

}
