 package com.arcapi.Leedv4Testcases;

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

public class S3DocumentRemoveAPITest extends BaseClass {

	@Test
	@Parameters({ "LOSheetName","LOProjectTypeColumn","rownumber" })
	public void S3DocumentRemoveAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		CommonMethod.ExtentReportConfig();
		
		CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("doc_id", data.getCellData(SheetName, "S3DocID", rownumber));
		
		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.header("Authorization", header)
				.header("content-type","application/json")
				.spec(reqSpec)
				.body(jsonAsMap)
				.when()
				.put("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/s3documents/").then().extract()
				.response();
		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		CommonMethod.test = CommonMethod.extent.startTest("S3Document Remove API Test"+ CommonMethod.getLabel(CommonMethod.responsetime), "Verifies credit upload document file").assignCategory("CheckCredit");


		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);
		
		System.out.println(CommonMethod.res.asString());
	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		
		CommonMethod.testlog("Info", "API responded in "
				+ CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);

	}
}