package com.easytaxi.common.thread;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.bo.TrackHistory;
import com.easytaxi.bo.UploadGPSData;
import com.easytaxi.common.utils.BeanFactoryUtil;
import com.easytaxi.location.service.PassengerDataService;
import com.easytaxi.location.service.TrackService;

public class RecordTrackingThread implements Runnable{
	private Log log = LogFactory.getLog(RecordTrackingThread.class);
	@Override
	public void run() {
		try {
			while( true ){
				if(BeanFactoryUtil.getBean("loginRecordDao")==null){
					Thread.sleep( 60 * 1000 );
				}
				PassengerDataService p_service = (PassengerDataService)BeanFactoryUtil.getBean("passengerDataService");
				TrackService trackService = (TrackService)BeanFactoryUtil.getBean("trackService");
				ConcurrentMap<String , List<UploadGPSData>> p_GPSData = p_service.getPassengerTrackingMap();
				if(p_GPSData!=null&&p_GPSData.size()>0){
					for (ConcurrentMap.Entry<String, List<UploadGPSData>> entry : p_GPSData.entrySet()) {
						//userid
						String key = entry.getKey();
						List<UploadGPSData> gpsList = entry.getValue();
						for (UploadGPSData uploadGPSData : gpsList) {
							TrackHistory trackHistory  = new TrackHistory();
							trackHistory.setType(1);
							trackHistory.setUserid(key);
							double lat = uploadGPSData.getGpsdata().getLat();
							double lng = uploadGPSData.getGpsdata().getLng();
							trackHistory.setLat(lat);
							trackHistory.setLng(lng);
							trackService.saveTrackHistories(trackHistory);
						}
					}
				}else{
					Thread.sleep( 60 * 1000 );
				}
			}
		} catch (Exception e) {
			log.error("检查账号会话线程运行失败：", e);
		}
		
	}

}
