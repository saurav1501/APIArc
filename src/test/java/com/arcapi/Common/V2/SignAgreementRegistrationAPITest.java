package com.arcapi.Common.V2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class SignAgreementRegistrationAPITest extends BaseClass {

	@Test(groups="SignAgreement")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void SignAgreementRegistrationAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		    map = new HashMap<Object,Object>();
		
		    try {
				
				projectID= data.getCellData(SheetName, ProjectTypeColumn, rownumber);
				url= "/assets/LEED:"+projectID+"/agreements/";
				String Key = "agreement";
				File Value = CommonMethod.file;
				map.put("SoReference","REGISTRATION");
				CommonMethod.res = MethodCall.POSTRequest(url, map,Key,Value);					
				Assertion.verifyStatusCode(CommonMethod.res, 409);
						
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

	



}