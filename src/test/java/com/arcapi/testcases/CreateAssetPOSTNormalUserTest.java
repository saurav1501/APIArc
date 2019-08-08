package com.arcapi.testcases;

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

public class CreateAssetPOSTNormalUserTest extends BaseClass {

	@Test(groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "SheetName", "ProjectType","ProjectTypeColumn","rownumber" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		String[] CountryOptions  = {"CN"};
		
		String StateCode = null;
		
		String Country = (CountryOptions[new Random().nextInt(CountryOptions.length)]);
		
		if(Country.equalsIgnoreCase("CN")){
			
			StateCode = "010";
		}
		
		else{
			
			StateCode = "10";
		}

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		CommonMethod.res = given().log().all()
				.parameters("name", "Test Project",
						    "rating_system","other", 
						    "gross_area", "10000", 
						    "occupancy", "500", 
						    "street", "Test Address", 
						    "city","Beijing", 
						    "country", Country, 
						    "state", StateCode, 
						    "project_type", ProjectType, 
						    "unitType", "IP",
						    "spaceType", "Circulation space",
						    "owner_email","stg-01@gmail.com",
						    "ownerType","Educational: College, Private", 
						    "confidential", false, 
						    "sign_agreement", false, 
						    "zip_code","065001",
						    "organization","T",
						    "manageEntityCountry",Country,
						    "geoLang","23.12",
						    "geoLat","44.1"
						)
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();
		
		System.out.println("This is " + Country + " " + "Project");

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Create New Asset Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Add asset")
				.assignCategory("CheckAsset");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.res.then().assertThat().statusCode(200);
		
		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(SheetName, ProjectTypeColumn, rownumber, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

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