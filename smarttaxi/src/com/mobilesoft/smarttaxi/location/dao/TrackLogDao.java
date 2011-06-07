package com.mobilesoft.smarttaxi.location.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mobilesoft.smarttaxi.bo.TrackLog;
import com.mobilesoft.smarttaxi.common.dao.BaseJdbcDao;

public class TrackLogDao extends BaseJdbcDao {

	
	 private final static String SAVE_TRACK_LOG = "insert into trackhistory(trackid,userid,type,createtime,lat,lng) values(?,?,?,now(),?,?)";

	    
	    class TrackLogRowMapper implements RowMapper {

	        @Override
	        public Object mapRow(ResultSet rs, int arg1) throws SQLException {
	        	TrackLog log = new TrackLog();
	        	log.setId(rs.getInt("id"));
	        	log.setCreatetime(rs.getDate("createtime"));
	        	log.setLat(rs.getDouble("lat"));
	        	log.setLng(rs.getDouble("lng"));
	        	log.setTrackid(rs.getString("trackid"));
	        	log.setUserid(rs.getString("userid"));
	        	log.setType(rs.getInt("type"));
	            return log;
	        }

	    }
	    
	    public void saveTrackLog(TrackLog log){
	    	getJdbcTemplate().update(SAVE_TRACK_LOG, new Object[] { log.getTrackid(),log.getUserid() ,
	    			log.getType(),log.getLat(),log.getLng()});
	    }
	
}
