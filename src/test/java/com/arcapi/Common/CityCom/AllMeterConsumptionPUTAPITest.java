package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.MeterData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class AllMeterConsumptionPUTAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AllMeterConsumptionPUTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws JsonProcessingException {
		
		int RowNum = 15;
		for (int i =2; i<= RowNum;i++) {
			
		MeterData meterData = new MeterData();
		meterData.setStart_date( "2018-01-01");
		meterData.setEnd_date("2019-01-31");
		meterData.setReading("1");
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:" + data.getCellData("DataInput", "CityMeterID", i) + "/consumption/ID:"+data.getCellData("DataInput", "CityPK", i)+"/?recompute_score=false";
		CommonMethod.res = MethodCall.PUTRequest(url, meterData);
		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
				

}
	}
}
