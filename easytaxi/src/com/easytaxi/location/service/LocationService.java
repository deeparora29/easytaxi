package com.easytaxi.location.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.easytaxi.bo.UploadGPSData;
import com.easytaxi.common.SystemPara;
import com.easytaxi.common.service.BaseService;
import com.easytaxi.common.utils.google.map.parser.GoogleMapGeocode;

public class LocationService extends BaseService{

	Log log = LogFactory.getLog(LocationService.class);
	
	private GoogleMapGeocode googleMapGeocode;
	
	private TaxiDataService taxiDataService ;
	
	private PassengerDataService passengerDataService ;
	
	
	public String initLocationData(){
		ConcurrentMap<String , UploadGPSData> taxiMap = taxiDataService.getTaxiGPSMap();
		StringBuffer data = new StringBuffer("[");
		int i = 0 ;
		for (String userId : taxiMap.keySet()) {
			UploadGPSData taxi = taxiMap.get(userId);
			String status = SystemPara.getTaxiStatus(taxi.getStatus()) ;
			/*String taxiLocation = taxi.getTaxiLocation();
			if(taxiLocation==null||taxiLocation.trim().length()==0){
				String latLng = taxi.getLat() + "," +taxi.getLng();
				taxiLocation = googleMapGeocode.getAddressByLatLng(latLng);
			}*/
			data.append("{")
			.append("latLng : {lat : "+taxi.getGpsdata().getLat()+", lng : "+taxi.getGpsdata().getLng()+"},")
			.append("taxiStatus : '"+status+"',")
			.append("taxiNo : '"+taxi.getCab()+"'");
			//.append("driverNo : '"+taxi.getDriverNo()+"',")
			//.append("taxiStatus : '"+status+"',")
			//.append("taxiAddress : '"+taxiLocation+"' ");
			
			if(i != taxiMap.size()-1){
				data.append(",\n");
			}else{
				data.append("}");
			}
			i++ ;
		}
		data.append("]"); 
		/*
		StringBuffer data = new StringBuffer("[\n");
		data.append("	{\n");
		data.append("		latLng : {lat : 30.658602, lng : 104.06486},\n");
		data.append("		taxiNo : '川A000001' ,\n");
		data.append("		driverNo : '100001' ,\n");
		data.append("		taxiStatus : '空车' ,\n");
		data.append("		taxiAddress : '成都市青羊区1'\n ");
		data.append("	},\n");
		
		data.append("	{\n");
		data.append("		latLng : {lat : 30.648602, lng : 104.06486},\n");
		data.append("		taxiNo : '川A000002' ,\n");
		data.append("		driverNo : '100002' ,\n");
		data.append("		taxiStatus : '空车' ,\n");
		data.append("		taxiAddress : '成都市青羊区2'\n ");
		data.append("	},\n");
		
		data.append("	{\n");
		data.append("		latLng : {lat : 30.638602, lng : 104.05486},\n");
		data.append("		taxiNo : '川A000003' ,\n");
		data.append("		driverNo : '100003' ,\n");
		data.append("		taxiStatus : '空车' ,\n");
		data.append("		taxiAddress : '成都市青羊区3'\n ");
		data.append("	},\n");
		
		data.append("	{\n");
		data.append("		latLng : {lat : 30.648602, lng : 104.05486},\n");
		data.append("		taxiNo : '川A000004' ,\n");
		data.append("		driverNo : '100004' ,\n");
		data.append("		taxiStatus : '空车' ,\n");
		data.append("		taxiAddress : '成都市青羊区4'\n ");
		data.append("	},\n");
		
		data.append("	{\n");
		data.append("		latLng : {lat : 30.678602, lng : 104.06486},\n");
		data.append("		taxiNo : '川A000005' ,\n");
		data.append("		driverNo : '100005' ,\n");
		data.append("		taxiStatus : '负载' ,\n");
		data.append("		taxiAddress : '成都市青羊区5' \n");
		data.append("	}");
		data.append("]"); */
		return data.toString() ;
	}
	

	String url = "jdbc:mysql://127.0.0.1:3306/mmchannel";
	String userName = "root";
	String password = "";

	public Connection getConnection() {
		Connection con = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = (Connection) DriverManager.getConnection(url, this.userName,this.password);
		} catch (SQLException sw) {
		}
		return con;
	}

	public void testProc() {
		Connection conn = getConnection();
		CallableStatement stmt = null;
		try {
			stmt = conn.prepareCall("{call get_serial_num(?,?,?,?)}");
			stmt.setString(1, "taxi_no");
			stmt.setInt(2, 13);
			stmt.setString(3, "true");
			stmt.registerOutParameter(4, Types.VARCHAR);
			stmt.execute();
			String i = stmt.getString("s_value");
			System.out.println("count = " + i);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("hahad = " + e.toString());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception ex) {
				System.out.println("ex : " + ex.getMessage());
			}
		}
	}
	

	public static void main(String[] args) {
		new LocationService().testProc();
	}

	public GoogleMapGeocode getGoogleMapGeocode() {
		return googleMapGeocode;
	}

	public void setGoogleMapGeocode(GoogleMapGeocode googleMapGeocode) {
		this.googleMapGeocode = googleMapGeocode;
	}
	
	public TaxiDataService getTaxiDataService() {
		return taxiDataService;
	}

	public void setTaxiDataService(TaxiDataService taxiDataService) {
		this.taxiDataService = taxiDataService;
	}
	
	public PassengerDataService getPassengerDataService() {
		return passengerDataService;
	}

	public void setPassengerDataService(PassengerDataService passengerDataService) {
		this.passengerDataService = passengerDataService;
	}
}
