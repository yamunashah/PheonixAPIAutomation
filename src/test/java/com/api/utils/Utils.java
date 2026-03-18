package com.api.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	
	private static Properties prop =  new Properties();
	private static String filePath = "constants/config.properties";
	private static String env;
	//private static FileReader configFileReader;
	
	private Utils()
	{
		
	}
	
	static
	{

		env = System.getProperty("env","qa");
		env = env.toLowerCase().trim();
		switch(env)
		{
		case "dev" -> filePath = "constants/config.dev.properties";
		case "qa" -> filePath = "constants/config.qa.properties";
		case "uat" -> filePath = "constants/config.uat.properties";
		default -> filePath = "constants/config.qa.properties";
		}
		
		//File configFile = new File(System.getProperty("user.dir") + File.separator+ "src" + File.separator + x"test" + File.separator + "resources" + File.separator + "constants" + File.separator + "config.properties");
		
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		
		if(input == null)
		{
			throw new RuntimeException("The file is not present at the path "+filePath);
		}
		try {
			//configFileReader = new FileReader(configFile);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key)
	{
			
			return prop.getProperty(key);
	}
	
}
