package com.sap.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.assessment.service.ParkService;
import com.sap.assessment.service.ParkingTest;

@RestController
@SpringBootApplication
@ComponentScan("com.sap.assessment")
public class Parking{
	@Autowired
	ParkService ps;

	 @CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(method=RequestMethod.POST, value="/park")
	public String checkPark(@RequestParam("size") String size, @RequestParam("carNum") String carNum){
		return ps.checkPark(size,carNum);
	}
	 @CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping(method=RequestMethod.POST, value="/checkout")
	public String checkOut(@RequestParam("parkNum") String parkNum, @RequestParam("carNum") String carNum){
		return ps.checkOut(parkNum,carNum);
	}
	
	  public static void main(String[] args) throws Exception {
	        SpringApplication.run(Parking.class, args);
	    }
	
}
