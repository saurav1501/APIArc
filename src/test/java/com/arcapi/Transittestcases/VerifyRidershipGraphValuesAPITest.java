package com.arcapi.Transittestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyRidershipGraphValuesAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyRidershipGraphValuesAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
	
		for(int i=2;i<15;i++) {
		String MeterName = data.getCellData("Graphs", "TransitHEMeterName", +  i);

		if (MeterName.equalsIgnoreCase("Ridership")) {
			String Ridership_Meter_ID = data.getCellData("Graphs", "TransitHEMeterID", i);

		     url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/resampled/"+Ridership_Meter_ID+"/?start_date=2018-01-01&end_2020-01-01";
			 CommonMethod.res = MethodCall.GETRequest(url);	
			 Assertion.verifyStatusCode(CommonMethod.res, 200);
			 for(int j=0;j<12;j++) {
			 CommonMethod.fetchedID = CommonMethod.res.path("reading["+j+"]").toString();
			 String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
			 String Act_Reading = data.getCellData("Graphs", "Ridership", j+2);
			 Assertion.verifyData(reading, Act_Reading);
									
			}
			
		}
	}
	
			
}}