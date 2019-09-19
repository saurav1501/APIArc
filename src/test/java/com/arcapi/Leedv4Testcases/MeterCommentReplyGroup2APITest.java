package com.arcapi.Leedv4Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

import net.minidev.json.JSONObject;

public class MeterCommentReplyGroup2APITest extends BaseClass {

	@Test(groups="CheckComment") 
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void MeterCommentReplyGroup2API(String SheetName, String ProjectTypeColumn, int rownumber){

		try {
			int rownum=2;

			 String[] type = {"waste","transport","human"};
			 
			 for(String Type : type) {

					String getParentID = data.getCellData("LEEDONLINE", "CommentIDGroup2", rownum);

					JSONObject jsonAsMap = new JSONObject();
					jsonAsMap.put("confidential", "false");
					jsonAsMap.put("data", "This is Reply Test Comment");
					jsonAsMap.put("parent", getParentID);
					
					url = "/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber)
					 + "/"+Type+"/comment/";
					
					CommonMethod.res = MethodCall.POSTRequest(url, jsonAsMap);

					Assertion.verifyStatusCode(CommonMethod.res, 200);				
					rownum++;

				}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		}
	
}