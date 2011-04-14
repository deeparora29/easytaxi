package com.easytaxi.request.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.common.dao.BaseJdbcDao;
import com.easytaxi.request.bo.RequestInfo;

/**
 * CallTaxiDao
 * 
 * @author rennnmia
 */
public class CallTaxiDao extends BaseJdbcDao {

    private final static String INSERT_REQUESTINFO = "insert into requestinfo (requestNo, userid, start_long, start_lat, start_text,"
        + " end_long, end_lat, end_text, number, luggage, comments, share, status, request_time) "
        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
    
    private final static String UPDATE_REQUESTINFO = "update requestinfo set status=?, operator_time=now(), operatorid=?, operator_type=?, operator_comments=? WHERE requestNo=?";

    private final static String DELETE_REQUESTINFO = "delete from requestinfo where requestNo=?";

    private final static String SELETE_REQUESTINFO_REQUESTNO = "select * from requestinfo where requestNo=?";

    private final static String SELECT_REQUESTINFO_STATUS = "select * from requestinfo where status=?";

    class RequestInfoRowMapper
        implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setComments(rs.getString("comments"));
            requestInfo.setEndLat(rs.getDouble("end_lat"));
            requestInfo.setEndLong(rs.getDouble("end_long"));
            requestInfo.setEndText(rs.getString("end_text"));
            requestInfo.setLuggage(rs.getInt("luggage"));
            requestInfo.setNumber(rs.getInt("number"));
            requestInfo.setOperatorComments(rs.getString("operator_comments"));
            requestInfo.setOperatorid(rs.getString("operatorid"));
            requestInfo.setOperatorTime(rs.getTimestamp("operator_time"));
            requestInfo.setOperatorType(rs.getInt("operator_type"));
            requestInfo.setRequestNo(rs.getString("requestNo"));
            requestInfo.setRequestTime(rs.getTimestamp("request_time"));
            requestInfo.setShare(rs.getString("share"));
            requestInfo.setStartLat(rs.getDouble("start_lat"));
            requestInfo.setStartLong(rs.getDouble("start_long"));
            requestInfo.setStartText(rs.getString("start_text"));
            requestInfo.setStatus(rs.getInt("status"));
            requestInfo.setUserid(rs.getString("userid"));
            return requestInfo;
        }

    }

    /**
     * @method insert to requestinfo table
     * @param requestInfo
     */
    public void doSaveRequestInfo(RequestInfo requestInfo) {
        getJdbcTemplate().update(
            INSERT_REQUESTINFO,
            new Object[] { requestInfo.getRequestNo(), requestInfo.getUserid(), requestInfo.getStartLong(),
                requestInfo.getStartLat(), requestInfo.getStartText(), requestInfo.getEndLong(),
                requestInfo.getEndLat(), requestInfo.getEndText(), requestInfo.getNumber(), requestInfo.getLuggage(),
                requestInfo.getComments(), requestInfo.getShare(), requestInfo.getStatus() });
    }

    /**
     * update requestinfo according to operator
     * 
     * @param requestInfo
     */
    public void doUpdateRequestInfoByOperator(RequestInfo requestInfo) {
        getJdbcTemplate().update(
            UPDATE_REQUESTINFO,
            new Object[] { requestInfo.getStatus(), requestInfo.getOperatorid(), requestInfo.getOperatorType(),
                requestInfo.getOperatorComments() ,requestInfo.getRequestNo() });
    }

    /**
     * delete the requestinfo according to requestNo
     * 
     * @param requestNo
     */
    public void doDeleteRequestInfo(String requestNo) {
        getJdbcTemplate().update(DELETE_REQUESTINFO, new Object[] { requestNo });
    }

    /**
     * Query RequestInfo according to requestNo
     * 
     * @param requestNo
     * @return
     */
    public RequestInfo getRequestInfo(String requestNo) {
        List<RequestInfo> list = getJdbcTemplate().query(SELETE_REQUESTINFO_REQUESTNO, new Object[] { requestNo },
            new RequestInfoRowMapper());
        return (RequestInfo) getObjectFromList(list);
    }

    /**
     * 根据时段和状态查询requestInfo
     * 
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    public List<RequestInfo> getRequestInfoBy(int status, String startTime, String endTime) {
        List<RequestInfo> list = new ArrayList<RequestInfo>();
        String sql = SELECT_REQUESTINFO_STATUS;
        if (StringUtils.isNotEmpty(startTime)) {
            sql += " and DATE_FORMAT(request_time, '%Y-%m-%d %H:%i:%s')>='" + startTime + "'";
            if (StringUtils.isNotEmpty(endTime))
                sql += " and DATE_FORMAT(request_time, '%Y-%m-%d %H:%i:%s')<='" + endTime + "'";
        }
        list = getJdbcTemplate().query(sql, new Object[] { new Integer(status) }, new RequestInfoRowMapper());
        return list;
    }

}
