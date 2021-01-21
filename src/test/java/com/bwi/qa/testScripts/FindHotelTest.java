package com.bwi.qa.testScripts;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bwi.qa.base.TestBase;
import com.bwi.qa.pages.FindHotelPage;
import com.bwi.qa.pages.HomePage;
import com.bwi.qa.util.ExcelUtil;

public class FindHotelTest extends TestBase {
	FindHotelPage findHotelPage;
	HomePage homePage = new HomePage();

	public FindHotelTest() {
		super();
	}

	@BeforeTest
	public void setUp() {
		initialization();
		findHotelPage = new FindHotelPage();
	}
	/**
	 * Use: To search hotels on the application
	 * @author Murali krishna Sara
	 * @param  Destination
	 * @param  CheckInMonth
	 * @param  CheckInDate
	 * @param  CheckOutMonth
	 * @param  CheckOutDate
	 * @return none
	 */
	//As of now I have used System.out.println() for my verification,but instead we have to use logs
	@Test(description = "Find My Hotel. . .", priority = 1, dataProviderClass = ExcelUtil.class, dataProvider = "dp")
	public void findHotel(String Destination, String CheckInMonth, String CheckInDate, String CheckOutMonth,
			String CheckOutDate) throws Exception {
		findHotelPage.enterDestination(Destination);
		findHotelPage.selectChechInDate(CheckInMonth, CheckInDate);
		findHotelPage.selectCheckOutDate(CheckOutMonth, CheckOutDate);
		findHotelPage.clickOnFindMyHotelButton();

		// Confirming Destination
		Assert.assertEquals(Destination, findHotelPage.verifyDestination());

		// Confirming CheckIn Date
		String checkIn = findHotelPage.verifyCheckInDate();
		String checkInConvertedMonth = convertMonth(CheckInMonth);
		String checkInMonthAndDate = CheckInDate.concat(checkInConvertedMonth);
		System.out.println("CheckIn Con Month:::::" + checkInMonthAndDate);
		Assert.assertEquals(checkIn, checkInMonthAndDate);

		// Confirming CheckOut Date
		String checkOut = findHotelPage.verifyCheckOutDate();
		String checkOutConvertedMonth = convertMonth(CheckOutMonth);
		String checkOutMonthAndDate = CheckOutDate.concat(checkOutConvertedMonth);
		System.out.println("CheckOut Con Month:::::" + checkOutMonthAndDate);
		Assert.assertEquals(checkOut, checkOutMonthAndDate);

		// Confirming availability of Hotel cards
		int hotelCardCount = findHotelPage.checkAvailabilityOfHotelCard();
		if (hotelCardCount > 0) {
			System.out.println("Hotel cards are displayed");
		} else {
			System.out.println("No Hotel cards is displayed");
		}
		findHotelPage.clickOnChangeSearch();
		findHotelPage.pressPageDownKey();
	}

	@AfterTest
	public void close()
	{ 
		driver.quit(); 
	}


}
