package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AllMeterConsumptionDeleteAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AllMeterConsumptionDeleteAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		int RowNum = 15;
		for (int i =2; i<= RowNum;i++) {		
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:" + data.getCellData("DataInput", "CityMeterID", i) + "/consumption/ID:"+data.getCellData("DataInput", "CityPK", i)+"/?recompute_score=false";;
		CommonMethod.res = MethodCall.DELETERequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		}
	}
}
