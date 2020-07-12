package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

	public boolean isUserValid(String user, String password) {
		if(user.equals("abcd") && password.equals("1234"))
			return true;
		
		return false;
	}
}
