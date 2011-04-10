package com.easytaxi.request.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.bo.CreditRecord;
import com.easytaxi.common.dao.BaseJdbcDao;

public class CreditRecordDao extends BaseJdbcDao {

    private final static String INSERT_CREDITRECORD = "insert into creditrecord (requstNo, userid, type, comments, credit, creditee, credit_time) values(?,?,?,?,?,?,now())";
    private final static String SELECT_CREDITRECORD_USERID = "select * from creditrecord where userid=? order by id desc";
    private final static String SELECT_CREDITRECORD_CREDITEE = "select * from creditrecord where creditee=? order by id desc";

    class CreditRecordRowMapper
        implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            CreditRecord record = new CreditRecord();
            record.setComments(rs.getString("comments"));
            record.setCredit(rs.getFloat("credit"));
            record.setCreditee(rs.getString("creditee"));
            record.setRequestNo(rs.getString("requestNo"));
            record.setType(rs.getInt("type"));
            record.setCreditTime(rs.getTimestamp("credit_time"));
            record.setCreditee(rs.getString("creditee"));
            return record;
        }

    }

    public void doSaveCreditRecord(CreditRecord record) {
        getJdbcTemplate().update(
            INSERT_CREDITRECORD,
            new Object[] { record.getRequestNo(), record.getUserid(), record.getType(), record.getComments(),
                record.getCredit(), record.getCreditee()
            });
    }

    public List<CreditRecord> getCreditRecordListByUserid(String userid, int limit) {
        String sql = SELECT_CREDITRECORD_USERID;
        if (limit > 0) {
            sql += SELECT_CREDITRECORD_USERID + " limit " + limit;
        }
        return (List<CreditRecord>) getJdbcTemplate().query(sql, new Object[] { userid }, new CreditRecordRowMapper());
    }

    public List<CreditRecord> getCreditRecordListByCreditee(String creditee, int limit) {
        String sql = SELECT_CREDITRECORD_USERID;
        if (limit > 0) {
            sql += SELECT_CREDITRECORD_CREDITEE + " limit " + limit;
        }
        return (List<CreditRecord>) getJdbcTemplate()
            .query(sql, new Object[] { creditee }, new CreditRecordRowMapper());
    }

}
