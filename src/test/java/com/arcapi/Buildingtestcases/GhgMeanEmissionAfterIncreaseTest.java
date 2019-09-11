package com.arcapi.Buildingtestcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.math.BigDecimal;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class GhgMeanEmissionAfterIncreaseTest extends BaseClass {

	@Test(groups="CheckEmession")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void GhgMeanEmissionAfterIncrease(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) {

		try {
			Thread.sleep(10000);
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
			+ "/ghg/mean/";
			CommonMethod.res = MethodCall.GETRequest(url);	
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			BigDecimal ResponseMean=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("mean");
			
			CommonMethod.fetchedID = ResponseMean.toString();

			data.setCellData(CustomSheetName, "MeanAfterEmissionIncrease", rownumber, CommonMethod.fetchedID);
			
			BigDecimal Mean = new BigDecimal(data.getCellData(CustomSheetName, "Mean", rownumber));
			assertThat(ResponseMean, greaterThan(Mean));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}