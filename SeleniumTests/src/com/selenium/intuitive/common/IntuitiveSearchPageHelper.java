package com.selenium.intuitive.common;

import org.openqa.selenium.WebDriver;
import com.selenium.common.AbstractE2ETestCase;

/*
 * This class is to create helper methods and re-use the code for future test cases
 */
public class IntuitiveSearchPageHelper extends AbstractE2ETestCase {
	
	public IntuitiveSearchPageHelper(WebDriver driver) {
		this.driver = driver;
	}
}
