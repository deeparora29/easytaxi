package com.easytaxi.location.service;

import java.util.ArrayList;
import java.util.List;

import com.easytaxi.bo.TrackHistory;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.location.dao.TrackDao;

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
