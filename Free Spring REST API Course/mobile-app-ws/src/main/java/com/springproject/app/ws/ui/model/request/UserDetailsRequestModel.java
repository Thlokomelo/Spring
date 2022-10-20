package com.springproject.app.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//class requests and stores user details



public class UserDetailsRequestModel {
	
	@NotNull(message="First name cannot be null")                                   //validation
	@Size(min=2, message = "First name must not be less than 2 characters")         //validation
	private String firstName;
	
	@NotNull(message="Last name cannot be null")                                   //validation
	@Size(min=2, message = "Last name must not be less than 2 characters")         //validation
	private String lastName;
	
	@NotNull(message="Email cannot be null")                                     //validation
	@Email                                                                       //validation
	private String email;
	
	@NotNull(message="Password cannot be null")                                  //validation
	@Size(min=8,max=16, message="Password must be equal or greater than 8 characters and less than 16 chaeracters")   //validation
	private String password;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
