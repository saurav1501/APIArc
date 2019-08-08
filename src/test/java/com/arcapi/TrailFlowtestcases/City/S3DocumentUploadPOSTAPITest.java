package com.arcapi.TrailFlowtestcases.City;

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

public class S3DocumentUploadPOSTAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void S3DocumentUploadPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode(SheetName,rownumber);

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		String[] arr = {"PF901","PF902","additional_file","PF903","PF904","PF905","PF906"};
		
		for(String value : arr) {
	

		CommonMethod.res = given().log().all().multiPart("action_file", CommonMethod.formuploadfile)
				.multiPart("upload_category", value).header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/uploadS3/").then()
				.extract().response();
		System.out.println(CommonMethod.responsetime);

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		CommonMethod.test = CommonMethod.extent
				.startTest("S3Document Upload POSTAPI Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies credit upload document file")
				.assignCategory("CheckCredit");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

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