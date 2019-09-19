package com.selenium.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import automationFramework.ApplicationConstants;

public class SelProperties {	
	private PropertyResourceBundle props;
	
	/** Pass in the name of the application/folder you want to use configuration from.
	 *  Will load the "config.properties" file, if the file exists, defaults to config-uat.properties.
	 *  Uses java.nio.file.Path/Paths to build in platform independence. 
	 *  
	 *  @param baseConfigDirName   
	 *      A string representing the full file path, like "Intuitive"
	 **/
	public SelProperties(String baseConfigDirName) {
		FileInputStream fis = null;
		Path sePropPath = Paths.get(ApplicationConstants.BASE_WORKSPACE_LOC,"config",baseConfigDirName,"config.properties");
		Path defaultSePropPath = Paths.get(ApplicationConstants.BASE_WORKSPACE_LOC,"config",baseConfigDirName,"config-uat.properties");
	
		try {
			System.out.println("The 'BASE_WORKSPACE_LOC' ("+ApplicationConstants.BASE_WORKSPACE_LOC
					+") for project based on the directory where java is started from.");
			boolean bFileExists = Files.exists(sePropPath);
			if (bFileExists) {
				fis = new FileInputStream(sePropPath.toString());
			} else {
				System.out.println("Did not find config.properties file for "+baseConfigDirName
						+"!   Trying to default to config-uat.properties");
				fis = new FileInputStream(defaultSePropPath.toString());
			}
			props = new PropertyResourceBundle(fis);		
		} catch (FileNotFoundException fnfe) {
			System.out.println("Application ("+baseConfigDirName+") config file not found: "+fnfe.getMessage());
			fnfe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error loading properties file: " + e.getMessage());
			e.printStackTrace();
		} 
		
		try {
			fis.close();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public String getProperty(String key) {
		return props.getString(key);
	}
	
	public String getMyURL() {
		return props.getString("ServerToUse");
	}
	
	public String getMyEnv() {
		return props.getString("Env");
	}
}