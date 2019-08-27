package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PaymentReceiptAPITest extends BaseClass {

	@Test(groups="CheckPayment")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void PaymentReceiptAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		try {
			url="/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/payments/receipt/"+data.getCellData("DataInput","ESalesdocument", 2)+"/?access-token="+CommonMethod.Token+ "&subscription-key="+CommonMethod.SubscriptionKey;
			CommonMethod.res =MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		


	}

}