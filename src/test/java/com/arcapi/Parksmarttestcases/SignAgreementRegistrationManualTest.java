package com.arcapi.Parksmarttestcases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Controller.TestUtils;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class SignAgreementRegistrationManualTest extends BaseClass {

	@Test(groups="CheckSignAggrement")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void SignAgreementRegistrationAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			map = new HashMap<Object,Object>();
				
			int RowNum = data.getRowCountbyColNum("Manual", 0);
			
			for(int i=2;i<=RowNum;i++) {

			url = "/assets/LEED:" + data.getCellData("Manual", ProjectTypeColumn, i) + "/agreements/";
			String Key = "agreement";
			File Value = CommonMethod.file;
			map.put("SoReference","REGISTRATION");
			CommonMethod.res = MethodCall.POSTRequest(url, map,Key,Value);					
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			Assertion.verifyStatusMessage(CommonMethod.res, "HTTP/1.1 200 OK");
			response= TestUtils.getResposeString(CommonMethod.res);
			Assert.assertTrue(response.contains("result"));
			Assert.assertTrue(response.contains("Agreement Signed Successfully"));

			
			
}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}