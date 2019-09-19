package com.selenium.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import automationFramework.SeleniumBrowsers;
import automationFramework.utils.AssertionHelper;

public abstract class AbstractSetUpTestCase {
	public static enum Browser {
		IE, FireFox, Chrome, Java
	};

	public StringBuffer verificationErrors = new StringBuffer();
	public boolean passed = false;
	public WebDriver driver;
	protected List<Boolean> checkpoints = new ArrayList<>();
	protected static String environment;
    protected static String sApp;
    
	public String name;

	public void setUpTestCase() {
		this.passed = false;
	}

	@BeforeMethod
	public void setUp_global() {
		System.setProperty("webdriver.ie.driver", "C:/Development/selenium/IEDriverServer.exe");
		//setup environment + sApp to drive reporting to qTestMgr
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown_global(ITestResult result) throws Exception {
		try {
			passed = AssertionHelper.assertValue(this.checkpoints);
			result.isSuccess();
		} finally {
			SeleniumBrowsers.closeBrowser();
		}
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}
}
