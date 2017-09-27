package com.slimek.jodi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HelloController {

	//private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printHello(ModelMap model)
	{
		Logger logger = LoggerFactory.getLogger(HelloController.class);
		logger.info("Hello requested");
		
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";
	}
}
