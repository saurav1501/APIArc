package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class AnalysisDataEmissionAfterDecreaseTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void AnalysisDataGetAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn,int rownumber) throws IOException, InterruptedException {

		Thread.sleep(10000);
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
		
		BigDecimal ResponseScore=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("energy.info_json.'Attributes for this project'.'Score (out of 100)'");
			
		System.out.println(ResponseScore);
			
		CommonMethod.fetchedID = ResponseScore.toString();
		
		data.setCellData(CustomSheetName, "ScoreAfterEmissionDecrease", rownumber, CommonMethod.fetchedID);
		
		BigDecimal ResponseAnalysis=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("energy.info_json.'Attributes for this project'.'Raw GHG (mtco2e/day)'");
		
		System.out.println(ResponseAnalysis);
			
		CommonMethod.fetchedID = ResponseAnalysis.toString();
		
		data.setCellData(CustomSheetName, "AnalysisAfterEmissionDecrease", rownumber, CommonMethod.fetchedID);
		
		BigDecimal Score = new BigDecimal(data.getCellData(CustomSheetName, "Score", rownumber));
		BigDecimal Analysis = new BigDecimal(data.getCellData(CustomSheetName, "Analysis", rownumber));
		
		assertThat(Score, greaterThan(ResponseScore));
		assertThat(Analysis, lessThan(ResponseAnalysis));
			
	}




}