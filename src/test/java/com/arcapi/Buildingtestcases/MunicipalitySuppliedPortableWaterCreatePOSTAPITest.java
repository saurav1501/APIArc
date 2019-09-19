package com.arcapi.Buildingtestcases;
import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.CreateMeter;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MunicipalitySuppliedPortableWaterCreatePOSTAPITest extends BaseClass{
	
		@Test(groups="CheckMeter")
		@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
		public void municipalitySuppliedPortableWaterCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

			CommonMethod.ExtentReportConfig();
			
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

			CreateMeter meterData = new CreateMeter();
		
			meterData.setName("MunicipalitySuppliedPortableWater");
			meterData.setNative_unit("gal");
			meterData.setType("47");
		
			CommonMethod.res = given().log().all().headers(headerMap).header("Authorization", header).spec(reqSpec)
					.body(meterData).when()
					.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/").then()
					.extract().response();
		
			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.responsetime);

			CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);
		
			System.out.println(CommonMethod.res.asString());
			
			CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			
			System.out.println(CommonMethod.res.path("id").toString());

			data.setCellData(sheetName, "WaterMeterID", rowNumTwo, CommonMethod.fetchedID);

			CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
			
			CommonMethod.res.then().assertThat().statusCode(201);

		}

	

	}


