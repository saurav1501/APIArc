package com.arcapi.ScenarioBasedTestcases;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ExcludeSpacesWaterOccupantPUTAPITest extends BaseClass {
	
	@Test(groups="VerifyDatacoverage")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })

	public void SetGeneralAllOccupantPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/occupant/water/";
		
		map = new HashMap<>();
		map.put("start_date","2011-01-01");
		map.put("total_occupant","3");
		map.put("exclude_spaces", "true");

		CommonMethod.res = MethodCall.PUTRequest(url, map);
	
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	

}

}


