package factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import dataprovider.ConfigReader;

public class BrowserFactory {
	public static WebDriver driver;
	// public static RemoteWebDriver driver;

	public static WebDriver getInstance() {
		return driver;
	}

	public static WebDriver getDriver(String browser, String applicationURL) throws MalformedURLException 
	{
		if (ConfigReader.getValue("cloud").equalsIgnoreCase("true")) 
		{
			
			if (browser.equalsIgnoreCase("Chrome")) 
			{
				
				ChromeOptions opt = new ChromeOptions();
				
				if (ConfigReader.getValue("headless").equalsIgnoreCase("true")) 
				{
					opt.addArguments("headless=new");
				}

			else 
			{
				driver = new ChromeDriver(opt);
			}
			
			// opt.addArguments(null)
			System.out.println("LOG:INFO - Setting up RemoteWebDriver for Chrome in cloud mode");
			driver = new RemoteWebDriver(new URL("http://18.207.122.26:4444/wd/hub"), opt);
		}
			

		} else if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Sorry Current We Dont Support " + browser + " Browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("pageload"))));

		driver.get(applicationURL);

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("implicitwait"))));

		driver.manage().timeouts()
				.scriptTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("scripttimeout"))));

		return driver;
	}

}
