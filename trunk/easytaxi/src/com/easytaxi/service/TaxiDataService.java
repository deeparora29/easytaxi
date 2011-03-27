package com.easytaxi.service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import com.easytaxi.bo.Taxi;
import com.easytaxi.common.service.BaseService;

public class TaxiDataService extends BaseService{
	
	private static TaxiDataService instance = new TaxiDataService();
	
	private static BlockingQueue<Taxi> taxiWorkQueue = new ArrayBlockingQueue<Taxi>(100);
	
	private TaxiDataService(){
		
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
	
	public static void main(String[] args) {
		System.out.println(taxiWorkQueue.isEmpty());
	}
}
