package automationFramework;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import net.sourceforge.marathon.javadriver.JavaDriver;
import net.sourceforge.marathon.javadriver.JavaProfile;
import net.sourceforge.marathon.javadriver.JavaProfile.LaunchMode;

public class SeleniumBrowsers {
	private static final String usrHome = System.getProperty("user.home"); 
	private static String binLocation = "C:/Program Files (x86)/Google/Chrome/Application";
	private static String chromeLocation = "C:/Development/selenium/chromedriver.exe";
	private static String ieLocation = "C:/Development/selenium/IEDriverServer.exe";
	private static String geckoDriverLoc = "C:/Development/selenium/geckodriver.exe";
	private static String ffBinaryLoc = "C:/Program Files (x86)/Mozilla Firefox/firefox.exe";
	private static WebDriver driver;

	/**
	 * Opens the URL in Chrome. Sets the location of the chrome web driver and
	 * the chrome application to the binLocation and chromeLocation for this
	 * object. Sets the implicit timeout to 10 seconds.
	 * 
	 * @param url
	 *            web application resource to get/navigate to
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openChromeBrowser(String url) {
		return openChromeBrowser(url, chromeLocation, binLocation);
	}

	/**
	 * Opens the URL in Chrome. Sets the location of the chrome web driver and
	 * the chrome application to the provided strings location and bin. Sets the
	 * implicit timeout to be 10 seconds. Returns an instance of Se WebDriver.
	 * 
	 * @param url
	 *            web application resource to get/navigate to
	 * @param location
	 *            Chrome web driver location
	 * @param bin
	 *            Chrome application bin, directory containing chrome.exe
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openChromeBrowser(String url, String location, String bin) {
		return openChromeBrowser(url, location, bin, false);
	}

	/**
	 * Opens the URL in Chrome. Sets the location of the chrome web driver and
	 * the chrome application to the provided strings location and bin. Sets the
	 * implicit timeout to be 10 seconds. If this is a browser compatibility
	 * test, then sending bBrowserCompatibilityTest = true will setup the pdfs
	 * to be downloaded instead of opened in a second window. Returns an
	 * instance of Se WebDriver.
	 * 
	 * @param url
	 *            web application resource to get/navigate to
	 * @param location
	 *            Chrome web driver location
	 * @param bin
	 *            Chrome application bin, directory containing chrome.exe
	 * @param bBrowserCompatibilityTest
	 *            boolean, is this a browser compatibility test?
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openChromeBrowser(String url, String location, String bin,
			boolean bBrowserCompatibilityTest) {
		binLocation = bin;
		chromeLocation = location;
		try {
			System.setProperty("webdriver.chrome.driver", location);

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setBinary(binLocation + "/chrome.exe");
			chromeOptions.addArguments("start-maximized");
			chromeOptions.addArguments("no-sandbox");
			chromeOptions.setAcceptInsecureCerts(true);
			if (bBrowserCompatibilityTest) {
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("download.default_directory", Paths.get(usrHome, "Downloads"));
				chromeOptions.setExperimentalOption("prefs", prefs);
			}

			driver = new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(url);

		} catch (Exception e) {
			if (e instanceof TimeoutException) {
				System.out.println(
						"Se.ChromeDriver - TimeoutException for: " + url + "|| Exception msg - " + e.getMessage());
				return getDriver();
			}
			System.out.println("Se.ChromeDriver - exception with chromedriver.exe:  ");
			System.out.println(e.getMessage());
			closeBrowser();
			throw new RuntimeException();
		}
		return getDriver();
	}

	/**
	 * Opens the URL in Internet Explorer (IE). Sets the location of the IE web
	 * driver to the default location, specified as ieLocation. Sets the timeout
	 * to be 10 seconds. Returns an instance of Se WebDriver.
	 * 
	 * @param url
	 *            web application resource to get/navigate to
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openIEBrowser(String url) {
		return openIEBrowser(url, ieLocation);
	}

	/**
	 * Opens the URL in IE. Sets the location of the IE web driver to the
	 * location provided. Also changes the ieLocation for this object. Sets the
	 * timeout to be 10 seconds. Returns an instance of Se WebDriver.
	 * 
	 * @param url
	 *            web application resource to get/navigate to
	 * @param location
	 *            location of the IE web driver
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openIEBrowser(String url, String location) {
		ieLocation = location;
		System.setProperty("webdriver.ie.driver", location);
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		ieOptions.destructivelyEnsureCleanSession();
		ieOptions.ignoreZoomSettings();
		ieOptions.introduceFlakinessByIgnoringSecurityDomains();
		driver = new InternetExplorerDriver(ieOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		return getDriver();
	}

	/**
	 * Opens the URL in Firefox. Sets the location of the gecko web driver to
	 * the default geckoDriverLoc for this object. Sets the implicit timeout to
	 * be 10 seconds. Returns an instance of Se WebDriver.
	 * 
	 * @param url
	 *            page to navigate to
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openFirefoxBrowser(String url) {
		return openFirefoxBrowser(url, geckoDriverLoc);
	}

	/**
	 * Opens the url in firefox. Sets the location of the firefox driver to the
	 * location provided. Also changes the firefoxLocation for this object. Sets
	 * the timeout to be 10 seconds.
	 * 
	 * @param url
	 *            page to navigate to
	 * @param location
	 *            location of the Firefox browser
	 */
	public static WebDriver openFirefoxBrowser(String url, String location) {
		return openFirefoxBrowser(url, location, false);
	}

	/**
	 * Opens the URL in Firefox. Sets the location of the gecko web driver to
	 * the location provided, plus sets the system property of
	 * 'webdriver.gecho.driver' based on the location submitted. The Firefox
	 * installation used is the default system installation, no possibility to
	 * customize the Firefox install used by the gecko web driver. Sets the
	 * profile used by Firefox to be se3-default, a default profile for the DDMI
	 * System Testing team. If this is a browser compatibility test, then
	 * sending bBrowserCompatibilityTest = true will setup the pdfs to be
	 * downloaded instead of opened in a second window. Sets the implicit
	 * timeout to be 10 seconds (removed - page load timeout to be 30 seconds
	 * and script timeout to be 45 seconds) and maximizes the browser window.
	 * Returns an instance of Se WebDriver.
	 * 
	 * @param url
	 *            page to navigate to
	 * @param location
	 *            location of the Gecko Web Driver
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openFirefoxBrowser(String url, String location, boolean bBrowserCompatibilityTest) {
		String sFFMIMETypes = "text/plain, application/pdf, text/csv, application/x-pdf, application/octet-stream,"
				+ "application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel,"
				+ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;";
		try {
			geckoDriverLoc = location;
			System.setProperty("webdriver.gecko.driver", geckoDriverLoc);

			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.setBinary(ffBinaryLoc);
			ffOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			ffOptions.setAcceptInsecureCerts(true);
			ffOptions.addPreference("browser.startup.page", 0);
			// browser.startup.homepage is bypassed if browser.startup.page = 0
			if (bBrowserCompatibilityTest) {
				ffOptions.addPreference("browser.download.folderList", 1);
				ffOptions.addPreference("browser.download.panel.shown", false);
				ffOptions.addPreference("browser.download.useDownloadDir", true);
				ffOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", sFFMIMETypes);
				ffOptions.addPreference("pdfjs.disabled", true);
				ffOptions.addPreference("print.use_native_print_dialog", true);
			}

			ffOptions.addPreference("browser.download.manager.showWhenStarting", false);
			ffOptions.addPreference("browser.download.manager.alertOnEXEOpen", true);
			ffOptions.addPreference("browser.download.manager.closeWhenDone", true);

			driver = new FirefoxDriver(ffOptions);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDriver();
	}

	/**
	 * Opens the Java application in the URL provided, typically a thick
	 * application client, by giving the absolute path to the .bat/.cmd or .exe.
	 * Sets the implicit timeout to be 10 seconds for the WebDriver. Returns an
	 * instance of Se WebDriver.
	 * 
	 * @param url
	 *            location of the java executable
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openJavaBrowser(String url) {
		JavaProfile profile = new JavaProfile(LaunchMode.COMMAND_LINE);
		profile.setCommand(url);
		return openJavaBrowser(profile);
	}

	/**
	 * Opens the Java application with the JavaProfile provided, typically
	 * containing the command set within the profile to be the absolute path to
	 * the .bat/.cmd or .exe desired. Sets teh implicit timeout to be 10 seconds
	 * for the WebDriver. Returns an instance of Se WebDriver.
	 * 
	 * @param profile
	 *            JavaProfile to use for the WebDriver, see Marathon's
	 *            JavaProfile
	 * @return WebDriver an instance of Se WebDriver
	 */
	public static WebDriver openJavaBrowser(JavaProfile profile) {
		try {
			driver = new JavaDriver(profile);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return getDriver();
	}

	/**
	 * Closes the current Se WebDriver instantiated by SeleniumBrowsers object.
	 */
	public static void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		} finally {
			quitDriver();
		}
	}

	/**
	 * Returns the current Se WebDriver
	 * 
	 * @return the current Se WebDriver object instantiated by SeleniumBrowsers
	 *         object
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * Returns the title of the current page referenced by Se WebDriver
	 * 
	 * @return title String representation of the current web page's title
	 */
	public static String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Gets the current URL
	 * 
	 * @return sURL String representation of the URL pulled from the current Se
	 *         WebDriver object
	 */
	public static String getURL() {
		return driver.getCurrentUrl();
	}

	/**
	 * Quits the current Se WebDriver
	 */
	public static void quitDriver() {
		driver.quit();
	}

	public static String getBrowserName() {
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		return caps.getBrowserName();
	}
}
