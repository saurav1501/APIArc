package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class WasteUpdatePUTAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void WasteUpdatePUTAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/ID:"
				+ data.getCellData(SheetName, "WasteID", rownumber) + "/";
		
		CommonMethod.res =MethodCall.POSTRequest(url);

		Assertion.verifyStatusCode(CommonMethod.res, 404);
	}
	
}