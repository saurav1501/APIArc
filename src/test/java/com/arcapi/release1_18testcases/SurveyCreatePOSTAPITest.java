package com.arcapi.release1_18testcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class SurveyCreatePOSTAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void SurveyCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException, InterruptedException {

		
		CommonMethod.test = CommonMethod.extent
				.startTest("Survey Create POST API" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Transit survey creation")
				.assignCategory("CheckSurvey");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		List<String> str = Arrays.asList("dirty","views to outdoors","sound","privacy", "smelly", "air quality","cleanliness","stuffy","loud","hot","cold","dark","glare","drafty","bright","humid","thermal comfort","light","daylight",""); //{"dirty","smelly","air quality","cleanliness"},{""};
		
		
		for(int value=-3;value<4;value++) {
			
			for(String compl : str) {
			
			/*if(value<=0) {
				
			compla = str[0];
			}
			
			else {
				
				compla = str[1];
			}*/

		JSONObject jsonAsMap1 = new JSONObject();
		jsonAsMap1.put("walk", 1);
		jsonAsMap1.put("bike", 1);
		jsonAsMap1.put("telecommute", 1);
		jsonAsMap1.put("bus", 1);
		jsonAsMap1.put("light_rail", 1);
		jsonAsMap1.put("heavy_rail", 1);
		jsonAsMap1.put("motorcycle", 1);
		jsonAsMap1.put("car", 1);
		jsonAsMap1.put("car23", 1);
		jsonAsMap1.put("cars4", 1);
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list.add(jsonAsMap1);

        JSONObject jsonAsMap = new JSONObject();
        if (value==0) {
        	
        	
    		jsonAsMap.put("tenant_name", "Abhishek Gupta");
    		jsonAsMap.put("response_method", "web");
    		jsonAsMap.put("routes",list);
    		jsonAsMap.put("satisfaction", value);
    		jsonAsMap.put("location", "Gurgaon");
    		jsonAsMap.put("feedbacks", "[]");
    		jsonAsMap.put("other_complaint", "None");
    		jsonAsMap.put("language", "English");
    		jsonAsMap.put("occupant_category", "regular_occupant");
        	
        	
        }
        
        else {
		
		jsonAsMap.put("tenant_name", "Abhishek Gupta");
		jsonAsMap.put("response_method", "web");
		jsonAsMap.put("routes",list);
		jsonAsMap.put("satisfaction", value);
		jsonAsMap.put("location", "Gurgaon");
		jsonAsMap.put("feedbacks", "['"+compl+"']");
		jsonAsMap.put("other_complaint", "None");
		jsonAsMap.put("language", "English");
		jsonAsMap.put("occupant_category", "regular_occupant");
        }
        
		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json")
				.header("Authorization", header).spec(reqSpec).body(jsonAsMap).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/survey/?key="+ data.getCellData(SheetName, "BuildingKeyID", rownumber))
				.then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		
		System.out.println(CommonMethod.res.asString());
	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		if(value==0 || value<0 && (compl.equals("sound") || compl.equals("privacy") || compl.equals("views to outdoors") || compl.equals("dirty") || compl.equals("smelly") || compl.equals("stuffy") || compl.equals("loud") || compl.equals("cold") || compl.equals("hot") || compl.equals("dark") || compl.equals("glare") || compl.equals("drafty") || compl.equals("bright") || compl.equals("humid")) || 
				value>0 && (compl.equals("sound") || compl.equals("privacy") || compl.equals("views to outdoors") || compl.equals("air quality") || compl.equals("cleanliness") || compl.equals("thermal comfort") || compl.equals("light") || compl.equals("daylight"))) {
		
		CommonMethod.res.then().assertThat().statusCode(201);
		}
		
		else {
			
			CommonMethod.res.then().assertThat().statusCode(400);
		}
			}}
	}
	
}