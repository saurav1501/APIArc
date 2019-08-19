package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.json.config.JsonPathConfig;

public class GhgMeanEmissionAfterIncreaseTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void GhgGetTestAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException, InterruptedException {

		Thread.sleep(10000);
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
		
        BigDecimal ResponseMean=JsonPath.with(CommonMethod.res.asString()).using(new JsonPathConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)).get("mean");
		
		System.out.println(ResponseMean);
		
		CommonMethod.fetchedID = ResponseMean.toString();

		data.setCellData(CustomSheetName, "MeanAfterEmissionIncrease", rownumber, CommonMethod.fetchedID);
		
		BigDecimal Mean = new BigDecimal(data.getCellData(CustomSheetName, "Mean", rownumber));
		
		System.out.println(Mean);
		
		assertThat(ResponseMean, greaterThan(Mean));
	}

	
}