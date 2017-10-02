package com.slimek.jodi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HelloController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printHello(ModelMap model)
	{
		Logger logger = LoggerFactory.getLogger(HelloController.class);
		logger.info("Hello requested");
		
		Properties prop = System.getProperties();
		logger.info("java.home: " + prop.getProperty("java.home"));
		logger.info("catalina.base: " + prop.getProperty("catalina.base"));
		
		try {
			
			try (Connection conn = JodiApplication.getConnection()) {
				
				Statement stmt = conn.createStatement();
				String sql = "SELECT * FROM worldinfo;";
				ResultSet results = stmt.executeQuery(sql);
				
				while (results.next()) {
					int id = results.getInt("id");
					String name = results.getString("name");
					logger.info("World " + id + " is " + name);
				}
			}
			
		} catch (Exception ex) {
			logger.error("getConnection() failed: " + ex.getMessage());
		}
		
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";
	}
}
