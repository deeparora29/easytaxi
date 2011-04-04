package com.easytaxi.service;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class LocationService {
	String url = "jdbc:mysql://127.0.0.1:3306/mmchannel"; 
    String userName = "root";
    String password = "";
    public Connection getConnection() {
        Connection con=null;
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = (Connection) DriverManager.getConnection(url, this.userName, this.password);
        }catch(SQLException sw){ 
         }
        return con;
    }
    public void testProc(){
        Connection conn = getConnection();
        CallableStatement stmt = null;
        try{
            stmt = conn.prepareCall("{call get_serial_num(?,?,?,?)}");  
            stmt.setString(1, "taxi_no");
            stmt.setInt(2, 13);
            stmt.setString(3, "true");
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.execute();
            String i= stmt.getString("s_value");
            System.out.println("count = " + i);
        }catch(Exception e){
        	e.printStackTrace();
            System.out.println("hahad = "+e.toString());
        }finally{
            try {
                stmt.close();
                conn.close();
            }catch (Exception ex) {
                System.out.println("ex : "+ ex.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new LocationService().testProc();
    }
}
