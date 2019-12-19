package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.google.gson.Gson;

public class AssetSubmitCertificatePostAPITest extends BaseClass {

	@Test(groups ="CheckData")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber" })
	public void AssetSubmitCertificatePostAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			map = new HashMap<>();
			map.put("type" , "Performance Score Certification");
			map.put( "certification_level","Arc Energy");
			map.put("certification_type","Performance Score Certification");
			map.put("review_start_date","2018-10-01");
			map.put("review_expiry_date","2019-10-01");
			map.put("score_submitted",87);
			map.put("improvement_score", 56);
			map.put("credit_submitted","PF901");
			map.put("base_score", 0);
			map.put("target_score", 87); 
			map.put("target_level","Not applicable");
			map.put("status","completed");
			
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/review/performance/";

			
			CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+url);
			log.info("POST Request Call URL "+baseURLLEED+url);
		
			CommonMethod.res= given().log().all().multiPart("file", CommonMethod.certification)
					.parameter("type" , "Performance Score Certification")
					.parameter( "certification_level","Arc Energy")
					.parameter("certification_type","Performance Score Certification")
					.parameter("review_start_date","2018-10-01")
					.parameter("review_expiry_date","2019-10-01")
					.parameter("score_submitted",87)
					.parameter("improvement_score", 56)
					.parameter("credit_submitted","PF901")
					.parameter("base_score", 0)
					.parameter("target_score", 87)
					.parameter("target_level","Not applicable")
					.parameter("status","completed")	
    				.headers(headerMap)
					.header("Authorization",header).spec(reqSpec).post(url).then().extract().response();

			
			Gson gson = new Gson();
			String jsonString = gson.toJson(map);
			log.info("Payload is  "+jsonString.toString());
			CommonMethod.testlog("Info", "Payload is " + jsonString.toString());
			
			
			CommonMethod.testlog("Pass", "Response from API" + "<br>" + CommonMethod.res.asString());
	        
			CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
			Assertion.verifyStatusCode(CommonMethod.res, 201);
			
		} catch (Exception e) {
		e.printStackTrace();
		}
	}



}