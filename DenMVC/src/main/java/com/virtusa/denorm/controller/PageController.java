package com.virtusa.denorm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	 @RequestMapping(value="/admin", method = RequestMethod.GET)
	    public ModelAndView adminPage() {
	 
	        ModelAndView m = new ModelAndView();
	        m.setViewName("AdminHome");
	 
	        return m;
	    }
	 
	    // Spring security will see this message.
	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView login(@RequestParam(value = "error", required = false) String error, 
	            @RequestParam(value = "logout", required = false) String logout) {
	 
	        ModelAndView m = new ModelAndView();
	        if (error != null) {
	            m.addObject("error", "Invalid username and password error.");
	        }
	 
	        if (logout != null) {
	            m.addObject("msg", "You have left successfully.");
	        }
	 
	        m.setViewName("login");
	        return m;
	    }
	}