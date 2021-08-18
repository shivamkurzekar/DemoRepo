package com.bookmyshow.Test;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.bookmyshow.PageClasses.HomePage;
import com.bookmyshow.PageClasses.SportsPage;
import com.bookmyshow.baseclass.BaseTest;
import com.bookmyshow.baseclass.PageBase;

public class SportsPageTest extends BaseTest {
	HomePage homePage;
	SportsPage sportsPage;
	
	@Test
	public void sportsPageTest() {
		logger = report.createTest("Sports Page Test");
		PageBase pageBase = new PageBase(driver, logger);
		try {
			PageFactory.initElements(driver, pageBase);
			homePage = pageBase.OpenApplication();
			homePage.selectCityAlert();
			sportsPage = homePage.navigateToSports();
			sportsPage.clickWeekEnd();
			sportsPage.getAllElements();
			sportsPage.sortAccordingToPrice();
			sportsPage.writeExcelData();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
