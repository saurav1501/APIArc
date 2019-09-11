package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class CreateAssetPOSTAPITest extends BaseClass {

	@Test(groups="CheckAddProject")
	@Parameters({ "SheetName", "ProjectType", "ProjectTypeColumn", "rownumber" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType, String ProjectTypeColumn, int rownumber)
		 {

		
		try {
			url = "/assets/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}

	

}