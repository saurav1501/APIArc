package com.arcapi.Transittestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifySulphurDioxideGraphValuesAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifySulphurDioxideGraphValuesAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {
		 
			try {
				String SO_Meter_ID = data.getCellData("Graphs", "TransitHEMeterID", 7);	
				url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/resampled/"+SO_Meter_ID+"/?start_date=2018-01-01&end_2020-01-01";
				CommonMethod.res = MethodCall.GETRequest(url);	 
				for(int j=0;j<12;j++) {
				CommonMethod.fetchedID = CommonMethod.res.path("reading["+j+"]").toString();
				String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
				String Act_Reading = data.getCellData("Graphs", "SulphurDioxide", j+2);
				Assertion.verifyData(reading, Act_Reading);
				Assertion.verifyStatusCode( CommonMethod.res, 200);
						
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
}}