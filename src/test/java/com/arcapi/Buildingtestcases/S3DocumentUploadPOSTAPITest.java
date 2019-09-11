package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class S3DocumentUploadPOSTAPITest extends BaseClass {

	@Test(groups="CheckUpload")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void S3DocumentUploadPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

			
		String[] arr = {"PF901","PF902","additional_file","PF903","PF904","PF905"};
		
		url="/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/uploadS3/";

		for(String value : arr) {				
			CommonMethod.res= given().log().all().multiPart("action_file", CommonMethod.formuploadfile)
				.multiPart("upload_category", value).header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/uploadS3/").then()
				.extract().response();
			
			Assertion.verifyStatusCode(CommonMethod.res, 200);	
		}	

	}

	
}