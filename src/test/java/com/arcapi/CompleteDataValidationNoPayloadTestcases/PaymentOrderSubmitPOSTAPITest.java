package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class PaymentOrderSubmitPOSTAPITest extends BaseClass {

	@Test(groups="CheckPayment")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void PaymentOrderSubmitPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber)
			{				
				try {
					url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/payments/order/";
					CommonMethod.res = MethodCall.POSTRequest(url);
					Assertion.verifyStatusCode(CommonMethod.res, 400);
					CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			}

			

		}