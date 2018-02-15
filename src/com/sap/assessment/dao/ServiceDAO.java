package com.sap.assessment.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.sap.assessment.database.DatabaseClass;

public class ServiceDAO {
	
	
	DatabaseClass dbc = new DatabaseClass();

	public ServiceDAO() {
		// TODO Auto-generated constructor stub
	}
	


	public String checkPark(String size, String carNum) {
	
		Connection conn = dbc.getConnection();
		Statement stmt,stmt1,stmt2,stmt3,stmt4 = null;
		String parkNum = null;
		int result = 0;
		int count = 0;
		int up_parkAvail=0;
		Timestamp inTime = new Timestamp(System.currentTimeMillis());
		Random r = new Random();
		try{
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select count from park_avail where size='"+size+"'");
		stmt1 = conn.createStatement();
		ResultSet exists = stmt1.executeQuery("Select car_num from park_hist where car_num='"+carNum+"' and Amt=0 and outtime='0000-00-00 00:00:00'");
		
		while(exists.next()){
		if(exists.getString(1).equalsIgnoreCase(carNum))return "Car with number:"+carNum+" is in the Parking Lot, you cannot park the same car!";	
		}
		while(rs.next()){
			if(rs.getInt(1)>0)parkNum = String.valueOf(r.nextInt((99999 - 10000) + 1) + 10000);
			else return "Parking Lot is full!";
		}
		stmt2 = conn.createStatement();
		result = stmt2.executeUpdate("Insert into park_hist "+ "values('"+carNum+"',"+parkNum+",'"+size+"','"+inTime+"','0000-00-00 00:00:00',0)");
		stmt3 = conn.createStatement();
		ResultSet park_count = stmt3.executeQuery("select count from park_avail where size='"+size+"'");
		while(park_count.next()){
			
			count = park_count.getInt(1);
		}
		count = count-1;
		stmt4 = conn.createStatement();
		up_parkAvail = stmt4.executeUpdate("UPDATE park_avail SET count="+count+" where size='"+size+"'");
		conn.close();
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		if(result==1 && up_parkAvail==1)return parkNum;
		else return "Car cannot be parked at this time, sorry for the inconvenince";
	}


	public String checkOut(String parkNum, String carNum) {
		Connection conn = dbc.getConnection();
		Statement stmt = null;
		Timestamp inTime= null;
		String size = null;
		int Amt = 0;
		int count = 0;
		Timestamp outTime = new Timestamp(System.currentTimeMillis());
		try{
			stmt = conn.createStatement();
			ResultSet checkOut = stmt.executeQuery("Select intime, size from park_hist where park_num='"+parkNum+"' and car_num='"+carNum+"' and outtime='0000-00-00 00:00:00' and Amt=0");
			checkOut.last();
			int rowCount = checkOut.getRow();
			if(rowCount==0)return "Car with car number - "+carNum+" and parking number - "+parkNum+" is already checked out or never parked";
			checkOut.beforeFirst();
			while(checkOut.next()){
				inTime = checkOut.getTimestamp(1);
				size = checkOut.getString(2);				
			}
			long diff = outTime.getTime() - inTime.getTime();
			long diffMin = diff/ (60*1000) % 60;
			if(diffMin>0)diffMin=1;
			long  diffHour = diff / (60*60*1000)%24;
			long diffDays = diff / (24*60*60*1000);
			
			if(size.equalsIgnoreCase("small"))
				Amt = (int) (diffDays * 50 + diffHour * 20 + diffMin * 20);
			else if(size.equalsIgnoreCase("Medium"))
				Amt = (int)(diffDays * 100 + diffHour * 30 + diffMin * 30);
			else
				Amt = (int)(diffDays * 200 + diffHour * 40 + diffMin * 40);
			
			int up_parkHist = stmt.executeUpdate("UPDATE park_hist SET amt="+Amt+", outtime='"+outTime+"' WHERE car_num='"+carNum+"' and park_num='"+parkNum+"' and intime='"+inTime+"'");
			ResultSet park_count = stmt.executeQuery("select count from park_avail where size='"+size+"'");
			while(park_count.next()){
				
				count = park_count.getInt(1);
			}
			count = count+1;
			int up_parkAvail = stmt.executeUpdate("UPDATE park_avail SET count="+count+" where size='"+size+"'");
			
			if(up_parkHist==1 && up_parkAvail==1)return String.valueOf(Amt);
			
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return "Car cannot be checked out now, sorry for the inconvenience!";
	}
}
