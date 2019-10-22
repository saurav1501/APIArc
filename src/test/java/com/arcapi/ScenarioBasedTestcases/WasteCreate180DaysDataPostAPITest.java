package com.arcapi.ScenarioBasedTestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Utill.MeterData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WasteCreate180DaysDataPostAPITest extends BaseClass {
	
	@Test(groups="CreateWaterMeter")
	public void WaterCreate180DaysDataPostAPI() throws IOException {

	
	String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumTwo);
	String meterID =  data.getCellData(sheetName, "MeterID", rowNumTwo);
	
			
	MeterData meterData= new MeterData();
	meterData.setEnd_date("2019-10-28");
	meterData.setStart_date("2019-05-01");
	meterData.setReading("1000");

	url = "/assets/LEED:" +projectType+ "/meters/ID:" +meterID+"/consumption/?recompute_score=false";
	
	CommonMethod.res = MethodCall.POSTRequest(url, meterData);
	Assertion.verifyStatusCode(CommonMethod.res, 201);


}
}
