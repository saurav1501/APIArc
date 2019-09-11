package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class ConsumptionUpdatePUTAPITest extends BaseClass {

	@Test(groups="CheckUpdate")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void ConsumptionUpdatePUTAPI(String SheetName, String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData(SheetName, "MeterID", rownumber) + "/consumption/ID:"
					+ data.getCellData(SheetName, "PK", rownumber) + "/";
			CommonMethod.res = MethodCall.PUTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	}