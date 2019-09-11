package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class ExcelUploadTemplatePOSTAPITest extends BaseClass {

	@Test(groups="CheckUpload")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void ExcelUploadTemplatePOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber)
			throws IOException {

		
		try {
			url = "/assets/LEED:"+ data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/excelupload/uploadS3/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			e.printStackTrace();
		}
}}