package com.springproject.app.ws.userservice;

//Interface creates a stand-alone class that can be tested independantly without 
//interfering with the UserController class and its other methods


import com.springproject.app.ws.ui.model.request.UserDetailsRequestModel;
import com.springproject.app.ws.ui.model.response.UserRest;

public interface UserService {
	UserRest createUser(UserDetailsRequestModel userDetails);
}
