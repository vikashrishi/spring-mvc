package com.example.springmvc.controller;

import javax.enterprise.inject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.service.UserValidationService;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	UserValidationService service;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String showWelcomePage() {
		
		return "welcome";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String handleLoginRequest(@RequestParam String name, @RequestParam String password, ModelMap model) {
		if(!(service.isUserValid(name, password))) {
			model.put("errorMessage", "Invalid Credentials!!");
			return "login";
		}else {
			
			model.put("name", name);
			model.put("password", password);
			return "welcome";
		}
		
	}
}
