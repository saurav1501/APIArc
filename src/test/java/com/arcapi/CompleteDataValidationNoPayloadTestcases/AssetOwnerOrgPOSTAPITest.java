package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class AssetOwnerOrgPOSTAPITest extends BaseClass {

	@Test
	// @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetOwnerOrgPOSTAPI() throws IOException {

	

		CommonMethod.test = CommonMethod.extent.startTest("AssetOwnerOrgPOST API Test", "AssetOwnerOrgPOSTAPITest")
				.assignCategory("AssetOwnerOrgPOSTAPITest");
		
		CommonMethod.res = given().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
					.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
					.when().post("/assets/searchowner/").then().contentType(ContentType.JSON).extract()
					.response();

			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

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