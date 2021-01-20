package com.bwi.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bwi.qa.base.TestBase;

public class FindHotelPage extends TestBase {
	HomePage homePage = new HomePage();
	public String CheckInDate = null;
	public String CheckOutDate = null;
	public boolean updateStatus = false;

	@FindBy(id = "destination-input")
	WebElement destinationTxtBox;

	@FindBy(id = "checkin")
	WebElement checkInDate;

	@FindBy(id = "checkout")
	WebElement checkOutDate;

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

	public FindHotelPage() {
		PageFactory.initElements(driver, this);
	}

	public String getPreviousDestination() {
		syncWait(2000);
		return destinationTxtBox.getText();
	}

	public void enterDestination(String destination) throws Exception {
		try {
			System.out.println("Destination:::" + destination);
			destinationTxtBox.clear();
			destinationTxtBox.sendKeys(destination);

			syncWait(2000);
			driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']"))
					.click();
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectChechInDate(String month, String date) throws InterruptedException {
		try {
			CheckInDate = month + " " + date;
			checkInDate.click();
			System.out.println("CheckIn:::" + CheckInDate);
			syncWait(2000);

			driver.findElement(By.xpath("//a[contains(@aria-label, '" + CheckInDate + "')]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectCheckoutDate(String month, String date) throws InterruptedException {
		try {
			CheckOutDate = month + " " + date;
			checkOutDate.click();

			System.out.println("CheckOut:::" + CheckOutDate);
			boolean availibilityOfCheckOutDate = verifyElementExistByLocator(
					By.xpath("//a[contains(@aria-label, '" + CheckOutDate + "')]"));
			System.out.println("CheckOutDate element availibility:::" + availibilityOfCheckOutDate);
			if (availibilityOfCheckOutDate) {
				driver.findElement(By.xpath("//a[contains(@aria-label, '" + CheckOutDate + "')]")).click();
			} else {
				System.out.println("checkOutDate Element is not available");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickfindMYHotelBtn() {
		if (homePage.verifyfindMYHotelBtn()) {
			String hotelText = findMYHotelBtn.getText();
			System.out.println("Text on FIND MY HOTEL button is:::" + hotelText);

			findMYHotelBtn.click();

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
		List<WebElement> lst = driver.findElements(By.xpath("//div[@class='aspectMaintainer']"));

		int count = lst.size();
		System.out.println("No of hotel cards available is:::" + count);

		return count;
	}

	public void clickOnChangeSearch() {
		changeSearchBtn.click();
	}

}
