package com.arcapi.Buildingtestcases;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class AssetDetailEmissionFactorTest extends BaseClass {

	@Test(groups ="EmissionFactor")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void AssetDetailAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/";

			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			BigDecimal xyz=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("emission_factor");
			CommonMethod.fetchedID = xyz.toString();
			
			Assert.assertEquals(CommonMethod.fetchedID, data.getCellData(CustomSheetName, "EmissionFactor",rownumber));
		} catch (Exception e) {
		e.printStackTrace();
		}
	}



}