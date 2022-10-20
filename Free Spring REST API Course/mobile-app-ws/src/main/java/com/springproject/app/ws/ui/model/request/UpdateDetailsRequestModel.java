package com.springproject.app.ws.ui.model.request;

//class updates and stores user details

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateDetailsRequestModel {
	@NotNull(message="First name cannot be null")                                   //validation
	@Size(min=2, message = "First name must not be less than 2 characters")         //validation
	private String firstName;
	
	@NotNull(message="Last name cannot be null")                                   //validation
	@Size(min=2, message = "Last name must not be less than 2 characters")         //validation
	private String lastName;

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
}
