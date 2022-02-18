package com.example.userlogin.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userlogin.dto.UserDto;
import com.example.userlogin.model.request.UserDetailsRequestModel;
import com.example.userlogin.model.response.UserDetailsResponseModel;
import com.example.userlogin.services.interfaces.IUserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class logincontroller {
	
	@Autowired
	IUserService userService;
	
	
	@GetMapping
	public String getUser() {
		return "Getting a User";
	}
	
	@PutMapping
	public String putUser() {
		return "Update User Called!";
	}
	
	@PostMapping
	public UserDetailsResponseModel postUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {

		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();
		UserDto userDto = new UserDto();
		
		BeanUtils.copyProperties(userDetailsRequestModel, userDto);
		
		UserDto created_user = userService.createUser(userDto);
		BeanUtils.copyProperties(created_user, userResponse);
		return userResponse;
		
		
		
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete User called";
	}

}
