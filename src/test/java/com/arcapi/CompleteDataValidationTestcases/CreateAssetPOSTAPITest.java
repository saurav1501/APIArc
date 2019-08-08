package com.arcapi.CompleteDataValidationTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

public class CreateAssetPOSTAPITest extends BaseClass {

	@Test(groups = { "Certification", "Precertification", "PerformanceScore", "Recertification" })
	@Parameters({ "SheetName", "ProjectType", "ProjectTypeColumn", "rownumber" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType, String ProjectTypeColumn, int rownumber)
			throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName, rownumber);

		CommonMethod.test = CommonMethod.extent.startTest("Create New Building Test  ", "Verifies Add asset")
				.assignCategory("CheckAsset");

String[] CountryOptions  = {"US"};
		
		String StateCode = null;
		
		String Country = (CountryOptions[new Random().nextInt(CountryOptions.length)]);
		
		if(Country.equalsIgnoreCase("US")){
			
			StateCode = "DC";
		}
		
		else{
			
			StateCode = "10";
		}
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		for (String str : value) {

			CommonMethod.res = given().log().all()
					.parameters("name", str, "rating_system", str, "gross_area", str, "occupancy", str,
							"street", str, "city", str, "country", str, "state", str, "project_type", str,
							"unitType", str, "spaceType", str, "owner_email", str, "ownerType", str,
							"confidential", str, "sign_agreement", str, "zip_code", str, "organization", str,
							"manageEntityCountry", str)
					.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey).header("Authorization", header)
					.spec(reqSpec).when().post("/assets/").then().extract().response();

			System.out.println("This is " + Country + " " + "Project");

			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.res.asString());

			System.out.println(CommonMethod.responsetime);

			CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

			CommonMethod.testlog("Info", "Starting Test for Test Data " + str);
			CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
			CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
			CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
			System.out.println(CommonMethod.res.asString());
			System.out.println("Content Type is : " + CommonMethod.res.getContentType());
			System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

		

		CommonMethod.res.then().assertThat().statusCode(400);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
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