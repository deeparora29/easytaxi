package com.mobilesoft.smarttaxi.track.service;

import com.mobilesoft.smarttaxi.bo.GPSData;
import com.mobilesoft.smarttaxi.common.service.BaseService;
import com.mobilesoft.smarttaxi.track.bo.GPSTrack;
import com.mobilesoft.smarttaxi.track.dao.GPSTrackDao;

public class GPSTrackService extends BaseService {
	
	private GPSTrackDao gpsTrackDao;

	public void setGpsTrackDao(GPSTrackDao gpsTrackDao) {
		this.gpsTrackDao = gpsTrackDao;
	}

	public GPSTrackDao getGpsTrackDao() {
		return gpsTrackDao;
	}
	
	public void updateRealtimeGPSData(GPSData data){
		try {
			getGpsTrackDao().saveRealtimeGPSData(data);
		} catch (Exception e) {
			logger.error("updateRealtimeGPSData [ " + data.getUserid() + "]", e);
		}
	}
	
	public GPSData getRealtimeGPSData(String userid){
		try {
			return getGpsTrackDao().getRealtimeGPSData(userid);
		} catch (Exception e) {
			logger.error("getRealtimeGPSData [ " + userid + "]", e);
		}
		return null;
	}
	
	public void saveGPSData(GPSTrack gpsTrack){
		try {
			getGpsTrackDao().saveTrackLog(gpsTrack);
		} catch (Exception e) {
			logger.error("SaveGPSData Error", e);
		}
	}
	
	public GPSTrack getLatestGPSData(String userid){
		try {
			return getGpsTrackDao().getLatestGPSTrack(userid);
		} catch (Exception e) {
			logger.error("getLatestGPSData [" + userid + "]:", e);
		}
		return null;
	}

}
