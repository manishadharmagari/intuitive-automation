package com.selenium.intuitive.pages;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import automationFramework.LocationStrategy;
import automationFramework.SeleniumBrowsers;
import automationFramework.utils.WebElementUtils;
import lombok.Getter;

/*
 * Page Objects
 */
@Getter
public class IntuitiveSearchPage {
	/*
	 * ----------------------------------------------------------------------
	 * |------------------------------ Links -----------------------------|
	 * ----------------------------------------------------------------------
	 */
	@FindBy(xpath = "//span[contains(text(),'Search')]/following::li/a[@class='searchIcon']")
	private WebElement searchIcon;

	@FindBy(xpath = "//div[@class='magic-box-icon']")
	private WebElement closeIcon;

	/*
	 * ----------------------------------------------------------------------
	 * |------------------------------ Text Fields-------------------------|
	 * ----------------------------------------------------------------------
	 */
	@FindBy(xpath = "//input[@title='Insert a query. Press enter to send']")
	private WebElement searchComboBox;

	/*
	 * ----------------------------------------------------------------------
	 * |--------------------------Label Text Fields------------------------|
	 * ----------------------------------------------------------------------
	 */
	@FindBy(xpath = "//span[contains(text(),'Specialty:')]/following::span[1]")
	private WebElement specialityLabelText;

	@FindBy(xpath = "//li[contains(@class,'coveo-pager-list')]")
	public List<WebElement> paginationList;

	/*
	 * ----------------------------------------------------------------------
	 * |-----------------------------Buttons-------------------------------|
	 * ----------------------------------------------------------------------
	 */
	@FindBy(xpath = "//a[@class='CoveoSearchButton coveo-accessible-button']")
	private WebElement coveoSearchButton;

	/*
	 * ----------------------------------------------------------------------
	 * |---------------------------- String Fields-------------------------|
	 * ----------------------------------------------------------------------
	 */
	public List<String> paginationLinks = Arrays.asList("1", "2", "3", "4", "5", "6");

	/*
	 * ----------------------------------------------------------------------
	 * |-------------------------- Search Results Section-----------------|
	 * ----------------------------------------------------------------------
	 */
	@FindBy(xpath = "//span[contains(text(),'Relevancy')]/following::div[@class='coveo-result-cell']")
	private WebElement relevantDataBasedOnSearchCriteria;

	// negative and positive scenario

	@FindBy(xpath = "//div[contains(text(),'No results')]")
	private WebElement noResultsOnInvalidSearch;

	/*
	 * ----------------------------------------------------------------------
	 * |----------------------------Check Boxes-----------------------------|
	 * ----------------------------------------------------------------------
	 */
	@FindBy(xpath = "//div[contains(text(),'No results')]")
	private WebElement specialityCheckBox;

	/*
	 * Set Methods
	 */
	public void setSearchComboBox(String searchText) {
		WebElementUtils.setText(searchComboBox, searchText, true);
	}

	/*
	 * Select Methods
	 */
	public void selectSpecialtyCheckBox(String speciality) {
		SeleniumBrowsers.getDriver().findElement(By.xpath("//span[contains(text(),'" + speciality
				+ "')]/preceding-sibling::div[contains(@class,'coveo-facet-value-checkbox')]")).click();
	}

	/*
	 * Get Methods
	 */
	public String getSelectedSpecialtyText() {
		return this.specialityLabelText.getText();
	}
	
	public By getSearchResults() {
		return LocationStrategy.XPATH
				.getBy("//span[contains(text(),'Relevancy')]/following::div[@class='coveo-result-cell']");
	}

	/*
	 * Click Methods
	 */
	public void clickSearchIcon() {
		this.searchIcon.click();
	}

	public void clickCloseIcon() {
		this.closeIcon.click();
	}

	public void clickCoveoSearchButton() {
		this.coveoSearchButton.click();
	}
}
