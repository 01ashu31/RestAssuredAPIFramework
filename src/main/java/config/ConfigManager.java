package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties properties;
	
	static {
		try {	
	FileInputStream fis= new FileInputStream("src/test/java/resources/config.properties");
	
	properties= new Properties();
	properties.load(fis);
		}catch(Exception e) {
			throw new RuntimeException("Failed to load config file");
		}
	}
	
	public static String get(String str) {
		return properties.getProperty(str);
	}
	

}
