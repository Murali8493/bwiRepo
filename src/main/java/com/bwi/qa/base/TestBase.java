package com.bwi.qa.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase
{
	public static WebDriver driver;
	public static Properties prop;

	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream config = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\bwi\\qa\\config\\config.properties");
			prop.load(config);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization()
	{
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\test\\resources\\drivers\\chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		System.out.println(prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
	}

	////////////// ReUsable methods///////////////////////////

	public boolean verifyElementExist(WebElement element)
	{
		boolean blnStatus = false;
		WebDriverWait localWebDriverWait = new WebDriverWait(driver, 60);
		try {
			localWebDriverWait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("Element is available:" + element + "Pass");

			blnStatus = true;

		} catch (RuntimeException localRuntimeException) {
			System.out.println("Error in finding Element:" + localRuntimeException.getMessage() + "Fail");
		}
		return blnStatus;
	}

	public void syncWait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			;
		}
	}

	public boolean verifyElementExistByLocator(By loc) {
		boolean blnStatus = false;
		WebDriverWait localWebDriverWait = new WebDriverWait(driver, 60);
		try {
			localWebDriverWait.until(ExpectedConditions.presenceOfElementLocated(loc));
			System.out.println("Element is available:" + loc + "Pass");
			blnStatus = true;

		} catch (RuntimeException localRuntimeException) {
			System.out.println("Error in finding Element:" + localRuntimeException.getMessage() + "Fail");
		}
		return blnStatus;
	}
	
	public String convertMonth(String selectedmonth)
	{
		String month = selectedmonth;
		switch (month)
		{
		 case "January": return "-Jan-2021";
		 case "February": return "-Feb-2021";
		 case "March": return "-Mar-2021";
		 case "April": return "-Apr-2021";
		 case "May": return "-May-2021";
		 case "June": return "-jun-2021";
		 case "July": return "-Jul-2021";
		 case "August": return "-Aug-2021";
		 case "September": return "-Sep-2021";
		 case "October": return "-Oct-2021";
		 case "November": return "-Nov-2021";
		 case "December": return "-Dec-2021";

		 default: return "invalid month";
		}
	}
	
	public void pressPageDownKey()
	{
		try
		{
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_PAGE_DOWN);
			r.keyRelease(KeyEvent.VK_PAGE_DOWN);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
