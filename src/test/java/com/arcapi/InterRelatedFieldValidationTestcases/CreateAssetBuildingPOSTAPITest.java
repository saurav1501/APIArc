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

public class CreateAssetBuildingPOSTAPITest extends BaseClass {

	@Test(groups ="CheckRatings")
	@Parameters({ "SheetName", "ProjectType","ProjectTypeColumn","rownumber" })
	public void CreateAssetBuildingPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber) throws IOException {

			
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
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		CommonMethod.testlog("Info", "Starting Test for Test Data " + str);
        CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString() );
        CommonMethod.testlog("Info","Content Type is : " + CommonMethod.res.getContentType());
        CommonMethod.testlog("Info","Status Code is : " + CommonMethod.res.getStatusCode());
        System.out.println(CommonMethod.res.asString());
        System.out.println("Content Type is : " + CommonMethod.res.getContentType());
		System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

	
		
        List<String> Actvalue = Arrays.asList("other","none","LEED V4 O+M: EB WP");
		
		if(Actvalue.contains(str)) {
		
		Assertion.verifyStatusCode(CommonMethod.res, 201);
		
		}
		
		else {
			
			Assertion.verifyStatusCode(CommonMethod.res, 400);
				
		}
		}

	}


}