package com.arcapi.Citytestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class MeasuresPostAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeasuresPostAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		CommonMethod.test = CommonMethod.extent
				.startTest("MeasuresPost API Test" + CommonMethod.getLabel(CommonMethod.responsetime),
						"MeasuresPostAPITest")
				.assignCategory("Measures");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		String basescore[] = {"base_score.A","base_score.A.1.1","base_score.A.2.1","base_score.A.3.1","base_score.A.4.1","base_score.A.5.1","base_score.A.6.1","base_score.A.7.1","base_score.A.8.1","base_score.A.9.1","base_score.A.10.1","base_score.B"};
		
		
		for(String base : basescore) {
			
		
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("measure_id", base);
		jsonAsMap.put("content_type", "checkbox");
		jsonAsMap.put("data", "true");

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header)
				.header("content-type","application/json")
				.spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/measures/" + 
				data.getCellData(SheetName, "MeasuresID", rownumber)+"/?points_pursued=10")
				.then().extract()
				.response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
		}	
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