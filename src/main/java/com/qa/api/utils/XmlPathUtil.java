package com.qa.api.utils;

import java.util.List;
import io.restassured.path.xml.XmlPath;

public class XmlPathUtil {
	
	public static <T> T read(String responseString, String xmlPathExpression) {
		
		XmlPath xmlPath = new XmlPath(responseString);
		return xmlPath.get(xmlPathExpression);
	}
	
	public static <T> List<T> readList(String responseString, String xmlPathExpression) {
		XmlPath xmlPath = new XmlPath(responseString);
		return xmlPath.getList(xmlPathExpression);
	}
 
}
