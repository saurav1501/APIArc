package com.arcapi.Citytestcases;

import static com.jayway.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class CheckOAuthTokenStatusAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "rownumber" })
	public void CheckOAuthTokenStatusAPI(String SheetName, int rownumber) {

		CommonMethod.ExtentReportConfig();
		
		//CommonMethod.GeneratingAuthCode(SheetName,rownumber);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey).spec(reqSpec)
				.header("Authorization", header).when()
				.get("/auth/token/").then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		CommonMethod.test = CommonMethod.extent
				.startTest("CheckOAuthTokenStatus API Test  " + CommonMethod.getLabel(CommonMethod.responsetime), "CheckOAuthTokenStatus")
				.assignCategory("CheckOAuthTokenStatus");

		System.out.println(CommonMethod.res.asString());
		

		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

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
