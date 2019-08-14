package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class MeasuresVersionGetAPITest extends BaseClass {

	@Test // (dependsOnMethods = {
			// "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void MeasuresVersionGetAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.test = CommonMethod.extent.startTest("MeasuresVersionPost API Test", "MeasuresVersionPostAPITest")
				.assignCategory("Measures");

		CommonMethod.res = given().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).header("content-type", "application/json").spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn,rownumber) +"/measuresversion/"+"?user_version=V1/")
				.then().extract().response();

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		CommonMethod.res.then().assertThat().statusCode(200);
		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		 CommonMethod.testlog("Info","**Verified repsponse V1**");
		  for(int i=0;i<20;i++)
		  {
			String responseBody = CommonMethod.res.path("results["+i+"].user_version");
	        Assert.assertEquals(responseBody.toString(),"V1");
		  }
		  
		CommonMethod.testlog("Pass","**Verified repsponse body conatain version V1**");
		
		CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
		CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
		CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
	
		
		
       
		
			  CommonMethod.testlog("Pass","**Verified repsponse V1**" + "<br>" + CommonMethod.res.asString());

			 
	}

	

}