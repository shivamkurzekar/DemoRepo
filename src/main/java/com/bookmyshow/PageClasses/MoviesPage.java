package com.bookmyshow.PageClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.bookmyshow.baseclass.PageBase;
import com.bookmyshow.utilities.WriteExcelData;

public class MoviesPage extends PageBase{
	
	@FindBy(xpath = "//div[@class=\"style__WidgetContainerBody-sc-1ljcxl3-0 jImLDr\"]")
	public WebElement commingSoon;
	
	@FindBy(xpath = "//div[@class=\"style__StyledText-sc-7o7nez-0 boewjJ\"]")
	public List<WebElement> languages;
	
	public MoviesPage(WebDriver driver, ExtentTest logger) {
		// TODO Auto-generated constructor stub
		super(driver, logger);
	}

	public void clickOnCommingSoon() {
		elementClick(commingSoon);		
	}

	public void getLanguages() throws Exception {
		System.out.println("Retriving languages");
		List<String> retrivedLanguages = new ArrayList<String>();
		for(WebElement language:languages) {
			retrivedLanguages.add(language.getText());
		}
		WriteExcelData writeExcelData = new WriteExcelData();
		writeExcelData.writeListData("Movie Languages", "List", retrivedLanguages);
		
	}
	
}
