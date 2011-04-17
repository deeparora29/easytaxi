package com.easytaxi.usermgr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.bo.Passenger;
import com.easytaxi.common.dao.BaseJdbcDao;
import com.easytaxi.usermgr.bo.LoginRecord;
import com.easytaxi.usermgr.dao.PassengerDao.PassengerRowMapper;

public class LoginRecordDao extends BaseJdbcDao {
    private final static String INSERT_LOGINRECORD = "insert into (userid, account, latitude, longtitude, login_time) values(?,?,?,?,now())";

    private final static String Query_MODIFIED_INNER24_HOURS = "select * from loginrecord where login_time < now()- 86400";
    
    private final static String DELETE_USER_LOGIN_LOG = "delete from loginrecord where userid=?"; 
    
    private static LoginRecordDao instance = new LoginRecordDao();
    
    public static LoginRecordDao getInstance(){
    	return instance==null?new LoginRecordDao():instance ;
    }
    
	class LoginRecordRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			LoginRecord loginRecord = new LoginRecord();
			loginRecord.setUserid(rs.getString("userid"));
			loginRecord.setAccount(rs.getString("account"));
			loginRecord.setLoginTime(rs.getDate("login_time"));
			loginRecord.setLatitude(rs.getDouble("latitude"));
			loginRecord.setLongtitude(rs.getDouble("longtitude"));
			return loginRecord;
		}
}
    
    /**
     * @method: save the login record
     * @param loginRecord
     */
    public void doSaveLoginRecord(LoginRecord loginRecord) {
        getJdbcTemplate().update(
            INSERT_LOGINRECORD,
            new Object[] { loginRecord.getUserid(), loginRecord.getAccount(),
                loginRecord.getLatitude(), loginRecord.getLongtitude() });
    }

    public List<LoginRecord> getModifiedInner24HoursData() {
        List<LoginRecord> list = getJdbcTemplate().query(Query_MODIFIED_INNER24_HOURS , new LoginRecordRowMapper());
        return list;
    }
    
    public void deleteUserLoginLog(String userid){
    	getJdbcTemplate().update(DELETE_USER_LOGIN_LOG, new Object[] { userid });
    }
    
}
