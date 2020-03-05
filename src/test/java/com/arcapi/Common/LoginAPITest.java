package com.arcapi.Common;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class LoginAPITest extends BaseClass {

	@Test(groups="Login")
	public void LoginAPI () {
		
		try {
			url= "/auth/login/";
			CommonMethod.res = MethodCall.POSTRequestLogin(url);
			Assertion.verifyStatusMessage(CommonMethod.res, "HTTP/1.1 200 OK");
			CommonMethod.res.then().assertThat().body("token_type", equalTo("Bearer"));
			
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		

	}

	
}
