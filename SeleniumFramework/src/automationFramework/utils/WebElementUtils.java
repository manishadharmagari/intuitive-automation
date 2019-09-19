package automationFramework.utils;

import org.openqa.selenium.WebElement;

public class WebElementUtils {

	public static boolean validateElementExists(WebElement webElement) {
		boolean elementExists = true;
		try {
			webElement.getSize();
		} catch (Exception e) {
			elementExists = false;
		}
		return elementExists;
	}

	public static void waitForTime(int milliSec) {
		try {
			Thread.sleep(milliSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void setText(WebElement ele, String sText, boolean clearText) {
		if (clearText) {
			ele.clear();
		}
		ele.sendKeys(sText);
	}	
}
