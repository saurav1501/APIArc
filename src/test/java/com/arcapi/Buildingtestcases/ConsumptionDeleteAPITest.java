package com.arcapi.Buildingtestcases;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ConsumptionDeleteAPITest extends BaseClass {

	@Test(groups="CheckConsumption")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void ConsumptionDeleteAPI(String SheetName, String ProjectTypeColumn, int rownumber){
		
		try {
			url ="/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData(SheetName, "MeterID", rownumber) + "/consumption/ID:"
					+ data.getCellData(SheetName, "PK", rownumber) + "/";
			
			CommonMethod.res = MethodCall.DELETERequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}