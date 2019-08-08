package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class SubmitReviewPOSTAPITest extends BaseClass {

	@Test // (dependsOnMethods =
			// {"com.arcapi.testcases.ReviewCertificationPricingAPITest.ReviewCertificationPricingAPI"
			// }, groups = {"Certification", "Precertification", "PerformanceScore",
			// "Recertification" })
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber", "ProjectType", "SoReferenceSR" })
	public void SubmitReviewPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber, String ProjectType,
			String SoReferenceSR) throws IOException {

		String s;
		System.out.println(ProjectType);
		CommonMethod.ExtentReportConfig();

		// CommonMethod.GeneratingAuthCode();

		CommonMethod.test = CommonMethod.extent.startTest("Submit Review POST API Test  ", "Verifies Submit Review")
				.assignCategory("CheckSubmitReview");

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		Gson gson = new Gson();

		String LeedID = data.getCellData(SheetName, ProjectTypeColumn, rownumber);

		if (ProjectType.equalsIgnoreCase("building")) {
			s = "ACS2SS103L-" + LeedID + ",ACS2WE103L-" + LeedID + ",ACS2EA102L-" + LeedID + ",ACS2EA105L-" + LeedID
					+ ",ACS2EA109L-" + LeedID + ",ACS2MR105L-" + LeedID + ",ACS2MR106L-" + LeedID + ",ACS2EQ103L-"
					+ LeedID + ",ACS2EQ106L-" + LeedID + ",ACS2EQ108L-" + LeedID;
		} else if (ProjectType.equalsIgnoreCase("city")) {
			s = "CTP1PR901L-" + LeedID + ",CTP1PR902L-" + LeedID + ",CTP1PR903L-" + LeedID + ",CTP1PR904L-" + LeedID
					+ ",CTP1PR905L-" + LeedID + ",CTP1PR906L-" + LeedID;
		}

		else if (ProjectType.equalsIgnoreCase("community")) {
			s = "CMP1PR901L-" + LeedID + ",CMP1PR902L-" + LeedID + ",CMP1PR903L-" + LeedID + ",CMP1PR904L-" + LeedID
					+ ",CMP1PR905L-" + LeedID + ",CMP1PR906L-" + LeedID;
		}

		else {
			s = "MTS2SS103L-" + LeedID + ",MTS2WE103L-" + LeedID + ",MTS2EA102L-" + LeedID + ",MTS2EA105L-" + LeedID
					+ ",MTS2EA109L-" + LeedID + ",MTS2MR105L-" + LeedID + ",MTS2MR106L-" + LeedID + ",MTS2EQ103L-"
					+ LeedID + ",MTS2EQ106L-" + LeedID + ",MTS2EQ108L-" + LeedID;

		}

		String creditJson = gson.toJson(s);

		System.out.println(creditJson);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/review/").then()
				.extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
		CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
		CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
		System.out.println(CommonMethod.res.asString());
		System.out.println("Content Type is : " + CommonMethod.res.getContentType());
		System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

		CommonMethod.res.then().assertThat().statusCode(400);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

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