package base;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import dataprovider.ConfigReader;
import factory.BrowserFactory;

public class BaseClass 
{
	
	//Before and After Annotations
	
	/* In this case Before and After Class
	 *  Open Browser
	 *  	Test
	 *  	Test
	 *  	Test
	 *  	Test
	 *  Close Browser
	 *  
	 *  // In this case Before and After Method
	 *  Open Browser
	 *  	Test
	 *  Close Browser
	 * 
	 *  Open Browser
	 *  	Test
	 *  Close Browser
	 */
	
	public WebDriver driver;
	
	@BeforeClass
	public void setupBrowser() throws MalformedURLException
	{
		System.out.println("LOG:INFO - Setting Up Browser");
		
		String browser=ConfigReader.getValue("browser");
		
		String appurl=ConfigReader.getValue("qaenv");
		
		driver=BrowserFactory.getDriver(browser,appurl);
		
		System.out.println("LOG:INFO - Application is up and running");
	}
	

	@AfterClass
	public void tearDown()
	{
		System.out.println("LOG:INFO - Closing Browser");
		driver.quit();
		System.out.println("LOG:INFO - Application and browser closed");
	}
	
}
