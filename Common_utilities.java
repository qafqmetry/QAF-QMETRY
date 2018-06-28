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
	55555
		protected static WebDriver driver;
	public static boolean driverClosed = false;
	public static boolean testStarted = false;

	@BeforeTest()
	@Parameters({ "browser" })
	public static void beforeTest(String browser) throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		try {
			switch (browser) {
			case "Desktop Chrome":
				System.setProperty("webdriver.chrome.driver",
						"C:\\Automation\\SeleniumAutomation\\SeleniumFramework\\drivers\\chromedriver_2.32.exe");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				driver = new ChromeDriver(options);
				// driver= new RemoteWebDriver(new
				// URL("http://127.0.0.1:8080/wd/hub"), capabilities);
				break;

			case "Desktop Firefox": // omni-channel
				break;
			}

			capabilities.setCapability("scriptName", "Give Script Name");

		} catch (Exception e) {
			System.out.println("Failed to get driver for  stacktrace is : " + getStackTraceAsString(e));

			throw e;
		}
	}// end of method beforeTest

	@AfterTest
	public static void quit() {

		driver.quit();
	}

	public static void quitBrowser() {
		// driver.quit();
		if (((RemoteWebDriver) driver).getSessionId() != null && !driverClosed) {
			driver.close();
			// driver.quit();
			driverClosed = true;
		}
		if (driver != null) {
			driver.quit();
		}
		System.out.println("Driver Closed");
	}

	//
	public static void openURL() throws MalformedURLException, AssertionError, NoSuchElementException, Exception {
		System.out.println("\n Launching: " + Config_Value.website.homeurl + " now...");
		try {
			driver.get(Config_Value.website.homeurl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
			System.out.println("- - Open Home Page URL ");
			takeSafeScreenshot();
		} catch (Exception ex) {
			System.out.println("\n Unable to open URL: " + Config_Value.website.homeurl + "not sure why" + ex);
		}
	}

	// *******************Beginning of Click
	// Methods************************************************************************************
	// To click the link/Button
	public boolean clickAction(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		try {
			element.click();
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}// end of method clickAction

	// Get the text of the Element
	public String getText(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}// end of method getText
	// Get the text of the Element by Test data

	public String getTextByTD(By byTD, String account) {
		String locator_dup = byTD.toString();
		String locator = locator_dup.replaceAll("replaceTDValue", account);
		By finallocator = By.xpath(locator.replaceAll("By.xpath:", "").trim());
		return driver.findElement(finallocator).getText();
	}

	// Get the text of the Element

	public boolean setText(WebElement element, String testData) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			element.sendKeys(testData);
			return true;
		} catch (Exception e) {
			return false;
		}
	} // end of method end of setText

	// Clear the text of the Element
	public boolean clearText(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			element.clear();
			return true;
		} catch (Exception e) {
			return false;
		}
	} // end of method clearText

	// To Select the DropDown
	public boolean selectDropdownByVisisbleText(WebElement element, String testData) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		if (element.isDisplayed()) {
			Select select = new Select(element);
			select.selectByVisibleText(testData.trim());
		} else {
			return false;
		}

		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}// end of method selectDropdownByVisisbleText

	// Select a dropdown by value
	public boolean selectDropdownByValue(WebElement element, String testData) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		if (element.isDisplayed()) {
			Select select = new Select(element);
			select.selectByValue(testData.trim());
		} else {
			return false;
		}

		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}// end of method selectDropdownByValue

	public boolean selectDropdownByIndex(WebElement element, int testData) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		if (element.isDisplayed()) {
			Select select = new Select(element);
			select.selectByIndex(testData);
		} else {
			return false;
		}

		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}// end of method selectDropdownByIndex

	// Click Top Level Menu
	public boolean click_Menu(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
			driver.manage().timeouts().implicitlyWait(Config_Value.WEBDRIVER_WAIT, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	} // end of method click_Menu

	// *******************End of Click
	// Methods************************************************************************************

	// ******************Beginning of Navigation
	// Methods*************************************************************************
	// To Navigate back
	public void navigateBack() {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of method navigateBack

	// To switch the Tab
	public boolean switchTab() {
		try {
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			return true;
		} catch (Exception e) {
			return false;

		}
	}// end of method switchTab

	public boolean switchtoOLDTab() {
		try {
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(0));
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	public boolean CloseAndSwitchtoOLDTab() {
		try {
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	// To switch the Frame
	public void switchFrame(String testdata) {
		try {
			Thread.sleep(10000);
			if (!(testdata.startsWith("0") || testdata.startsWith("1"))) {
				driver.switchTo().frame(testdata);
			} else {
				// System.out.println(driver.findElements(By.tagName("Iframe")).size());
				int frame = Integer.parseInt(testdata);
				driver.switchTo().frame(frame);
			}
		} catch (Exception e) {

		}
	} // end of method switchFrame

	// To switch to main window
	public void switchToMainWindow() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of method switchToMainWindow

	public static void scrollPageTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		System.out.println("Scrolled to top of the page");

	}// end of method scrollPageTop

	public static void scrollPageDown() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600)");
		System.out.println("Scrolled to middle of the page");
	} // end of method scrollPageDown

	// ******************End of Navigation
	// Methods*************************************************************************

	// *****************Beginning of API
	// methods**************************************************************************

	protected static WebElement sfluentWait(final By locator, RemoteWebDriver driver, long defaultimeout) {
		try {

			FluentWait<RemoteWebDriver> wait = new FluentWait<RemoteWebDriver>(driver)
					.withTimeout(defaultimeout, TimeUnit.SECONDS).pollingEvery(250, TimeUnit.MILLISECONDS)
					.ignoring(Exception.class);
			// .ignoring(NoSuchElementException.class);
			WebElement webelement = wait.until(new Function<RemoteWebDriver, WebElement>() {
				public WebElement apply(RemoteWebDriver driver) {
					return driver.findElement(locator);
				}
			});
			return webelement;
		} catch (Exception e) {
			return null;
		}
	}// end of method sfluentWait

	// Print Stack Trace of beforeTest method
	protected static String getStackTraceAsString(Exception ex) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);
		return stringWriter.toString();
	}// end of method getStackTraceAsString

	// Take ScreenShot
	protected static byte[] takeSafeScreenshot() {
		try {
			if (!testStarted)
				return null;

			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		} catch (Exception ex) {
			System.out.println(getStackTraceAsString(ex));
			return null;
		}
	} // end of method takeSafeScreenshot()

	protected String getNextDay() {
		DateFormat dateFormat = new SimpleDateFormat("d");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		date = cal.getTime();
		return dateFormat.format(date);

	}

	protected String getNext3Days() {
		DateFormat dateFormat = new SimpleDateFormat("d");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 3);
		date = cal.getTime();
		return dateFormat.format(date);

	}

	protected String getCurrentYear() {
		DateFormat dateFormat = new SimpleDateFormat("yy");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		date = cal.getTime();
		return dateFormat.format(date);

	}

	protected boolean selectDropdown(WebElement element, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
			wait.until(ExpectedConditions.visibilityOf(element));
			if (element.isDisplayed()) {
				Select select = new Select(element);
				select.selectByVisibleText(data);
				return true;
			}
			return false;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	// *****************End of Select
	// dropdown**************************************************************************

	// *****************Start
	// Select_Single_CheckBox*************************************************************************
	public boolean selectSingleCheckBox(By element) {
		boolean flag = false;
		;
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		By locator = element;
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		WebElement oCheckBox = driver.findElement(locator);
		if (!oCheckBox.isSelected()) {
			oCheckBox.click();
			flag = true;
		}
		if (flag == true) {
			return true;
		} else {
			return false;
		}
	} // End of method Select_Single_CheckBox

	public boolean SelectCheckBox(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		By locator = ele;
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		List<WebElement> oCheckBox = driver.findElements(locator);
		for (int numofCheckboxes = 0; numofCheckboxes < oCheckBox.size(); numofCheckboxes++) {
			WebElement element = oCheckBox.get(numofCheckboxes);
			element.click();
			return true;
		}
		return false;
	} // End of method selectRandomCheckBox
	// *****************end of

	// *****************Start of Get
	// Content*************************************************************************
	public boolean getContent(By element) {
		try {
			boolean flag = false;
			;
			WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
			By locator = element;
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			List<WebElement> elements = driver.findElements(locator);
			for (WebElement ele : elements) {
				String elementData = ele.getText();
				System.out.println(elementData);
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	} // end of method GET_CONTENT

	// *****************end of Get
	// Content*************************************************************************

	/*--------------------------Validate date format-----------------*/

	public boolean validateDateFormat(String value, String format) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			return false;
		}
		if (date != null)
			return true;
		else
			return false;
	}
	/*------------End of validate date format---------------------------------*/

	// Validate Element is Displayed
	public boolean validateElementisDisplayed(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}// end of method validateElementisDisplayed

	// Validate Element is Clickable
	public boolean validateElementisClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element.isDisplayed();
	}// end of method validateElementisClickable

	// Validate Element is Enabled
	public boolean validateElementisEnabled(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isEnabled();
	}// end of method validateElementisEnabled

	// Validate Object is not present
	public boolean validateElementisNotDisplayed(WebElement element) {
		try {
			if (!element.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}// end of method validateElementisNotDisplayed

	// Validate the Content of the Element Text is exact match
	public boolean validateContent(WebElement element, String data) {
		WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
		wait.until(ExpectedConditions.visibilityOf(element));
		if (element.getText().toString().equals(data)) {
			return true;
		}
		return false;
	}// end of method validateContent

	// Validate the Content of the Element Text contains data
	public boolean validateContains(WebElement element, String data) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Config_Value.WEBDRIVER_WAIT);
			wait.until(ExpectedConditions.visibilityOf(element));
			if (element.getText().toString().contains(data)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}// end of method validateContains

	// ********************End of Validate

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
