package com.arcapi.Leedv4Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class HumanExperienceConsumptionDeleteAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void HumanExperienceConsumptionDeleteAPI(String SheetName, String ProjectTypeColumn, int rownumber)
	 {

		

		int RowNum = 8;
		
		for (int i = 2; i <= RowNum; i++) {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/consumption/ID:"
					+ data.getCellData("DataInput", "HumanExperiencePK", i) + "/?recompute_score=false";
	
			CommonMethod.res = MethodCall.DELETERequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);

			
		}
	}

}