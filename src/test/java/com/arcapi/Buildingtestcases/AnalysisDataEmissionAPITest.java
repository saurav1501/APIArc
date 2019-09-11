package com.arcapi.Buildingtestcases;

import java.math.BigDecimal;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class AnalysisDataEmissionAPITest extends BaseClass {

	@Test(groups="CheckEmession")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void AnalysisDataEmissionAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) {

		try {
			Thread.sleep(20000);
			url = "/assets/LEED:" +  data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/analysis/";
			
			CommonMethod.res = MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(CommonMethod.res, 200);
				
			BigDecimal score=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("energy.info_json.'Attributes for this project'.'Score (out of 100)'");
					
			CommonMethod.fetchedID = score.toString();
			
			data.setCellData(CustomSheetName, "Score", rownumber, CommonMethod.fetchedID);
			
			BigDecimal analysis=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("energy.info_json.'Attributes for this project'.'Raw GHG (mtco2e/day)'");
				
			CommonMethod.fetchedID = analysis.toString();

			System.out.println(CommonMethod.fetchedID);
			
			data.setCellData(CustomSheetName, "Analysis", rownumber, CommonMethod.fetchedID);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}



}