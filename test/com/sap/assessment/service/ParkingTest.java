package com.sap.assessment.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ParkingTest {
	
	private ParkService parkService = new ParkService();


	@Test
	public void parkSuccess() {
		String parkNum=parkService.checkPark("Large", "54692");
		int parkNumLen = parkNum.length();
		assertEquals(parkNumLen,5);
	}
	
	@Test
	public void parkFail() {
		String failResponse=parkService.checkPark("Medium", "54688");
		//int parkNumLen = parkNum.length();
		assertEquals(failResponse,"Car with number:54688 is in the Parking Lot, you cannot park the same car!");
	}
	
	@Test
	public void checkFail() {
		String failResponse=parkService.checkOut("16195", "113428");
		//int parkNumLen = parkNum.length();
		assertEquals(failResponse,"Car with car number - 113428 and parking number - 16195 is already checked out or never parked");
	}
	
	@Test
	public void checkSuccess() {
		String successResponse=parkService.checkOut("95692", "12312");
		//int parkNumLen = parkNum.length();
		assertEquals(successResponse,"630");
	}
	
	

}
