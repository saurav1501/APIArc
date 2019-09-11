package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class PortfoliosDetailPUTAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "rownumber" })
	public void PortfoliosDetailPUTAPI(String SheetName, int rownumber) {

		try {
			testlog.assignCategory("Portfolios");
			url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber) + "/";
			CommonMethod.res = MethodCall.PUTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}}