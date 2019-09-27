package com.arcapi.ScenarioBasedTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class OLDExcelUploadTemplatePOSTAPITest extends BaseClass {

	@Test(groups="CheckExcelupload")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void OLDExcelUploadTemplatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/excelupload/uploadS3/";	
			CommonMethod.res = MethodCall.POSTRequestExcelUpload(url, "action_file", CommonMethod.oldexcelfile);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
		} catch (Exception e) {
			e.printStackTrace();
		}		
			

	
	}

}