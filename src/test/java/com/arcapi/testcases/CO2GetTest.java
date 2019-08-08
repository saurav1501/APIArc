package com.arcapi.testcases;

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

public class CO2GetTest extends BaseClass {

	@Test(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CO2GetTestAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res = given().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						+ "/co2/?resample=2017-01-05/P1D,2017-01-06/P1D,2017-01-07/P1D,2017-01-08/P1D")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("CO2 Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies List of Assets")
				.assignCategory("CheckAsset");

		CommonMethod.fetchingJSONResponse("/assets/LEED:" + 
				data.getCellData(SheetName, ProjectTypeColumn, rownumber)
				+ "/co2/?resample=2017-04-03/P1D,2017-04-04/P1D,2017-04-05/P1D,2017-04-06/P1D,2017-04-07/P1D,2017-04-08/P1D,2017-04-09/P1D,2017-04-10/P1D,2017-04-11/P1D,2017-04-12/P1D");

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
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