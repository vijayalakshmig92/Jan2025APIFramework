package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties prop = new Properties();
	
	static {
		InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties");
		if(input !=null) {
			try {
				prop.load(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
		public static void set(String key, String value) {
			prop.setProperty(key, value);
		}
		
		public static String getProperty(String key) {
			return prop.getProperty(key);
		}
		
}

