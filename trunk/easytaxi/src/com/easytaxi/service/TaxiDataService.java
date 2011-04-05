package com.easytaxi.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.service.BaseService;

public class TaxiDataService extends BaseService{
	
	private static TaxiDataService instance = new TaxiDataService();
	
	//用于接受出租车提供的相关数据，共后续处理
	private static BlockingQueue<Taxi> taxiWorkQueue = new LinkedBlockingQueue<Taxi>();
	
	//存放出租车信息
	private static ConcurrentMap<String , Taxi> taxiInfoMap = new ConcurrentHashMap<String, Taxi>();
	
	public TaxiDataService(){
		
	}
	
	public static TaxiDataService getInstance(){
		return instance == null ? new TaxiDataService():instance ;
	}
	
	
	public void offer(Taxi taxi){
		taxiWorkQueue.offer( taxi );
	}
	
	public BlockingQueue<Taxi> getTaxiWorkQueue(){
		return taxiWorkQueue ;
	}
	
	
	public ConcurrentMap<String , Taxi> getTaxiInfoMap(){
		return taxiInfoMap ;
	}
	
	public void updateTaxiInfo(Taxi taxi){
		taxiInfoMap.put(taxi.getPlateNumber(), taxi);
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(taxiWorkQueue.isEmpty());
	}
}
