package automationFramework.utils;

import java.util.List;

import org.testng.Assert;

public class AssertionHelper {

	/**
	 * A wrapper for calling org.testng.Assert.assertTrue(bStatus, sMsg),
	 * returns the same boolean bStatus
	 * 
	 * @param sMsg
	 *            message to display if the assert fails
	 * @param bStatus
	 *            value of the boolean being tested
	 * @return boolean return boolean (true||false) based on
	 *         assertTrue(sMsg,bValue)
	 * 
	 */
	public static boolean assertValue(String sMsg, boolean bStatus) {
		Assert.assertTrue(bStatus, sMsg);
		return bStatus;
	}

	/**
	 * Checks all of the primitive boolean values in the List and returns a
	 * false if any of them are false. Replaced assertValue() from
	 * AbstractVPTestCase during Selenium upgrade.
	 * 
	 * @param value
	 *            List of booleans
	 * @return boolean true if all the values are true, false if any one value
	 *         is false.
	 */
	public static boolean assertValue(List<Boolean> value) {
		return !value.contains(false);
	}
}
