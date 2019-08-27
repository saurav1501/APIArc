package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.AddProjectPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class AssetOwnerOrgPOSTAPITest extends BaseClass {

	@Test(groups="OwnerOrg")
	public void AssetOwnerOrgPOSTAPI() throws IOException, InterruptedException {

		try {
			url ="/assets/searchowner/";
			payload = AddProjectPayload.addOwnerOrg();
			CommonMethod.res = MethodCall.POSTRequest(url, payload);		
			Assertion.verifyStatusCode(CommonMethod.res, 201);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}