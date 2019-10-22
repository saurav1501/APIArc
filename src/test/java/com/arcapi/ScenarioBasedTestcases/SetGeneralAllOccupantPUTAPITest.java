package com.arcapi.ScenarioBasedTestcases;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class SetGeneralAllOccupantPUTAPITest extends BaseClass {
	
	@Test(groups="VerifyDatacoverage")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })

	public void SetGeneralAllOccupantPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

	url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/";
	
	map = new HashMap<>();
	map.put("survey_score_occupant","total_occupant");

	CommonMethod.res = MethodCall.PUTRequest(url, map);
	
	Assertion.verifyStatusCode(CommonMethod.res, 200);
	

}

}


