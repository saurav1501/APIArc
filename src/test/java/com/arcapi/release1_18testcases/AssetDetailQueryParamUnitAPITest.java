package com.arcapi.release1_18testcases;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class AssetDetailQueryParamUnitAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetDetailQueryParamAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Asset Detail API Test",
						"Verifies Asset detail")
				.assignCategory("CheckAsset");
		
		String val[] = {"IP","SI"};
		
		for(String unit : val) {

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/?unit="+unit).then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		int expected_Gross_Area = Integer.parseInt(GrossArea);
		
		if(unit.equalsIgnoreCase("ip")) {
		
	    int Actual_Gross_Area = CommonMethod.res.path("gross_area");

		System.out.println(Actual_Gross_Area);
		
		

		assertThat(Actual_Gross_Area, is(equalTo(expected_Gross_Area)));
		
		}
		
		else {
			
			Float Area = CommonMethod.res.path("gross_area");
			
		    Area = (float) ((Area)*(10.7692));
			System.out.println(Area);
			
			int Actual_Gross_Area = Math.round(Area);
			
			assertThat(Actual_Gross_Area, is(equalTo(expected_Gross_Area)));
			
			//Float area= Actual_Gross_Area*(10.7692);
			
		}
		
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