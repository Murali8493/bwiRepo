package com.bwi.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bwi.qa.base.TestBase;

public class HomePage extends TestBase
{

	@FindBy(id = "btn-modify-stay-update")
	WebElement findMYHotelBtn;
	
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	public boolean verifyfindMYHotelBtn(){
		return findMYHotelBtn.isDisplayed();
	}
}