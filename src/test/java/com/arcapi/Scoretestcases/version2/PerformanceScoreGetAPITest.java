package com.arcapi.Scoretestcases.version2;

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

public class PerformanceScoreGetAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetPerformanceScoreListAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res= given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/scores/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Asset Performance Score list API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Performance scores")
				.assignCategory("CheckAsset");

		System.out.println(CommonMethod.res.asString());
		
		String energy_score = CommonMethod.res.path("energy").toString();
		String water_score = CommonMethod.res.path("water").toString();
		String waste_score = CommonMethod.res.path("waste").toString();
		String humanexp_score = CommonMethod.res.path("human_experience").toString();
		String tansport_score = CommonMethod.res.path("transport").toString();
		String base_score = CommonMethod.res.path("base").toString();
		
		data.setCellData("Score", "EnergyScore", rownumber, energy_score);
		data.setCellData("Score", "WaterScore", rownumber, water_score);
		data.setCellData("Score", "WasteScore", rownumber, waste_score);
		data.setCellData("Score", "HumanExpScore", rownumber, humanexp_score);
		data.setCellData("Score", "TransportScore", rownumber, tansport_score);
		data.setCellData("Score", "BaseScore", rownumber, base_score);
		
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