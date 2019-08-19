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

public class GhgMeanEmissionTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void GhgGetTestAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber)
						+ "/ghg/mean/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Ghg Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies List of Assets")
				.assignCategory("CheckAsset");

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
        BigDecimal xyz=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("mean");
		
		System.out.println(xyz);
		
		CommonMethod.fetchedID = xyz.toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(CustomSheetName, "Mean", rownumber, CommonMethod.fetchedID);
	}

	

}