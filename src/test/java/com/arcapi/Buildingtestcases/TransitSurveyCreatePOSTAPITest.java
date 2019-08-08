package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class TransitSurveyCreatePOSTAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void TransitSurveyCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException, InterruptedException {

		
		JSONObject jsonAsMap1 = new JSONObject();
		jsonAsMap1.put("walk", 1);
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list.add(jsonAsMap1);

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("tenant_name", "Abhishek Gupta");
		jsonAsMap.put("response_method", "web");
		jsonAsMap.put("routes",list);
		

		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json")
				.header("Authorization", header).spec(reqSpec).body(jsonAsMap).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/survey/transit/?key="+ data.getCellData(SheetName, "BuildingKeyID", rownumber))
				.then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		CommonMethod.test = CommonMethod.extent
				.startTest("Transit Survey Create API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Transit survey creation")
				.assignCategory("CheckSurvey");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		
		System.out.println(CommonMethod.res.asString());
	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(201);

	}

	@AfterMethod
	public void teardown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			CommonMethod.test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			CommonMethod.test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			CommonMethod.test.log(LogStatus.PASS, "Test passed");
		}

		CommonMethod.extent.endTest(CommonMethod.test);
		CommonMethod.extent.flush();

	}
}