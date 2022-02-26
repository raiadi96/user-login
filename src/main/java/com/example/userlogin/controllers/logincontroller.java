package com.example.userlogin.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userlogin.dto.UserDto;
import com.example.userlogin.exceptions.UserServiceException;
import com.example.userlogin.model.request.OperationStatusEnum;
import com.example.userlogin.model.request.UserDetailsRequestModel;
import com.example.userlogin.model.response.ExceptionResponseEnum;
import com.example.userlogin.model.response.OperationStatusResponseModel;
import com.example.userlogin.model.response.UserDetailsResponseModel;
import com.example.userlogin.services.interfaces.IUserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class logincontroller {
	
	@Autowired
	IUserService userService;
	
	
	@GetMapping(path ="/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserDetailsResponseModel getUser(@PathVariable String userId) {
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();

		UserDto user_detail = userService.getUserById(userId);
		BeanUtils.copyProperties(user_detail, userResponse);
		return userResponse;
	}
	
	@PutMapping(path ="/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserDetailsResponseModel putUser(@PathVariable String userId, @RequestBody UserDetailsRequestModel userDetails) {
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		UserDto user_detail = userService.updateUser(userId, userDto);
		BeanUtils.copyProperties(user_detail, userResponse);
		return userResponse;
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserDetailsResponseModel postUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
		
		if(userDetailsRequestModel.getFirst_name() == null) throw new UserServiceException(ExceptionResponseEnum.MISSING_REQUIRED_FIELD.getMessage());
		
		UserDetailsResponseModel userResponse = new UserDetailsResponseModel();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetailsRequestModel, userDto);
		UserDto created_user = userService.createUser(userDto);
		BeanUtils.copyProperties(created_user, userResponse);
		return userResponse;
		
		
		
	}
	
	@DeleteMapping(path ="/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public OperationStatusResponseModel deleteUser(@PathVariable String userId) {
		OperationStatusResponseModel operationModel = new OperationStatusResponseModel();
		operationModel.setOperationName(OperationStatusEnum.DELETE.name());
		userService.deleteUser(userId);
		operationModel.setOperationStatus(OperationStatusEnum.SUCCESS.name());
		return operationModel;
	}
}
