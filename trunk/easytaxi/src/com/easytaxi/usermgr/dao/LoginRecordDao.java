package com.easytaxi.usermgr.dao;

import com.easytaxi.common.dao.BaseJdbcDao;
import com.easytaxi.usermgr.bo.LoginRecord;

public class LoginRecordDao extends BaseJdbcDao {
    private final static String INSERT_LOGINRECORD = "insert into (userid, type, phone, latitude, longtitude, login_time) values(?,?,?,?,?,now())";

    /**
     * @method: save the login record
     * @param loginRecord
     */
    public void doSaveLoginRecord(LoginRecord loginRecord) {
        getJdbcTemplate().update(
            INSERT_LOGINRECORD,
            new Object[] { loginRecord.getUserid(), loginRecord.getType(), loginRecord.getPhone(),
                loginRecord.getLatitude(), loginRecord.getLongtitude() });
    }

}
