package com.bwi.qa.testScripts;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bwi.qa.base.TestBase;
import com.bwi.qa.pages.HomePage;


public class HomePageTest extends TestBase 
{
	HomePage homePage;

	public HomePageTest() 
	{
		super();
	}
	
	@BeforeTest
	public void setUp()
	{
		initialization();
		homePage=new HomePage();
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle()
	{
		String homePageTitle = homePage.verifyHomePageTitle();
		System.out.println("Title is:::::"+ homePageTitle);
		Assert.assertEquals(homePageTitle, "Book Direct at Best Western Hotels & Resorts");
	}
	@Test(priority=2)
	public void checkFindMYHotelBtn(){
		Assert.assertTrue(homePage.verifyfindMYHotelBtn());
	}
}
