package com.easytaxi.common.thread;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easytaxi.bo.Taxi;
import com.easytaxi.common.utils.BeanFactoryUtil;
import com.easytaxi.location.service.TaxiDataService;

/**
 * 
 * @Package com.easytaxi.common.thread
 *
 * @Title: TaxiDataManageThread.java 
 *	
 * @Description: 出租车数据后台处理线程
 *
 * @Copyright:   Copyright (c)2010
 *
 * @author:      付奎
 *
 * @date 2011-3-27 下午10:27:34
 *
 */
public class TaxiDataManageThread implements Runnable{

	private Log log = LogFactory.getLog(TaxiDataManageThread.class);
	
	
	@Override
	public void run() {
		while (true) {
			try {
				TaxiDataService service = null;
				try {
					service = (TaxiDataService) BeanFactoryUtil.getBean("taxiDataService");
				} catch (Exception e) {
					Thread.sleep(30 * 1000);
				}
				if (service != null) {
					List<Taxi> taxiList = service.getModifiedInner24HoursData();
					if (taxiList != null && taxiList.size() > 0) {
						log.info("系统中会话存在超过24小时的出租车数量为：" + taxiList.size());
						for (Taxi taxi : taxiList) {
							ConcurrentMap<String, Taxi> temp = service.getTaxiLoginInfoMap();
							if (taxi != null) {
								temp.remove(taxi.getUserid());
								log.info("系统检测到用户： " + taxi.getUserid()+ "登录时间超过24小时，需要重新登录");
							}
						}
					} else {
						Thread.sleep(60 * 1000);
					}
				}
			} catch (Exception e) {
				log.error("出租车数据后台处理线程运行失败：", e);
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
				}
			}
		}
		
		
	}

}
