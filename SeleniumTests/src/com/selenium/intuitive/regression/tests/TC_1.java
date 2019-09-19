package com.selenium.intuitive.regression.tests;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.selenium.common.AbstractE2ETestCase;
import com.selenium.intuitive.common.IntuitiveSearchPageHelper;
import com.selenium.intuitive.pages.IntuitiveSearchPage;
import automationFramework.Helper;
import automationFramework.Randoms;
import automationFramework.utils.WebElementUtils;

/**
 * TC-1 Enter a search keyword, when results are displayed
 * 
 * @author Manisha Dharmagiri
 *
 */
public class TC_1 extends AbstractE2ETestCase {
	IntuitiveSearchPage objIntuitiveSearchPage;
	IntuitiveSearchPageHelper objIntuitiveSearchPageHelper;

	@BeforeMethod(groups = { "intutive-regression" })
	public void setUp() throws Exception {
		setUpTestCase("Intuitive"); // Read the URL from excel sheet, 
		// Based on the requirement pass the browser name (Chrome/Firefox/IE) like below, default browser is chrome
		//setUpTestCase("Intuitive", Browser.Chrome);
		objIntuitiveSearchPage = PageFactory.initElements(driver, IntuitiveSearchPage.class);
		objIntuitiveSearchPageHelper = new IntuitiveSearchPageHelper(driver);
	}

	@Test(groups = { "intutive-regression" })
	public void testTC1() throws Exception {
		try {
			/*
			 * UseCase 1:
			 */
			objIntuitiveSearchPage.clickSearchIcon();
			objIntuitiveSearchPage.setSearchComboBox("Products");
			objIntuitiveSearchPage.clickCoveoSearchButton();
			Helper.wait(objIntuitiveSearchPage.getSearchResults(), 5, driver);
			assertValue("Failing to see relevant data in search results section bassed on search criteria'!!",
					WebElementUtils
							.validateElementExists(objIntuitiveSearchPage.getRelevantDataBasedOnSearchCriteria()));
			
			List<String> paginationLinks = objIntuitiveSearchPage.getPaginationLinks();
			for (int i = 0; i < paginationLinks.size(); i++) {
				assertValue("Missing pagination on the page!!" + i,
						objIntuitiveSearchPage.getPaginationList().get(i).getText().equals(paginationLinks.get(i)));
			}
			
			objIntuitiveSearchPage.setSearchComboBox(Randoms.randomName());
			objIntuitiveSearchPage.clickCoveoSearchButton();
			assertValue("Failing to see expected behavior on the page, expected no results for provided search'!!",
					WebElementUtils.validateElementExists(objIntuitiveSearchPage.getNoResultsOnInvalidSearch()));
			objIntuitiveSearchPage.clickCloseIcon();
			objIntuitiveSearchPage.clickCoveoSearchButton();
				
			// Note: If I provide invalid data, no error message is being displayed for user. (TBD with developers - what is the expected behavior?), log bug if required 
			
			/*
			 * UseCase 2
			 */
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
}