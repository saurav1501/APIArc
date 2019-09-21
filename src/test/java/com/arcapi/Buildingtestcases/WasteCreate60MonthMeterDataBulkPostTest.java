package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Utill.WasteMeterData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;



public class WasteCreate60MonthMeterDataBulkPostTest extends BaseClass {

	@Test(groups="CreateMeter")
	public void CreateWasteMeterDataPost(){
	  	
	    try {
			String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone",rowNumTwo);		
			for(int i=2;i<=63;i++) {
				
				String start_date =  data.getCellData("MeterData", "startDate", i);
				String end_date =  data.getCellData("MeterData", "endDate", i);
				String waste_generated =  data.getCellData("MeterData", "reading1", i);
				String waste_diverted =  data.getCellData("MeterData", "reading2", i);
				String unit =  data.getCellData("MeterData", "unit", i);
				
				WasteMeterData meterData= new WasteMeterData();
				meterData.setUnit(unit);
				meterData.setStart_date(start_date);
				meterData.setEnd_date(end_date);
				meterData.setWaste_generated(waste_generated);
				meterData.setWaste_diverted(waste_diverted);
			
			url = "/assets/LEED:" + projectType + "/waste/";
			CommonMethod.res = MethodCall.POSTRequest(url, meterData);
			Assertion.verifyStatusCode(CommonMethod.res , 201);
			
}
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
	   
	}
	

}