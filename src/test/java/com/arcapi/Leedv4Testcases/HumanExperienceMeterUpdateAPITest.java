package com.arcapi.Leedv4Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class HumanExperienceMeterUpdateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void HumanExperienceMeterUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber) {
  
        String[] Unit = {"ug/m3","mg/m3", "ppm","ppb"};
        
        String[] Type = {"263","260","258","565","566","567","568","569","570","571","572"};
		
		for (int i =2; i<= 2;i++) {
			for (int j =0; j< Unit.length;j++) {
				for (int k =0; k< Type.length;k++) {
			
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", "Formaldehyde");
		jsonAsMap.put("native_unit", Unit[j]);
		jsonAsMap.put("type", Type[k]);

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
				+ data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/";

		CommonMethod.res = MethodCall.PUTRequest(url, jsonAsMap);
			
		if(j==0 && k==0){
			Assertion.verifyStatusCode(CommonMethod.res, 400);
		}
		
		else if(j==1 && k>0){
			
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			}
		
		else if(j==2 && k>1){
			
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			}
		
		else if(j==3 && k>=0){
			if(k==10) {
				System.out.println("inside");
				Assertion.verifyStatusCode(CommonMethod.res, 200);		
				
			}
			else {
				Assertion.verifyStatusCode(CommonMethod.res, 400);
			}
		}
        else {
        	Assertion.verifyStatusCode(CommonMethod.res, 200);		
        	
        }
		
		}
	}
}}
	
}