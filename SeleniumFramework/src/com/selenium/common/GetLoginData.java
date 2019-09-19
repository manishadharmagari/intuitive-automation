package com.selenium.common;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import automationFramework.ApplicationConstants;
import jxl.Sheet;
import jxl.Workbook;

public class GetLoginData {

	/**
	 * This returns the userInfo (URL) for the tests
	 * 
	 * @param sEnv
	 *            string representing the environment under test
	 * @param sApp
	 *            application to retrieve login data for
	 * 
	 * @return List<String> URL provided criteria,
	 */
	public static List<String> loginData(String sEnv, String sApp) throws Exception {
		List<String> loginInfo = new ArrayList<String>();
		FileInputStream fi = new FileInputStream(
				Paths.get(ApplicationConstants.BASE_WORKSPACE_LOC, "resources", "Logins.xls").toString());
		Workbook w = Workbook.getWorkbook(fi);
		Sheet s = w.getSheet(0);
		for (int row = 1; row < s.getRows(); row++) {
			if (s.getCell(0, row).getContents().equals(sApp) && !s.getCell(0, row).getContents().contains(".com")
					&& s.getCell(1, row).getContents().equals(sEnv.toLowerCase())) {
				loginInfo.add(0, s.getCell(2, row).getContents());
				loginInfo.add(1, s.getCell(3, row).getContents());
				break;
			}
		}
		return loginInfo;
	}
}
