package com.arcapi.Common.V2;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.ReusableMethods;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class RecomputeVerifyTotalScoreGraphGetAPITest extends BaseClass {

	@Test(groups="CheckScoreGraph")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void RecomputeVerifyTotalScoreGraphGetAPI(String SheetName,String ProjectTypeColumn, int rownumber){

		try {
			
			int month = Calendar.getInstance().get(Calendar.MONTH);
			month = month+2;
			
			System.out.println(month);
			int year = Calendar.getInstance().get(Calendar.YEAR);
			System.out.println(year);
			int months= month;
			int myonths= 12;
			int col = 13;
				for(int i=1;i<=12;i++) {
						
						if(months>=1) {
					
						url = "/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/scores/recompute/?at="+year+"-"+months+"-01";
						CommonMethod.res = MethodCall.GETRequest(url);
						Assertion.verifyStatusCode(CommonMethod.res, 200);
						
						months = month -i;
						
						int totalScore = ReusableMethods.totalScore();
						String total = String.valueOf(totalScore);
						
						System.out.println(total);
						
						String caltotal = data.getCellData("DataInput", "TotalScore", col);
						double calTotal = Double.parseDouble(caltotal);
						BigDecimal CalTotal = new BigDecimal(calTotal);
						CalTotal = CalTotal.setScale(0, RoundingMode.HALF_UP);
						String CalcTotal = CalTotal.toString();
						
				
						Assertion.verifyData(total, CalcTotal);
						
						
						col--;
						}
					
						else {

							 year = 2018;
							 url = "/assets/LEED:"+data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/scores/recompute/?at="+year+"-"+myonths+"-01";
							 CommonMethod.res = MethodCall.GETRequest(url);
							 
								int totalScore = ReusableMethods.totalScore();
								String total = String.valueOf(totalScore);
								
								System.out.println(total);
								
								String caltotal = data.getCellData("DataInput", "TotalScore", col);
								double calTotal = Double.parseDouble(caltotal);
								BigDecimal CalTotal = new BigDecimal(calTotal);
								CalTotal = CalTotal.setScale(0, RoundingMode.HALF_UP);
								String CalcTotal = CalTotal.toString();
								
						
								Assertion.verifyData(total, CalcTotal);
							
								col--;
									 
						}
									
				}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(Status.FAIL, MarkupHelper.createLabel(e.toString().toLowerCase()+"Test"+ " : FAILED", ExtentColor.RED));
		
		}
	
	}
	
	

	

}