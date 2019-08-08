package com.arcapi.Communitytestcases;

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

import net.minidev.json.JSONObject;

public class CreditUpdatePUTAPITest extends BaseClass {

	
	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" },groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber","ProjectType","CreditColumn","CreditColumnNum" })
	public void CreditUpdatePUTAPI(String SheetName,String ProjectTypeColumn, int rownumber, String ProjectType, String CreditColumn, int CreditColumnNum) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Credit Update PUT API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Credit update")
				.assignCategory("CheckCredit");

		JSONObject jsonAsMap = new JSONObject();
		
		jsonAsMap.put("is_readyForReview", "True");
		
        int RowNum = data.getRowCountbyColNum("Credit", CreditColumnNum);
		
		for (int i=2; i<=RowNum; i++) {
        
			String CreditID = data.getCellData("Credit", CreditColumn , i);
	
			CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
					.header("Content-type", "application/json").header("Authorization", header)
					.spec(reqSpec).body(jsonAsMap).when()
					.put("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/actions/ID:" + CreditID + "/")
					.then().extract().response();
			
			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.responsetime);


		}

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

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