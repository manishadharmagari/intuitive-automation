package com.selenium.common;

import java.util.List;
import org.testng.annotations.BeforeMethod;
import automationFramework.SeleniumBrowsers;
import automationFramework.utils.AssertionHelper;

public class AbstractE2ETestCase extends AbstractVPTestCase {
	public static enum Browser {
		IE, FireFox, Chrome, Java
	};

	private static final String chromeBinLocation = "C:/Program Files (x86)/Google/Chrome/Application";
	private static final String chromeLocation = "C:/Development/selenium/chromedriver.exe";
	private static final String ieLocation = "C:/Development/selenium/IEDriverServer.exe";
	private static final String firefoxLocation = "C:/Development/selenium/geckodriver.exe";
	protected static String environment;
	protected static String sApp;
	protected static String sBrowser;

	@BeforeMethod
	public void setUp_global() {

	}

	public List<String> setUpTestCase(String app) {
		return setUpTestCase(app, Browser.Chrome);
	}

	/**
	 * Setup for any test requiring a special login data sheet, like smoke
	 * tests, combining the default set up pieces and returns the userInfo for
	 * logins.
	 * 
	 * @param app
	 *            Application id the test will execute against.
	 * @param browser
	 *            Browser for test execution, i.e., decides which browser\client
	 *            will be open and what values to drive file location for Web
	 *            Driver
	 * @param sLoginDataFile
	 *            Absolute path to login data sheet, containing the URL\path for
	 *            browser
	 * 
	 * @return user information matching the provided criteria
	 */
	public List<String> setUpTestCase(String app, Browser browser) {
		this.passed = false;
		sApp = app;
		environment = new SelProperties(app).getMyEnv();
		List<String> userInfo = null;
		try {
			userInfo = GetLoginData.loginData(environment, app);
			if (userInfo.get(0) == null) {
				userInfo.set(0, "");
			}
		} catch (java.lang.IndexOutOfBoundsException e) {
		} catch (java.io.FileNotFoundException e) {
			System.out.println("Error reading the test case file. Will continue with a null Test Case object.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (browser.equals(Browser.FireFox)) {
			sBrowser = "FireFox";
			this.driver = SeleniumBrowsers.openFirefoxBrowser(userInfo.get(0), firefoxLocation);
		} else if (browser.equals(Browser.IE)) {
			sBrowser = "IE";
			this.driver = SeleniumBrowsers.openIEBrowser(userInfo.get(0), ieLocation);
		} else if (browser.equals(Browser.Chrome)) {
			sBrowser = "Chrome";
			this.driver = SeleniumBrowsers.openChromeBrowser(userInfo.get(0), chromeLocation, chromeBinLocation);
		}
		return userInfo;
	}

	public static boolean assertValue(String sMsg, boolean bValidationStatus) {
		return AssertionHelper.assertValue(sMsg, bValidationStatus);
	}
}
