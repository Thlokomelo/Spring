package com.springproject.app.ws.ui.controller;

// This class is responsible for processing incoming REST API requests, preparing a model, and returning the view to be rendered as a response.


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.app.ws.exceptions.UserServiceException;
import com.springproject.app.ws.ui.model.request.UpdateDetailsRequestModel;
import com.springproject.app.ws.ui.model.request.UserDetailsRequestModel;
import com.springproject.app.ws.ui.model.response.UserRest;
import com.springproject.app.ws.userservice.UserService;
import com.springproject.app.ws.userservice.impl.UserServiceImpl;

@RestController                                    //registers class as a Rest Controller
@RequestMapping("/users")                          // http://localhost:8080/users, Responsible for all paths
public class UserController {
	
	Map<String, UserRest> users;                   //property of map data type created, object called users created
	
	@Autowired                 //Creates an instance of the userService implementation and makes it accessible
	UserService userService;

	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue = "desc", required = false) String sort)
	{
		return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}
	 
	@GetMapping(path="/{userId}",                                      //method reads path variable userId
			produces =  {                                              // method produces content in XML and JSon format
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					} )
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		if (users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(                                                       // creates user details
			consumes =  {                                              // method consumes content in XML and JSon format
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  {                                              // method produces content in XML and JSon format
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails){      // reads userDetails, @Valid validates our data
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		//generate random userId using UUID
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
	    if(users == null) users = new LinkedHashMap<>();
		users.put(userId,  returnValue);
		
		UserRest returnValue1 = userService.createUser(userDetails);
		return new ResponseEntity<UserRest>(returnValue1, HttpStatus.OK);
	}
	

	@PutMapping(path="/{userId}",  consumes =  {                       // method consumes content in XML and JSon format
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  {                                              // method produces content in XML and JSon format
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )                                              
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateDetailsRequestModel userDetails ) {  //reads path variable and updates user details
		 
		UserRest storedUserDetails = users.get(userId);                    //creates new object
		 storedUserDetails.setFirstName(userDetails.getFirstName());       //sets new first name
		 storedUserDetails.setLastName(userDetails.getLastName());        //sets new last name
		  
		 users.put(userId, storedUserDetails);                            //stores new details
		 
		 return storedUserDetails;
	}

	@DeleteMapping(path="/{id}")                                        //reads path variable userId
	public ResponseEntity<Void> deleteUser(@PathVariable String id)     //ResponseEntity method accepts path variable to be deleted
	{
		users.remove(id);                                               //user details are deleted from the linkedhashmap using the generated UserId
		
		return ResponseEntity.noContent().build();                      //ResponseEntity returns noContent if users details are removed successfully,
		                                                                //build method is used to build the response
	}

}
