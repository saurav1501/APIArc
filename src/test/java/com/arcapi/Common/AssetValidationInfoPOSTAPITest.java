package com.arcapi.Common;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetValidationInfoPOSTAPITest extends BaseClass {

	@Test(groups="checkAsset")
	public void AssetValidationInfoPOSTAPI() {
		
		try {
			url="/assets/validation/";
			payload  = AddProjectPayload.validationPayload();
			CommonMethod.res =MethodCall.POSTRequest(url, payload);		
			Assertion.verifyStatusCode(CommonMethod.res,200);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	


}