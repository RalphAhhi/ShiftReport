package com.fv.shiftreport.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SettingUtil {
	
	private static Map<String,String> settings ;
	
	public static void populateProps() throws IOException{
		Properties props = new Properties();
		InputStream inputStream = 
				SettingUtil.class.getClassLoader().getResourceAsStream("properties/settings.properties");
		try{
			settings = new HashMap<String,String>();
			props.load(inputStream);
			for(String name: props.stringPropertyNames()){
				settings.put(name, props.getProperty(name));
			}
			
		}finally{
			if(inputStream!=null){
				inputStream.close();	
			}
		}
	}
	
	public static String getValue(String key){
		return settings.get(key);
	}

}
