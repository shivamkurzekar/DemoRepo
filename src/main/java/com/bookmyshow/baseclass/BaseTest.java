package com.bookmyshow.baseclass;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.bookmyshow.utilities.ExtentReportManager;

public class BaseTest {

	public WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	
//	public void invokeBrowser(String browserName) {
//		if (browserName.equalsIgnoreCase("Chrome")) {
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--disable-notifications");
//			options.addArguments(Arrays.asList("--no-sandbox", "--ignore-certificate-errors",
//					"--homepage=about:blank", "--no-first-run"));
//			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//			driver = new ChromeDriver(options);
//		}
//		else if (browserName.equalsIgnoreCase("Mozilla")) {
//			System.setProperty("webdriver.gecko.driver",
//					System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
//			FirefoxOptions options = new FirefoxOptions();
//			options.setProfile(new FirefoxProfile());
//			options.addPreference("dom.webnotifications.enabled", false);
//			driver = new FirefoxDriver(options);
//		}
//		
//		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//	}
	
	public void invokeBrowser(String browserName) throws Exception {
		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments(Arrays.asList("--no-sandbox", "--ignore-certificate-errors",
					"--homepage=about:blank", "--no-first-run"));
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			String hubURL = "http://192.168.1.5:4444/wd/hub";
			driver = new RemoteWebDriver(new URL(hubURL), options);
		}
		else if (browserName.equalsIgnoreCase("Mozilla")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
		}
		
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeClass
	public void openBrowser() throws IOException {
		Properties prop = new Properties();
		InputStream readFile = null;
		readFile = new FileInputStream("config.properties");
		prop.load(readFile);
		String browser = (String) prop.get("Browser");
		try {
			invokeBrowser(browser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void closeAll() {
		report.flush();
		logger.log(Status.INFO, "Quiting the Browser");
		driver.quit();
		System.out.println("--END--");
	}
	
	public void delay(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
