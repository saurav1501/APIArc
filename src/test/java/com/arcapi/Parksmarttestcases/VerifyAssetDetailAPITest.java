package com.arcapi.Parksmarttestcases;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;

public class VerifyAssetDetailAPITest extends BaseClass {

	@Test(groups="CheckAsset")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyAssetDetailAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

	    try {
			url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/";
			CommonMethod.res= MethodCall.GETRequest(url);	
			JsonPath jp = new JsonPath(CommonMethod.res.asString()); 
			String occupancy = jp.get("occupancy").toString();
			String LeedID = jp.get("leed_id").toString();
			String Gross = jp.get("gross_area").toString();
			double value = Double.parseDouble(Gross);
			int GFA = (int)value;
			String OpHours = jp.get("operating_hours").toString();
			CommonMethod.res.then().assertThat().body("name", equalTo(data.getCellData(SheetName, "ProjectName", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("unitType", equalTo(data.getCellData(SheetName, "Unit", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("street", equalTo(data.getCellData(SheetName, "Address", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("city", equalTo(data.getCellData(SheetName, "City", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("state", equalTo(data.getCellData(SheetName, "State", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("country", equalTo(data.getCellData(SheetName, "Country", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("ownerType", equalTo(data.getCellData(SheetName, "OwnerType", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("organization", equalTo(data.getCellData(SheetName, "OwnerOrg", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("owner_email", equalTo(data.getCellData(SheetName, "OwnerEmail", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("manageEntityCountry", equalTo(data.getCellData(SheetName, "OwnerCountry", rownumber).toString()));
			CommonMethod.res.then().assertThat().body("year_constructed", equalTo(data.getCellData(SheetName, "Year", rownumber).toString()));
			Assert.assertEquals(occupancy, data.getCellData(SheetName, "Occupancy", rownumber));
			Assert.assertEquals(LeedID, data.getCellData(SheetName, ProjectTypeColumn, rownumber));
			Assert.assertEquals(GFA, Integer.parseInt(data.getCellData(SheetName, "GFA", rownumber)));
			Assert.assertEquals(OpHours, data.getCellData(SheetName, "OpHours", rownumber));
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		
	    } catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}

	
}