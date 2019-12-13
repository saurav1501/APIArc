package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class SubmitReviewPOSTAPITest extends BaseClass {

	@Test(groups="CheckReview") 
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber", "ProjectType", "SoReferenceSR" })
	public void SubmitReviewPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber, String ProjectType,
			String SoReferenceSR) {

			try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/review/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	
}