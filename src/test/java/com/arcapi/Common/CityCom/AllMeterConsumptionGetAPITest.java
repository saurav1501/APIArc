package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AllMeterConsumptionGetAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AllMeterConsumptionGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		int RowNum = 15;
		for (int i =2; i<= RowNum;i++) {
		
		String Reading = data.getCellData("DataInput", "CityMeterReading", i);
		
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:" + data.getCellData("DataInput", "CityMeterID", i) + "/consumption/";
		CommonMethod.res = MethodCall.GETRequest(url);
		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		CommonMethod.fetchedID = CommonMethod.res.path("results[0].reading").toString();
		Assertion.verifyData(CommonMethod.fetchedID, Reading);

		
		System.out.println(CommonMethod.fetchedID);
		System.out.println(Reading);
		
		CommonMethod.fetchedID = CommonMethod.res.path("results[0].id").toString();

		System.out.println(CommonMethod.fetchedID);
		data.setCellData("DataInput", "CityPK", i, CommonMethod.fetchedID);

		}
	}
}
