package com.arcapi.release1_18testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class OccupantGetAPITest extends BaseClass {

	@Test(groups="CheckOccupant")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void OccupantGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

			
		try {
			String val[] = {"general","transport","human"};
			
			for(String value : val) {

			url = "/assets/LEED:"+ data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						+ "/occupant/"+value+"/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}