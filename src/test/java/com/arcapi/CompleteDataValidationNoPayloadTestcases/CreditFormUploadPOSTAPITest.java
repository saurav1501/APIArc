package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class CreditFormUploadPOSTAPITest extends BaseClass {

	@Test(groups="CheckCredit")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void CreditFormUploadPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber)  {

		
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/actions/ID:"
					+ data.getCellData(SheetName,"CreditID", rownumber) +"/forms/upload/";

			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 404);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}