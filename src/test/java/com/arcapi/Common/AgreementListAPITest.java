package com.arcapi.Common;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class AgreementListAPITest extends BaseClass {

	@Test(groups="AgreementList")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AgreementListAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		CommonMethod.res = given().log().all()
				.header("Ocp-Apim-Subscription-Key",
						CommonMethod.SubscriptionKey)
				.header("Authorization", header)
				.spec(reqSpec)
				.when()
				.get("/assets/LEED:"
						+ data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						+ "/agreements/").then().contentType(ContentType.JSON)
				.extract().response();
		System.out.println(CommonMethod.res.asString());
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.res.then().spec(respSpec);
	}
}