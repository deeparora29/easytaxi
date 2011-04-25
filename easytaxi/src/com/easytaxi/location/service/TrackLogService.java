package com.easytaxi.location.service;

import com.easytaxi.bo.TrackLog;
import com.easytaxi.location.dao.TrackLogDao;

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
