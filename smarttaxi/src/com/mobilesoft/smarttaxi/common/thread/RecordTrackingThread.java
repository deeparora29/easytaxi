package com.mobilesoft.smarttaxi.common.thread;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mobilesoft.smarttaxi.bo.TrackLog;
import com.mobilesoft.smarttaxi.bo.UploadGPSData;
import com.mobilesoft.smarttaxi.common.utils.BeanFactoryUtil;
import com.mobilesoft.smarttaxi.location.service.PassengerDataService;
import com.mobilesoft.smarttaxi.location.service.TrackLogService;

public class RecordTrackingThread implements Runnable{
	private Log log = LogFactory.getLog(RecordTrackingThread.class);
	
	private ConcurrentMap<String ,String > trackingIdMap = new ConcurrentHashMap<String, String>() ;
	
	
	@Override
	public void run() {
		try {
			while( true ){
				PassengerDataService p_service = null;
				TrackLogService logservice = null;
				try {
					p_service = (PassengerDataService)BeanFactoryUtil.getBean("passengerDataService");
					logservice = (TrackLogService)BeanFactoryUtil.getBean("trackLogService");
				} catch (Exception e) {
					Thread.sleep( 60 * 1000 );
				}
				if(p_service!=null&&logservice!=null){
					ConcurrentMap<String , List<UploadGPSData>> p_GPSData = p_service.getPassengerTrackingMap();
					if(p_GPSData!=null&&p_GPSData.size()>0){
						for (ConcurrentMap.Entry<String, List<UploadGPSData>> entry : p_GPSData.entrySet()) {
							//userid
							String key = entry.getKey();
							List<UploadGPSData> gpsList = entry.getValue();
							for (UploadGPSData uploadGPSData : gpsList) {
								String userid = uploadGPSData.getUserId();
								String trackid = trackingIdMap.get(userid);
								double lat = uploadGPSData.getGpsdata().getLat();
								double lng = uploadGPSData.getGpsdata().getLng();
								String track = uploadGPSData.getTrack();
								if("start".equals(track)){
									trackid = logservice.getTrackId();
									trackingIdMap.put(userid, trackid);
								}
								TrackLog log  = new TrackLog(trackid,userid,1,lat,lng);
								
								logservice.saveTrackLog(log);
							}
							//重map中删除相关user gps数据
							p_GPSData.remove(key);
						}
					}else{
						Thread.sleep( 60 * 1000 );
					}
				}else{
					Thread.sleep( 60 * 1000 );
				}
				
				
			}
		} catch (Exception e) {
			log.error("检查账号会话线程运行失败：", e);
			try {
				Thread.sleep( 3 * 60 * 1000 );
			} catch (InterruptedException e1) {
			}
		}
		
	}

}
