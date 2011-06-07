package com.mobilesoft.smarttaxi.location.service;

import com.mobilesoft.smarttaxi.bo.TrackLog;
import com.mobilesoft.smarttaxi.location.dao.TrackLogDao;

public class TrackLogService {
	private TrackLogDao trackLogDao;
	
	public void saveTrackLog(TrackLog log){
		if("".equals(log.getTrackid())){
			String trackId = trackLogDao.getSerialNum("track_id", 12, "true");
			log.setTrackid(trackId);
		}
		
		trackLogDao.saveTrackLog(log);
	}
	
	public String getTrackId(){
    	return trackLogDao.getSerialNum("track_id", 12, "true");
    	
    }

	public TrackLogDao getTrackLogDao() {
		return trackLogDao;
	}

	public void setTrackLogDao(TrackLogDao trackLogDao) {
		this.trackLogDao = trackLogDao;
	}
	
	
	
}
