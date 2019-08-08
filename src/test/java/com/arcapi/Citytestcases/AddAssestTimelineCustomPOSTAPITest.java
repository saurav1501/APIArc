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
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

public class AddAssestTimelineCustomPOSTAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.PaymentOrderSubmitPOSTAPITest.PaymentOrderSubmitPOSTAPI" })
	@Parameters({ "SheetName","CustomSheetName","rownumber" })
	public void AddAssestTimeline(String SheetName,String CustomSheetName, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		CommonMethod.test = CommonMethod.extent
				.startTest("Add Asset Timeline APITest",
						"Verifies Timeline is added after project registration payment")
				.assignCategory("CheckAsset");
		
        int maxrow = Customdata.getRowCountbyColNum(CustomSheetName, 1);
		
		for(int i=2;i<=maxrow;i++) {

		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.header("Authorization", header)
				.spec(reqSpec)
				.when()
				.post("/assets/LEED:"
						+ Customdata.getCellData(CustomSheetName, "ProjectID", i)
						+ "/timeline/").then().contentType(ContentType.JSON)
				.extract().response();
		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		
		
		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);
		
		System.out.println(CommonMethod.res.asString());

		
		

		//System.out.println(data.getCellData(SheetName, ProjectTypeColumn, rownumber));
		
		CommonMethod.testlog("Pass", "verifies response from API" + "<br>"
				+ CommonMethod.res.asString());
		
		

		

		CommonMethod.testlog("Info", "API responded in "
				+ CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
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