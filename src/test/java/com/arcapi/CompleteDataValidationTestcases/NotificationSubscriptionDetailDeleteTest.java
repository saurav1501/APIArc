package com.arcapi.CompleteDataValidationTestcases;

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

public class NotificationSubscriptionDetailDeleteTest extends BaseClass {

	@Test // (dependsOnMethods = {
			// "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void NotificationSubscriptionDetailDelete(String SheetName, String ProjectTypeColumn, int rownumber)
			throws IOException {

		CommonMethod.ExtentReportConfig();

		// CommonMethod.GeneratingAuthCode();

		CommonMethod.test = CommonMethod.extent
				.startTest("NotificationSubscriptionDetail Delete Test ", "NotificationSubscriptionDetailDeleteTest")
				.assignCategory("NotificationSubscriptionDetailDeleteTest");

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		for (String str : value) {

			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("destination", str);
			jsonAsMap.put("score_change_notification", str);
			jsonAsMap.put("stype", str);

			CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
					.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
					.body(jsonAsMap).when()
					.delete("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
							+ "/subscriptions/ID:" + data.getCellData(SheetName, "SubscriptionPK", rownumber) + "/")
					.then().extract().response();

			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.responsetime);

			CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

			CommonMethod.testlog("Info", "Starting Test for Test Data " + str);
			CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
			CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
			CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
			System.out.println(CommonMethod.res.asString());
			System.out.println("Content Type is : " + CommonMethod.res.getContentType());
			System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

		

		CommonMethod.res.then().assertThat().statusCode(404);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
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