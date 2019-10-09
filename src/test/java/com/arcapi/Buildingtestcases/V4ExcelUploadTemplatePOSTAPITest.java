package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class V4ExcelUploadTemplatePOSTAPITest extends BaseClass {
	
	@Test(groups="CheckExcelupload")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void V4ExcelUploadTemplatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/excelupload/uploadS3/";	
			CommonMethod.res = MethodCall.POSTRequestExcelUpload(url, "action_file", CommonMethod.v4excelfile);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}		
			

	
	}

	
}



