package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class AnalysisDataEmissionAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void AnalysisDataGetAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException, InterruptedException {

		Thread.sleep(20000);
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" +  data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber) + "/analysis/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Analysis data Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Analysis data generated")
				.assignCategory("CheckAnalysis");

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
		BigDecimal score=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("energy.info_json.'Attributes for this project'.'Score (out of 100)'");
			
		System.out.println(score);
			
		CommonMethod.fetchedID = score.toString();

		System.out.println(CommonMethod.fetchedID);
		
		data.setCellData(CustomSheetName, "Score", rownumber, CommonMethod.fetchedID);
		
		BigDecimal analysis=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("energy.info_json.'Attributes for this project'.'Raw GHG (mtco2e/day)'");
		
		System.out.println(analysis);
			
		CommonMethod.fetchedID = analysis.toString();

		System.out.println(CommonMethod.fetchedID);
		
		data.setCellData(CustomSheetName, "Analysis", rownumber, CommonMethod.fetchedID);
		
		
		
	}



}