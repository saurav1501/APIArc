package com.arcapi.Common.Neg;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ReviewCertificationPricingAPITest extends BaseClass {

	@Test(groups="CheckPaymentReview")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber","SoReference" })
	public void ReviewCertificationPricingAPI(String SheetName,String ProjectTypeColumn, int rownumber, String SoReference) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/payments/price/?soreference="+SoReference;
	
		CommonMethod.res = MethodCall.GETRequest(url);
		
		CommonMethod.fetchedID = CommonMethod.res.path("price.company_code[0]").toString();
		CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
		System.out.println(CommonMethod.fetchedID);
		data.setCellData(SheetName, "CompanyCode", rownumber, CommonMethod.fetchedID);
		
		CommonMethod.fetchedID = CommonMethod.res.path("price.description[0]").toString();
		CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
		System.out.println(CommonMethod.fetchedID);
		data.setCellData(SheetName, "MaterialDescription", rownumber, CommonMethod.fetchedID);
		
		CommonMethod.fetchedID = CommonMethod.res.path("price.material[0]").toString();
		CommonMethod.fetchedID = CommonMethod.fetchedID.replaceAll("\\[", "").replaceAll("\\]", "");
		System.out.println(CommonMethod.fetchedID);
		data.setCellData(SheetName, "MaterialCode", rownumber, CommonMethod.fetchedID);
		
		Assertion.verifyStatusCode(CommonMethod.res, 403);
		
	}


}