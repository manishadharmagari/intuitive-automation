# intuitive-automation
intuitive-automation


# CONFIGS
Environment related configuration changes at <b>\SeleniumTests\config\Intuitive\config-uat.properties</b></br>
TestCase related configuration changes <b>\SeleniumTests\resources\Logins.xls</b></br>
TestCase result output location <b>\SeleniumTests\test-output</b></br>
TestCase source code location <b>\SeleniumTests\src</b></br>
TestCase Framework code location <b>\SeleniumFramework\src</b></br>

# Selenium Framework
automationFramework
In this package, application constants, randoms, helper,
In selenium browsers,
Opens the URL in specified browser(chrome). Sets the location of the browser(chrome) web driver and
the browser(chrome) application to the binLocation and chromeLocation for this.
LocationStrategy class -> we can access "By" elements from this class

automationFramework.utils
In this package, mainly focussed on creating utils for webelements, dropdowns, check boxes, radio buttons, file utils, browser window utils 
WebElementUtils -> added waits, and validations for element presence and set text to input data in fields
RandomUtils -> Written code to generate random name  

com.selenium.common
AbstractE2ETestCase -> created Setup for any test requiring a special login data sheet, tests, combining the default set up pieces and returns the userInfo for logins.
Created values to drive file location for Web Driver
Fetch excel sheet data information - URL
tearDown_global() -> added checkpoints and quit driver
assertValue -> A wrapper for calling org.testng.Assert.assertTrue(bStatus, sMsg) returns the boolean bStatus
GetLoginData -> 	This returns the userInfo (URL) for the tests URL provided here read data from excel sheet
SelProperties -> Pass in the name of the application/folder you want to use configuration from.

# Selenium Tests
com.selenium.intuitive.common -> Helper class to write consolidated methods in future test cases
com.selenium.intuitive.pages -> page objects are created in this package
@Getter -> Lombok jar is added to call getters
com.selenium.intuitive.regression.tests -> UseCases are created in this package

# TEST CASE - USECASES CODE 
TestCase scenario code class -> <b>\SeleniumTests\src\com\selenium\intuitive\regression\tests\UseCase2.java</b></br>
TestCase scenario code class -> <b>\SeleniumTests\src\com\selenium\intuitive\regression\tests\UseCase2.java</b></br>

intuitive-regression-testng.xml
TestSuite-> added groups to execute all the tests in the suite (group name->intutive-regression)

# Test Execution video has been committed 
