package com.tc.ak.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOperation {
	Properties prop;
	public PropertyFileOperation(String filepath) throws IOException
	{
		File file=new File(filepath);
		FileInputStream input= new FileInputStream(file);
	    prop=new Properties();
		prop.load(input);
		
	}
	
	public String readData(String key) throws FileNotFoundException
	{
		String value=prop.getProperty(key);
		return value;
			
	}

}
