package com.arcapi.UserAccessibilityTestcases;

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

public class AssetDocumentAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void AssetDocumentAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCodeAdminUser(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res = given().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/documents/").then().extract()
				.response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Asset Document API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Asset document detail")
				.assignCategory("CheckAsset");
		
		CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString() );
        CommonMethod.testlog("Info","Content Type is : " + CommonMethod.res.getContentType());
        CommonMethod.testlog("Info","Status Code is : " + CommonMethod.res.getStatusCode());

		CommonMethod.fetchingJSONResponse(
				"/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/documents/");

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.fetchedID = CommonMethod.res.path("EtFile[0].Documentnumber").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(SheetName, "DocumentNumber", rownumber, CommonMethod.fetchedID);
		
		CommonMethod.fetchedID = CommonMethod.res.path("EtFile[0].ApplicationId").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(SheetName, "ApplicationID", rownumber, CommonMethod.fetchedID);
		
		CommonMethod.fetchedID = CommonMethod.res.path("EtFile[0].FileId").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(SheetName, "FileID", rownumber, CommonMethod.fetchedID);
		
		CommonMethod.testlog("Info", "API responded in "
				+ CommonMethod.responsetime + " Milliseconds");
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