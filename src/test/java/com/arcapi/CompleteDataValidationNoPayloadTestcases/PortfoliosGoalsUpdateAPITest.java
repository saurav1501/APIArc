package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class PortfoliosGoalsUpdateAPITest extends BaseClass {

	@Test(groups="Portfolios")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void PortfoliosGoalsUpdateAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url = "/portfolios/ID:" + data.getCellData(SheetName, "PortfolioID", rownumber) + "/goals/";
			CommonMethod.res = MethodCall.PUTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}}