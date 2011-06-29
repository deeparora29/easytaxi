package com.mobilesoft.smarttaxi.track.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.mobilesoft.smarttaxi.bo.GPSData;
import com.mobilesoft.smarttaxi.common.dao.BaseJdbcDao;
import com.mobilesoft.smarttaxi.track.bo.GPSTrack;

/**
 * 
 * @author renmian
 * 
 */
public class GPSTrackDao extends BaseJdbcDao {

	private static String INSERT_TRACKLOG = "insert into tracklog (trackid, userid, requestNo, type, createtime, lat, lng, speed, direction, status) "
			+ "values (?,?,?,?,?,?,?,?,?,?)";

	private static String INSERT_REALTIMEGPSDATA = "insert into realtimegpsdata (userid, lat, lng, createtime) "
			+ "values (?, ?, ?, now())";

	private static String UPDATE_REALTIMEGPSDATA = "update realtimegpsdata set lat=?, lng=?, createtime=now() where userid=?";

	private static String DELETE_REALTIMEGPSDATA = "delete from realtimegpsdata where userid=?";

	private static String SELECT_REALTIMEGPSDATA_USERID = "select * from realtimegpsdata where userid=?";

	private static String SELECT_TRACKLOG = "select * from tracklog where 1=1";

	private static String SELECT_TRACKLOG_USERID_LATEST = "SELECT * FROM tracklog WHERE id=(SELECT MAX(id) FROM tracklog WHERE userid=?)";

	private static String SELECT_TRACKLOG_REQUESTNO = "SELECT * FROM tracklog WHERE requestNo=? and userid=?";
	
	/**
	 * save realtime GPS Data
	 * @param data
	 */
	public void saveRealtimeGPSData(GPSData data) {
		getJdbcTemplate().update(DELETE_REALTIMEGPSDATA,
				new Object[] { data.getUserid() });
		getJdbcTemplate()
				.update(
						INSERT_REALTIMEGPSDATA,
						new Object[] { data.getUserid(), data.getLat(),
								data.getLng() });
	}
	
	/**
	 * query the realtime gps data
	 * @param userid
	 * @return
	 */
	public GPSData getRealtimeGPSData(String userid) {
		return (GPSData) getJdbcTemplate().queryForObject(
				SELECT_REALTIMEGPSDATA_USERID, new Object[] { userid },
				new GPSDataRowMapper());
	}

	/**
	 * Save GPS data
	 * 
	 * @param tracker
	 */
	public void saveTrackLog(GPSTrack tracker) {
		getJdbcTemplate().update(
				INSERT_TRACKLOG,
				new Object[] { tracker.getTrackid(), tracker.getUserid(),
						tracker.getRequestNo(), tracker.getType(),
						tracker.getCreatetime(), tracker.getLat(),
						tracker.getLng(), tracker.getSpeed(),
						tracker.getDirection(), tracker.getStatus() });
	}

	/**
	 * get user's latest track
	 * 
	 * @param userid
	 * @return
	 */
	public GPSTrack getLatestGPSTrack(String userid) {
		return (GPSTrack) getJdbcTemplate().queryForObject(
				SELECT_TRACKLOG_USERID_LATEST, new Object[] { userid },
				new GPSTrackRowMapper());
	}

	/**
	 * get GPS Track By requestNo
	 * 
	 * @param requestNo
	 * @param userid
	 * @return
	 */
	public List<GPSTrack> getGPSTrackByRequestNo(String requestNo, String userid) {
		return (List<GPSTrack>) getJdbcTemplate().query(
				SELECT_TRACKLOG_REQUESTNO, new Object[] { requestNo, userid },
				new GPSTrackRowMapper());
	}

	class GPSTrackRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			GPSTrack tracker = new GPSTrack();
			tracker.setCreatetime(rs.getTimestamp("createtime"));
			tracker.setDirection(rs.getInt("direction"));
			tracker.setLat(rs.getDouble("lat"));
			tracker.setLng(rs.getDouble("lng"));
			tracker.setRequestNo(rs.getString("requestNo"));
			tracker.setSpeed(rs.getFloat("speed"));
			tracker.setStatus(rs.getInt("status"));
			tracker.setTrackid(rs.getString("trackid"));
			tracker.setType(rs.getInt("type"));
			tracker.setUserid(rs.getString("userid"));
			return tracker;
		}

	}

	class GPSDataRowMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			GPSData gps = new GPSData();
			gps.setCreatetime(rs.getTimestamp("createtime"));
			gps.setLat(rs.getDouble("lat"));
			gps.setLng(rs.getDouble("lng"));
			gps.setUserid(rs.getString("userid"));
			return gps;
		}

	}

}
