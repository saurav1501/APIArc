package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class AssetValidationInfoPOSTAPITest extends BaseClass {

	@Test(groups="CheckData")
	public void AssetValidationInfoPOSTAPI() {

		try {
			url = "/assets/validation/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
}