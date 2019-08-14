package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class CreateAssetPOSTAPITest extends BaseClass {

	@Test(groups = { "Certification", "Precertification", "PerformanceScore", "Recertification" })
	@Parameters({ "SheetName", "ProjectType", "ProjectTypeColumn", "rownumber" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType, String ProjectTypeColumn, int rownumber)
			throws IOException {

		
		   CommonMethod.test = CommonMethod.extent.startTest("Create New Building Test  ", "Verifies Add asset")
				.assignCategory("CheckAsset");
	
			CommonMethod.res = given().log().all()
					.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey).header("Authorization", header)
					.spec(reqSpec).when().post("/assets/").then().extract().response();

			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.res.asString());

			System.out.println(CommonMethod.responsetime);

			CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

			
			CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
			CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
			CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
			System.out.println(CommonMethod.res.asString());
			System.out.println("Content Type is : " + CommonMethod.res.getContentType());
			System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

		

		CommonMethod.res.then().assertThat().statusCode(400);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
	}

	

}