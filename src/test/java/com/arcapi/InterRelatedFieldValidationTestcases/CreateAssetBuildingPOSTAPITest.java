package com.arcapi.InterRelatedFieldValidationTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

public class CreateAssetBuildingPOSTAPITest extends BaseClass {

	@Test(groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "SheetName", "ProjectType","ProjectTypeColumn","rownumber" })
	public void CreateAssetBuildingPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Create New Asset Building Test  ",
						"Verifies Add asset")
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
		
		
		for (String str : RatingSystemValue) {
		
		CommonMethod.res = given().log().all()
				.parameters("name", "Test Project",
						    "rating_system",str, 
						    "gross_area", "10000", 
						    "occupancy", "500", 
						    "street", "Test Address", 
						    "city","DC", 
						    "country", Country, 
						    "state", StateCode, 
						    "project_type", ProjectType, 
						    "unitType", "IP",
						    "spaceType", "Airport: Hangar",
						    "owner_email","stg-01@gmail.com",
						    "ownerType","Educational: College, Private", 
						    "confidential", false, 
						    "sign_agreement", false, 
						    "zip_code","20037",
						    "organization","T",
						    "manageEntityCountry",Country)
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();
		
		System.out.println("This is " + Country + " " + "Project");

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		CommonMethod.testlog("Info", "Starting Test for Test Data " + str);
        CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString() );
        CommonMethod.testlog("Info","Content Type is : " + CommonMethod.res.getContentType());
        CommonMethod.testlog("Info","Status Code is : " + CommonMethod.res.getStatusCode());
        System.out.println(CommonMethod.res.asString());
        System.out.println("Content Type is : " + CommonMethod.res.getContentType());
		System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

	
		
      List<String> Actvalue = Arrays.asList("other","none");
		
		if(Actvalue.contains(str)) {
		
		CommonMethod.res.then().assertThat().statusCode(201);
		
		}
		
		else {
			
	    CommonMethod.res.then().assertThat().statusCode(400);
			
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