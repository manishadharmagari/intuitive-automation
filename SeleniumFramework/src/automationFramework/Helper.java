package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

	/**
	 * This is the public function that dynamically waits until a web element
	 * present on the page
	 * 
	 * @param element
	 *            pass the By element that needs to be identified
	 * @param driver
	 *            WebDriver to pass
	 * @param timeInSeconds
	 *            - pass the time in seconds that you want to wait for
	 */
	public static void wait(By element, int timeInSeconds, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (Exception e) {
			throw e;
		}
	}
}