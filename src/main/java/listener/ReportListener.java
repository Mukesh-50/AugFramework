package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import dataprovider.ConfigReader;
import factory.BrowserFactory;
import helper.Utility;

public class ReportListener extends BrowserFactory implements ITestListener
{
	/*
	 *  Date Time Based Report - Done
	 *  
	 *  Capture Screenshot on failure
	 */
	
	ExtentReports extent=ExtentManager.getInstance();
	
	ExtentTest extentTest;

	public void onTestSuccess(ITestResult result) 
	{
		String screenshot=Utility.getScreenshotAsBase64(BrowserFactory.getInstance());
		
		if(ConfigReader.getValue("screenshotOnSuccess").equalsIgnoreCase("true"))
		{
			extentTest.pass("Test Executed Successfully",MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		}
		else
		{
			extentTest.pass("Test Executed Successfully");
		}
		
		
	}
	
	public void onTestStart(ITestResult result)
	{
		extentTest=extent.createTest(result.getMethod().getMethodName());
	}
	
	public void onTestFailure(ITestResult result) 
	{
		String screenshot=Utility.getScreenshotAsBase64(BrowserFactory.getInstance());
		
		if(ConfigReader.getValue("screenshotOnFailure").equalsIgnoreCase("true"))
		{
		extentTest.fail("Test Failed "+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		}
		else
		{
			extentTest.fail("Test Failed "+result.getThrowable().getMessage());
		}
	}
	
	public void onTestSkipped(ITestResult result) 
	{   
		String screenshot=Utility.getScreenshotAsBase64(BrowserFactory.getInstance());
		
		if(ConfigReader.getValue("screenshotOnSkip").equalsIgnoreCase("true"))
		{
			extentTest.skip("Test Skipped "+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		}
		else
		{
			extentTest.skip("Test Skipped "+result.getThrowable().getMessage());
		}
		
		
		
	}
	
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
	
}
