package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class MeterUpdatePUTAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void MeterUpdatePUTAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {


	try {
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
				+ data.getCellData(SheetName, "MeterID", rownumber) + "/";

		CommonMethod.res = MethodCall.PUTRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 400);
		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
	} catch (Exception e) {
		e.printStackTrace();
	}

}
}