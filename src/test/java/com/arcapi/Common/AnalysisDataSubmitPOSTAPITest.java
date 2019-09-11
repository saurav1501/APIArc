package com.arcapi.Common;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AnalysisDataSubmitPOSTAPITest extends BaseClass {

	@Test(groups="CheckAnalysis")
	@Parameters({"SheetName","ProjectTypeColumn","rownumber"})
	public void AnalysisDataSubmitPOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			url = "/assets/LEED:" +  data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/analysis/";
			payload = AddProjectPayload.analyisData();
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


}