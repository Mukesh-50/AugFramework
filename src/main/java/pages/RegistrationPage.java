package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helper.Utility;

public class RegistrationPage {

	WebDriver driver;

	public RegistrationPage(WebDriver driver) {

		this.driver = driver;
	}
	
	   private By name=By.id("name");
	   private By email=By.id("email");
	   private By passowrd=By.id("password");
	   
	  
	   private By selectState=By.id("state");
	   private By hobbies=By.id("hobbies");
	   private By signUpBtn=By.xpath("//button[text()='Sign up']");
	   private By signUpMsg=By.xpath("//div[text()='Signup successfully, Please login!']");
	   
	   
	   public String createUser(String username , String emailAddress, String pass, String intrest, String gender, String state, String hob) {
		   By courseCheckBox=By.xpath("//label[text()='"
		   		+ intrest
		   		+ "']//preceding::input[1]");
		    By gen=By.xpath("//input[@value='"
		    		+ gender
		    		+ "']");
		   
		   Utility.findElement(driver, name).sendKeys(username);
		   Utility.findElement(driver, passowrd).sendKeys(pass);
		   String[] mailVal = emailAddress.split("@");
		   String finalMail=mailVal[0]+Utility.getCurrentDateTime()+"@"+mailVal[1];
		   Utility.findElement(driver, email).sendKeys(finalMail);
		   Utility.findElement(driver, courseCheckBox).click();
		   Utility.findElement(driver, gen).click();
		   Utility.selectValue(driver, selectState,state);
		   Utility.selectValue(driver, hobbies,hob);
		   Utility.clickElement(driver, signUpBtn);
		   String text = Utility.findElement(driver, signUpMsg).getText();
		   return text;
	   }
}
