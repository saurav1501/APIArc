package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class AssetOwnerOrgPOSTAPITest extends BaseClass {

	@Test(groups="CheckOwner")
	public void AssetOwnerOrgPOSTAPI(){

	
			try {
				url = "/assets/searchowner/";
				CommonMethod.res = MethodCall.POSTRequest(url);
				Assertion.verifyStatusCode(CommonMethod.res, 400);
				CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}


}