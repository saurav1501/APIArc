package com.arcapi.Parksmarttestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreateAssetParksmartPOSTAPITest extends BaseClass {

	@Test(groups ="CheckAsset")
	@Parameters({ "SheetName", "ProjectType","ProjectTypeColumn","rownumber" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber) throws IOException {

		String[] CountryOptions  = {"US"};
		
		String StateCode = null;
		
		String Country = (CountryOptions[new Random().nextInt(CountryOptions.length)]);
		
		if(Country.equalsIgnoreCase("US")){
			
			StateCode = "DC";
		}
		
		else{
			
			StateCode = "10";
		}
		

		CommonMethod.res = given().log().all()
				.parameters(
					    "name", "MachineTestProject-Parksmart",
					    "project_type", ProjectType,
					    "rating_system","PARKSMART", 
					    "ownerType","Investor: Bank",
					    "organization","A & A Dealers",
					    "owner_email","stg-01@gmail.com",
					    "manageEntityCountry",Country,
					    "confidential", false,
					    "noOfParkingSpace", "15", 
					    "noOfParkingLevels","2",
					    "year_constructed", "2018-04-30",
					    "street", "1 Mount Road", 
					    "city","DC", 
					    "country", Country, 
					    "state", StateCode,
					    "zip_code","20037"
					    )
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();
		
		CommonMethod.res.then().assertThat().statusCode(201);
		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();
		log.info(CommonMethod.fetchedID);
		data.setCellData(SheetName, ProjectTypeColumn, rownumber, CommonMethod.fetchedID);
	}


}