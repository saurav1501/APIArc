package com.arcapi.CrossRatingSystemtestcases;

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

public class WasteGetAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WasteGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Waste Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Waste Detail")
				.assignCategory("CheckWaste");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
		CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
		CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
		System.out.println(CommonMethod.res.asString());
		System.out.println("Content Type is : " + CommonMethod.res.getContentType());
		System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

		CommonMethod.res.then().assertThat().statusCode(404);
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