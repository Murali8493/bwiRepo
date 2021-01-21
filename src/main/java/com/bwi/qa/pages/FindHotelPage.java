package com.bwi.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bwi.qa.base.TestBase;

public class FindHotelPage extends TestBase {
	HomePage homePage = new HomePage();
	public String checkInDate = null;
	public String checkOutDate = null;
	public boolean updateStatus = false;

	@FindBy(id = "destination-input")
	WebElement destinationTxtBox;

	@FindBy(id = "checkin")
	WebElement checkInDateSelect;

	@FindBy(id = "checkout")
	WebElement checkOutDateSelect;

	@FindBy(id = "btn-modify-stay-update")
	WebElement findMyHotelButton;

	@FindBy(id = "btn-modify-stay")
	WebElement changeSearchButton;

	@FindBy(id = "summary-destination")
	WebElement summaryDestination;

	@FindBy(id = "summary-checkin")
	WebElement summaryCheckIn;

	@FindBy(id = "summary-checkout")
	WebElement summaryCheckOut;

	@FindBy(css = "div.aspectMaintainer")
	List<WebElement> hotelCardsList;

	/**
	 * Use: This is a constructor
	 * @author Murali krishna Sara
	 * @param  none
	 * @return none
	 */
	public FindHotelPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Use: To get previous destinaiton name
	 * @author Murali krishna Sara
	 * @param  none
	 * @return String
	 */
	public String getPreviousDestination() {
		syncWait(2000);
		return destinationTxtBox.getText();
	}

	/**
	 * Use: To select the destination in the application
	 * @author Murali krishna Sara
	 * @param  String : need to provide destination value
	 * @return none
	 */
	public void enterDestination(String destination) throws Exception {
		System.out.println("Destination:::" + destination);
		destinationTxtBox.clear();
		destinationTxtBox.sendKeys(destination);
		syncWait(2000);
		driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']")).click();
	}

	/**
	 * Use: To select the checkindate in the application
	 * @author Murali krishna Sara
	 * @param1  String: need to provide month
	 * @param2  String: need to provide date
	 * @return none
	 */
	public void selectChechInDate(String month, String date) throws InterruptedException {
		checkInDate = month + " " + date;
		checkInDateSelect.click();
		System.out.println("CheckIn:::" + checkInDate);
		syncWait(2000);
		driver.findElement(By.xpath("//a[contains(@aria-label, '" + checkInDate + "')]")).click();
	}

	/**
	 * Use: To select the checkoutdate in the application
	 * @author Murali krishna Sara
	 * @param1  String: need to provide month
	 * @param2  String: need to provide date
	 * @return none
	 */
	public void selectCheckOutDate(String month, String date) throws InterruptedException {
		checkOutDate = month + " " + date;
		checkOutDateSelect.click();
		System.out.println("CheckOut:::" + checkOutDate);
		boolean availibilityOfCheckOutDate = verifyElementExistByLocator(
				By.xpath("//a[contains(@aria-label, '" + checkOutDate + "')]"));
		System.out.println("CheckOutDate element availibility:::" + availibilityOfCheckOutDate);
		if (availibilityOfCheckOutDate) {
			driver.findElement(By.xpath("//a[contains(@aria-label, '" + checkOutDate + "')]")).click();
		} else {
			System.out.println("checkOutDate Element is not available");
		}
	}

	/**
	 * Use: To click on the FindMyHotel Button in the application
	 * @author Murali krishna Sara
	 * @param  none
	 * @return none
	 */
	public void clickOnFindMyHotelButton() {
		if (homePage.verifyFindMyHotelButton()) {
			String hotelText = findMyHotelButton.getText();
			System.out.println("Text on FIND MY HOTEL button is:::" + hotelText);
			findMyHotelButton.click();
		} else {
			System.out.println("Find My Hotel button is not avaiable");
		}
	}

	/**
	 * Use: To verify the entered Destination
	 * @author Murali krishna Sara
	 * @param  none
	 * @return String
	 */
	public String verifyDestination() {
		String destinationVerify = null;
		boolean status = verifyElementExist(summaryDestination);
		System.out.println("Visibility Status of summaryDestination is::::" + status);

		if (status) {
			destinationVerify = summaryDestination.getText();
			System.out.println("Verified Destination is:::" + destinationVerify);
		}
		return destinationVerify;
	}

	/**
	 * Use: To verify the entered checkindate
	 * @author Murali krishna Sara
	 * @param  none
	 * @return String
	 */
	public String verifyCheckInDate() {
		String checkinDateVerify = null;
		boolean status = verifyElementExist(summaryCheckIn);
		System.out.println("Visibility Status of summaryCheckIn is::::" + status);
		if (status) {
			checkinDateVerify = summaryCheckIn.getText();
			System.out.println("Verified summaryCheckIn is:::" + checkinDateVerify);
		}
		return checkinDateVerify;
	}

	/**
	 * Use: To verify the entered checkoutdate
	 * @author Murali krishna Sara
	 * @param  none
	 * @return String
	 */
	public String verifyCheckOutDate() {
		String checkOutDateVerify = null;
		boolean status = verifyElementExist(summaryCheckOut);
		System.out.println("Visibility Status of summaryCheckOut is::::" + status);
		if (status) {
			checkOutDateVerify = summaryCheckOut.getText();
			System.out.println("Verified summaryCheckOut is:::" + checkOutDateVerify);
		}
		return checkOutDateVerify;
	}

	/**
	 * Use: To verify the availability of hotel cards
	 * @author Murali krishna Sara
	 * @param  none
	 * @return int
	 */
	public int checkAvailabilityOfHotelCard() {
		syncWait(2000);
		handelDevTool();
		int count =hotelCardsList.size();
		System.out.println("================================HOTEL DETAILS=========================================");
		System.out.println("No of hotel cards available are:::" + count);
		for(int i=0;i<count;i++)
		{
			System.out.println("===========================Hotel no "+(i+1)+"=======================");
			System.out.println(hotelCardsList.get(i).getText().trim());
		}
		System.out.println();
		System.out.println();
		return count;
	}

	/**
	 * Use: To click on changesearch button
	 * @author Murali krishna Sara
	 * @param  none
	 * @return none
	 */
	public void clickOnChangeSearch() {
		changeSearchButton.click();
	}

}
