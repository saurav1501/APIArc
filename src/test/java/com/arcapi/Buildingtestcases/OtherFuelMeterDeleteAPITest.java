package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class OtherFuelMeterDeleteAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void OtherFuelMeterDeleteAPI(String SheetName,String ProjectTypeColumn, int rownumber){

				
		try {
			int RowNum = data.getRowCountbyColNum("DataInput", 0);
			
			for (int i =2; i<= RowNum;i++) {

			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "OtherFuelMeterID", i) + "/";

			CommonMethod.res = MethodCall.DELETERequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);

}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}