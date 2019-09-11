package com.arcapi.Buildingtestcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.math.BigDecimal;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class GhgMeanEmissionAfterDecreaseTest extends BaseClass {

	@Test(groups="CheckEmession")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void GhgMeanEmissionAfterDecrease(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		try {
			url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
			+ "/ghg/mean/";
			
			CommonMethod.res = MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(CommonMethod.res, 200);	
      
			BigDecimal ResponseMean=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("mean");
			
			CommonMethod.fetchedID = ResponseMean.toString();

			data.setCellData(CustomSheetName, "MeanAfterEmissionDecrease", rownumber, CommonMethod.fetchedID);
			
			BigDecimal Mean = new BigDecimal(data.getCellData(CustomSheetName, "Mean", rownumber));
				
			assertThat(ResponseMean,lessThan(Mean));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	}