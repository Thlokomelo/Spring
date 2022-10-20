package com.springproject.app.ws.shared;

import java.util.UUID;

import org.springframework.stereotype.Service;

//class generates random UserId using UUID

@Service                                           // makes class accessible to other classes
public class Utils {
	public String generateUserId() {              //method 
		return UUID.randomUUID().toString();     //generates userId
	}
}