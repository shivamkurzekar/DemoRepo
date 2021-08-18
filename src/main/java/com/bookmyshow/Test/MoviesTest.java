package com.bookmyshow.Test;

import org.testng.annotations.Test;

import com.bookmyshow.PageClasses.HomePage;
import com.bookmyshow.PageClasses.MoviesPage;
import com.bookmyshow.baseclass.BaseTest;
import com.bookmyshow.baseclass.PageBase;

public class MoviesTest extends BaseTest{
	
	HomePage homePage;
	MoviesPage moviesPage;
	
	@Test
	public void moviesPageTest() throws Exception {
		logger = report.createTest("Movies Page Test");
		PageBase pageBase = new PageBase(driver, logger);
		
		homePage = pageBase.OpenApplication();
		homePage.selectCityAlert();
		moviesPage = homePage.navigateToMovies();
		moviesPage.clickOnCommingSoon();
		moviesPage.getLanguages();
		System.out.println("");
	}
}
