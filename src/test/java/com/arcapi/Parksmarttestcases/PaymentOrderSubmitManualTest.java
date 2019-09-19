package com.arcapi.Parksmarttestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class PaymentOrderSubmitManualTest extends BaseClass {

	@Test(groups="CheckPayment")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber"})
	public void PaymentOrderSubmitPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber)
	{

				
       try {
		int RowNum = data.getRowCountbyColNum("Manual", 0);
			
			for(int i=2;i<=RowNum;i++) {

			url = "/assets/LEED:" + data.getCellData("Manual", ProjectTypeColumn, i) + "/payments/order/";

			payload=AddProjectPayload.paymentCC();				
			CommonMethod.res= MethodCall.POSTRequest(url, payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			Assertion.verifyStatusMessage(CommonMethod.res,statusMessage);	

		}
	} catch (JsonProcessingException e) {
		
		e.printStackTrace();
	}
	}

	
}