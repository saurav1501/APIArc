package com.arcapi.TrailFlowtestcases.Building;

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

public class AssetAppaddPOSTAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetAppaddPOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res = given().log().all().parameters("app", "2")
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/apps/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

	    System.out.println(CommonMethod.responsetime);


		CommonMethod.test = CommonMethod.extent
				.startTest("Asset App add Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies App can be added to asset")
				.assignCategory("CheckAsset");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		

		
		CommonMethod.testlog("Pass", "App code 1 is added to Asset" + "<br>" + CommonMethod.res.asString());

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