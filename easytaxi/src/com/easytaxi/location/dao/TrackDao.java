package com.easytaxi.location.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.easytaxi.bo.TrackHistory;
import com.easytaxi.common.dao.BaseJdbcDao;

public class TrackDao extends BaseJdbcDao {

    private final static String SELECT_TRACK_USERID = "select * from trackhistory where userid=? ORDER BY trackid desc";

    private final static String SAVE_TRACK_HISTORIES = "insert into trackhistory(trackid,userid,type,begintime,endtime,trackfile,lat,lng) values(?,?,?,now(),now(),?,?,?)";

    
    class TrackHistoryRowMapper
        implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
            TrackHistory trackHistory = new TrackHistory();
            trackHistory.setBegintime(rs.getTimestamp("begintime"));
            trackHistory.setEndtime(rs.getTimestamp("endtime"));
            trackHistory.setUserid(rs.getString("userid"));
            trackHistory.setTrackfile(rs.getString("trackfile"));
            trackHistory.setType(rs.getInt("type"));
            trackHistory.setTrackid(rs.getString("trackid"));
            return trackHistory;
        }

    }

    public List<TrackHistory> getTrackHistoryList(String userid, int limits) {
        String sql = SELECT_TRACK_USERID;
        if (limits > 0)
            sql += " limit " + limits;
        return getJdbcTemplate().query(sql, new Object[] { userid }, new TrackHistoryRowMapper());
    }
    
    public void saveTrackHistories(TrackHistory trackHistory){
    	String trackid = getSerialNum("track_id", 12, "true");
    	getJdbcTemplate().update(SAVE_TRACK_HISTORIES, new Object[] { trackid,trackHistory.getUserid() ,
    			trackHistory.getType(),trackHistory.getTrackfile(),trackHistory.getLat(),trackHistory.getLng()});
    }

}
