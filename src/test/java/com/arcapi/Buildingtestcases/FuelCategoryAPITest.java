package com.arcapi.Buildingtestcases;

import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class FuelCategoryAPITest extends BaseClass {

	@Test(groups="CheckFuel")
	public void FuelCategoryAPI() {

		try {
			url = "/fuel/category/";
			CommonMethod.res= MethodCall.GETRequest(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
}