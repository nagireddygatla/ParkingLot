package com.sap.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.sap.assessment.dao.ServiceDAO;

@Service
@ComponentScan("com.sap.assessment")
public class ParkService {
	
	ServiceDAO sd = new ServiceDAO();

	
	public String checkPark(String size, String carNum){
		
		return sd.checkPark(size,carNum);
	}
	

	public String checkOut(String parkNum, String carNum) {
		
		return sd.checkOut(parkNum, carNum);
	}

}
