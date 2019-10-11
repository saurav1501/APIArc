package com.arcapi.Transittestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ExcelTemplateMetersHEAPITest extends BaseClass {

	
	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ExcelTemplateMetersHEAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

	
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/?page_size=20";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			
			Integer meter = CommonMethod.res.path("count");
			for(int i=0;i<meter;i++){
			
	        Integer type = CommonMethod.res.path("results["+i+"].fuel_type.id");
	        System.out.println(type);
	        
	        if(type==257) {
	        	/*  AQI*/
	        	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	        	 data.setCellData("Graphs", "TransitHEMeterID", 2, meterID);	
	        }
	        if(type==258) {
	        	/*  PM 25*/
	        	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	        	 data.setCellData("Graphs", "TransitHEMeterID", 3, meterID);	
	        }
	        if(type==259) {
	        	/****PM10**********/
	        	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	        	 data.setCellData("Graphs", "TransitHEMeterID",4, meterID);	
	        }
	        if(type==260) {
	        	/* Ozone*/
	        	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	        	 data.setCellData("Graphs", "TransitHEMeterID",5, meterID);	
	        }
	        if(type==261) {
	        	/**Nitrogen Dioxide**/
	        	 System.out.println("No");
	        	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	        	 data.setCellData("Graphs", "TransitHEMeterID", 6, meterID);	
	        }
	        if(type==262) {
	        	/*************Sulfur Dioxide**/
	        	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	        	 data.setCellData("Graphs", "TransitHEMeterID",7, meterID);	
	        }
	        if(type==263) {
	        	/**CarbonMonoxide***/
	        	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	        	 data.setCellData("Graphs", "TransitHEMeterID", 8, meterID);	
	        }
	        if(type==264) {
	        	/**RiderShip***/
	        	 String meterID = CommonMethod.res.path("results["+i+"].id").toString();
	        	 data.setCellData("Graphs", "TransitHEMeterID", 9, meterID);	
	        }		
	        		
	    } 
	}
			
	}		
	

		

