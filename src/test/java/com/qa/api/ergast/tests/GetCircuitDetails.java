package com.qa.api.ergast.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.testng.annotations.Test;

import com.qa.api.utils.XmlPathUtil;

public class GetCircuitDetails {
	
	@Test
	public void getCircuitDetailsTest() {
		String xmlPath = "C:\\Users\\vijil\\eclipse-workspace\\Apr2026APIFW\\src\\test\\resources\\xmls\\circuits.xml";
		try {
			String xml = Files.readString(Paths.get(xmlPath));
			List<String> circuitId= XmlPathUtil.readList(xml, "MRData.CircuitTable.Circuit.@circuitId");
			System.out.println("CircuitId is " + circuitId);
			String latitude = XmlPathUtil.read(xml, "**.find{it.@circuitId == 'albert_park'}.Location.@lat");
			System.out.println("Latitude is " +latitude);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
