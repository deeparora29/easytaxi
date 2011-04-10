package com.easytaxi.request.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.bo.CreditRecord;
import com.easytaxi.common.dao.BaseJdbcDao;

public class CreditRecordDao extends BaseJdbcDao {

    private final static String INSERT_CREDITRECORD = "insert into creditrecord (requstNo, userid, comments, credit, credit_userid, credit_time) values(?,?,?,?,?,now())";
    private final static String SELECT_CREDITRECORD_USERID = "select * from creditrecord where userid=? order by id desc";
    private final static String SELECT_CREDITRECORD_CREDIT_USERID = "select * from creditrecord where credit_userid=? order by id desc";
    private final static String SELECT_CREDITRECORD_CAB = "SELECT a.*, b.plate_number FROM creditrecord a, taxi b WHERE a.credit_userid=b.userid AND b.plate_number=? ORDER BY id desc";

    class CreditRecordRowMapper
        implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            CreditRecord record = new CreditRecord();
            record.setUserid(rs.getString("userid"));
            record.setComments(rs.getString("comments"));
            record.setCredit(rs.getFloat("credit"));
            record.setCreditUserid(rs.getString("credit_userid"));
            record.setRequestNo(rs.getString("requestNo"));
            // record.setType(rs.getInt("type"));
            record.setCreditTime(rs.getTimestamp("credit_time"));
            return record;
        }

    }

    /**
     * insert a record
     * 
     * @param record
     */
    public void doSaveCreditRecord(CreditRecord record) {
        getJdbcTemplate().update(
            INSERT_CREDITRECORD,
            new Object[] { record.getRequestNo(), record.getUserid(), record.getComments(), record.getCredit(),
                record.getCreditUserid()
            });
    }

    /**
     * 根据userid获取列表
     * 
     * @param userid
     * @param limit
     * @return
     */
    public List<CreditRecord> getCreditRecordListByUserid(String userid, int limit) {
        String sql = SELECT_CREDITRECORD_USERID;
        if (limit > 0) {
            sql += " limit " + limit;
        }
        return (List<CreditRecord>) getJdbcTemplate().query(sql, new Object[] { userid }, new CreditRecordRowMapper());
    }

    /**
     * 根据评价者获取列表
     * 
     * @param creditUserid
     * @param limit
     * @return
     */
    public List<CreditRecord> getCreditRecordListByCreditUserid(String creditUserid, int limit) {
        String sql = SELECT_CREDITRECORD_CREDIT_USERID;
        if (limit > 0) {
            sql += " limit " + limit;
        }
        return (List<CreditRecord>) getJdbcTemplate().query(sql, new Object[] { creditUserid },
            new CreditRecordRowMapper());
    }

    /**
     * 根据出租车号码获取列表
     * 
     * @param cab
     * @param limit
     * @return
     */
    public List<CreditRecord> getCreditRecordListByCab(String cab, int limit) {
        String sql = SELECT_CREDITRECORD_CAB;
        if (limit > 0) {
            sql += " limit " + limit;
        }
        return (List<CreditRecord>) getJdbcTemplate().query(sql, new Object[] { cab }, new CreditRecordRowMapper());
    }

}
