package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.CityComPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CreditAddTeamPOSTAPITest extends BaseClass {

	@Test(groups="CheckCredit")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber","CreditColumn","CreditColumnNum" })
	public void CreditAddTeamPOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber, String CreditColumn, int CreditColumnNum){
        
		
		try {
			int RowNum = 13;
			payload = CityComPayload.emailPayload();
			for (int i=2; i<=RowNum;i++) {
			String CreditID = data.getCellData("Credit", CreditColumn , i);	
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/actions/ID:" + CreditID +"-"+ data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/teams/";
			CommonMethod.res = MethodCall.POSTRequest(url,payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}