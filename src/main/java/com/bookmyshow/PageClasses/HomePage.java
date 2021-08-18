package com.bookmyshow.PageClasses;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.bookmyshow.baseclass.PageBase;
import com.bookmyshow.PageClasses.SportsPage;

public class HomePage extends PageBase{

	
	public HomePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}
	
	public WebElement selectCity;
	
	@FindBy(xpath = "//a[text()=\"Sports\"]")
	public WebElement sports;
	
	@FindBy(xpath = "//a[text()=\"Movies\"]")
	public WebElement movies;
	
	@FindBy(xpath = "//div[text()=\"Sign in\"]")
	public WebElement signIn;
	
	@FindBy(xpath="//*[text()=\"Continue with Google\"]")
	public WebElement continueWithGoogle;
	
	public void selectCityAlert() throws IOException {
		Properties prop = new Properties();
		InputStream readFile = new FileInputStream("config.properties");
		prop.load(readFile);
		String city = (String) prop.get("city");
		selectCity = driver.findElement(By.xpath("//span[contains(text(),\""+city+"\")]"));
		elementClick(selectCity);
	}
	
	public MoviesPage navigateToMovies() {
		elementClick(movies);
		MoviesPage moviesPage = new MoviesPage(driver,logger);
		PageFactory.initElements(driver, moviesPage);
		logger.log(Status.INFO, "Navigated to Movies Page");
		return moviesPage;
	}

	public SignInPage navigateToSignIn() {
		elementClick(signIn);
		delay(3);
		waitForElement(By.xpath("//*[text()=\"Continue with Google\"]"));
		elementClick(continueWithGoogle);
		SignInPage signinpage = new SignInPage(driver,logger);
		PageFactory.initElements(driver, signinpage);
		logger.log(Status.INFO, "Navigated to SignIn Page");
		return signinpage;
	}

	public SportsPage navigateToSports() {
		elementClick(sports);
		SportsPage sportsPage = new SportsPage(driver, logger);
		PageFactory.initElements(driver, sportsPage);
		logger.log(Status.INFO, "Navigated to Sports Page");
		return sportsPage;
	}

}
