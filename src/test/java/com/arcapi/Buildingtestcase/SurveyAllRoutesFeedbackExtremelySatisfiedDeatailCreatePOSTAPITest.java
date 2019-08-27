package com.arcapi.Buildingtestcase;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.SurveyModes;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class SurveyAllRoutesFeedbackExtremelySatisfiedDeatailCreatePOSTAPITest extends BaseClass{
	
	@Test(groups="CheckSurvey")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void SurveyWithAllRoutesCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException, InterruptedException {
        
		SurveyModes survey = new SurveyModes();
		survey.setBike(modeValue1);
		survey.setBus(modeValue1);
		survey.setCar(modeValue1);
		survey.setCar23(modeValue1);
		survey.setCars4(modeValue1);
		survey.setLight_rail(modeValue1);
		survey.setHeavy_rail(modeValue1);
		survey.setMotorcycle(modeValue1);
		survey.setWalk(modeValue1);
		survey.setTelecommute(modeValue1);
			
		Map<Object,Object> surveyDetails = new HashMap<Object, Object>();	
		surveyDetails.put("tenant_name", "Saurav Sinha");
		surveyDetails.put("response_method", "web");
		surveyDetails.put("satisfaction",3);
		surveyDetails.put("location","Delhi");
		surveyDetails.put("occupant_category", "regular_occupant");
		surveyDetails.put("other_complaint", "Test Completent");
		surveyDetails.put("language","English");
		surveyDetails.put("routes",Arrays.asList(survey));
		surveyDetails.put("feedbacks","['views to outdoors','sound','privacy','air quality','cleanliness','light','privacy','daylight']");
		
		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json")
				.header("Authorization", header).spec(reqSpec).body(surveyDetails).when()
				.post("/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber) +"/survey/?key="+ data.getCellData(sheetName, "BuildingKeyID", rownumber))
				.then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(201);

	}

   

}
