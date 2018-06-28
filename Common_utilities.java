package utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jetSynthesys.TestAutomation.AutomationTestFactory;
import com.jetSynthesys.TestAutomation.MobileAutomationTest;
import com.jetSynthesys.TestAutomation.TestUtility.Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import pageobjects.Po_Login;
import utility.Configuration;
import utility.Listner;

public class Common_utilities {

	static WebDriver driver;
	Dimension size;
	
	@BeforeTest
	public void init() throws InterruptedException, MalformedURLException{

		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("appium-version", Configuration.appiumVersion);
		capabilities.setCapability(CapabilityType.VERSION, Configuration.VERSION);
		capabilities.setCapability(CapabilityType.PLATFORM, Configuration.PLATFORM);
		capabilities.setCapability("deviceName", Configuration.deviceName);//3100429cc9026200
		capabilities.setCapability("appPackage", Configuration.appPackage);
		capabilities.setCapability("appActivity", Configuration.appActivity);
		Thread.sleep(3000);
		capabilities.setCapability("app",System.getProperty("user.dir") + Configuration.appPath); 
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		Listner i = new Listner();
		i.init(driver);
		Thread.sleep(4000);
		
	}	
	
	
	public WebDriver scroll(WebDriver driver) throws Exception{
		size = driver.manage().window().getSize();
		  System.out.println(size);
		   
		  //Find swipe start and end point from screen's with and height.
		  //Find starty point which is at bottom side of screen.
		  int starty = (int) (size.height * 0.80);
		  //Find endy point which is at top side of screen.
		  int endy = (int) (size.height * 0.20);
		  //Find horizontal point where you wants to swipe. It is in middle of screen width.
		  int startx = size.width / 2;
		  System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

		  //Swipe from Bottom to Top.
		  ((AndroidDriver) driver).swipe(startx, starty, startx, endy, 3000);
		  ((AndroidDriver) driver).swipe(startx, starty, startx, endy, 3000);
		  Thread.sleep(2000);
		
		  return driver;

	}
	
	public WebDriver scrollOnce(WebDriver driver) throws Exception{
		size = driver.manage().window().getSize();
		  System.out.println(size);
		   
		  //Find swipe start and end point from screen's with and height.
		  //Find starty point which is at bottom side of screen.
		  int starty = (int) (size.height * 0.80);
		  //Find endy point which is at top side of screen.
		  int endy = (int) (size.height * 0.20);
		  //Find horizontal point where you wants to swipe. It is in middle of screen width.
		  int startx = size.width / 2;
		  System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

		  //Swipe from Bottom to Top.
		  ((AndroidDriver) driver).swipe(startx, starty, startx, endy, 3000);
		 
		  Thread.sleep(2000);
		
		  return driver;

	}
	
	
}
