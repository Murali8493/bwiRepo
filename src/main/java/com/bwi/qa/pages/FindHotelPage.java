package com.bwi.qa.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bwi.qa.base.TestBase;

public class FindHotelPage extends TestBase 
{
	HomePage homePage = new HomePage();
	public String CheckInDate = null;
	public String CheckOutDate = null;
	public boolean updateStatus=false;

	@FindBy(id = "destination-input")
	WebElement destinationTxtBox;

	// @FindBy(xpath = "//ul[@id='google-suggestions']//li[@data-place='New York,
	// NY, USA']")
	// WebElement selectDestination;

	@FindBy(id = "checkin")
	WebElement checkInDate;

	// @FindBy(xpath = "//a[contains(@aria-label, 'February 23')]")
	// WebElement selectCheckInDate;

	@FindBy(id = "checkout")
	WebElement checkOutDate;

	// @FindBy(xpath = "//a[contains(@aria-label, 'February 25')]")
	// WebElement selectCheckOutDate;

	@FindBy(id = "btn-modify-stay-update")
	WebElement findMYHotelBtn;

	@FindBy(id = "btn-modify-stay")
	WebElement changeSearchBtn;

	@FindBy(id = "summary-destination")
	WebElement summaryDestination;
	
	@FindBy(id = "summary-checkin")
	WebElement summaryCheckIn;
	
	@FindBy(id = "summary-checkout")
	WebElement summaryCheckOut;

	@FindBy(xpath = "//div[@class='aspectMaintainer']")
	WebElement hotelCardAvailability;
	

	
	// Initializing the Page Objects:
	public FindHotelPage() {
		PageFactory.initElements(driver, this);
	}
 
	public String getPreviousDestination()
	{
		syncWait(2000);
		return destinationTxtBox.getText() ;
		
	}

	public void enterDestination(String destination) throws Exception
	{

		try {
			System.out.println("Destination:::" + destination);
			destinationTxtBox.clear();
			destinationTxtBox.sendKeys(destination);

			syncWait(2000);
			driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']")).click();;
					

			// driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='"+destination+"']")).click();
			// }
			/*
			 * else { System.out.println("Destination Element is not available"); }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectChechInDate(String month, String date) throws InterruptedException 
	{
		CheckInDate = month + " " + date;
		checkInDate.click();
		System.out.println("CheckIn:::" + CheckInDate);

		syncWait(2000);

		driver.findElement(By.xpath("//a[contains(@aria-label, '" + CheckInDate + "')]")).click();
	

		/*
		 * } else { System.out.println("checkInDate Element is not available"); }
		 */
	}

	public void selectCheckoutDate(String month, String date) throws InterruptedException 
	{
		CheckOutDate = month + " " + date;
		checkOutDate.click();

		System.out.println("CheckOut:::" + CheckOutDate);
	
		  boolean availibilityOfCheckOutDate=verifyElementExistByLocator(By.
		  xpath("//a[contains(@aria-label, '"+CheckOutDate+"')]"));
		  
		  System.out.println("CheckOutDate element availibility:::"
		  +availibilityOfCheckOutDate);
		  
		  if(availibilityOfCheckOutDate) 
		  {

		driver.findElement(By.xpath("//a[contains(@aria-label, '" + CheckOutDate + "')]")).click();

		  } 
		  else { System.out.println("checkOutDate Element is not available"); }
		 
	}

	public void clickfindMYHotelBtn()
	{
		
		if (homePage.verifyfindMYHotelBtn())
		{
			String hotelText = findMYHotelBtn.getText();
			System.out.println("Text on FIND MY HOTEL button is:::" + hotelText);

			findMYHotelBtn.click();

		} else {
			System.out.println("Find My Hotel button is not avaiable");
		}
	}



	public String verifyDestination() 
	{
		String destinationVerify = null;
		boolean status = verifyElementExist(summaryDestination);
		System.out.println("Visibility Status of summaryDestination is::::" + status);

		if (status)
		{
			destinationVerify = summaryDestination.getText();
			System.out.println("Verified Destination is:::" + destinationVerify);
		}
		return destinationVerify;
	}
	
	public String verifyCheckInDate()
	{
		String checkinDateVerify = null;
		boolean status = verifyElementExist(summaryCheckIn);
		System.out.println("Visibility Status of summaryCheckIn is::::" + status);

		if (status)
		{
			checkinDateVerify = summaryCheckIn.getText();
			System.out.println("Verified summaryCheckIn is:::" + checkinDateVerify);
		}

		return checkinDateVerify;
	}
	public String verifyCheckOutDate() 
	{
		String checkOutDateVerify = null;
		boolean status = verifyElementExist(summaryCheckOut);
		System.out.println("Visibility Status of summaryCheckOut is::::" + status);

		if (status)
		{
			checkOutDateVerify = summaryCheckOut.getText();
			System.out.println("Verified summaryCheckOut is:::" + checkOutDateVerify);
		}
		return checkOutDateVerify;
	}
	
	public int checkAvailabilityOfHotelCard()
	{
		syncWait(2000);
		List<WebElement> lst=driver.findElements(By.xpath("//div[@class='aspectMaintainer']"));
  
		int count=lst.size();
		System.out.println("No of hotel cards available is:::"+count);
		
		return count;
	}
	public void clickOnChangeSearch()
	{
		changeSearchBtn.click();
	}
 


	
	

	////////////// ReUsable methods///////////////////////////

/*	public boolean verifyElementExist(WebElement element)
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
	}*/


}
