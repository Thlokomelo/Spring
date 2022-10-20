package com.springproject.app.ws.userservice.impl;

//class implements UserService Interface

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.app.ws.shared.Utils;
import com.springproject.app.ws.ui.model.request.UserDetailsRequestModel;
import com.springproject.app.ws.ui.model.response.UserRest;
import com.springproject.app.ws.userservice.UserService;

@Service // makes class accessible to other classes
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;

	Utils utils;

	public UserServiceImpl() {
	}

	@Autowired                            //Creates an instance of the userService implementation and makes it accessible
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());

		//generates userId 
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);

		if (users == null)
			users = new LinkedHashMap<>();
		users.put(userId, returnValue);

		return returnValue;

	}

}
