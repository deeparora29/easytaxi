package com.mobilesoft.smarttaxi.common.thread;

import org.apache.log4j.Logger;

public class PassengerDataManageThread extends Thread {
	
	private Logger log = Logger.getLogger(PassengerDataManageThread.class);
	
	public void run(){
		log.debug("Save Passenger Info");
		
	}

}
