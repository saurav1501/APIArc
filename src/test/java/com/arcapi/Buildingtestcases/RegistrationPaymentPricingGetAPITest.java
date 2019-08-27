package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class RegistrationPaymentPricingGetAPITest extends BaseClass {

	@Test(groups="CheckPayment")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void RegistrationPaymentPricingGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
	
		try {
			projectID=data.getCellData(SheetName, ProjectTypeColumn, rownumber);
			url="/assets/LEED:"+projectID+"/payments/price/?soreference=registration";
			
			CommonMethod.res = MethodCall.GETRequest(url);
			
			/**************Storing Data In Excel Sheet************************/
			CommonMethod.fetchedID = CommonMethod.res.path("price.company_code[0]").toString();
			CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
			log.info(CommonMethod.fetchedID);
			data.setCellData(SheetName, "CompanyCode", rownumber, CommonMethod.fetchedID);
			CommonMethod.fetchedID = CommonMethod.res.path("price.description[0]").toString();
			CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
			log.info(CommonMethod.fetchedID);
			data.setCellData(SheetName, "MaterialDescription", rownumber, CommonMethod.fetchedID);
			CommonMethod.fetchedID = CommonMethod.res.path("price.material[0]").toString();
			CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
			log.info(CommonMethod.fetchedID);
			data.setCellData(SheetName, "MaterialCode", rownumber, CommonMethod.fetchedID);
			
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			Assertion.verifyStatusMessage(CommonMethod.res, statusMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}