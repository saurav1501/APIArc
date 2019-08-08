package com.arcapi.Transittestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class AnalysisDataSubmitPOSTAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AnalysisDataSubmitPOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		
		
		
JSONObject jsonAsMap1 = new JSONObject();
		
		
		jsonAsMap1.put("Operating Hours","62.0");
		jsonAsMap1.put("Name", "Test name");
		JSONObject jsonAsMap = new JSONObject();
		
		jsonAsMap.put("category", "Basic");
		jsonAsMap.put("info_json",jsonAsMap1.toString());
		jsonAsMap.put("effective_at", "2016-08-22T00:00:00");
		

				
		
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-Type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap)
				.when()
				.post("/assets/LEED:" +  data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/analysis/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		CommonMethod.test = CommonMethod.extent
				.startTest("Analysis data Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Analysis data submit")
				.assignCategory("CheckAnalysis");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		

		
		CommonMethod.testlog("Pass", "verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
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