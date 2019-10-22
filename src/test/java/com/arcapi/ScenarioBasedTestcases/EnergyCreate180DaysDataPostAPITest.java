package com.arcapi.ScenarioBasedTestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class EnergyCreate180DaysDataPostAPITest extends BaseClass {
	
	@Test(groups="CreateMeter")
	public void EnergyCreate180DaysDataPostAPI() throws IOException {

	payload = MeterPayload.meterData5();
	String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumTwo);
	String meterID =  data.getCellData(sheetName, "MeterID", rowNumTwo);
	
			
	

	url = "/assets/LEED:" + projectType + "/meters/ID:" + meterID + "/consumption/?recompute_score=false";
	
	CommonMethod.res = MethodCall.POSTRequest(url, payload);
	Assertion.verifyStatusCode(CommonMethod.res, 201);


}
}
