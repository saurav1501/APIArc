package com.arcapi.Buildingtestcases;

import java.math.BigDecimal;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class GhgMeanEmissionTest extends BaseClass {

	@Test(groups = "CheckData")
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void GhgGetTestAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
			+ "/ghg/mean/";
			
			CommonMethod.res = MethodCall.GETRequest(url);	
			BigDecimal xyz=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("mean");
			CommonMethod.fetchedID = xyz.toString();
			System.out.println(CommonMethod.fetchedID);
			data.setCellData(CustomSheetName, "Mean", rownumber, CommonMethod.fetchedID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	

}