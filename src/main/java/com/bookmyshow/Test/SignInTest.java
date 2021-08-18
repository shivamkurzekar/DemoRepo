package com.bookmyshow.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.bookmyshow.PageClasses.HomePage;
import com.bookmyshow.PageClasses.SignInPage;
import com.bookmyshow.baseclass.BaseTest;
import com.bookmyshow.baseclass.PageBase;

public class SignInTest extends BaseTest{
	
	HomePage homePage;
	SignInPage signinPage;
	
	@Test(dataProvider = "EmailData")
	public void singinPageTest(String emailid) {
		logger = report.createTest("SignIn Page Test");
		PageBase pageBase = new PageBase(driver, logger);
		try {
			PageFactory.initElements(driver, pageBase);
			homePage = pageBase.OpenApplication();
			homePage.selectCityAlert();
			signinPage = homePage.navigateToSignIn();
			signinPage.navigatetoSignin();
			signinPage.enterEmail(emailid);
			signinPage.clickNext();
			signinPage.errorMessagecould();
			signinPage.closepopup();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@DataProvider(name = "EmailData")
	public Object[][] emailData() throws IOException {
		Properties prop = new Properties();
		InputStream readFile = null;
		readFile = new FileInputStream("config.properties");
		prop.load(readFile);
		String emailid = (String) prop.get("emailid");
		return new Object[][] {{emailid}};
	}
	
}
