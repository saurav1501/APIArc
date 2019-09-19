package com.arcapi.InterRelatedFieldValidationTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreateAssetTransitPOSTAPITest extends BaseClass {

	@Test(groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "SheetName", "ProjectType","ProjectTypeColumn","rownumber" })
	public void CreateAssetTransitPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber) throws IOException {

				
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
				.parameters(
					    "name", "MachineTestProject-Transit",
					    "unitType", "IP",
					    "project_type", ProjectType,
					    "rating_system",str, 
					    "spaceType","Transit: Station",
					    "station_type", "aboveground",
					    "annual_ridership", "365",
					    "operating_hours", "14",
					    "full_time_staff", "7",
					    "time_spent_by_riders", "120",
					    "ownerType","Government Use: Local, Public Housing Authority",
					    "organization","A & A Dealers",
					    "owner_email","stg-01@gmail.com",
					    "manageEntityCountry",Country,
					    "gross_area", "5600",
					    "confidential", false, 
					    "street", "1 mount road", 
					    "city","DC", 
					    "country", Country, 
					    "state", StateCode,
					    "zip_code","20037"
					    )
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.testlog("Info", "Starting Test for Test Data " + str);
        CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString() );
        CommonMethod.testlog("Info","Content Type is : " + CommonMethod.res.getContentType());
        CommonMethod.testlog("Info","Status Code is : " + CommonMethod.res.getStatusCode());
        
		
        List<String> Actvalue = Arrays.asList("LEED V4 O+M: TR");
		
		if(Actvalue.contains(str)) {
		
		Assertion.verifyStatusCode(CommonMethod.res , 201);	
		}
		
		else {
			
			Assertion.verifyStatusCode(CommonMethod.res , 400);	
			
		}
		}

	}
	
}