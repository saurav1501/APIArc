 package com.arcapi.Common.V2;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ResampledTotalScoreGetAPITest extends BaseClass {

	@Test(groups="CheckAnalysis")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ResampledTotalScoreGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			url = "/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/resampled/total_score/";
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			String str = CommonMethod.res.path("score").toString();
			String[] str1 = str.split(",");
			int i=2;
			for(String str2: str1) {
				String text = str2.replace("[","").replace("]","").replace(" ", "");
				System.out.println(text);
				data.setCellData("DataInput","TotalScore",i,text);
				i= i+1;
		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	

}