package com.arcapi.Buildingtestcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import java.math.BigDecimal;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class AnalysisDataEmissionAfterIncreaseTest extends BaseClass {

	@Test(groups="CheckAnalysis")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void AnalysisDataEmissionAfterIncrease(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber){

		try {
			Thread.sleep(30000);
			url = "/assets/LEED:" +  data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/analysis/";

			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			BigDecimal ResponseScore=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("energy.info_json.'Attributes for this project'.'Score (out of 100)'");
			CommonMethod.fetchedID = ResponseScore.toString();		
			data.setCellData(CustomSheetName, "ScoreAfterEmissionIncrease", rownumber, CommonMethod.fetchedID);
			BigDecimal ResponseAnalysis=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("energy.info_json.'Attributes for this project'.'Raw GHG (mtco2e/day)'");
			CommonMethod.fetchedID = ResponseAnalysis.toString();
			data.setCellData(CustomSheetName, "AnalysisAfterEmissionIncrease", rownumber, CommonMethod.fetchedID);
			BigDecimal Score = new BigDecimal(data.getCellData(CustomSheetName, "Score", rownumber));
			BigDecimal Analysis = new BigDecimal(data.getCellData(CustomSheetName, "Analysis", rownumber));
			assertThat(ResponseScore, lessThan(Score));
			assertThat(ResponseAnalysis, greaterThan(Analysis));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

	


}