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

	public FindHotelPage() {
		PageFactory.initElements(driver, this);
	}

	public String getPreviousDestination() {
		syncWait(2000);
		return destinationTxtBox.getText();
	}

	public void enterDestination(String destination) throws Exception {
			System.out.println("Destination:::" + destination);
			destinationTxtBox.clear();
			destinationTxtBox.sendKeys(destination);
			syncWait(2000);
			driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']")).click();
	}

	public void selectChechInDate(String month, String date) throws InterruptedException {
			checkInDate = month + " " + date;
			checkInDateSelect.click();
			System.out.println("CheckIn:::" + checkInDate);
			syncWait(2000);
			driver.findElement(By.xpath("//a[contains(@aria-label, '" + checkInDate + "')]")).click();
	}

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

	public void clickOnFindMyHotelButton() {
		if (homePage.verifyFindMyHotelButton()) {
			String hotelText = findMyHotelButton.getText();
			System.out.println("Text on FIND MY HOTEL button is:::" + hotelText);
			findMyHotelButton.click();
		} else {
			System.out.println("Find My Hotel button is not avaiable");
		}
	}

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

	public int checkAvailabilityOfHotelCard() {
		syncWait(2000);
		int count =hotelCardsList.size();
		System.out.println("No of hotel cards available is:::" + count);
		return count;
	}

	public void clickOnChangeSearch() {
		changeSearchButton.click();
	}

}
