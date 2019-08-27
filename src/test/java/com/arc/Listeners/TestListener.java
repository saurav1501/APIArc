package com.arc.Listeners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.arc.driver.BaseClass;
import com.aventstack.extentreports.ExtentReports;


public class TestListener extends BaseClass  implements ITestListener {

    
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

/*	@Override
    public synchronized void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        test = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
       
    }*/
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}