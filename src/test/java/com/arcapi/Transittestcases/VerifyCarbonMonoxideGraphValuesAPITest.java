package com.arcapi.Transittestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyCarbonMonoxideGraphValuesAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyCarbonMonoxideGraphValuesAPI(String SheetName,String ProjectTypeColumn, int rownumber) {
        
		try {
			String CO_Meter_ID = data.getCellData("Graphs", "TransitHEMeterID", 8);	
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/resampled/"+CO_Meter_ID+"/?start_date=2018-01-01&end_2020-01-01"; 	
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			 
			 for(int j=0;j<12;j++) {
				 
					CommonMethod.fetchedID = CommonMethod.res.path("reading["+j+"]").toString();
					String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
					System.out.println(reading);
				 	String Act_Reading = data.getCellData("Graphs", "CarbonMonoxide", j+2);
					Assertion.verifyData(reading, Act_Reading);
					
			 	}
		} catch (Exception e) {
				e.printStackTrace();
		}
			
}}