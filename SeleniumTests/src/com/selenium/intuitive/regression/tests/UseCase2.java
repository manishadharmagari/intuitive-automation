package com.selenium.intuitive.regression.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.selenium.common.AbstractE2ETestCase;
import com.selenium.intuitive.common.IntuitiveSearchPageHelper;
import com.selenium.intuitive.pages.IntuitiveSearchPage;
import automationFramework.utils.WebElementUtils;

/**
 * Use case 2: Select specialty “Cardiac Surgery” as the filter and verify the search results pages
 * 
 * @author Manisha Dharmagiri
 *
 */
public class UseCase2 extends AbstractE2ETestCase {
	IntuitiveSearchPage objIntuitiveSearchPage;
	IntuitiveSearchPageHelper objIntuitiveSearchPageHelper;

	@BeforeMethod(groups = { "intutive-regression" })
	public void setUp() throws Exception {
		setUpTestCase("Intuitive"); 
		objIntuitiveSearchPage = PageFactory.initElements(driver, IntuitiveSearchPage.class);
		objIntuitiveSearchPageHelper = new IntuitiveSearchPageHelper(driver);
	}

	@Test(groups = { "intutive-regression" })
	public void UseCase2_SelectSpecialty() throws Exception {
		try {
			objIntuitiveSearchPage.clickSearchIcon();
			objIntuitiveSearchPage.clickCoveoSearchButton();

			objIntuitiveSearchPage.selectSpecialtyCheckBox("Cardiac surgery");
			assertValue("Failing to see selected specialty under results section!!",
					objIntuitiveSearchPage.getSelectedSpecialtyText().equals("Cardiac surgery"));
			assertValue("Failing to see relevant data in search results section bassed on search criteria'!!",
					WebElementUtils
							.validateElementExists(objIntuitiveSearchPage.getRelevantDataBasedOnSearchCriteria()));
		} catch (Exception e) {
			passed = false;
			throw e;
		}
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		tearDown_global();
	}
}