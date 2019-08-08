package com.arcapi.Citytestcases;

import static com.jayway.restassured.RestAssured.given;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class RecomputePerformanceCustomCrimeScoreGetTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","CustomSheetName","rownumber","paramvalue" })
	public void RecomputePerformanceScoreGet(String SheetName,String CustomSheetName, int rownumber, String paramvalue) throws IOException {

	
		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent.startTest(
				"RecomputePerformanceScore Get API Test",
				"Recompute Score").assignCategory("Recompute");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
        int maxrow = Customdata.getRowCountbyColNum(CustomSheetName, 1);
		
		for(int i=2;i<=2;i++) {

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + Customdata.getCellData(CustomSheetName, "ProjectID", i) + "/scores/recompute/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
		CommonMethod.fetchedID = CommonMethod.res.path(paramvalue).toString();
		
		CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\.*0*$", "");
		
		System.out.println(CommonMethod.fetchedID);
		
		String score = Customdata.getCellData(CustomSheetName, "Score", i);
		
		double value1 = Integer.parseInt(score)/2;
		
	    System.out.println(value1);
		
		System.out.println(score);
	   
	   if(StringUtils.equalsIgnoreCase(CommonMethod.fetchedID,score)) {
		   
		   Customdata.setCellData(CustomSheetName, "Result", i, "PASS");
	   }
	   
	   else {
		   
		   System.out.println("Value Mismatch");
		   
		  Customdata.setCellData(CustomSheetName, "Result", i, "FAIL");
		  Customdata.setCellData(CustomSheetName, "ResponseValue", i, CommonMethod.fetchedID);
	   }
		
	   
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