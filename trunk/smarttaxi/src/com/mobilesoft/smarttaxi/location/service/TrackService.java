package com.mobilesoft.smarttaxi.location.service;

import java.util.ArrayList;
import java.util.List;

import com.mobilesoft.smarttaxi.bo.TrackHistory;
import com.mobilesoft.smarttaxi.common.service.BaseService;
import com.mobilesoft.smarttaxi.location.dao.TrackDao;

public class TrackService extends BaseService {

    private TrackDao trackDao;

    public void setTrackDao(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    public TrackDao getTrackDao() {
        return trackDao;
    }

    public List<TrackHistory> getTrackHistories(String userid, int limits) {
        List<TrackHistory> list = new ArrayList<TrackHistory>();
        try {
            list = getTrackDao().getTrackHistoryList(userid, limits);
        } catch (Exception e) {
            logger.error("Get Track history by user[" + userid + "] error: ", e);
        }
        return list;
    }

    
    public void saveTrackHistories(TrackHistory trackHistory){
    	trackDao.saveTrackHistories(trackHistory);
    }
    
    
}
