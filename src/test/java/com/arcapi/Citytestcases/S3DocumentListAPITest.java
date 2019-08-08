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
import com.relevantcodes.extentreports.LogStatus;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class S3DocumentListAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void S3DocumentListAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		CommonMethod.ExtentReportConfig();
		
		//CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.header("Authorization", header)
				.spec(reqSpec)
				.when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/s3documents/").then().extract()
				.response();
		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		CommonMethod.test = CommonMethod.extent.startTest("S3DocumenList API Test "+ CommonMethod.getLabel(CommonMethod.responsetime), "Verifies credit upload document file").assignCategory("CheckCredit");


		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);
		
		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		
		CommonMethod.testlog("Info", "API responded in "
				+ CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
		/*CommonMethod.res.then().assertThat().body("doc_category[0]", equalTo("PF905"));
		CommonMethod.res.then().assertThat().body("doc_category[1]", equalTo("PF904"));
		CommonMethod.res.then().assertThat().body("doc_category[2]", equalTo("PF903"));
		CommonMethod.res.then().assertThat().body("doc_category[3]", equalTo("PF902"));
		CommonMethod.res.then().assertThat().body("doc_category[4]", equalTo("PF901"));*/
		
		
		
		CommonMethod.fetchedID = CommonMethod.res.path("id[0]").toString();

		data.setCellData(SheetName, "S3DocID", rownumber, CommonMethod.fetchedID);

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