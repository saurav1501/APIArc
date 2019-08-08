package com.arcapi.release1_18testcases;

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

public class OccupantPostAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.PaymentOrderSubmitPOSTAPITest.PaymentOrderSubmitPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void OccupantPostAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		String val[] = {"transport","human"};
		
		for(String value : val) {
			
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("start_date", "2019-01-01");
			jsonAsMap.put("total_occupant", 200);
			jsonAsMap.put("regular_occupant", 130);
			jsonAsMap.put("visitor_occupant", 70);

		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.header("Authorization", header)
				.header("content-type", "application/json")
				.spec(reqSpec)
				.body(jsonAsMap)
				.when()
				.post("/assets/LEED:"
						+ data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						+ "/occupant/"+value+"/").then().contentType(ContentType.JSON)
				.extract().response();
		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		CommonMethod.test = CommonMethod.extent
				.startTest("Add Asset Timeline APITest  "+ CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Timeline is added after project registration payment")
				.assignCategory("CheckAsset");
		
		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);
		
		System.out.println(CommonMethod.res.asString());

		
		CommonMethod.testlog("Pass", "verifies response from API" + "<br>"
				+ CommonMethod.res.asString());
		
	
		CommonMethod.testlog("Info", "API responded in "
				+ CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(201);
	}

	}

	@AfterMethod
	public void teardown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			CommonMethod.test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			CommonMethod.test.log(LogStatus.SKIP,
					"Test skipped " + result.getThrowable());
		} else {
			CommonMethod.test.log(LogStatus.PASS, "Test passed");
		}

		CommonMethod.extent.endTest(CommonMethod.test);
		CommonMethod.extent.flush();

	}

}